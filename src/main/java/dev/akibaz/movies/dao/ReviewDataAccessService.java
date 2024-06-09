package dev.akibaz.movies.dao;

import dev.akibaz.movies.model.Review;
import org.springframework.stereotype.Repository;

@Repository
public class ReviewDataAccessService implements ReviewDao {
    private final ReviewRepository reviewRepository;

    public ReviewDataAccessService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }


    @Override
    public Review insertReview(Review review) {
        return reviewRepository.save(review);
    }
}
