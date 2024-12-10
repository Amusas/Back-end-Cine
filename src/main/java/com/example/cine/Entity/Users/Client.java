package com.example.cine.Entity.Users;

import com.example.cine.Entity.Buy.Tarjeta;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "clients")
@Getter @Setter
//generar boleto con Jasper report y enviarlo por el correo
public class Client extends Usuario {

    @OneToMany(mappedBy = "client")
    private List<Tarjeta> tarjetas;

}
