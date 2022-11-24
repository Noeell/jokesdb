package ch.bbw.m151.jokesdb.controller;

import ch.bbw.m151.jokesdb.service.RatingsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class RatingsController {

    @Autowired
    private RatingsService ratingsService;

    @PostMapping("/saverating/{rating}")
    public void saveRating(@PathVariable("rating") int rating){
        ratingsService.saveRating(rating);
    }
}
