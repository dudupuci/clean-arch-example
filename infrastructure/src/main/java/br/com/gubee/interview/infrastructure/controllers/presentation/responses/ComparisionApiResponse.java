package br.com.gubee.interview.infrastructure.controllers.presentation.responses;

import br.com.gubee.interview.application.usecases.hero.comparision.ComparisionOutput;

import java.util.List;

public record ComparisionApiResponse(
        String heroId,
        String heroName,
        String anotherHeroId,
        String anotherHeroName,
        HeroPowerStatsApiResponse heroPowerStats,
        HeroPowerStatsApiResponse anotherHeroPowerStats,
        List<String> details
) {
    public record HeroPowerStatsApiResponse(
            Short strength,
            Short agility,
            Short dexterity,
            Short intelligence
    ) {
        public static HeroPowerStatsApiResponse from(ComparisionOutput.HeroPowerStatsOutput output) {
            return new HeroPowerStatsApiResponse(
                    output.strength(),
                    output.agility(),
                    output.dexterity(),
                    output.intelligence()
            );
        }
    }
    public static ComparisionApiResponse from(ComparisionOutput output) {
        return new ComparisionApiResponse(
                output.heroId(),
                output.heroName(),
                output.anotherHeroId(),
                output.anotherHeroName(),
                HeroPowerStatsApiResponse.from(output.heroPowerStats()),
                HeroPowerStatsApiResponse.from(output.anotherHeroPowerStats()),
                output.details()
        );
    }
}
