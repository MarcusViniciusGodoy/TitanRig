package com.titanrig.titanrig.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInsertDTO extends UserDTO{

    private String password;

    UserInsertDTO(){
        super();
    }
}
