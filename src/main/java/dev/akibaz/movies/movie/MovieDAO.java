package dev.akibaz.movies.movie;

import java.util.List;

public interface MovieDAO {
    List<Movie> selectAllMovies();
}
