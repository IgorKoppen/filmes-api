package com.github.igorkoppen.filmes.api.dto;

import com.github.igorkoppen.filmes.api.model.Review;
import jakarta.validation.constraints.NotEmpty;

public class ReviewsWithUserDTO {

    private Long id;
    @NotEmpty(message = "Texto não pode estar vazio.")
    private String texto;
    private UserNameDTO user;


    public ReviewsWithUserDTO(Long id, String texto, UserNameDTO user) {
        this.id = id;
        this.texto = texto;
        this.user = user;
    }

    public ReviewsWithUserDTO(Review review) {
        this.id = review.getId();
        this.texto = review.getTexto();
        this.user = new UserNameDTO(review.getUser());
    }

    public Long getId() {
        return id;
    }

    public String getTexto() {
        return texto;
    }

    public UserNameDTO getUser() {
        return user;
    }

}
