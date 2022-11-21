package ch.bbw.m151.jokesdb;

import ch.bbw.m151.jokesdb.datamodel.JokesEntity;
import ch.bbw.m151.jokesdb.repository.JokesRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class RemoteJokesService {
    @Autowired
    private JokesRepository jokesRepository;

    @Data
    static class JokeDTO {
        boolean error;
        String category;
        String type;
        String setup;
        String delivery;
        String joke;
        Flags flags;
        int id;
        boolean safe;
        String lang;
    }

    @Data
    public static class Flags {
        boolean nsfw;
        boolean religious;
        boolean political;
        boolean racist;
        boolean sexist;
        boolean explicit;
    }

    @EventListener(ContextRefreshedEvent.class)
    public void prefillDatabase() {

        List<JokesEntity> jokesEntityList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            JokeDTO joke = getJoke();
            JokesEntity jokesEntity = new JokesEntity();
            jokesEntity.setJoke(joke.getJoke());
            jokesEntity.setCategory(joke.getCategory());
            jokesEntity.setType(joke.getType());
            jokesEntity.setSetup(joke.getSetup());
            jokesEntity.setDelivery(joke.getDelivery());
            jokesEntity.setSafe(joke.isSafe());
            jokesEntity.setLang(joke.getLang());

            jokesEntity.setExplicit(joke.getFlags().explicit);
            jokesEntity.setNsfw(joke.getFlags().nsfw);
            jokesEntity.setRacist(joke.getFlags().racist);
            jokesEntity.setReligious(joke.getFlags().religious);
            jokesEntity.setPolitical(joke.getFlags().political);
            jokesEntity.setSexist(joke.getFlags().sexist);

            var counter = 0;

            for (JokesEntity singleJoke : jokesEntityList) {
                if (Objects.equals(singleJoke.getJoke(), jokesEntity.getJoke()) && Objects.equals(singleJoke.getSetup(), jokesEntity.getSetup())) {
                    counter++;
                }
            }

            if (counter == 0) {
                jokesRepository.save(jokesEntity);
                jokesEntityList.add(jokesEntity);
            }
        }
    }

    public JokeDTO getJoke() {
        WebClient client = WebClient.create("https://v2.jokeapi.dev");

        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = client.method(HttpMethod.GET);

        WebClient.RequestBodySpec bodySpec = uriSpec.uri("/joke/dark");

        WebClient.RequestHeadersSpec headersSpec = bodySpec.body(
                BodyInserters.fromPublisher(Mono.just("data"), String.class)
        );

        Mono<JokeDTO> response = headersSpec.retrieve()
                .bodyToMono(JokeDTO.class);

        return response.block();
    }
}