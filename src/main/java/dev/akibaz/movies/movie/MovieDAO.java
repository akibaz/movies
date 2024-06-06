package dev.akibaz.movies.movie;

import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface MovieDAO {
    List<Movie> selectAllMovies();

    Optional<Movie> selectMovieById(ObjectId movieId);

    Optional<Movie> selectMovieByImdbId(String imdbId);
}
