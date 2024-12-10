package com.example.cine.Entity.Movies;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "movies")
@Setter @Getter
public class Movie {

    @Id
    @GeneratedValue
    private long id;

    private String ruta_imagen;

    private String title;

    private String description;

    private String coupon;

    @Enumerated(EnumType.STRING)
    private GenreMovie genre;

    private double valor;

    @OneToMany(mappedBy = "movie")
    private List<Review> reviews;

    private String programacion;


}
