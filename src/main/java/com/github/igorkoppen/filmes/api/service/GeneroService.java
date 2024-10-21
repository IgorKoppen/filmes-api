package com.github.igorkoppen.filmes.api.service;

import com.github.igorkoppen.filmes.api.dto.GeneroDTO;
import com.github.igorkoppen.filmes.api.exception.DatabaseException;
import com.github.igorkoppen.filmes.api.exception.ResourceNotFoundException;
import com.github.igorkoppen.filmes.api.model.Filme;
import com.github.igorkoppen.filmes.api.model.Genero;
import com.github.igorkoppen.filmes.api.repository.GeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GeneroService {

    @Autowired
    private GeneroRepository generoRepository;

    @Transactional
    public GeneroDTO insert(GeneroDTO generoDTO){
        Genero genero = new Genero();
        copyToEntity(generoDTO,genero);
        genero = generoRepository.save(genero);
        return toDTO(genero);
    }

    @Transactional(readOnly = true)
    public List<GeneroDTO> findAll(){
        List<Genero> generos = generoRepository.findAll();
        return generos.stream().map(this::toDTO).toList();
    }

    @Transactional(readOnly = true)
    public GeneroDTO findById(Long id){
        Genero genero =  generoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Genero not founded with id: " + id));
        return toDTO(genero);
    }
    @Transactional
    public GeneroDTO update(Long id, GeneroDTO generoDTO){
        Genero genero =  generoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Genero not founded with id: " + id));
        copyToEntity(generoDTO,genero);
        genero = generoRepository.save(genero);
        return toDTO(genero);
    }
    @Transactional
    public void  delete(Long id){
        if (!generoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado! Id: " + id);
        }
        try {
            generoRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }
    }

    private void copyToEntity(GeneroDTO dto, Genero entity){
        entity.setNome(dto.getNome());
        entity.setFilmes(dto.getFilmes().stream().map(Filme::new).toList());

    }

    private GeneroDTO toDTO(Genero genero){
        return new GeneroDTO(genero);
    }
}
