package ch.bbw.m151.jokesdb.service;

import ch.bbw.m151.jokesdb.datamodel.JokesEntity;
import ch.bbw.m151.jokesdb.datamodel.RatingsEntity;
import ch.bbw.m151.jokesdb.repository.RatingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingsService {

    @Autowired
    private RatingsRepository ratingsRepository;

    public void saveOrUpdateRating(int rating, int jokeId) {
        RatingsEntity ratingsEntity = new RatingsEntity();
        ratingsEntity.setRating(rating);
        ratingsEntity.setJokesId(new JokesEntity().setId(jokeId));

        List<RatingsEntity> ratingsEntities = ratingsRepository.findAll();

        for (RatingsEntity singleRatingsEntity :
                ratingsEntities) {
            if (singleRatingsEntity.getJokesId().getId() == jokeId) {
                ratingsEntity.setRating(rating);
                ratingsEntity.setId(singleRatingsEntity.getId());
                ratingsEntity.setCreatedOn(singleRatingsEntity.getCreatedOn());
            }
        }
        ratingsRepository.save(ratingsEntity);
    }


    public int loadRating(int jokeId) {
        JokesEntity jokesEntity = new JokesEntity();
        List<RatingsEntity> ratingsEntities = ratingsRepository.findAll();
        RatingsEntity foundEntry = new RatingsEntity();
        foundEntry.setRating(10);
        foundEntry.setJokesId(jokesEntity);
        for (RatingsEntity rating :
                ratingsEntities) {
            if (rating.getJokesId().getId() == jokeId) {
                foundEntry = rating;
            }
        }

        return foundEntry.getRating();
    }
}
