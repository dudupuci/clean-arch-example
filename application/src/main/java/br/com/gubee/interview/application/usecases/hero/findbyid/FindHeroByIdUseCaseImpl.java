package br.com.gubee.interview.application.usecases.hero.findbyid;

import br.com.gubee.interview.domain.entities.hero.Hero;
import br.com.gubee.interview.domain.entities.hero.HeroGateway;
import br.com.gubee.interview.domain.exceptions.HeroNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
public class FindHeroByIdUseCaseImpl extends FindHeroByIdUseCase {
    private final HeroGateway gateway;

    public FindHeroByIdUseCaseImpl(final HeroGateway gateway) {
        this.gateway = Objects.requireNonNull(gateway);
    }

    @Override
    public Hero execute(final FindHeroByIdCommand anIn) {
        Optional<Hero> heroOptional = this.gateway.findById(anIn.id());

        if (heroOptional.isEmpty()) {
            throw new HeroNotFoundException("Hero with id " + anIn.id() + " has not found");
        }
        return heroOptional.get();
    }
}
