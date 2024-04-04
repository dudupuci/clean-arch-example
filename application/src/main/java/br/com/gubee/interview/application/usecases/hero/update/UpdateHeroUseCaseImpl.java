package br.com.gubee.interview.application.usecases.hero.update;

import br.com.gubee.interview.domain.entities.hero.Hero;
import br.com.gubee.interview.domain.entities.hero.HeroRepository;
import br.com.gubee.interview.domain.exceptions.HeroNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.Objects;
import java.util.Optional;


public class UpdateHeroUseCaseImpl extends UpdateHeroUseCase {

    private final HeroRepository gateway;

    public UpdateHeroUseCaseImpl(final HeroRepository gateway) {
        this.gateway = Objects.requireNonNull(gateway);
    }

    @Override
    public void execute(final UpdateHeroCommand anIn) {
        try {
            Optional<Hero> heroOptional = this.gateway.findById(anIn.id());

            assert heroOptional.isPresent();
            var heroToUpdate = heroOptional.get();

            var updatedHero = heroToUpdate.update(
                    anIn.name(),
                    anIn.race(),
                    anIn.enabled(),
                    anIn.strength(),
                    anIn.agility(),
                    anIn.dexterity(),
                    anIn.intelligence()
            );

            this.gateway.update(updatedHero);
        } catch (EmptyResultDataAccessException err) {
            throw new HeroNotFoundException("Hero not found");
        }
    }
}
