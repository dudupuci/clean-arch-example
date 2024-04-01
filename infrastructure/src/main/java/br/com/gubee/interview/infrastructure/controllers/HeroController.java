package br.com.gubee.interview.infrastructure.controllers;

import br.com.gubee.interview.application.usecases.hero.create.CreateHeroCommand;
import br.com.gubee.interview.application.usecases.hero.findall.FindAllHeroesCommand;
import br.com.gubee.interview.application.usecases.hero.findbyid.FindHeroByIdCommand;
import br.com.gubee.interview.application.usecases.hero.update.UpdateHeroCommand;
import br.com.gubee.interview.domain.entities.hero.Hero;
import br.com.gubee.interview.domain.exceptions.HeroNotFoundException;
import br.com.gubee.interview.infrastructure.configuration.usecases.HeroFacade;
import br.com.gubee.interview.infrastructure.controllers.interfaces.HeroControllerAPI;
import br.com.gubee.interview.infrastructure.controllers.presentation.requests.CreateHeroApiRequest;
import br.com.gubee.interview.infrastructure.controllers.presentation.responses.FindHeroByIdApiResponse;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/heroes")
public class HeroController implements HeroControllerAPI {

    private final HeroFacade heroFacade;

    public HeroController(final HeroFacade heroFacade) {
        this.heroFacade = heroFacade;
    }

    @Override
    public ResponseEntity<Hero> create(final CreateHeroApiRequest request) {
        var hero = this.heroFacade.create(request.toCommand());
        return ResponseEntity.created(URI.create("/heroes/" + hero.getId())).body(hero);
    }

    @Override
    public ResponseEntity<FindHeroByIdApiResponse> findById(UUID id) {
        var command = new FindHeroByIdCommand(id.toString());
        try {
            var heroOutput = this.heroFacade.findById(command);
            return ResponseEntity.ok(FindHeroByIdApiResponse.from(heroOutput));
        } catch (HeroNotFoundException | EmptyResultDataAccessException err) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<List<Hero>> findAll(String name) {
        var command = new FindAllHeroesCommand(name);
        var heroes = this.heroFacade.findAll(command);
        return ResponseEntity.ok().body(heroes);
    }

    @Override
    public ResponseEntity<Void> update(UpdateHeroCommand command) {
        try {
            this.heroFacade.update(command);
            return ResponseEntity.ok().build();
        } catch (HeroNotFoundException err) {
            return ResponseEntity.notFound().build();
        }
    }

}
