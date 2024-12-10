package com.example.cine.Entity.Buy;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "salas")
@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
public class Sala {

    @Id
    @GeneratedValue
    private long id;

    //private int[][] chairs;

    private boolean thirdDimension;

    private String movie_name;


}
