package com.example.cine.Repository.Services;
import com.example.cine.Entity.Movies.Movie;
import com.example.cine.Repository.Interfaces.IMovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class MovieService {

    private final IMovieRepository movieRepository;

    @Autowired
    public MovieService(IMovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    public Movie findByName(String title) {
        return movieRepository.findByTitle(title).orElse(null);
    }

    @Transactional
    public void save(Movie movie) {
        movieRepository.save(movie);
    }

    @Transactional
    public void deleteByTitle(String title){
        movieRepository.deleteByTitle(title);
    }

}
