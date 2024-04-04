package br.com.gubee.interview.application.usecases;

import br.com.gubee.interview.application.usecases.factory.TestUtils;
import br.com.gubee.interview.application.usecases.hero.delete.DeleteHeroCommand;
import br.com.gubee.interview.application.usecases.hero.update.UpdateHeroCommand;
import br.com.gubee.interview.domain.entities.hero.Hero;
import br.com.gubee.interview.domain.entities.hero.HeroId;
import br.com.gubee.interview.domain.enums.Race;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;


public class UpdateHeroCommandIT {

    private UpdateHeroCommand command;
    private final Hero hero = TestUtils.generateHeroInstanceFullArgs();


    @Given("a hero with full args to update")
    public void createHeroWithFullArgs() {
        Assertions.assertNotNull(hero);
    }

    @When("create a new update command")
    public void whenICreateTheUpdateCommand() {
        var powerStats = hero.getPowerStats();
        command = new UpdateHeroCommand(
                hero.getId().getValue(),
                hero.getName(),
                hero.getRace(),
                powerStats.getStrength(),
                powerStats.getAgility(),
                powerStats.getDexterity(),
                powerStats.getIntelligence(),
                hero.getEnabled()
        );

    }

    @And("assert hero is updated")
    public void andAssertHeroIsUpdated() {
        var updatedHero = hero.update(
                "anotherHero",
                Race.HUMAN,
                false,
                (short) 100,
                (short) 101,
                (short) 102,
                (short) 103
        );

        Assertions.assertEquals(hero.getId().getValue(), updatedHero.getId().getValue());
        Assertions.assertEquals(hero.getName(), updatedHero.getName());
        Assertions.assertEquals(hero.getRace(), updatedHero.getRace());
        Assertions.assertEquals(hero.getEnabled(), updatedHero.getEnabled());
        Assertions.assertEquals(hero.getPowerStats().getAgility(), updatedHero.getPowerStats().getAgility());
        Assertions.assertEquals(hero.getPowerStats().getDexterity(), updatedHero.getPowerStats().getDexterity());
        Assertions.assertEquals(hero.getPowerStats().getIntelligence(), updatedHero.getPowerStats().getIntelligence());
        Assertions.assertEquals(hero.getPowerStats().getStrength(), updatedHero.getPowerStats().getStrength());
    }

    @Then("the update command should be not null")
    public void thenTheCommandShouldBeNotNull() {
        Assertions.assertNotNull(command);
    }
}