package dev.akibaz.movies;

import java.util.List;

public interface MovieDAO {
    List<Movie> selectAllMovies();
}
