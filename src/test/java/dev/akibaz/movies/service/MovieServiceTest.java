package dev.akibaz.movies.service;

import dev.akibaz.movies.dao.MovieDAO;
import dev.akibaz.movies.exception.ResourceNotFoundException;
import dev.akibaz.movies.model.Movie;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MovieServiceTest {
    private MovieService underTest;
    @Mock
    private MovieDAO movieDAO;

    @BeforeEach
    void setUp() {
        underTest = new MovieService(movieDAO);
    }

    @Test
    void getALlMovies() {
        // Given

        // When
        underTest.getALlMovies();

        //Then
        verify(movieDAO).selectAllMovies();
    }

    @Test
    void getMovieById() {
        // Given
        ObjectId movieId = new ObjectId();
        when(movieDAO.selectMovieById(movieId)).thenReturn(Optional.of(new Movie()));
        // When
        Movie actual = underTest.getMovieById(movieId);

        //Then
        verify(movieDAO).selectMovieById(movieId);
        assertThat(actual).isNotNull();
    }

    @Test
    void getMovieByIdThrowsResourceNotFoundExceptionWhenMovieDoesNotExistById() {
        // Given
        ObjectId movieId = ObjectId.get();
        when(movieDAO.selectMovieById(movieId)).thenReturn(Optional.empty());
        // When

        //Then
        assertThatThrownBy(() -> underTest.getMovieById(movieId))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Movie with id: %s not found.".formatted(movieId));
    }

    @Test
    void getMovieByImdbId() {
        // Given
        String imdbId = "imdbId";
        when(movieDAO.selectMovieByImdbId(imdbId)).thenReturn(Optional.of(new Movie()));

        // When
        underTest.getMovieByImdbId(imdbId);

        //Then
        verify(movieDAO).selectMovieByImdbId(imdbId);
    }

    @Test
    void getMovieByImdbIdThrowsResourceNotFoundExceptionWhenMovieDoesNotExistByImdbId() {
        // Given
        String imdbId = "imdbId";
        when(movieDAO.selectMovieByImdbId(imdbId)).thenReturn(Optional.empty());

        // When

        //Then
        assertThatThrownBy(() -> underTest.getMovieByImdbId(imdbId))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Movie with imdbId: %s not found.".formatted(imdbId));
    }
}