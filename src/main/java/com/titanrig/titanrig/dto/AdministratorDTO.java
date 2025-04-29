package com.titanrig.titanrig.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.titanrig.titanrig.entities.Administrator;

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
public class AdministratorDTO implements Serializable{
    private static final long serialVersionUID = 1L;

    private Long id;

    @NotBlank(message = "Campo obrigatório")
    private String name;

    @NotBlank(message = "Campo obrigatório")
    private String phone;

    @Email(message = "Favor entrar com email válido")
    private String email;

    Set<RoleDTO> roles = new HashSet<>();

    public AdministratorDTO(Administrator entity){
        id = entity.getId();
        name = entity.getName();
        email = entity.getEmail();
        entity.getRoles().forEach(role -> this.roles.add(new RoleDTO(role)));
    }
}
