package ch.bbw.m151.jokesdb.controller;

import ch.bbw.m151.jokesdb.datamodel.JokesEntity;
import ch.bbw.m151.jokesdb.repository.JokesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class JokesController {

    private final JokesRepository jokesRepository;

    /**
     * @param pageable to be called with params `?page=3&size=5`
     * @return hilarious content
     */
    @GetMapping("/jokes")
    public List<JokesEntity> getJokes(Pageable pageable) {
        return jokesRepository.findAll(pageable)
                .getContent();
    }
}
