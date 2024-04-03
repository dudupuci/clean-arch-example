package br.com.gubee.interview.application.usecases.hero.comparision;

public record HeroComparisionOutput(
        String id,
        String name,
        ComparisionOutput.HeroPowerStatsOutput powerStatsOutput
) {
}
