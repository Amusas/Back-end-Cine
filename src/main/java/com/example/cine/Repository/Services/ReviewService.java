package com.example.cine.Repository.Services;

import com.example.cine.Entity.Movies.Review;
import com.example.cine.Repository.Interfaces.IReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class ReviewService {

    private IReviewRepository reviewRepository;

    @Autowired
    public ReviewService(IReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    public Review findByid(long id) {
        return reviewRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Review review) {
        reviewRepository.save(review);
    }

    @Transactional
    public void deleteById(long id){
        reviewRepository.deleteById(id);
    }

}
