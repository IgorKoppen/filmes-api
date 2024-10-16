package com.github.igorkoppen.filmes.api.repository;

import com.github.igorkoppen.filmes.api.model.Filme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmeRepository extends JpaRepository<Filme,Long> {
}
