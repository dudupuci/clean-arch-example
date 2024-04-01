package br.com.gubee.interview.infrastructure.controllers.presentation.requests;

import br.com.gubee.interview.application.usecases.hero.update.UpdateHeroCommand;
import br.com.gubee.interview.domain.entities.powerstats.PowerStats;
import br.com.gubee.interview.domain.enums.Race;
import com.fasterxml.jackson.annotation.JsonProperty;

public record UpdateHeroApiRequest(
        String id,
        String name,
        Race race,
        @JsonProperty("power_stats")
        PowerStats powerStats,
        Boolean enabled
) {
    public UpdateHeroCommand toCommand() {
        return new UpdateHeroCommand(
                id,
                name,
                race,
                powerStats,
                enabled
        );
    }
}
