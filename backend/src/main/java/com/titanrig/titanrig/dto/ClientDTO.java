package com.titanrig.titanrig.dto;

import com.titanrig.titanrig.entities.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ClientDTO {

    private Long id;
    private String name;

    public ClientDTO(User entity) {
        id = entity.getId();
        name = entity.getName();
    }
}
