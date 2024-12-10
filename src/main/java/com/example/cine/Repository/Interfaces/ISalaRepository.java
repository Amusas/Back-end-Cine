package com.example.cine.Repository.Interfaces;

import com.example.cine.Entity.Buy.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISalaRepository extends JpaRepository<Sala, Long> {
}

