package com.example.cine.Controller;

import com.example.cine.Entity.Movies.Movie;
import com.example.cine.Repository.Services.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Movies")
@Slf4j
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }


    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/save")
    public void guardarPelicula(@RequestBody Movie movie){
        movieService.save(movie);
    }


    @GetMapping("/get/{title}")
    public Movie obtenerPelicula(@PathVariable String title){
        return movieService.findByName(title);
    }


    @GetMapping("/get")
    public List<Movie> obtenerPeliculas(){
        return movieService.findAll();
    }


    @DeleteMapping("/delete/{title}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarPelicula (@PathVariable String title){

    }


}
