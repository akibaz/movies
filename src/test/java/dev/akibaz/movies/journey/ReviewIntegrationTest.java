package dev.akibaz.movies.journey;

import dev.akibaz.movies.model.Movie;
import dev.akibaz.movies.model.Review;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReviewIntegrationTest {
    @Autowired
    private WebTestClient client;
    private final String uri = "/api/v1/movie/reviews";

    @Test
    void canCreateReview() {
        // get all movies
        List<Movie> allMovies = client.get()
                .uri("/api/v1/movies")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(new ParameterizedTypeReference<Movie>() {
                })
                .returnResult()
                .getResponseBody();
        // get a single movie imdbId
        String imdbId = allMovies.get(0).getImdbId();
        // create payload
        String testReviewBody = "test review body";
        Map<String, String> payload = new HashMap<>();
        payload.put("imdbId", imdbId);
        payload.put("reviewBody", testReviewBody);
        // create review
        Review actualReview = client.post()
                .uri(uri)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(payload), Map.class)
                .exchange()
                .expectStatus()
                .isCreated()
                .expectBody(Review.class)
                .returnResult()
                .getResponseBody();
        assertThat(actualReview).isNotNull();
        assertThat(actualReview.getBody()).isEqualTo(testReviewBody);
    }
}
