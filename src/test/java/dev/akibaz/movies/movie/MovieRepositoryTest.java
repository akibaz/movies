package dev.akibaz.movies.movie;

import dev.akibaz.movies.AbstractTestcontainersUnitTest;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
class MovieRepositoryTest extends AbstractTestcontainersUnitTest {
    @Autowired
    private MovieRepository underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }

    @Test
    void findMovieByImdbId() {
        // Given
        String imdbId = "tt3915174";
        Movie movie = new Movie(
                new ObjectId("66604a2a27461237f2a34c08"),
                imdbId,
                "Puss in Boots: The Last Wish",
                "2022-12-21",
                "https://www.youtube.com/watch?v=tHb7WlgyaUc",
                List.of("Action", "Adventure", "Comedy", "Family"),
                "https://image.tmdb.org/t/p/w500/1NqwE6LP9IEdOZ57NCT51ftHtWT.jpg",
                List.of("https://image.tmdb.org/t/p/original/r9PkFnRUIthgBp2JZZzD380MWZy.jpg",
                        "https://image.tmdb.org/t/p/original/faXT8V80JRhnArTAeYXz0Eutpv9.jpg",
                        "https://image.tmdb.org/t/p/original/pdrlEaknhta2wvE2dcD8XDEbAI4.jpg",
                        "https://image.tmdb.org/t/p/original/tGwO4xcBjhXC0p5qlkw37TrH6S6.jpg",
                        "https://image.tmdb.org/t/p/original/cP8YNG3XUeBmO8Jk7Skzq3vwHy1.jpg",
                        "https://image.tmdb.org/t/p/original/qLE8yuieTDN93WNJRmFSAEJChOg.jpg",
                        "https://image.tmdb.org/t/p/original/vNuHqmOJRQXY0PBd887DklSDlBP.jpg",
                        "https://image.tmdb.org/t/p/original/uUCc62M0I3lpZy0SiydbBmUIpNi.jpg",
                        "https://image.tmdb.org/t/p/original/2wPJIFrBhzzAP8oHDOlShMkERH6.jpg",
                        "https://image.tmdb.org/t/p/original/fnfirCEDIkxZnQEtEMMSgllm0KZ.jpg"),
                new ArrayList<>()
        );
        underTest.save(movie);

        // When
        Optional<Movie> actual = underTest.findMovieByImdbId(imdbId);

        //Then
        assertThat(actual).isPresent();
        assertThat(actual.get().getImdbId()).isEqualTo(imdbId);
        assertThat(actual.get()).isEqualTo(movie);
    }

    @Test
    void findMovieByImdbIdReturnsEmptyOptionalWhenMovieNotFound() {
        // Given
        String imdbId = "tt3915174";
        Movie movie = new Movie(
                new ObjectId("66604a2a27461237f2a34c08"),
                imdbId,
                "Puss in Boots: The Last Wish",
                "2022-12-21",
                "https://www.youtube.com/watch?v=tHb7WlgyaUc",
                List.of("Action", "Adventure", "Comedy", "Family"),
                "https://image.tmdb.org/t/p/w500/1NqwE6LP9IEdOZ57NCT51ftHtWT.jpg",
                List.of("https://image.tmdb.org/t/p/original/r9PkFnRUIthgBp2JZZzD380MWZy.jpg",
                        "https://image.tmdb.org/t/p/original/faXT8V80JRhnArTAeYXz0Eutpv9.jpg",
                        "https://image.tmdb.org/t/p/original/pdrlEaknhta2wvE2dcD8XDEbAI4.jpg",
                        "https://image.tmdb.org/t/p/original/tGwO4xcBjhXC0p5qlkw37TrH6S6.jpg",
                        "https://image.tmdb.org/t/p/original/cP8YNG3XUeBmO8Jk7Skzq3vwHy1.jpg",
                        "https://image.tmdb.org/t/p/original/qLE8yuieTDN93WNJRmFSAEJChOg.jpg",
                        "https://image.tmdb.org/t/p/original/vNuHqmOJRQXY0PBd887DklSDlBP.jpg",
                        "https://image.tmdb.org/t/p/original/uUCc62M0I3lpZy0SiydbBmUIpNi.jpg",
                        "https://image.tmdb.org/t/p/original/2wPJIFrBhzzAP8oHDOlShMkERH6.jpg",
                        "https://image.tmdb.org/t/p/original/fnfirCEDIkxZnQEtEMMSgllm0KZ.jpg"),
                new ArrayList<>()
        );
        underTest.save(movie);

        // When
        Optional<Movie> actual = underTest.findMovieByImdbId("imdbId");

        //Then
        assertThat(actual).isNotPresent();
    }
}