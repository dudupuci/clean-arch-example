package br.com.gubee.interview.infrastructure.factory.facade;

import br.com.gubee.interview.application.usecases.hero.create.CreateHeroCommand;
import br.com.gubee.interview.application.usecases.hero.create.CreateHeroUseCaseImpl;
import br.com.gubee.interview.application.usecases.hero.update.UpdateHeroCommand;
import br.com.gubee.interview.application.usecases.hero.update.UpdateHeroUseCase;
import br.com.gubee.interview.application.usecases.hero.update.UpdateHeroUseCaseImpl;
import br.com.gubee.interview.domain.entities.hero.HeroRepository;
import br.com.gubee.interview.infrastructure.factory.TestUtils;
import br.com.gubee.interview.infrastructure.persistence.facade.HeroFacade;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UpdateUseCaseIT {

    @Mock
    private HeroFacade heroFacade;

    @Mock
    private HeroRepository repository;

    @InjectMocks
    private UpdateHeroUseCaseImpl updateHeroUseCase;

    @Test
    public void test() {
        var hero = TestUtils.generateHeroInstanceFullArgs();
        updateHeroUseCase.execute(TestUtils.generateUpdateCommand(hero));

        verify(heroFacade).update(any(UpdateHeroCommand.class));

        verify(repository, times(1)).save(any());

        var heroId = hero.getId().getValue();

        this.repository.findById(hero.getId().getValue());

        verify(repository, times(1)).findById(heroId);

        this.repository.deleteById(heroId);

        verify(repository, times(1)).deleteById(heroId);
    }
}
