package br.com.gubee.interview.application.usecases.hero.create;

import br.com.gubee.interview.domain.entities.hero.Hero;
import br.com.gubee.interview.domain.entities.hero.HeroRepository;
import br.com.gubee.interview.domain.entities.powerstats.PowerStats;
import br.com.gubee.interview.domain.exceptions.HeroNameAlreadyExistsException;
import br.com.gubee.interview.domain.exceptions.HeroNotFoundException;
import br.com.gubee.interview.domain.exceptions.NullOrInvalidDataEnteredException;
import jakarta.validation.constraints.Null;
import org.springframework.dao.DuplicateKeyException;

import java.util.Objects;


public class CreateHeroUseCaseImpl extends CreateHeroUseCase{
    private final HeroRepository gateway;

    public CreateHeroUseCaseImpl(final HeroRepository repository) {
        this.gateway = Objects.requireNonNull(repository, "Heroe's repository must be not null.");
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

            var savedHero = this.gateway.save(hero);

            return CreateHeroOutput.with(
                    savedHero.getId().getValue(),
                    savedHero.getName(),
                    savedHero.getRace(),
                    savedHero.getPowerStats(),
                    savedHero.getEnabled()
            );

        } catch (DuplicateKeyException err) {
            throw new HeroNameAlreadyExistsException("Hero with name "+anIn.name()+ " already exists on database.");
        } catch (NullPointerException err) {
            throw new NullOrInvalidDataEnteredException("Check the data entered"+err.getMessage());
        }
    }
}
