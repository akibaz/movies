package dev.akibaz.movies;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

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
}