package dev.akibaz.movies.service;

import dev.akibaz.movies.exception.ResourceNotFoundException;
import dev.akibaz.movies.model.Movie;
import dev.akibaz.movies.dao.MovieDAO;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    private final MovieDAO movieDAO;

    public MovieService(MovieDAO movieDAO) {
        this.movieDAO = movieDAO;
    }

    public List<Movie> getALlMovies() {
        return movieDAO.selectAllMovies();
    }

    public Movie getMovieById(ObjectId movieId) {
        return movieDAO.selectMovieById(movieId).orElseThrow(() ->
                new ResourceNotFoundException("Movie with id: %s not found.".formatted(movieId)));
    }

    public Movie getMovieByImdbId(String imdbId) {
        return movieDAO.selectMovieByImdbId(imdbId).orElseThrow(() ->
                new ResourceNotFoundException("Movie with imdbId: %s not found.".formatted(imdbId)));
    }
}
