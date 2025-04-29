package com.titanrig.titanrig.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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
public class UserDTO implements Serializable{
    private static final long serialVersionUID = 1L;

    private Long id;

    @NotBlank(message = "Campo obrigatório")
    private String name;

    @NotBlank(message = "Campo obrigatório")
    private String phone;

    @Email(message = "Favor entrar com email válido")
    private String email;
    private String cpf;

    Set<RoleDTO> roles = new HashSet<>();

    public UserDTO(User entity){
        id = entity.getId();
        name = entity.getName();
        email = entity.getEmail();
        cpf = entity.getCpf();
        entity.getRoles().forEach(role -> this.roles.add(new RoleDTO(role)));
    }
}
