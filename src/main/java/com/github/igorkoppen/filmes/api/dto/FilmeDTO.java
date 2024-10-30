package com.github.igorkoppen.filmes.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.igorkoppen.filmes.api.model.Filme;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


import java.util.Collections;
import java.util.List;

public class FilmeDTO {


    private Long id;
    @NotEmpty(message = "titulo não pode estar vazio")
    private String titulo;
    @NotNull(message = "ano não pode estar vazio")
    @Positive(message = "ano tem que ser positivo")
    private Integer ano;

    private List<ReviewDTO> reviews;

    private GeneroDTO genero;

    public FilmeDTO(Long id, String titulo, Integer ano, List<ReviewDTO> reviews, GeneroDTO genero) {
        this.id = id;
        this.titulo = titulo;
        this.ano = ano;
        this.reviews = reviews;
        this.genero = genero;
    }

    public FilmeDTO(Filme filme) {
        this.id = filme.getId();
        this.titulo = filme.getTitulo();
        this.ano = filme.getAno();
        if(filme.getReviews() != null){
            this.reviews =  filme.getReviews().stream().map(review -> new ReviewDTO(review.getId(),review.getTexto(),review.getUser().getId(), review.getFilme().getId())).toList();
        }else{
            this.reviews = Collections.emptyList();
        }
        this.genero = new GeneroDTO(filme.getGenero());
    }


    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public Integer getAno() {
        return ano;
    }

    public List<ReviewDTO> getReviews() {
        return reviews;
    }

    public GeneroDTO getGenero() {
        return genero;
    }
}
