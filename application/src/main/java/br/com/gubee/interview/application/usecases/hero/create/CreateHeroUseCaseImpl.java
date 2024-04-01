package br.com.gubee.interview.application.usecases.hero.create;

import br.com.gubee.interview.domain.entities.hero.Hero;
import br.com.gubee.interview.domain.entities.hero.HeroRepository;
import br.com.gubee.interview.domain.entities.powerstats.PowerStats;

import java.util.Objects;


public class CreateHeroUseCaseImpl extends CreateHeroUseCase{
    private final HeroRepository gateway;

    public CreateHeroUseCaseImpl(final HeroRepository repository) {
        this.gateway = Objects.requireNonNull(repository, "Heroe's gateway must be not null.");
    }

    @Override
    public Hero execute(final CreateHeroCommand anIn) {
        final var powerStats = PowerStats.instantiate(
                anIn.powerStats().getStrength(),
                anIn.powerStats().getAgility(),
                anIn.powerStats().getDexterity(),
                anIn.powerStats().getIntelligence()
        );

        final var hero = Hero.instantiate(
                anIn.name(),
                anIn.race(),
                powerStats
        );

        hero.validate();
        return this.gateway.save(hero);
    }
}
