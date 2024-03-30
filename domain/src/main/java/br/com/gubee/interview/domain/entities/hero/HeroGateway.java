package br.com.gubee.interview.domain.entities.hero;

import java.util.List;
import java.util.Optional;

public interface HeroGateway {
    Hero create(Hero anHero);
    void update(Hero hero);
    Optional<Hero> findById(String id);
    void deleteById(String id);
    List<Hero> findAll(String name);
}
