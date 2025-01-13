package com.andrezktt.product_catalog.dto;

import com.andrezktt.product_catalog.entities.User;

import java.util.HashSet;
import java.util.Set;

public class UserInsertDTO extends UserDTO {

    private String password;

    public UserInsertDTO(String password) {
        super();
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
