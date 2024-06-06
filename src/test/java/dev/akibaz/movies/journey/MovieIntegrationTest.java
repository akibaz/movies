package dev.akibaz.movies.journey;

import dev.akibaz.movies.movie.Movie;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MovieIntegrationTest {
    @Autowired
    private WebTestClient client;
    private final String apiPath = "/api/v1/movies";

    @Test
    void canGetAllMovies() {
        // send get request for all movies
        List<Movie> allMovies = client.get()
                .uri(apiPath)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(new ParameterizedTypeReference<Movie>() {
                })
                .returnResult()
                .getResponseBody();
        assertThat(allMovies).isNotEmpty();
        assertThat(allMovies).hasSize(10);
    }

    @Test
    void canGetMovieById() {
        // get all movies
        List<Movie> allMovies = client.get()
                .uri(apiPath)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(new ParameterizedTypeReference<Movie>() {
                })
                .returnResult()
                .getResponseBody();
        assertThat(allMovies).isNotEmpty();
        // get a movie by id
        ObjectId movieId = allMovies.get(0).getId();
        Movie actual = client.get()
                .uri(apiPath + "/movie/{movieId}", movieId)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(Movie.class)
                .returnResult()
                .getResponseBody();
        assertThat(actual).isIn(allMovies);
    }

    @Test
    void canGetMovieByImdbId() {
        // get all movies
        List<Movie> allMovies = client.get()
                .uri(apiPath)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(new ParameterizedTypeReference<Movie>() {
                })
                .returnResult()
                .getResponseBody();
        assertThat(allMovies).isNotEmpty();
        // get a movie by imdbId
        String imdbId = allMovies.get(0).getImdbId();
        Movie actual = client.get()
                .uri(apiPath + "/{imdbId}", imdbId)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(Movie.class)
                .returnResult()
                .getResponseBody();
        assertThat(actual).isIn(allMovies);
    }
}
