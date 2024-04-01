package br.com.gubee.interview.application.usecases.hero.findall;

import br.com.gubee.interview.domain.entities.hero.Hero;
import br.com.gubee.interview.domain.entities.hero.HeroRepository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


public class FindAllHeroesUseCaseImpl extends FindAllHeroesUseCase {
    private final HeroRepository gateway;

    public FindAllHeroesUseCaseImpl(final HeroRepository gateway) {
        this.gateway = Objects.requireNonNull(gateway);
    }

    @Override
    public List<FindAllHeroesOutput> execute(final FindAllHeroesCommand anIn) {
        List<Hero> heroes = this.gateway.findAll(anIn.name());
        return heroes.stream()
                .map(FindAllHeroesOutput::from)
                .collect(Collectors.toList());
    }
}
