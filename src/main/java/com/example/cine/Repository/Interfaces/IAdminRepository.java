package com.example.cine.Repository.Interfaces;

import com.example.cine.Entity.Users.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAdminRepository extends JpaRepository<Admin, Long> {

    Optional<Admin> findByUsername(String username);

}

