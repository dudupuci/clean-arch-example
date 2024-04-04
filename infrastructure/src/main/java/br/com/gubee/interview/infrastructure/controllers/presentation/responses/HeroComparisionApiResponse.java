package br.com.gubee.interview.infrastructure.controllers.presentation.responses;

import br.com.gubee.interview.application.usecases.hero.comparision.HeroComparisionOutput;

public record HeroComparisionApiResponse(
        String id,
        String name,
        ComparisionApiResponse.HeroPowerStatsApiResponse powerStats
) {
    public static HeroComparisionApiResponse from(HeroComparisionOutput output) {
        return new HeroComparisionApiResponse(
                output.id(),
                output.name(),
                ComparisionApiResponse.HeroPowerStatsApiResponse.from(output.powerStatsOutput())
        );
    }
}
