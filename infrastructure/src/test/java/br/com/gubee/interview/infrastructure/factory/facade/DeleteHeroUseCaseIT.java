package br.com.gubee.interview.infrastructure.factory.facade;

import br.com.gubee.interview.application.usecases.hero.create.CreateHeroCommand;
import br.com.gubee.interview.application.usecases.hero.create.CreateHeroUseCaseImpl;
import br.com.gubee.interview.application.usecases.hero.delete.DeleteHeroCommand;
import br.com.gubee.interview.application.usecases.hero.delete.DeleteHeroUseCase;
import br.com.gubee.interview.application.usecases.hero.delete.DeleteHeroUseCaseImpl;
import br.com.gubee.interview.domain.entities.hero.HeroRepository;
import br.com.gubee.interview.infrastructure.factory.TestUtils;
import br.com.gubee.interview.infrastructure.persistence.facade.HeroFacade;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class DeleteHeroUseCaseIT {

    @Mock
    private HeroFacade heroFacade;

    @Mock
    private HeroRepository repository;

    @InjectMocks
    private CreateHeroUseCaseImpl createHeroUseCase;


    @Test
    public void test() {
        var heroOutput = createHeroUseCase.execute(TestUtils.generateHeroCommand());
        assertNotNull(heroOutput);

        when(heroFacade.create(any(CreateHeroCommand.class))).thenReturn(heroOutput);

        verify(repository, times(1)).save(any());

        this.repository.findById(heroOutput.id());

        verify(repository, times(1)).findById(heroOutput.id());

        this.repository.deleteById(heroOutput.id());

        verify(repository, times(1)).deleteById(heroOutput.id());
    }
}
