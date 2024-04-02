package br.com.gubee.interview.infrastructure.controllers.presentation.responses;

import br.com.gubee.interview.application.usecases.hero.create.CreateHeroOutput;
import br.com.gubee.interview.domain.enums.Race;

public record CreateHeroApiResponse(
        String id,
        String name,
        Race race,
        CreateHeroPowerStatsApiResponse powerStatsApiResponse,
        Boolean enabled
) {
    public record CreateHeroPowerStatsApiResponse(
            Short strength,
            Short agility,
            Short dexterity,
            Short intelligence
    ) {
        public static CreateHeroPowerStatsApiResponse from(CreateHeroOutput.CreateHeroPowerStatsOutput output) {
            return new CreateHeroPowerStatsApiResponse(
                    output.strength(),
                    output.agility(),
                    output.dexterity(),
                    output.intelligence()
            );
        }

    }

    public static CreateHeroApiResponse from(CreateHeroOutput output) {
        return new CreateHeroApiResponse(
                output.id(),
                output.name(),
                output.race(),
                CreateHeroPowerStatsApiResponse.from(output.powerStatsOutput()),
                output.enabled()
        );
    }
}
