package br.com.gubee.interview.application.usecases;

import br.com.gubee.interview.application.usecases.hero.comparision.ComparisionCommand;
import br.com.gubee.interview.application.usecases.hero.comparision.ComparisionOutput;
import br.com.gubee.interview.application.usecases.hero.comparision.ComparisionUseCase;
import br.com.gubee.interview.application.usecases.hero.comparision.ComparisionUseCaseImpl;
import br.com.gubee.interview.domain.entities.hero.Hero;
import br.com.gubee.interview.domain.entities.hero.HeroId;
import br.com.gubee.interview.domain.entities.hero.HeroRepository;
import br.com.gubee.interview.domain.entities.powerstats.PowerStats;
import br.com.gubee.interview.domain.enums.Race;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.Instant;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


public class ComparisionIT {

    @Mock
    private HeroRepository heroRepository;
    private ComparisionUseCase useCase;
    private ComparisionCommand command;
    private ComparisionOutput output;

    @Given("a hero with ID {string}")
    public void givenAHeroWithId(String heroId) {
        MockitoAnnotations.openMocks(this);
        useCase = new ComparisionUseCaseImpl(heroRepository);
        Hero hero = new Hero(
                HeroId.from("352eebfa-477f-49ec-a169-12acd5952884"),
                Instant.now(),
                Instant.now(),
                "hero_name",
                Race.ALIEN,
                PowerStats.instantiate(
                        (short) 1,
                        (short) 2,
                        (short) 3,
                        (short) 4
                ),
                true

        );
        heroId = hero.getId().getValue();
        when(heroRepository.findById(heroId)).thenReturn(Optional.of(hero));
    }

    @And("another hero with ID {string}")
    public void givenAnotherHeroWithId(String anotherHeroId) {
        Hero anotherHero = new Hero(
                HeroId.from("4883c384-17a1-4aa8-a5e3-13e679b4bcd3"),
                Instant.now(),
                Instant.now(),
                "another_hero_name",
                Race.HUMAN,
                PowerStats.instantiate(
                        (short) 4,
                        (short) 3,
                        (short) 2,
                        (short) 1
                ),
                true
        );
        anotherHeroId = anotherHero.getId().getValue();
        when(heroRepository.findById(anotherHeroId)).thenReturn(Optional.of(anotherHero));
    }

    @When("I compare the heroes")
    public void whenICompareTheHeroes() {
        command = new ComparisionCommand("352eebfa-477f-49ec-a169-12acd5952884", "4883c384-17a1-4aa8-a5e3-13e679b4bcd3");
        output = useCase.execute(command);
    }

    @Then("the comparison output should contain the correct hero IDs")
    public void thenTheComparisonOutputShouldContainTheCorrectHeroIds() {
        assertEquals("352eebfa-477f-49ec-a169-12acd5952884", output.hero().id());
        assertEquals("4883c384-17a1-4aa8-a5e3-13e679b4bcd3", output.anotherHero().id());
    }
}
