package com.github.igorkoppen.filmes.api.repository;

import com.github.igorkoppen.filmes.api.model.Genero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneroRepository extends JpaRepository<Genero,Long> {
}
