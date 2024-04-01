package br.com.gubee.interview.infrastructure.configuration.usecases.impl;

import br.com.gubee.interview.application.usecases.hero.create.CreateHeroCommand;
import br.com.gubee.interview.application.usecases.hero.create.CreateHeroUseCase;
import br.com.gubee.interview.application.usecases.hero.findall.FindAllHeroesCommand;
import br.com.gubee.interview.application.usecases.hero.findall.FindAllHeroesUseCase;
import br.com.gubee.interview.application.usecases.hero.findbyid.FindHeroByIdCommand;
import br.com.gubee.interview.application.usecases.hero.findbyid.FindHeroByIdOutput;
import br.com.gubee.interview.application.usecases.hero.findbyid.FindHeroByIdUseCase;
import br.com.gubee.interview.application.usecases.hero.update.UpdateHeroCommand;
import br.com.gubee.interview.application.usecases.hero.update.UpdateHeroUseCase;
import br.com.gubee.interview.domain.entities.hero.Hero;
import br.com.gubee.interview.infrastructure.configuration.usecases.HeroFacade;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HeroFacadeImpl implements HeroFacade {

    private final CreateHeroUseCase createHeroUseCase;
    private final FindHeroByIdUseCase findHeroByIdUseCase;
    private final FindAllHeroesUseCase findAllHeroesUseCase;
    private final UpdateHeroUseCase updateHeroUseCase;

    public HeroFacadeImpl(
            final CreateHeroUseCase createHeroUseCase,
            final FindHeroByIdUseCase findHeroByIdUseCase,
            final FindAllHeroesUseCase findAllHeroesUseCase,
            final UpdateHeroUseCase updateHeroUseCase
    ) {
        this.createHeroUseCase = createHeroUseCase;
        this.findHeroByIdUseCase = findHeroByIdUseCase;
        this.findAllHeroesUseCase = findAllHeroesUseCase;
        this.updateHeroUseCase = updateHeroUseCase;
    }

    @Override
    public Hero create(final CreateHeroCommand command) {
        return this.createHeroUseCase.execute(command);
    }

    @Override
    public FindHeroByIdOutput findById(final FindHeroByIdCommand command) {
        return this.findHeroByIdUseCase.execute(command);
    }

    @Override
    public List<Hero> findAll(final FindAllHeroesCommand command) {
        return this.findAllHeroesUseCase.execute(command);
    }

    @Override
    public void update(final UpdateHeroCommand command) {
        this.updateHeroUseCase.execute(command);
    }
}
