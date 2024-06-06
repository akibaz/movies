package dev.akibaz.movies.movie;

import dev.akibaz.movies.exception.ResourceNotFoundException;
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
    void getMovieByIdThrowsResourceNotFoundExceptionWhenMovieDoesNotExist() {
        // Given
        ObjectId movieId = ObjectId.get();
        when(movieDAO.selectMovieById(movieId)).thenReturn(Optional.empty());
        // When

        //Then
        assertThatThrownBy(() -> underTest.getMovieById(movieId))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Movie with id: %s not found.".formatted(movieId));
    }

}