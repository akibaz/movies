package dev.akibaz.movies;

import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
public class TestcontainersUnitTest extends AbstractTestcontainersUnitTest {

    @Test
    void canStartDb() {
        assertThat(mongoDBContainer.isCreated()).isTrue();
        assertThat(mongoDBContainer.isRunning()).isTrue();
    }
}
