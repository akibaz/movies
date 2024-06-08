package dev.akibaz.movies.dao;

import dev.akibaz.movies.model.Movie;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("mongoDb")
public class MovieDataAccesService implements MovieDAO {
    private final MovieRepository movieRepository;

    public MovieDataAccesService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public List<Movie> selectAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Optional<Movie> selectMovieById(ObjectId movieId) {
        return movieRepository.findById(movieId);
    }

    @Override
    public Optional<Movie> selectMovieByImdbId(String imdbId) {
        return movieRepository.findMovieByImdbId(imdbId);
    }
}
