package com.github.igorkoppen.filmes.api.service;

import com.github.igorkoppen.filmes.api.dto.ReviewDTO;
import com.github.igorkoppen.filmes.api.exception.DatabaseException;
import com.github.igorkoppen.filmes.api.exception.ResourceNotFoundException;
import com.github.igorkoppen.filmes.api.model.Filme;
import com.github.igorkoppen.filmes.api.model.Review;
import com.github.igorkoppen.filmes.api.model.User;
import com.github.igorkoppen.filmes.api.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Transactional
    public ReviewDTO insert(ReviewDTO reviewDTO){
        Review review = new Review();
        copyToEntity(reviewDTO,review);
        review = reviewRepository.save(review);
        return toDTO(review);
    }

    @Transactional(readOnly = true)
    public List<ReviewDTO> findAll(){
        List<Review> reviews = reviewRepository.findAll();
        return reviews.stream().map(this::toDTO).toList();
    }

    @Transactional(readOnly = true)
    public ReviewDTO findById(Long id){
        Review review =  reviewRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Review not founded with id: " + id));
        return toDTO(review);
    }
    @Transactional
    public ReviewDTO update(Long id, ReviewDTO reviewDTO){
        Review review =  reviewRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Review not founded with id: " + id));
        copyToEntity(reviewDTO,review);
        review = reviewRepository.save(review);
        return toDTO(review);
    }
    @Transactional
    public void  delete(Long id){
        if (!reviewRepository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado! Id: " + id);
        }
        try {
            reviewRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }
    }

    private void copyToEntity(ReviewDTO dto, Review entity){
        entity.setTexto(dto.getTexto());
        entity.setFilme(new Filme(dto.getFilme()));
        entity.setUser(new User(dto.getUser()));
    }

    private ReviewDTO toDTO(Review review){
        return new ReviewDTO(review);
    }
}
