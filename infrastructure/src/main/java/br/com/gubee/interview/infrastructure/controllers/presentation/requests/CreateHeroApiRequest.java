package br.com.gubee.interview.infrastructure.controllers.presentation.requests;

import br.com.gubee.interview.application.usecases.hero.create.CreateHeroCommand;
import br.com.gubee.interview.domain.entities.powerstats.PowerStats;
import br.com.gubee.interview.domain.enums.Race;
import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateHeroApiRequest(
        String name,
        Race race,
        @JsonProperty("power_stats")
        PowerStats powerStats
) {
    public CreateHeroCommand toCommand() {
        return new CreateHeroCommand(name, race, powerStats);
    }
}
