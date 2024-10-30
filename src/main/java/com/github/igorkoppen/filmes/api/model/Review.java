package com.github.igorkoppen.filmes.api.model;

import com.github.igorkoppen.filmes.api.dto.ReviewDTO;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "tb_review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String texto;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @ManyToOne
    @JoinColumn(name="filme_id", nullable=false)
    private Filme filme;

    public Review(Long id, String texto, User user, Filme filme) {
        this.id = id;
        this.texto = texto;
        this.user = user;
        this.filme = filme;
    }

    public Review() {
    }

    public Review(ReviewDTO reviewDTO) {
        User user = new User();
        Filme filme = new Filme();
        user.setId(reviewDTO.getUserId());
        filme.setId(reviewDTO.getFilmeId());
        this.id = reviewDTO.getId();
        this.texto = reviewDTO.getTexto();
        this.user = user;
        this.filme = filme;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Filme getFilme() {
        return filme;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return Objects.equals(id, review.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
