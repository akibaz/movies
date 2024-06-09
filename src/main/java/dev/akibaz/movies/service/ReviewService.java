package dev.akibaz.movies.service;

import dev.akibaz.movies.dao.MovieDAO;
import dev.akibaz.movies.dao.ReviewDao;
import dev.akibaz.movies.exception.ResourceNotFoundException;
import dev.akibaz.movies.model.Movie;
import dev.akibaz.movies.model.Review;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    private final ReviewDao reviewDao;
    private final MovieDAO movieDAO;
    private final MongoTemplate mongoTemplate;

    public ReviewService(ReviewDao reviewDao, MovieDAO movieDAO, MongoTemplate mongoTemplate) {
        this.reviewDao = reviewDao;
        this.movieDAO = movieDAO;
        this.mongoTemplate = mongoTemplate;
    }

    public Review createReview(String imdbId, String reviewBody) {
        if (!movieDAO.existsMovieByImdbId(imdbId)) {
            throw new ResourceNotFoundException(
                    "Movie with imdbId " + imdbId + " does not exist."
            );
        }

        Review review = reviewDao.insertReview(new Review(reviewBody));
        mongoTemplate.update(Movie.class)
                .matching(Criteria.where("imdbId").is(imdbId))
                .apply(new Update().push("reviewIds").value(review))
                .first()
                .getModifiedCount();

        return review;
    }
}
