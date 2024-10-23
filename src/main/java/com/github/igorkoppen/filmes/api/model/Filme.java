package com.github.igorkoppen.filmes.api.model;


import com.github.igorkoppen.filmes.api.dto.FilmeDTO;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_filme")
public class Filme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String titulo;
    @Column(nullable = false)
    private Integer ano;

    @OneToMany(mappedBy = "filme")
    private List<Review> reviews;

    @ManyToOne
    @JoinColumn(name="genero_id", nullable=false)
    private Genero genero;

    public Filme(Long id, String titulo, Integer ano, List<Review> reviews, Genero genero) {
        this.id = id;
        this.titulo = titulo;
        this.ano = ano;
        this.reviews = reviews;
        this.genero = genero;
    }

    public Filme() {
    }

    public Filme(FilmeDTO filme) {
        this.id = filme.getId();
        this.titulo = filme.getTitulo();
        this.ano = filme.getAno();
        this.genero = new Genero(filme.getGenero());
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Filme filme = (Filme) o;
        return Objects.equals(id, filme.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
