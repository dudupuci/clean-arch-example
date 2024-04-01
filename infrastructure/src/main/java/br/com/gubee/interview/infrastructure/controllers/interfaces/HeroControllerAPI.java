package br.com.gubee.interview.infrastructure.controllers.interfaces;

import br.com.gubee.interview.application.usecases.hero.create.CreateHeroCommand;
import br.com.gubee.interview.application.usecases.hero.update.UpdateHeroCommand;
import br.com.gubee.interview.domain.entities.hero.Hero;
import br.com.gubee.interview.infrastructure.controllers.presentation.requests.CreateHeroApiRequest;
import br.com.gubee.interview.infrastructure.controllers.presentation.responses.FindHeroByIdApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


public interface HeroControllerAPI {

    @PostMapping
    ResponseEntity<Hero> create(@RequestBody CreateHeroApiRequest request);

    @GetMapping("/{id}")
    ResponseEntity<FindHeroByIdApiResponse> findById(@PathVariable("id") UUID id);

    @GetMapping
    ResponseEntity<List<Hero>> findAll(@RequestParam(name = "name", required = false) String name);

    @PutMapping("/{id}")
    ResponseEntity<Void> update(@RequestBody UpdateHeroCommand command);
}
