package dev.akibaz.movies;

import org.springframework.stereotype.Repository;

import java.util.List;

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
}
