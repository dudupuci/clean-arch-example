package br.com.gubee.interview.infrastructure;

import br.com.gubee.interview.domain.entities.hero.Hero;
import br.com.gubee.interview.domain.entities.hero.HeroGateway;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HeroesGatewayImpl implements HeroGateway {
    @Override
    public Hero create(Hero anHero) {
        return null;
    }

    @Override
    public void update(Hero hero) {

    }

    @Override
    public Optional<Hero> findById(String id) {
        return Optional.empty();
    }

    @Override
    public void deleteById(String id) {

    }

    @Override
    public List<Hero> findAll(String name) {
        return null;
    }
}
