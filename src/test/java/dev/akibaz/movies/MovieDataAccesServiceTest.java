package dev.akibaz.movies;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

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
}