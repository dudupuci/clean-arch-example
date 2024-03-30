package br.com.gubee.interview.application.usecases.hero.update;

import br.com.gubee.interview.domain.entities.hero.Hero;
import br.com.gubee.interview.domain.entities.hero.HeroGateway;
import br.com.gubee.interview.domain.exceptions.HeroNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
public class UpdateHeroUseCaseImpl extends UpdateHeroUseCase {

    private final HeroGateway gateway;

    public UpdateHeroUseCaseImpl(final HeroGateway gateway) {
        this.gateway = Objects.requireNonNull(gateway);
    }

    @Override
    public void execute(final UpdateHeroCommand anIn) {
        Optional<Hero> heroOptional = this.gateway.findById(anIn.id());

        if (heroOptional.isEmpty()) {
            throw new HeroNotFoundException("Hero with id " + anIn.id() + " has not found");
        }

        var heroToUpdate = heroOptional.get();

        heroToUpdate.update(
                anIn.race(),
                anIn.powerStats(),
                anIn.enabled()
        );

        this.gateway.update(heroToUpdate);
    }
}
