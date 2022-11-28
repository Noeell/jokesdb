package ch.bbw.m151.jokesdb.controller;

import ch.bbw.m151.jokesdb.repository.RatingsRepository;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RatingsControllerTest implements WithAssertions {

    @Autowired
    private RatingsController ratingsController;

    @Autowired
    private RatingsRepository ratingsRepository;

    @AfterEach
    void tearDown() {
        ratingsRepository.deleteAll();
    }

    @Test
    void checkSaveRating() {
        ratingsController.saveOrUpdateRating(5, 1);

        assertThat(ratingsController.loadRating(1)).isEqualTo(5);
    }

    @Test
    void checkUpdateRating() {
        int jokeId = 1;
        ratingsController.saveOrUpdateRating(5, jokeId);
        ratingsController.saveOrUpdateRating(1, jokeId);

        assertThat(ratingsController.loadRating(jokeId))
                .as("Check rating from jokeId '%s'", jokeId)
                .isEqualTo(1);
    }

    @Test
    void checkLoadRatingWithUnknownJokeId() {
        assertThat(ratingsController.loadRating(1))
                .as("Check rating from unknown jokeId, should be 10 by default")
                .isEqualTo(10);
    }

}