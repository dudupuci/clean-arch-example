package br.com.gubee.interview.application.usecases.hero.create;

import br.com.gubee.interview.domain.entities.hero.Hero;
import br.com.gubee.interview.domain.entities.hero.HeroRepository;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CreateHeroUseCaseImpl extends CreateHeroUseCase{
    private final HeroRepository gateway;

    public CreateHeroUseCaseImpl(final HeroRepository gateway) {
        this.gateway = Objects.requireNonNull(gateway, "Heroe's gateway must be not null.");
    }

    @Override
    public Hero execute(final CreateHeroCommand anIn) {
        final var hero = Hero.instantiate(
                anIn.name(),
                anIn.race(),
                anIn.powerStats()
        );
        // checar criacao de powerStats

        hero.validate();
        this.gateway.save(hero);
        return hero;
    }
}
