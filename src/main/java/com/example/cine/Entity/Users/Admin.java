package com.example.cine.Entity.Users;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "administradores")
@Getter @Setter
public class Admin extends Usuario {

    String cedula;

}
