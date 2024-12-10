package com.example.cine.Entity.Buy;

import com.example.cine.Entity.Users.Client;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "tarjetas")
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Tarjeta {

    @Id
    @GeneratedValue
    private long id;

    private String numero;

    private String csv;

    private String userName;

    private double saldo;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

}
