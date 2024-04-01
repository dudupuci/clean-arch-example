package br.com.gubee.interview.application.usecases.hero.comparision;

import br.com.gubee.interview.domain.entities.powerstats.PowerStats;
import br.com.gubee.interview.domain.valueobjects.Comparision;

import java.util.List;

public record ComparisionOutput(
        String heroId,
        String heroName,
        String anotherHeroId,
        String anotherHeroName,
        HeroPowerStatsOutput heroPowerStats,
        HeroPowerStatsOutput anotherHeroPowerStats,
        List<String> details
) {
    public record HeroPowerStatsOutput(
            Short strength,
            Short agility,
            Short dexterity,
            Short intelligence
    ) {
        public static HeroPowerStatsOutput from(PowerStats powerStats) {
            return new HeroPowerStatsOutput(
                    powerStats.getStrength(),
                    powerStats.getAgility(),
                    powerStats.getDexterity(),
                    powerStats.getIntelligence()
            );
        }

    }

    public static ComparisionOutput from(Comparision comparision) {
        return new ComparisionOutput(
                comparision.heroId(),
                comparision.heroName(),
                comparision.anotherHeroId(),
                comparision.anotherHeroName(),
                HeroPowerStatsOutput.from(comparision.heroPowerStats()),
                HeroPowerStatsOutput.from(comparision.anotherHeroPowerStats()),
                comparision.details()
        );
    }
}