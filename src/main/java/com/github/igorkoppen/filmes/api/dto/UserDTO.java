package com.github.igorkoppen.filmes.api.dto;


import com.github.igorkoppen.filmes.api.model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;


public class UserDTO {


    private Long id;
    @NotEmpty(message = "Nome não pode estar vazio.")
    private String name;
    @NotEmpty(message = "Nome não pode estar vazio.")
    @Email(message = "Formato de email inválido!")
    private String email;
    @NotEmpty(message = "Nome não pode estar vazio.")


    private Set<RoleDTO> roles = new HashSet<>();

    public UserDTO(Long id, String name, String email, Set<RoleDTO> roles) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.roles = roles;
    }

    public UserDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        user.getRoles().forEach(role -> this.roles.add(new RoleDTO(role)));
    }
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }


    public Set<RoleDTO> getRoles() {
        return roles;
    }
}
