package com.github.igorkoppen.filmes.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.igorkoppen.filmes.api.model.Review;
import jakarta.validation.constraints.NotEmpty;

public class ReviewDTO {

    private Long id;
    @NotEmpty(message = "Texto não pode estar vazio.")
    private String texto;
    @JsonIgnore
    private UserDTO user;
    @JsonIgnore
    private FilmeDTO filme;


    public ReviewDTO(Long id, String texto, UserDTO user, FilmeDTO filme) {
        this.id = id;
        this.texto = texto;
        this.user = user;
        this.filme = filme;
    }

    public ReviewDTO(Review review) {
        this.id = review.getId();
        this.texto = review.getTexto();
    }

    public Long getId() {
        return id;
    }

    public String getTexto() {
        return texto;
    }

    public UserDTO getUser() {
        return user;
    }

    public FilmeDTO getFilme() {
        return filme;
    }
}
