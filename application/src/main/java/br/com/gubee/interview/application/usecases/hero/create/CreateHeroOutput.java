package br.com.gubee.interview.application.usecases.hero.create;

import br.com.gubee.interview.domain.entities.powerstats.PowerStats;
import br.com.gubee.interview.domain.enums.Race;

public record CreateHeroOutput(
        String id,
        String name,
        Race race,
        CreateHeroPowerStatsOutput powerStatsOutput,
        Boolean enabled

) {

    public record CreateHeroPowerStatsOutput(
            Short strength,
            Short agility,
            Short dexterity,
            Short intelligence
    ) {
        public static CreateHeroPowerStatsOutput from(PowerStats powerStats) {
            return new CreateHeroPowerStatsOutput(
                    powerStats.getStrength(),
                    powerStats.getAgility(),
                    powerStats.getDexterity(),
                    powerStats.getIntelligence()
            );
        }
    }

    public static CreateHeroOutput with(
            final String id,
            final String name,
            final Race race,
            final PowerStats powerStats,
            final Boolean enabled
    ) {
        return new CreateHeroOutput(
                id,
                name,
                race,
                CreateHeroPowerStatsOutput.from(powerStats),
                enabled
        );
    }
}
