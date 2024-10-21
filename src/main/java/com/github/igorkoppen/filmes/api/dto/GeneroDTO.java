package com.github.igorkoppen.filmes.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.igorkoppen.filmes.api.model.Genero;
import jakarta.validation.constraints.NotEmpty;

import java.util.Collections;
import java.util.List;

public class GeneroDTO {
    private Long id;
    @NotEmpty(message = "nome não pode estar vazio")
    private String nome;
    @JsonIgnore
    private List<FilmeDTO> filmes;

    public GeneroDTO(Long id, String nome, List<FilmeDTO> filmes) {
        this.id = id;
        this.nome = nome;
        this.filmes = filmes;
    }

    public GeneroDTO(Genero genero) {
        this.id = genero.getId();
        this.nome = genero.getNome();
        if (genero.getFilmes() != null) {
            this.filmes = genero.getFilmes().stream().map(FilmeDTO::new).toList();
        } else {
            this.filmes = Collections.emptyList();
        }

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

    public List<FilmeDTO> getFilmes() {
        return filmes;
    }

    public void setFilmes(List<FilmeDTO> filmes) {
        this.filmes = filmes;
    }
}
