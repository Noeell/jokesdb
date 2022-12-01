package ch.bbw.m151.jokesdb.controller;

import ch.bbw.m151.jokesdb.service.RatingsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class RatingsController {

    @Autowired
    private RatingsService ratingsService;

    @PostMapping("/saverating/{rating}/{jokeid}")
    public ResponseEntity saveOrUpdateRating(@PathVariable("rating") int rating, @PathVariable("jokeid") int jokeId) {
        ratingsService.saveOrUpdateRating(rating, jokeId);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/loadrating/{jokeid}")
    public ResponseEntity<Integer> loadRating(@PathVariable("jokeid") int jokeId) {
        return ResponseEntity.ok(ratingsService.loadRating(jokeId));
    }
}
