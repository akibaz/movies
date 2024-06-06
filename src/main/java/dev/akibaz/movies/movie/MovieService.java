package dev.akibaz.movies.movie;

import dev.akibaz.movies.exception.ResourceNotFoundException;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    private final MovieDAO movieDAO;

    public MovieService(@Qualifier("mongoDb") MovieDAO movieDAO) {
        this.movieDAO = movieDAO;
    }

    public List<Movie> getALlMovies() {
        return movieDAO.selectAllMovies();
    }

    public Movie getMovieById(ObjectId movieId) {
        return movieDAO.selectMovieById(movieId).orElseThrow(() ->
                new ResourceNotFoundException("Movie with id: %s not found.".formatted(movieId)));
    }
}
