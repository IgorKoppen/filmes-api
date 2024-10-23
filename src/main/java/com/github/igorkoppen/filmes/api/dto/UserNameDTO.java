package com.github.igorkoppen.filmes.api.dto;

import com.github.igorkoppen.filmes.api.model.User;
import jakarta.validation.constraints.NotEmpty;

public class UserNameDTO {

    private Long id;
    @NotEmpty(message = "Nome não pode estar vazio.")
    private String name;

    public UserNameDTO(Long id, String name, String email, String password) {
        this.id = id;
        this.name = name;
    }

    public UserNameDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
