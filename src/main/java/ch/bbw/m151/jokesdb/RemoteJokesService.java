package ch.bbw.m151.jokesdb;

import lombok.Data;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class RemoteJokesService {

    @Data
    static class JokeDTO {
        boolean error;
        String category;
        String type;
        String setup;
        String delivery;
        String joke;
        Object flags;
        int id;
        boolean safe;
        String lang;
    }

    static class Flags {
        boolean nsfw;
        boolean religious;
        boolean political;
        boolean racist;
        boolean sexist;
        boolean explicit;
    }

    public JokeDTO getJoke() {
        WebClient client = WebClient.create("https://v2.jokeapi.dev");

        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = client.method(HttpMethod.GET);

        WebClient.RequestBodySpec bodySpec = uriSpec.uri("/joke/programming");

        WebClient.RequestHeadersSpec headersSpec = bodySpec.body(
                BodyInserters.fromPublisher(Mono.just("data"), String.class)
        );

        Mono<JokeDTO> response = headersSpec.retrieve()
                .bodyToMono(JokeDTO.class);

        return response.block();
    }
}
