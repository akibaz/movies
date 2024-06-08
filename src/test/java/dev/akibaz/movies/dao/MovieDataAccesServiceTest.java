package dev.akibaz.movies.dao;

import dev.akibaz.movies.model.Movie;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MovieDataAccesServiceTest {
    private MovieDataAccesService underTest;
    @Mock
    private MovieRepository movieRepository;

    @BeforeEach
    void setUp() {
        underTest = new MovieDataAccesService(movieRepository);
    }

    @Test
    void selectAllMovies() {
        // Given

        // When
        underTest.selectAllMovies();

        //Then
        verify(movieRepository).findAll();
    }

    @Test
    void selectMovieById() {
        // Given
        ObjectId movieId = new ObjectId();
        when(movieRepository.findById(movieId)).thenReturn(Optional.of(new Movie()));
        // When
        Optional<Movie> actual = underTest.selectMovieById(movieId);

        //Then
        verify(movieRepository).findById(movieId);
        assertThat(actual).isPresent();
    }

    @Test
    void selectMovieByIdReturnsEmptyOptionalWhenMovieNotPresent() {
        // Given
        ObjectId movieId = new ObjectId();
        when(movieRepository.findById(movieId)).thenReturn(Optional.ofNullable(null));
        // When
        Optional<Movie> actual = underTest.selectMovieById(movieId);

        //Then
        verify(movieRepository).findById(movieId);
        assertThat(actual).isNotPresent();
    }

    @Test
    void selectMovieByImdbId() {
        // Given
        String imdbId = "imdbId";
        // When
        underTest.selectMovieByImdbId(imdbId);

        //Then
        verify(movieRepository).findMovieByImdbId(imdbId);
    }
}