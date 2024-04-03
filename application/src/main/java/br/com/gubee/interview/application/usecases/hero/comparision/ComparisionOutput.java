package br.com.gubee.interview.application.usecases.hero.comparision;

import br.com.gubee.interview.domain.entities.powerstats.PowerStats;
import br.com.gubee.interview.domain.valueobjects.Comparision;

import java.util.List;

public record ComparisionOutput(
        HeroComparisionOutput hero,
        HeroComparisionOutput anotherHero,
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
                new HeroComparisionOutput(
                        comparision.heroId(),
                        comparision.heroName(),
                        HeroPowerStatsOutput.from(comparision.heroPowerStats())
                ),
                new HeroComparisionOutput(
                        comparision.anotherHeroId(),
                        comparision.anotherHeroName(),
                        HeroPowerStatsOutput.from(comparision.anotherHeroPowerStats())
                ),
                comparision.details()
        );
    }
}