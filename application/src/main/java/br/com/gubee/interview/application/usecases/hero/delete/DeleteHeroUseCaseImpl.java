package br.com.gubee.interview.application.usecases.hero.delete;

import br.com.gubee.interview.domain.entities.hero.Hero;
import br.com.gubee.interview.domain.entities.hero.HeroRepository;
import br.com.gubee.interview.domain.exceptions.HeroNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.Objects;
import java.util.Optional;


public class DeleteHeroUseCaseImpl extends DeleteHeroUseCase {

    private final HeroRepository gateway;

    public DeleteHeroUseCaseImpl(final HeroRepository gateway) {
        this.gateway = Objects.requireNonNull(gateway);
    }

    @Override
    public void execute(final DeleteHeroCommand anIn) {
        try {
            Optional<Hero> heroOptional = this.gateway.findById(anIn.id());
            assert heroOptional.isPresent();
            this.gateway.deleteById(heroOptional.get().getId().getValue());
        } catch (EmptyResultDataAccessException err) {
            throw new HeroNotFoundException("Hero not found");
        }
    }
}
