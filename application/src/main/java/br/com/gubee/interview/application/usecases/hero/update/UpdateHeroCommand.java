package br.com.gubee.interview.application.usecases.hero.update;

import br.com.gubee.interview.domain.entities.powerstats.PowerStats;
import br.com.gubee.interview.domain.enums.Race;

public record UpdateHeroCommand(
        String id,
        String name,
        Race race,
        PowerStats powerStats,
        Boolean enabled
) {
    public static UpdateHeroCommand with(
            final String id,
            final String name,
            final Race race,
            final PowerStats powerStats,
            final Boolean enabled
    ) {
        return new UpdateHeroCommand(id, name, race, powerStats, enabled);
    }
}
