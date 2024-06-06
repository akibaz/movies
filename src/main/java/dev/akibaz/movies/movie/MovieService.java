package dev.akibaz.movies.movie;

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

    public Movie getMovieById(ObjectId objectId) {
        return movieDAO.selectMovieById(objectId).orElseThrow();
    }
}
