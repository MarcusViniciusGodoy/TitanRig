package com.titanrig.titanrig.services;

import java.util.List;
import java.util.Optional;

import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.titanrig.titanrig.dto.UserDTO;
import com.titanrig.titanrig.dto.UserInsertDTO;
import com.titanrig.titanrig.dto.UserUpdateDTO;
import com.titanrig.titanrig.entities.Role;
import com.titanrig.titanrig.entities.User;
import com.titanrig.titanrig.exceptions.ResourceNotFoundException;
import com.titanrig.titanrig.projections.UserDetailsProjection;
import com.titanrig.titanrig.repositories.RoleRepository;
import com.titanrig.titanrig.repositories.UserRepository;
import com.titanrig.titanrig.services.exceptions.DatabaseException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService implements UserDetailsService{

    @Autowired
    private UserRepository repository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    @Lazy
    private AuthService authService;

    @Transactional(readOnly = true)
    public Page<UserDTO> findAllPaged(Pageable pageable){
        Page<User> list = repository.findAll(pageable);
        return list.map(x -> new UserDTO(x));
    }

    @Transactional(readOnly = true)
    public UserDTO findById(Long id) {
        Optional<User> obj = repository.findById(id);
        User entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new UserDTO(entity);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        List<UserDetailsProjection> result = repository.searchUserAndRolesByEmail(username);
        if (result.size() == 0){
            throw new UsernameNotFoundException("User not found");
        }
        User user = new User();
        user.setEmail(result.get(0).getUsername());
        user.setPassword(result.get(0).getPassword());

        for (UserDetailsProjection projection : result){
            user.addRole(new Role(projection.getRoleId(), projection.getAuthority()));
        }
        return user;
    }

    protected User authenticated(){
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Jwt jwtPrincipal = (Jwt) authentication.getPrincipal();
            String username = jwtPrincipal.getClaim("username");
            return repository.findByEmail(username);
        } catch (Exception e) {
            throw new UsernameNotFoundException("User not found");
        }  
    }

    @Transactional(readOnly = true)
    public UserDTO findMe() {
        User entity = authService.authenticated();
        return new UserDTO(entity);
    }

    @Transactional
    public UserDTO insert(UserInsertDTO dto) {
       User entity = new User();
       copyDtoToEntity(dto, entity);
       entity.setPassword(dto.getPassword());
       entity = repository.save(entity);
       return new UserDTO(entity);
    }

    @Transactional
    public UserDTO update(Long id, UserUpdateDTO dto) {
        try {
            User entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new UserDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
	    if (!repository.existsById(id)) {
		    throw new ResourceNotFoundException("Resource not found");
	    }
	    try {
        	repository.deleteById(id);    		
	    }
    	catch (DataIntegrityViolationException e) {
        	throw new DatabaseException("Integrity violation");
   	    }
    }

    private void copyDtoToEntity(UserDTO dto, User entity) {
    entity.setName(dto.getName());
    entity.setPhone(dto.getPhone());
    entity.setEmail(dto.getEmail());
    entity.setCpf(dto.getCpf());
    entity.setBirthDate(dto.getBirthDate());

    entity.getRoles().clear();
    for (String roleName : dto.getRoles()) {
        Role role = roleRepository.findByAuthority(roleName)
            .orElseThrow(() -> new ResourceNotFoundException("Role not found: " + roleName));
        entity.addRole(role);
    }
}
}
