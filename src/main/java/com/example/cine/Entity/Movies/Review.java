package com.example.cine.Entity.Movies;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "reviews")
@Setter @Getter
public class Review {

    @Id
    @GeneratedValue
    private long id;

    private int rate;

    @Column(name = "name_user")
    private String nameUser;

    private String description;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;
}
