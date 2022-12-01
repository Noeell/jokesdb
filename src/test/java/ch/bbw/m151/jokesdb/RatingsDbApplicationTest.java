package ch.bbw.m151.jokesdb;

import ch.bbw.m151.jokesdb.datamodel.JokesEntity;
import ch.bbw.m151.jokesdb.datamodel.RatingsEntity;
import ch.bbw.m151.jokesdb.repository.RatingsRepository;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class RatingsDbApplicationTest implements WithAssertions {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    RatingsRepository ratingsRepository;

    @Test
    void ratingsCanBeCreatedViaHttp() {
        RatingsEntity ratingsEntity = new RatingsEntity();
        ratingsEntity.setRating(2);

        JokesEntity jokesEntity = new JokesEntity();
        jokesEntity.setId(1);
        ratingsEntity.setJokesId(jokesEntity);

        webTestClient.post()
                .uri("/saverating/{rating}/{jokeid}", ratingsEntity.getRating(), jokesEntity.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(ratingsEntity))
                .exchange()
                .expectStatus().isCreated();
    }

    @Test
    void ratingsCanBeSearchedViaHttp() {
        RatingsEntity ratingsEntity = new RatingsEntity();
        ratingsEntity.setRating(2);

        JokesEntity jokesEntity = new JokesEntity();
        jokesEntity.setId(1);
        ratingsEntity.setJokesId(jokesEntity);

        ratingsRepository.save(ratingsEntity);

        webTestClient.get()
                .uri("/loadrating/{jokeid}", jokesEntity.getId())
                .header(HttpHeaders.ACCEPT, "application/json")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Integer.class)
                .value(slice -> {
                    assertThat(slice).isEqualTo(ratingsEntity.getRating());
                });

    }
}
