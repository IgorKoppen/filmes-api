package com.github.igorkoppen.filmes.api.service;

import com.github.igorkoppen.filmes.api.dto.FilmeDTO;
import com.github.igorkoppen.filmes.api.exception.DatabaseException;
import com.github.igorkoppen.filmes.api.exception.ResourceNotFoundException;
import com.github.igorkoppen.filmes.api.model.Filme;
import com.github.igorkoppen.filmes.api.model.Genero;
import com.github.igorkoppen.filmes.api.model.Review;
import com.github.igorkoppen.filmes.api.repository.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FilmeService {

    @Autowired
    FilmeRepository filmeRepository;

    @Transactional
    public FilmeDTO insert(FilmeDTO filmeDTO) {
        Filme filme = new Filme();
        copyToEntity(filmeDTO, filme);
        filme = filmeRepository.save(filme);
        return toDTO(filme);
    }

    @Transactional(readOnly = true)
    public List<FilmeDTO> findAll() {
        List<Filme> filmes = filmeRepository.findAll();
        return filmes.stream().map(this::toDTO).toList();
    }

    @Transactional(readOnly = true)
    public FilmeDTO findById(Long id) {
        Filme filme = filmeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Filme not founded with id: " + id));
        return toDTO(filme);
    }

    @Transactional
    public FilmeDTO update(Long id, FilmeDTO filmeDTO) {
        Filme filme = filmeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Filme not founded with id: " + id));
        copyToEntity(filmeDTO, filme);
        filme = filmeRepository.save(filme);
        return toDTO(filme);
    }

    @Transactional
    public void delete(Long id) {
        if (!filmeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado! Id: " + id);
        }
        try {
            filmeRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }
    }

    private void copyToEntity(FilmeDTO dto, Filme entity) {
        entity.setAno(dto.getAno());
        entity.setTitulo(dto.getTitulo());
        entity.setGenero(new Genero(dto.getGenero()));
        entity.setReviews(dto.getReviews().stream().map(Review::new).toList());

    }

    private FilmeDTO toDTO(Filme filme) {
        return new FilmeDTO(filme);
    }
}
