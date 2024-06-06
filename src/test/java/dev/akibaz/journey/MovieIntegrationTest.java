package dev.akibaz.journey;

import dev.akibaz.movies.Movie;
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
}
