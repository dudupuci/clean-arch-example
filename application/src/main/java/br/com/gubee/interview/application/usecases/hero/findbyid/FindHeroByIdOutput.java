package br.com.gubee.interview.application.usecases.hero.findbyid;

import br.com.gubee.interview.domain.entities.powerstats.PowerStats;
import br.com.gubee.interview.domain.enums.Race;

public record FindHeroByIdOutput(
        String name,
        Race race,
        PowerStats powerStats,
        Boolean enabled

) {
    public static FindHeroByIdOutput with(
            final String name,
            final Race race,
            final PowerStats powerStats,
            final Boolean enabled
    ) {
        return new FindHeroByIdOutput(name, race, powerStats, enabled);
    }
}
