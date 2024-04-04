package br.com.gubee.interview.infrastructure.controllers;

import br.com.gubee.interview.application.usecases.hero.comparision.ComparisionCommand;
import br.com.gubee.interview.application.usecases.hero.delete.DeleteHeroCommand;
import br.com.gubee.interview.application.usecases.hero.findall.FindAllHeroesCommand;
import br.com.gubee.interview.application.usecases.hero.findbyid.FindHeroByIdCommand;
import br.com.gubee.interview.infrastructure.persistence.facade.HeroFacade;
import br.com.gubee.interview.infrastructure.controllers.interfaces.HeroControllerAPI;
import br.com.gubee.interview.infrastructure.controllers.presentation.requests.CreateHeroApiRequest;
import br.com.gubee.interview.infrastructure.controllers.presentation.requests.UpdateHeroApiRequest;
import br.com.gubee.interview.infrastructure.controllers.presentation.responses.ComparisionApiResponse;
import br.com.gubee.interview.infrastructure.controllers.presentation.responses.CreateHeroApiResponse;
import br.com.gubee.interview.infrastructure.controllers.presentation.responses.FindAllHeroesApiResponse;
import br.com.gubee.interview.infrastructure.controllers.presentation.responses.FindHeroByIdApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/heroes")
@CrossOrigin(value = "http://localhost:3000")
public class HeroController implements HeroControllerAPI {

    private final HeroFacade heroFacade;

    public HeroController(final HeroFacade heroFacade) {
        this.heroFacade = heroFacade;
    }

    @Override
    public ResponseEntity<CreateHeroApiResponse> create(final CreateHeroApiRequest request) {
        var heroOutput = this.heroFacade.create(request.toCommand());
        var heroApiResponse = CreateHeroApiResponse.from(heroOutput);
        return ResponseEntity.created(URI.create("/heroes/" + heroApiResponse.id())).body(heroApiResponse);
    }

    @Override
    public ResponseEntity<FindHeroByIdApiResponse> findById(final UUID id) {
        var command = new FindHeroByIdCommand(id.toString());
        var heroOutput = this.heroFacade.findById(command);
        return ResponseEntity.ok(FindHeroByIdApiResponse.from(heroOutput));
    }

    @Override
    public ResponseEntity<List<FindAllHeroesApiResponse>> findAll(final String name) {
        var command = new FindAllHeroesCommand(name);
        var heroes = this.heroFacade.findAll(command);
        var output = heroes.stream().map(FindAllHeroesApiResponse::from).toList();
        return ResponseEntity.ok().body(output);
    }

    @Override
    public ResponseEntity<Void> update(final UpdateHeroApiRequest request) {
        this.heroFacade.update(request.toCommand());
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> deleteById(UUID id) {
        var command = new DeleteHeroCommand(id.toString());
        this.heroFacade.delete(command);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<ComparisionApiResponse> compare(final UUID heroId, final UUID anotherHeroId) {
        var command = new ComparisionCommand(heroId.toString(), anotherHeroId.toString());
        var comparisionOutput = this.heroFacade.compare(command);
        return ResponseEntity.ok().body(ComparisionApiResponse.from(comparisionOutput));
    }

}
