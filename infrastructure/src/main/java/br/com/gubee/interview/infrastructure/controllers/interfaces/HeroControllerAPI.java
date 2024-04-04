package br.com.gubee.interview.infrastructure.controllers.interfaces;

import br.com.gubee.interview.application.usecases.hero.comparision.ComparisionCommand;
import br.com.gubee.interview.application.usecases.hero.create.CreateHeroCommand;
import br.com.gubee.interview.application.usecases.hero.update.UpdateHeroCommand;
import br.com.gubee.interview.domain.entities.hero.Hero;
import br.com.gubee.interview.domain.exceptions.HeroNotFoundException;
import br.com.gubee.interview.infrastructure.controllers.exceptions.StandardError;
import br.com.gubee.interview.infrastructure.controllers.exceptions.handlers.ControllerExceptionHandler;
import br.com.gubee.interview.infrastructure.controllers.presentation.requests.CreateHeroApiRequest;
import br.com.gubee.interview.infrastructure.controllers.presentation.requests.UpdateHeroApiRequest;
import br.com.gubee.interview.infrastructure.controllers.presentation.responses.ComparisionApiResponse;
import br.com.gubee.interview.infrastructure.controllers.presentation.responses.CreateHeroApiResponse;
import br.com.gubee.interview.infrastructure.controllers.presentation.responses.FindAllHeroesApiResponse;
import br.com.gubee.interview.infrastructure.controllers.presentation.responses.FindHeroByIdApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


public interface HeroControllerAPI {

    @Operation(summary = "Create a new hero", description = "Returns the data from the new hero if it worked")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Create a new Hero", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = CreateHeroApiResponse.class))
            }),
            @ApiResponse(responseCode = "400", description = "Error while hero creation", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))
            }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))
            })
    })
    @PostMapping
    ResponseEntity<?> create(@RequestBody CreateHeroApiRequest request);


    @Operation(summary = "Find a existent hero's data", description = "Returns the data from the hero if it worked")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Hero found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = FindHeroByIdApiResponse.class))
            }),
            @ApiResponse(responseCode = "404", description = "Hero not found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))
            }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))
            })
    })
    @GetMapping("/{id}")
    ResponseEntity<FindHeroByIdApiResponse> findById(@PathVariable("id") UUID id);


    @Operation(summary = "List all existent heroes", description = "Returns a list of all heroes if it worked and has the option to filter by name")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Heroes listed", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = FindAllHeroesApiResponse.class)))
            }),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))
            }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))
            })
    })
    @GetMapping
    ResponseEntity<?> findAll(@RequestParam(name = "name", required = false) String name);


    @Operation(summary = "Update a existent hero's data", description = "Returns a ok status code if it worked")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Hero updated successfuly"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))
            }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))
            })
    })
    @PutMapping("/{id}")
    ResponseEntity<?> update(@RequestBody UpdateHeroApiRequest request);


    @Operation(summary = "Delete a existent hero's data", description = "Returns a no content status code if it worked")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Hero deleted successfuly"),
            @ApiResponse(responseCode = "404", description = "Hero not found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))
            }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))
            })
    })
    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteById(@PathVariable("id") UUID id);


    @Operation(summary = "Compares the power stats of two heroes", description = "Returns a comparison between heroes and a list of details")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Heroes compared", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ComparisionApiResponse.class))
            }),
            @ApiResponse(responseCode = "400", description = "Bad request", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))
            }),
            @ApiResponse(responseCode = "404", description = "Not found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))
            }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))
            })
    })
    @GetMapping("/compare/{heroId}/{anotherHeroId}")
    ResponseEntity<?> compare(
            @PathVariable("heroId") UUID heroId,
            @PathVariable("anotherHeroId") UUID anotherHeroId
    );

}
