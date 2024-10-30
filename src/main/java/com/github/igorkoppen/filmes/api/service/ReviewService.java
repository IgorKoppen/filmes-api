package com.github.igorkoppen.filmes.api.service;

import com.github.igorkoppen.filmes.api.dto.ReviewDTO;
import com.github.igorkoppen.filmes.api.exception.DatabaseException;
import com.github.igorkoppen.filmes.api.exception.ResourceNotFoundException;
import com.github.igorkoppen.filmes.api.model.Filme;
import com.github.igorkoppen.filmes.api.model.Review;
import com.github.igorkoppen.filmes.api.model.User;
import com.github.igorkoppen.filmes.api.repository.FilmeRepository;
import com.github.igorkoppen.filmes.api.repository.ReviewRepository;
import com.github.igorkoppen.filmes.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private FilmeRepository filmeRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public ReviewDTO insert(ReviewDTO reviewDTO) {
        Review review = new Review();
        copyToEntity(reviewDTO, review);
        review = reviewRepository.save(review);
        return toDTO(review);
    }

    @Transactional(readOnly = true)
    public List<ReviewDTO> findAll() {
        List<Review> reviews = reviewRepository.findAll();
        return reviews.stream().map(this::toDTO).toList();
    }

    @Transactional(readOnly = true)
    public ReviewDTO findById(Long id) {
        Review review = reviewRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Review not founded with id: " + id));
        return toDTO(review);
    }

    @Transactional
    public ReviewDTO update(Long id, ReviewDTO reviewDTO) {
        Review review = reviewRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Review not founded with id: " + id));
        copyToEntity(reviewDTO, review);
        review = reviewRepository.save(review);
        return toDTO(review);
    }

    @Transactional
    public void delete(Long id) {
        if (!reviewRepository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado! Id: " + id);
        }
        try {
            reviewRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }
    }

    @Transactional(readOnly = true)
    public List<ReviewDTO> findByFilme(Long filmeId) {
        if (!filmeRepository.existsById(filmeId)) {
            throw new ResourceNotFoundException("Recurso não encotrado!");
        }
        Filme filme = filmeRepository.getReferenceById(filmeId);
        List<Review> reviewList = reviewRepository.findByFilme(filme);
        return reviewList.stream().map(this::toDTO).toList();
    }
    @Transactional(readOnly = true)
    public List<ReviewDTO> findByUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("Recurso não encotrado!");
        }
        User user = userRepository.getReferenceById(userId);
        List<Review> reviewList = reviewRepository.findByUser(user);
        return reviewList.stream().map(this::toDTO).toList();
    }

    private void copyToEntity(ReviewDTO dto, Review entity) {
        entity.setTexto(dto.getTexto());
        entity.setFilme(findFilmeEntityById(dto.getFilmeId()));
        entity.setUser(findUserEnityById(dto.getUserId()));
    }

    private User findUserEnityById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuario não encontrado com id: " + id));
    }

    private Filme findFilmeEntityById(Long id) {
        return filmeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Filme não encontrado com id: " + id));
    }

    private ReviewDTO toDTO(Review review) {
        return new ReviewDTO(review.getId(), review.getTexto(), review.getUser().getId(), review.getFilme().getId());
    }

}
