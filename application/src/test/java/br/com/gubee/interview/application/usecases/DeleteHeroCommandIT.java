package br.com.gubee.interview.application.usecases;

import br.com.gubee.interview.application.usecases.factory.TestUtils;
import br.com.gubee.interview.application.usecases.hero.delete.DeleteHeroCommand;
import br.com.gubee.interview.domain.entities.hero.Hero;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;


public class DeleteHeroCommandIT {

    private DeleteHeroCommand command;
    private final Hero hero = TestUtils.generateHeroInstanceFullArgs();


    @Given("a hero with full argz")
    public void createHeroWithFullArgs() {
        Assertions.assertNotNull(hero);
    }

    @When("create a new delete command")
    public void whenICreateTheDeleteCommand() {
        command = new DeleteHeroCommand(hero.getId().getValue());
    }

    @Then("the delete command should be not null")
    public void thenTheCommandShouldBeNotNull() {
        Assertions.assertNotNull(command);
    }
}