package br.com.gubee.interview.infrastructure.controllers;

import br.com.gubee.interview.application.usecases.hero.comparision.ComparisionCommand;
import br.com.gubee.interview.application.usecases.hero.delete.DeleteHeroCommand;
import br.com.gubee.interview.application.usecases.hero.findall.FindAllHeroesCommand;
import br.com.gubee.interview.application.usecases.hero.findbyid.FindHeroByIdCommand;
import br.com.gubee.interview.application.usecases.hero.update.UpdateHeroCommand;
import br.com.gubee.interview.domain.entities.hero.Hero;
import br.com.gubee.interview.domain.exceptions.CannotCompareTheSameHeroException;
import br.com.gubee.interview.domain.exceptions.HeroNameAlreadyExistsException;
import br.com.gubee.interview.domain.exceptions.HeroNotFoundException;
import br.com.gubee.interview.infrastructure.configuration.usecases.HeroFacade;
import br.com.gubee.interview.infrastructure.controllers.interfaces.HeroControllerAPI;
import br.com.gubee.interview.infrastructure.controllers.presentation.requests.CreateHeroApiRequest;
import br.com.gubee.interview.infrastructure.controllers.presentation.requests.UpdateHeroApiRequest;
import br.com.gubee.interview.infrastructure.controllers.presentation.responses.ComparisionApiResponse;
import br.com.gubee.interview.infrastructure.controllers.presentation.responses.CreateHeroApiResponse;
import br.com.gubee.interview.infrastructure.controllers.presentation.responses.FindHeroByIdApiResponse;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/heroes")
public class HeroController implements HeroControllerAPI {

    private final HeroFacade heroFacade;

    public HeroController(final HeroFacade heroFacade) {
        this.heroFacade = heroFacade;
    }

    @Override
    public ResponseEntity<?> create(final CreateHeroApiRequest request) {
        try {
            var heroOutput = this.heroFacade.create(request.toCommand());
            var heroApiResponse = CreateHeroApiResponse.from(heroOutput);
            return ResponseEntity.created(URI.create("/heroes/" + heroApiResponse.id())).body(heroApiResponse);
        } catch (HeroNameAlreadyExistsException err) {
            return ResponseEntity.badRequest().body(err.getMessage());
        }

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
    public ResponseEntity<?> findAll(String name) {
        try {
            var command = new FindAllHeroesCommand(name);
            var heroes = this.heroFacade.findAll(command);
            return ResponseEntity.ok().body(heroes);
        } catch (IllegalArgumentException err) {
            return ResponseEntity.badRequest().body(err.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> update(UpdateHeroApiRequest request) {
        try {
            this.heroFacade.update(request.toCommand());
            return ResponseEntity.ok().build();
        } catch (HeroNotFoundException | EmptyResultDataAccessException err) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Hero not found");
        }
    }

    @Override
    public ResponseEntity<?> deleteById(UUID id) {
        try {
            var command = new DeleteHeroCommand(id.toString());
            this.heroFacade.delete(command);
            return ResponseEntity.noContent().build();
        } catch (HeroNotFoundException | EmptyResultDataAccessException err) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Hero not found");
        }
    }

    @Override
    public ResponseEntity<?> compare(final ComparisionCommand command) {
        try {
            var comparisionOutput = this.heroFacade.compare(command);
            return ResponseEntity.ok().body(ComparisionApiResponse.from(comparisionOutput));
        } catch (HeroNotFoundException | EmptyResultDataAccessException err) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Hero not found");
        } catch (CannotCompareTheSameHeroException err) {
            return ResponseEntity.badRequest().body(err.getMessage());
        }
    }

}
