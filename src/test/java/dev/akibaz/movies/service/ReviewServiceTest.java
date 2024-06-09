package dev.akibaz.movies.service;

import dev.akibaz.movies.AbstractTestcontainersUnitTest;
import dev.akibaz.movies.dao.MovieDAO;
import dev.akibaz.movies.dao.ReviewDao;
import dev.akibaz.movies.exception.ResourceNotFoundException;
import dev.akibaz.movies.model.Review;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@DataMongoTest
@ExtendWith(MockitoExtension.class)
class ReviewServiceTest extends AbstractTestcontainersUnitTest {
    private ReviewService underTest;
    @Mock
    private ReviewDao reviewDao;
    @Mock
    private MovieDAO movieDAO;
    @Autowired
    private MongoTemplate mongoTemplate;

    @BeforeEach
    void setUp() {
        underTest = new ReviewService(
                reviewDao,
                movieDAO,
                mongoTemplate
        );
    }

    @Test
    void createReview() {
        // Given
        String imdbId = "tt3915174";
        when(movieDAO.existsMovieByImdbId(imdbId)).thenReturn(true);
        String reviewBody = "This is a review";
        Review review = new Review(reviewBody);
        when(reviewDao.insertReview(review)).thenReturn(review);
        // When
        Review result = underTest.createReview(imdbId, reviewBody);

        //Then
        ArgumentCaptor<Review> reviewArgumentCaptor = ArgumentCaptor.forClass(Review.class);
        verify(reviewDao).insertReview(reviewArgumentCaptor.capture());
        Review actualReview = reviewArgumentCaptor.getValue();
        assertThat(actualReview).isEqualTo(review);
        assertThat(result).isEqualTo(review);
    }

    @Test
    void createReviewThrowsResourceNotFoundExceptionWhenMovieNotFoundByProvidedImdbId() {
        // Given
        String imdbId = "tt3915174";
        when(movieDAO.existsMovieByImdbId(imdbId)).thenReturn(false);

        // When

        //Then
        assertThatThrownBy(() -> underTest.createReview(imdbId, "test"))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Movie with imdbId " + imdbId + " does not exist.");

    }
}