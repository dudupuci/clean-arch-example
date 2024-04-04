package br.com.gubee.interview.infrastructure.persistence.facade.impl;

import br.com.gubee.interview.application.usecases.hero.comparision.ComparisionCommand;
import br.com.gubee.interview.application.usecases.hero.comparision.ComparisionOutput;
import br.com.gubee.interview.application.usecases.hero.comparision.ComparisionUseCase;
import br.com.gubee.interview.application.usecases.hero.create.CreateHeroCommand;
import br.com.gubee.interview.application.usecases.hero.create.CreateHeroOutput;
import br.com.gubee.interview.application.usecases.hero.create.CreateHeroUseCase;
import br.com.gubee.interview.application.usecases.hero.delete.DeleteHeroCommand;
import br.com.gubee.interview.application.usecases.hero.delete.DeleteHeroUseCase;
import br.com.gubee.interview.application.usecases.hero.findall.FindAllHeroesCommand;
import br.com.gubee.interview.application.usecases.hero.findall.FindAllHeroesOutput;
import br.com.gubee.interview.application.usecases.hero.findall.FindAllHeroesUseCase;
import br.com.gubee.interview.application.usecases.hero.findbyid.FindHeroByIdCommand;
import br.com.gubee.interview.application.usecases.hero.findbyid.FindHeroByIdOutput;
import br.com.gubee.interview.application.usecases.hero.findbyid.FindHeroByIdUseCase;
import br.com.gubee.interview.application.usecases.hero.update.UpdateHeroCommand;
import br.com.gubee.interview.application.usecases.hero.update.UpdateHeroUseCase;
import br.com.gubee.interview.infrastructure.persistence.facade.HeroFacade;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HeroFacadeImpl implements HeroFacade {

    private final CreateHeroUseCase createHeroUseCase;
    private final FindHeroByIdUseCase findHeroByIdUseCase;
    private final FindAllHeroesUseCase findAllHeroesUseCase;
    private final UpdateHeroUseCase updateHeroUseCase;
    private final DeleteHeroUseCase deleteHeroUseCase;
    private final ComparisionUseCase comparisionUseCase;

    public HeroFacadeImpl(
            final CreateHeroUseCase createHeroUseCase,
            final FindHeroByIdUseCase findHeroByIdUseCase,
            final FindAllHeroesUseCase findAllHeroesUseCase,
            final UpdateHeroUseCase updateHeroUseCase,
            final DeleteHeroUseCase deleteHeroUseCase,
            final ComparisionUseCase comparisionUseCase
    ) {
        this.createHeroUseCase = createHeroUseCase;
        this.findHeroByIdUseCase = findHeroByIdUseCase;
        this.findAllHeroesUseCase = findAllHeroesUseCase;
        this.updateHeroUseCase = updateHeroUseCase;
        this.deleteHeroUseCase = deleteHeroUseCase;
        this.comparisionUseCase = comparisionUseCase;
    }

    @Override
    public CreateHeroOutput create(final CreateHeroCommand command) {
        return this.createHeroUseCase.execute(command);
    }

    @Override
    public FindHeroByIdOutput findById(final FindHeroByIdCommand command) {
        return this.findHeroByIdUseCase.execute(command);
    }

    @Override
    public List<FindAllHeroesOutput> findAll(final FindAllHeroesCommand command) {
        return this.findAllHeroesUseCase.execute(command);
    }

    @Override
    public void update(final UpdateHeroCommand command) {
        this.updateHeroUseCase.execute(command);
    }

    @Override
    public void delete(final DeleteHeroCommand command) {
        this.deleteHeroUseCase.execute(command);
    }

    @Override
    public ComparisionOutput compare(final ComparisionCommand command) {
        return this.comparisionUseCase.execute(command);
    }
}
