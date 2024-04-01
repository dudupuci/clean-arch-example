package br.com.gubee.interview.infrastructure.configuration.usecases;

import br.com.gubee.interview.application.usecases.hero.create.CreateHeroUseCase;
import br.com.gubee.interview.application.usecases.hero.create.CreateHeroUseCaseImpl;
import br.com.gubee.interview.application.usecases.hero.findall.FindAllHeroesUseCase;
import br.com.gubee.interview.application.usecases.hero.findall.FindAllHeroesUseCaseImpl;
import br.com.gubee.interview.application.usecases.hero.findbyid.FindHeroByIdUseCase;
import br.com.gubee.interview.application.usecases.hero.findbyid.FindHeroByIdUseCaseImpl;
import br.com.gubee.interview.application.usecases.hero.update.UpdateHeroUseCase;
import br.com.gubee.interview.application.usecases.hero.update.UpdateHeroUseCaseImpl;
import br.com.gubee.interview.domain.entities.hero.HeroRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HeroUseCasesConfig {
    private final HeroRepository heroRepository;

    public HeroUseCasesConfig(final HeroRepository heroRepository) {
        this.heroRepository = heroRepository;
    }

    @Bean
    public CreateHeroUseCase createHeroUseCase() {
        return new CreateHeroUseCaseImpl(heroRepository);
    }

    @Bean
    public FindHeroByIdUseCase findHeroByIdUseCase() {
        return new FindHeroByIdUseCaseImpl(heroRepository);
    }

    @Bean
    public FindAllHeroesUseCase findAllHeroesUseCase() {
        return new FindAllHeroesUseCaseImpl(heroRepository);
    }

    @Bean
    public UpdateHeroUseCase updateHeroUseCase() {return new UpdateHeroUseCaseImpl(heroRepository); }
}
