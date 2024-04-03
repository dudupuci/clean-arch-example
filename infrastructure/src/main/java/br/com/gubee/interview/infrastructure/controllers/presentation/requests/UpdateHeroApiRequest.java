package br.com.gubee.interview.infrastructure.controllers.presentation.requests;

import br.com.gubee.interview.application.usecases.hero.update.UpdateHeroCommand;
import br.com.gubee.interview.domain.enums.Race;

public record UpdateHeroApiRequest(
        String id,
        String name,
        Race race,
        Short strength,
        Short agility,
        Short dexterity,
        Short intelligence,
        Boolean enabled
) {
    public UpdateHeroCommand toCommand() {
        return new UpdateHeroCommand(
                id,
                name,
                race,
                strength,
                agility,
                dexterity,
                intelligence,
                enabled
        );
    }
}
