package dev.akibaz.movies.dao;

import dev.akibaz.movies.model.Review;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ReviewDataAccessServiceTest {
    private ReviewDataAccessService underTest;
    @Mock
    private ReviewRepository reviewRepository;

    @BeforeEach
    void setUp() {
        underTest = new ReviewDataAccessService(reviewRepository);
    }

    @Test
    void insertReview() {
        // Given
        Review review = new Review(
                "Test review."
        );

        // When
        underTest.insertReview(review);

        //Then
        verify(reviewRepository).save(review);
    }
}