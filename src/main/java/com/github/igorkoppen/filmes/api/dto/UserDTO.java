package com.github.igorkoppen.filmes.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.igorkoppen.filmes.api.model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;


import java.util.Collections;
import java.util.List;

public class UserDTO {


    private Long id;
    @NotEmpty(message = "Nome não pode estar vazio.")
    private String name;
    @NotEmpty(message = "Nome não pode estar vazio.")
    @Email(message = "Formato de email inválido!")
    private String email;
    @NotEmpty(message = "Nome não pode estar vazio.")
    @Size(min = 6, message = "Mínimo de 6 digitos para a senha")
    private String password;
    @JsonIgnore
    private List<ReviewDTO> reviews;

    public UserDTO(Long id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }


    public UserDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        if(user.getReviews() != null){
            this.reviews = user.getReviews().stream().map(review -> new ReviewDTO(review)).toList() ;
        }else{
            this.reviews = Collections.emptyList();
        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public List<ReviewDTO> getReviews() {
        return reviews;
    }
}
