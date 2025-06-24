package com.titanrig.titanrig.dto;

import java.io.Serializable;
import java.time.LocalDate;
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

    private Long id;

    @NotBlank(message = "Campo obrigatório")
    private String name;

    @NotBlank(message = "Campo obrigatório")
    private String phone;

    @Email(message = "Favor entrar com email válido")
    private String email;
    private String cpf;
    private LocalDate birthDate;

    Set<RoleDTO> roles = new HashSet<>();

    public UserDTO(User entity){
        id = entity.getId();
        name = entity.getName();
        phone = entity.getPhone();
        email = entity.getEmail();
        cpf = entity.getCpf();
        birthDate = entity.getBirthDate();
        entity.getRoles().forEach(role -> this.roles.add(new RoleDTO(role)));
    }
}
