package br.com.gubee.interview.application.usecases.hero.update;

import br.com.gubee.interview.domain.entities.hero.Hero;
import br.com.gubee.interview.domain.entities.hero.HeroRepository;
import br.com.gubee.interview.domain.exceptions.HeroNotFoundException;

import java.util.Objects;
import java.util.Optional;


public class UpdateHeroUseCaseImpl extends UpdateHeroUseCase {

    private final HeroRepository gateway;

    public UpdateHeroUseCaseImpl(final HeroRepository gateway) {
        this.gateway = Objects.requireNonNull(gateway);
    }

    @Override
    public void execute(final UpdateHeroCommand anIn) {
        Optional<Hero> heroOptional = this.gateway.findById(anIn.id());

        if (heroOptional.isEmpty()) {
            throw new HeroNotFoundException("Hero with id " + anIn.id() + " has not found");
        }

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
    }
}
