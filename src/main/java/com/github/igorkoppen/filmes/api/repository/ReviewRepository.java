package com.github.igorkoppen.filmes.api.repository;

import com.github.igorkoppen.filmes.api.model.Filme;
import com.github.igorkoppen.filmes.api.model.Review;
import com.github.igorkoppen.filmes.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {

    @Query("SELECT obj FROM Review obj WHERE: filme IS NULL OR obj.filme = :filme")
    List<Review> findByFilme(Filme filme);

    @Query("SELECT obj FROM Review obj WHERE: user IS NULL OR obj.user = :user")
    List<Review> findByUser(User user);
}
