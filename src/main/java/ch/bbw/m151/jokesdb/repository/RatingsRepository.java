package ch.bbw.m151.jokesdb.repository;

import ch.bbw.m151.jokesdb.datamodel.RatingsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface RatingsRepository extends JpaRepository<RatingsEntity, Integer> {

}
