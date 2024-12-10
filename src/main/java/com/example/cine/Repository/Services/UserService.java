package com.example.cine.Repository.Services;

import com.example.cine.Entity.Users.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserService extends JpaRepository<Usuario,Integer> {

    // Método para obtener el usuario desde tu base de datos
    public Usuario findByUsername(String username);


}
