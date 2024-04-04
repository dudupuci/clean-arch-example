package br.com.gubee.interview.infrastructure.persistence.facade;

import br.com.gubee.interview.application.usecases.hero.comparision.ComparisionCommand;
import br.com.gubee.interview.application.usecases.hero.comparision.ComparisionOutput;
import br.com.gubee.interview.application.usecases.hero.create.CreateHeroCommand;
import br.com.gubee.interview.application.usecases.hero.create.CreateHeroOutput;
import br.com.gubee.interview.application.usecases.hero.delete.DeleteHeroCommand;
import br.com.gubee.interview.application.usecases.hero.findall.FindAllHeroesCommand;
import br.com.gubee.interview.application.usecases.hero.findall.FindAllHeroesOutput;
import br.com.gubee.interview.application.usecases.hero.findbyid.FindHeroByIdCommand;
import br.com.gubee.interview.application.usecases.hero.findbyid.FindHeroByIdOutput;
import br.com.gubee.interview.application.usecases.hero.update.UpdateHeroCommand;

import java.util.List;

public interface HeroFacade {
     CreateHeroOutput create(CreateHeroCommand command);
     FindHeroByIdOutput findById(FindHeroByIdCommand command);
     List<FindAllHeroesOutput> findAll(FindAllHeroesCommand command);
     void update(UpdateHeroCommand command);
     void delete(DeleteHeroCommand command);
     ComparisionOutput compare(ComparisionCommand command);
}
