package com.github.igorkoppen.filmes.api.dto;

import com.github.igorkoppen.filmes.api.model.Review;
import com.github.igorkoppen.filmes.api.model.User;


import java.util.List;

public class UserDTO {


    private Long id;
    private String name;
    private String email;
    private String password;
    private List<Long> reviews;

    public UserDTO(Long id, String name, String email, String password, List<Review> reviews) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.reviews = reviews.stream().map(Review::getId).toList();
    }

    public UserDTO(User user) {
        this.reviews = user.getReviews().stream().map(Review::getId).toList();
    }
}
