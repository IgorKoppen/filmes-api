package com.github.igorkoppen.filmes.api.dto;

import jakarta.validation.constraints.Size;

import java.util.Set;

public class UserInsertDTO extends UserDTO{

    @Size(min = 6, message = "Mínimo de 6 digitos para a senha")
    private String password;


    public UserInsertDTO(Long id, String name, String email, Set<RoleDTO> roles, String password) {
        super(id, name, email, roles);
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
