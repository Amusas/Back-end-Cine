package com.example.cine.Repository.Interfaces;

import com.example.cine.Entity.Buy.Tarjeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITarjetaRepository extends JpaRepository<Tarjeta, Long> {
}

