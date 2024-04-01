package br.com.gubee.interview.domain.valueobjects;

import br.com.gubee.interview.domain.entities.powerstats.PowerStats;

import java.util.List;

public record Comparision(
        String heroId,
        String heroName,
        String anotherHeroId,
        String anotherHeroName,
        PowerStats heroPowerStats,
        PowerStats anotherHeroPowerStats,
        List<String> details
) {
    public static Comparision with(
            final String heroId,
            final String heroName,
            final String anotherHeroId,
            final String anotherHeroName,
            final PowerStats heroPowerStats,
            final PowerStats anotherHeroPowerStats,
            final List<String> details
    ) {
        return new Comparision(heroId, heroName, anotherHeroId, anotherHeroName,heroPowerStats, anotherHeroPowerStats, details);
    }

}
