package br.com.gubee.interview.infrastructure.controllers.presentation.responses;

import br.com.gubee.interview.application.usecases.hero.findall.FindAllHeroesOutput;
import br.com.gubee.interview.domain.enums.Race;
import com.fasterxml.jackson.annotation.JsonProperty;

public record FindAllHeroesApiResponse(
        String id,
        String name,
        Race race,
        @JsonProperty("power_stats")
        FindAllHeroesPowerStatsApiResponse powerStats,
        Boolean enabled
) {
    public record FindAllHeroesPowerStatsApiResponse(
            Short strength,
            Short agility,
            Short dexterity,
            Short intelligence
    ) {
        public static FindAllHeroesPowerStatsApiResponse from(final FindAllHeroesOutput.FindAllHeroesPowerStatsOutput output) {
            return new FindAllHeroesPowerStatsApiResponse(
                    output.strength(),
                    output.agility(),
                    output.dexterity(),
                    output.intelligence()
            );
        }

    }

    public static FindAllHeroesApiResponse from(final FindAllHeroesOutput output) {
        return new FindAllHeroesApiResponse(
                output.id(),
                output.name(),
                output.race(),
                FindAllHeroesPowerStatsApiResponse.from(output.powerStatsOutput()),
                output.enabled()
        );
    }
}
