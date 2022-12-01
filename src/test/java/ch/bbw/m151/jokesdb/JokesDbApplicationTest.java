package ch.bbw.m151.jokesdb;

import ch.bbw.m151.jokesdb.datamodel.JokesEntity;
import ch.bbw.m151.jokesdb.repository.JokesRepository;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class JokesDbApplicationTest implements WithAssertions {

    @Autowired
    JokesRepository jokesRepository;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void jokesAreLoadedAtStartup() {
        var jokes = jokesRepository.findAll();
        assertThat(jokes).hasSizeGreaterThan(20);
    }

    @Test
    void jokesCanBeRetrievedViaHttpGet() {
        webTestClient.get()
                .uri("/randomjoke")
                .header(HttpHeaders.ACCEPT, "application/json")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(JokesEntity.class)
                .hasSize(1);
    }
}
