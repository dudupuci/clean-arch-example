package br.com.gubee.interview.infrastructure.configuration.usecases;

import br.com.gubee.interview.application.usecases.hero.comparision.ComparisionCommand;
import br.com.gubee.interview.application.usecases.hero.create.CreateHeroCommand;
import br.com.gubee.interview.application.usecases.hero.delete.DeleteHeroCommand;
import br.com.gubee.interview.application.usecases.hero.findall.FindAllHeroesCommand;
import br.com.gubee.interview.application.usecases.hero.findbyid.FindHeroByIdCommand;
import br.com.gubee.interview.application.usecases.hero.findbyid.FindHeroByIdOutput;
import br.com.gubee.interview.application.usecases.hero.update.UpdateHeroCommand;
import br.com.gubee.interview.domain.entities.hero.Hero;
import br.com.gubee.interview.domain.valueobjects.Comparision;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface HeroFacade {
     Hero create(CreateHeroCommand command);
     FindHeroByIdOutput findById(FindHeroByIdCommand command);
     List<Hero> findAll(FindAllHeroesCommand command);
     void update(UpdateHeroCommand command);
     void delete(DeleteHeroCommand command);
     Comparision compare(ComparisionCommand command);
}
