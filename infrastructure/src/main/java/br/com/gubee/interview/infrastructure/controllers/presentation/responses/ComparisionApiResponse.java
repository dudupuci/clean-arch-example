package br.com.gubee.interview.infrastructure.controllers.presentation.responses;

import br.com.gubee.interview.application.usecases.hero.comparision.ComparisionOutput;

import java.util.List;

public record ComparisionApiResponse(
        HeroComparisionApiResponse hero,
        HeroComparisionApiResponse anotherHero,
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
                HeroComparisionApiResponse.from(output.hero()),
                HeroComparisionApiResponse.from(output.anotherHero()),
                output.details()
        );
    }
}
