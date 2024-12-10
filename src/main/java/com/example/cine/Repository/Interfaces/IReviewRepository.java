package com.example.cine.Repository.Interfaces;

import com.example.cine.Entity.Movies.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IReviewRepository extends JpaRepository<Review, Long> {
}

