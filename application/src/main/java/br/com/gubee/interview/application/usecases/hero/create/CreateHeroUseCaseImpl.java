package br.com.gubee.interview.application.usecases.hero.create;

import br.com.gubee.interview.domain.entities.hero.Hero;
import br.com.gubee.interview.domain.entities.hero.HeroRepository;
import br.com.gubee.interview.domain.entities.powerstats.PowerStats;
import br.com.gubee.interview.domain.exceptions.HeroNameAlreadyExistsException;
import br.com.gubee.interview.domain.exceptions.HeroNotFoundException;

import java.util.Objects;


public class CreateHeroUseCaseImpl extends CreateHeroUseCase{
    private final HeroRepository gateway;

    public CreateHeroUseCaseImpl(final HeroRepository repository) {
        this.gateway = Objects.requireNonNull(repository, "Heroe's gateway must be not null.");
    }

    @Override
    public CreateHeroOutput execute(final CreateHeroCommand anIn) {
        try {
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

            var savedHero = this.gateway.save(hero); // verificar onde ocorre a validacao de powerstats

            return CreateHeroOutput.with(
                    savedHero.getId().getValue(),
                    savedHero.getName(),
                    savedHero.getRace(),
                    savedHero.getPowerStats(),
                    savedHero.getEnabled()
            );

        } catch (Exception err) {
            throw new HeroNameAlreadyExistsException("Hero with name "+anIn.name()+ " already exists on database.");
        }
    }
}
