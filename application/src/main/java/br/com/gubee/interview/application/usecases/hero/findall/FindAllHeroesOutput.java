package br.com.gubee.interview.application.usecases.hero.findall;

import br.com.gubee.interview.domain.entities.hero.Hero;
import br.com.gubee.interview.domain.entities.powerstats.PowerStats;
import br.com.gubee.interview.domain.enums.Race;

public record FindAllHeroesOutput(
        String id,
        String name,
        Race race,
        FindAllHeroesPowerStatsOutput powerStatsOutput,
        Boolean enabled

) {
    public record FindAllHeroesPowerStatsOutput(
             Short strength,
             Short agility,
             Short dexterity,
             Short intelligence
    ) {
        public static FindAllHeroesPowerStatsOutput from(PowerStats powerStats) {
            return new FindAllHeroesPowerStatsOutput(
                    powerStats.getStrength(),
                    powerStats.getAgility(),
                    powerStats.getDexterity(),
                    powerStats.getIntelligence()
            );
        }
    }

    public static FindAllHeroesOutput from(Hero hero) {
        return new FindAllHeroesOutput(
                hero.getId().getValue(),
                hero.getName(),
                hero.getRace(),
                FindAllHeroesPowerStatsOutput.from(hero.getPowerStats()),
                hero.getEnabled()
        );
    }
}
