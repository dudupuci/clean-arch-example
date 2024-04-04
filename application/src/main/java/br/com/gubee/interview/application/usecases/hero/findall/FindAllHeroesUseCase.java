package br.com.gubee.interview.application.usecases.hero.findall;

import br.com.gubee.interview.application.UseCase;
import br.com.gubee.interview.domain.entities.hero.Hero;

import java.util.List;

public abstract class FindAllHeroesUseCase extends UseCase<FindAllHeroesCommand, List<FindAllHeroesOutput>> {
}
