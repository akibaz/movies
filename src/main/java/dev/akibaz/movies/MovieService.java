package dev.akibaz.movies;

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
}
