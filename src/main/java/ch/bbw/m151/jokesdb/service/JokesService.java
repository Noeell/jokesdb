package ch.bbw.m151.jokesdb.service;

import ch.bbw.m151.jokesdb.datamodel.JokesEntity;
import ch.bbw.m151.jokesdb.repository.JokesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JokesService {

    private static final Logger log = LoggerFactory.getLogger(JokesService.class);

    @Autowired
    private JokesRepository jokesRepository;


    public JokesEntity getOneJoke() {
        List<JokesEntity> jokesEntities = jokesRepository.findAll();
        int randomEntity = (int) (Math.random() * jokesEntities.size());

        return jokesEntities.get(randomEntity);
    }

    @EventListener(ContextRefreshedEvent.class)
    public void preloadDatabase() {
      /*  if (jokesRepository.count() != 0) {
            log.info("database already contains data...");
            return;
        }
        log.info("will load jokes from classpath...");
        try (var lineStream = Files.lines(new ClassPathResource("chucknorris.txt").getFile()
                .toPath(), StandardCharsets.UTF_8)) {
            var jokes = lineStream.filter(x -> !x.isEmpty())
                    .map(x -> new JokesEntity().setJoke(x))
                    .toList();
            jokesRepository.saveAll(jokes);
        } catch (IOException e) {
            throw new RuntimeException("failed reading jokes from classpath", e);
        }*/
    }
}
