package ch.bbw.m151.jokesdb.service;

import ch.bbw.m151.jokesdb.datamodel.RatingsEntity;
import ch.bbw.m151.jokesdb.repository.RatingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingsService {

    @Autowired
    private RatingsRepository ratingsRepository;

    public void saveRating(int rating){
        RatingsEntity ratingsEntity = new RatingsEntity();
        ratingsEntity.setRating(rating);

        ratingsRepository.save(ratingsEntity);
    }
}
