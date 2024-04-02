package br.com.gubee.interview.application.usecases.hero.findbyid;

import br.com.gubee.interview.domain.entities.hero.Hero;
import br.com.gubee.interview.domain.entities.hero.HeroRepository;
import br.com.gubee.interview.domain.exceptions.HeroNotFoundException;

import java.util.Objects;
import java.util.Optional;


public class FindHeroByIdUseCaseImpl extends FindHeroByIdUseCase {
    private final HeroRepository gateway;

    public FindHeroByIdUseCaseImpl(final HeroRepository gateway) {
        this.gateway = Objects.requireNonNull(gateway);
    }

    @Override
    public FindHeroByIdOutput execute(final FindHeroByIdCommand anIn) {
        Optional<Hero> heroOptional = this.gateway.findById(anIn.id());

        if (heroOptional.isEmpty()) {
            throw new HeroNotFoundException("Hero with id " + anIn.id() + " has not found");
        }

        System.out.println(heroOptional.get());
        var obj = heroOptional.get();
        return new FindHeroByIdOutput(
                obj.getName(),
                obj.getRace(),
                obj.getPowerStats(),
                obj.getEnabled()
        );
    }
}
