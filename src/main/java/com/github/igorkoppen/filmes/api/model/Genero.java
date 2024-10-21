package com.github.igorkoppen.filmes.api.model;


import com.github.igorkoppen.filmes.api.dto.GeneroDTO;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_genero")
public class Genero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @OneToMany(mappedBy = "genero")
    private List<Filme> filmes;

    public Genero(Long id, String nome, List<Filme> filmes) {
        this.id = id;
        this.nome = nome;
        this.filmes = filmes;
    }

    public Genero() {
    }

    public Genero(GeneroDTO genero) {
        this.id = genero.getId();
        this.nome = genero.getNome();
        this.filmes = genero.getFilmes().stream().map(Filme::new).toList();
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

    public List<Filme> getFilmes() {
        return filmes;
    }

    public void setFilmes(List<Filme> filmes) {
        this.filmes = filmes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genero genero = (Genero) o;
        return Objects.equals(id, genero.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
