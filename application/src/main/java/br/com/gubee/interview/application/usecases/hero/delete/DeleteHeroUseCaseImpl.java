package br.com.gubee.interview.application.usecases.hero.delete;

import br.com.gubee.interview.domain.entities.hero.Hero;
import br.com.gubee.interview.domain.entities.hero.HeroRepository;
import br.com.gubee.interview.domain.exceptions.HeroNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
public class DeleteHeroUseCaseImpl extends DeleteHeroUseCase {

    private final HeroRepository gateway;

    public DeleteHeroUseCaseImpl(final HeroRepository gateway) {
        this.gateway = Objects.requireNonNull(gateway);
    }

    @Override
    public void execute(final DeleteHeroCommand anIn) {
        Optional<Hero> heroOptional = this.gateway.findById(anIn.id());

        if (heroOptional.isEmpty()) {
            throw new HeroNotFoundException("Hero with id " + anIn.id() + " has not found");
        }

        this.gateway.deleteById(anIn.id());
    }
}
