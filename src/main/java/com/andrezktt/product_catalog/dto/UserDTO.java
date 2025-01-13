package com.andrezktt.product_catalog.dto;

import com.andrezktt.product_catalog.entities.Role;
import com.andrezktt.product_catalog.entities.User;

import java.util.HashSet;
import java.util.Set;

public class UserDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;

    private Set<RoleDTO> roles = new HashSet<>();

    public UserDTO() {
    }

    public UserDTO(Long id, String firstName, String lastName, String email, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public UserDTO(User entity) {
        id = entity.getId();
        firstName = entity.getFirstName();
        lastName = entity.getLastName();
        email = entity.getEmail();
        entity.getRoles().forEach(e -> this.roles.add(new RoleDTO(e)));
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Set<RoleDTO> getRoles() {
        return roles;
    }
}
