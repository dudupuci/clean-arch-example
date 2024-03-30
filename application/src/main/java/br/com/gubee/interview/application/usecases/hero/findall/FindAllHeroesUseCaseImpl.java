package br.com.gubee.interview.application.usecases.hero.findall;

import br.com.gubee.interview.domain.entities.hero.Hero;
import br.com.gubee.interview.domain.entities.hero.HeroGateway;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class FindAllHeroesUseCaseImpl extends FindAllHeroesUseCase {
    private final HeroGateway gateway;

    public FindAllHeroesUseCaseImpl(final HeroGateway gateway) {
        this.gateway = Objects.requireNonNull(gateway);
    }

    @Override
    public List<Hero> execute(final FindAllHeroesCommand anIn) {
        return this.gateway.findAll(anIn.name());
    }
}
