package com.titanrig.titanrig.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import com.titanrig.titanrig.entities.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;

    @NotBlank(message = "Campo obrigatório")
    private String name;

    @NotBlank(message = "Campo obrigatório")
    private String phone;

    @Email(message = "Favor entrar com email válido")
    private String email;
    private String cpf;

    private List<String> roles = new ArrayList<>();

    public UserDTO(User entity){
        id = entity.getId();
        name = entity.getName();
        email = entity.getEmail();
        cpf = entity.getCpf();
        for (GrantedAuthority role : entity.getRoles()){
            roles.add(role.getAuthority());
        }
    }
}
