package dev.akibaz.movies.controller;

import dev.akibaz.movies.model.Movie;
import dev.akibaz.movies.service.MovieService;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies() {
        return new ResponseEntity<>(
                movieService.getALlMovies(),
                HttpStatus.OK
        );
    }

    @GetMapping("/movie/{movieId}")
    public ResponseEntity<Movie> getMovieById(@PathVariable("movieId") ObjectId movieId) {
        return new ResponseEntity<>(
                movieService.getMovieById(movieId),
                HttpStatus.OK
        );
    }

    @GetMapping("/{imdbId}")
    public ResponseEntity<Movie> getMovieByImdbId(@PathVariable("imdbId") String imdbId) {
        return new ResponseEntity<>(
                movieService.getMovieByImdbId(imdbId),
                HttpStatus.OK
        );
    }
}
