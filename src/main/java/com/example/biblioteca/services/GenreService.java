package com.example.biblioteca.services;

import com.example.biblioteca.models.Genre;
import com.example.biblioteca.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {
    @Autowired
    private GenreRepository genreRepository;
    public List<Genre> getGenres(){
        return genreRepository.findAll();
    }
    public Genre getGenreById(Long id){
        return genreRepository.findById(id).orElse(null);
    }
}
