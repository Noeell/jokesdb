package ch.bbw.m151.jokesdb.controller;

import ch.bbw.m151.jokesdb.datamodel.JokesEntity;
import ch.bbw.m151.jokesdb.service.JokesService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class JokesController {

    @Autowired
    private JokesService jokesService;

    /**
     * @param pageable to be called with params `?page=3&size=5`
     * @return hilarious content
     */
    @GetMapping("/randomjoke")
    public JokesEntity getRandomJoke() {
        return jokesService.getOneJoke();
    }
}
