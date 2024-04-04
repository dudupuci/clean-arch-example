package br.com.gubee.interview.application.usecases.hero.update;

import br.com.gubee.interview.domain.entities.powerstats.PowerStats;
import br.com.gubee.interview.domain.enums.Race;

public record UpdateHeroCommand(
        String id,
        String name,
        Race race,
        Short strength,
        Short agility,
        Short dexterity,
        Short intelligence,
        Boolean enabled
) {
    public static UpdateHeroCommand with(
            final String id,
            final String name,
            final Race race,
            final Short strength,
            final Short agility,
            final Short dexterity,
            final Short intelligence,
            final Boolean enabled
    ) {
        return new UpdateHeroCommand(id, name, race, strength, agility, dexterity, intelligence, enabled);
    }
}
