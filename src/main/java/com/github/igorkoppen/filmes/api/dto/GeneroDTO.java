package com.github.igorkoppen.filmes.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.igorkoppen.filmes.api.model.Genero;
import jakarta.validation.constraints.NotEmpty;


public class GeneroDTO {
    private Long id;
    @NotEmpty(message = "nome não pode estar vazio")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String nome;

    public GeneroDTO(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public GeneroDTO(Genero genero) {
        this.id = genero.getId();
        this.nome = genero.getNome();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
