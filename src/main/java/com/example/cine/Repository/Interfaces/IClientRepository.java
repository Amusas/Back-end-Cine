package com.example.cine.Repository.Interfaces;

import com.example.cine.Entity.Users.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByUsername(String username);

    void deleteClientByUsername(String username);
}
