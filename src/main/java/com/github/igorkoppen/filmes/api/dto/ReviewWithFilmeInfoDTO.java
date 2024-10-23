package com.github.igorkoppen.filmes.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotEmpty;

public class ReviewWithFilmeInfoDTO {

    private Long id;
    @NotEmpty(message = "Texto não pode estar vazio.")
    private String texto;
    @JsonIgnoreProperties({"reviews"})
    private FilmeDTO user;

    public ReviewWithFilmeInfoDTO(Long id, String texto, FilmeDTO user) {
        this.id = id;
        this.texto = texto;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public String getTexto() {
        return texto;
    }

    public FilmeDTO getUser() {
        return user;
    }
}
