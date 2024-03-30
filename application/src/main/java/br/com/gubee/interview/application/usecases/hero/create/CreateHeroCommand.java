package br.com.gubee.interview.application.usecases.hero.create;

import br.com.gubee.interview.domain.entities.powerstats.PowerStats;
import br.com.gubee.interview.domain.enums.Race;

public record CreateHeroCommand(
        String name,
        Race race,
        PowerStats powerStats
) {
    public static CreateHeroCommand with(
            final String name,
            final Race race,
            final PowerStats powerStats
    ) {
        return new CreateHeroCommand(name, race, powerStats);
    }
}
