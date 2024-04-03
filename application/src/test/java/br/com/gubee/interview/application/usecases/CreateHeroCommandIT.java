package br.com.gubee.interview.application.usecases;

import br.com.gubee.interview.application.usecases.factory.TestUtils;
import br.com.gubee.interview.application.usecases.hero.create.CreateHeroCommand;
import br.com.gubee.interview.domain.entities.hero.Hero;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;


public class CreateHeroCommandIT {

    private CreateHeroCommand command;
    private final Hero hero = TestUtils.generateHeroInstanceFullArgs();


    @Given("a hero with full args to create")
    public void createHero() {
        Assertions.assertNotNull(hero);
    }

    @When("create a new create command")
    public void whenICreateTheCommand() {
        command = new CreateHeroCommand(hero.getName(), hero.getRace(), hero.getPowerStats());
    }

    @Then("the create command should be not null")
    public void thenTheCommandShouldBeNotNull() {
        Assertions.assertNotNull(command);
    }
}