package com.github.igorkoppen.filmes.api.dto;

import com.github.igorkoppen.filmes.api.model.Role;

public class RoleDTO {

    private Long id;
    private String authority;

    public RoleDTO(String authority, Long id) {
        this.authority = authority;
        this.id = id;
    }
    public RoleDTO(Role role){
        this.authority = role.getAuthority();
        this.id = role.getId();
    }

    public Long getId() {
        return id;
    }

    public String getAuthority() {
        return authority;
    }
}
