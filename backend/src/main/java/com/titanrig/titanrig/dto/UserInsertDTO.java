package com.titanrig.titanrig.dto;

import java.util.HashSet;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInsertDTO extends UserDTO{

    @NotBlank(message = "Campo obrigatório")
    private String password;

    public UserInsertDTO() {
        super();
    }

    public UserInsertDTO(String name, String phone, String email, String password) {
        super(null, name, phone, email, null, null, new HashSet<>()); 
        this.password = password;
    }
}
