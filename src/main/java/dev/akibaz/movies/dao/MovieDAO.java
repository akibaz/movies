package dev.akibaz.movies.dao;

import dev.akibaz.movies.model.Movie;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface MovieDAO {
    List<Movie> selectAllMovies();

    Optional<Movie> selectMovieById(ObjectId movieId);

    Optional<Movie> selectMovieByImdbId(String imdbId);

    boolean existsMovieByImdbId(String imdbId);
}
