package dev.akibaz.movies.movie;

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
}