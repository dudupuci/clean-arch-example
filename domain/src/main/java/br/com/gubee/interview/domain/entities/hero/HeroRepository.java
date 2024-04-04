package br.com.gubee.interview.domain.entities.hero;

import java.util.List;
import java.util.Optional;

public interface HeroRepository {
    Hero save(Hero anHero);
    void update(Hero anHero);
    Optional<Hero> findById(String id);
    void deleteById(String id);
    List<Hero> findAll(String name);
    List<Hero> findAll();
}
