package com.github.igorkoppen.filmes.api.dto;

import jakarta.validation.constraints.NotEmpty;

public class ReviewDTO {

    private Long id;
    @NotEmpty(message = "Texto não pode estar vazio.")
    private String texto;


    private Long userId;
    private Long filmeId;


    public ReviewDTO(Long id, String texto, Long userId, Long filmeId) {
        this.id = id;
        this.texto = texto;
        this.userId = userId;
        this.filmeId = filmeId;
    }


    public Long getId() {
        return id;
    }

    public String getTexto() {
        return texto;
    }

    public Long getFilmeId() {
        return filmeId;
    }

    public Long getUserId() {
        return userId;
    }
}
