package br.com.gubee.interview.infrastructure.controllers.presentation.responses;

import br.com.gubee.interview.application.usecases.hero.findbyid.FindHeroByIdOutput;
import br.com.gubee.interview.domain.entities.powerstats.PowerStats;
import br.com.gubee.interview.domain.enums.Race;

public record FindHeroByIdApiResponse(
        String name,
        Race race,
        PowerStats powerStats,
        Boolean enabled
) {
    public static FindHeroByIdApiResponse from(FindHeroByIdOutput output) {
        return new FindHeroByIdApiResponse(
                output.name(),
                output.race(),
                output.powerStats(),
                output.enabled()
        );
    }
}
