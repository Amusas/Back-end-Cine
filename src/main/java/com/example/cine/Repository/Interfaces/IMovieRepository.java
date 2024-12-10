package com.example.cine.Repository.Interfaces;
import com.example.cine.Entity.Movies.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IMovieRepository extends JpaRepository<Movie, Long> {


    Optional<Movie> findByTitle(String title);

    void deleteByTitle(String title);

}
