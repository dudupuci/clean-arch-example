package br.com.gubee.interview.infrastructure.factory;

import br.com.gubee.interview.application.usecases.hero.create.CreateHeroCommand;
import br.com.gubee.interview.application.usecases.hero.update.UpdateHeroCommand;
import br.com.gubee.interview.domain.entities.hero.Hero;
import br.com.gubee.interview.domain.entities.hero.HeroId;
import br.com.gubee.interview.domain.entities.powerstats.PowerStats;
import br.com.gubee.interview.domain.entities.powerstats.PowerStatsId;
import br.com.gubee.interview.domain.enums.Race;

import java.time.Instant;

public class TestUtils {

    public static CreateHeroCommand generateHeroCommand() {
        return new CreateHeroCommand("hero1010", Race.ALIEN, new PowerStats(null,
                Instant.now(),
                Instant.now(),
                (short) 1,
                (short) 1,
                (short) 1,
                (short) 1));
    }

    public static UpdateHeroCommand generateUpdateCommand(Hero hero) {
        return new UpdateHeroCommand(
                hero.getId().getValue(),
                hero.getName(),
                hero.getRace(),
                hero.getPowerStats().getStrength(),
                hero.getPowerStats().getAgility(),
                hero.getPowerStats().getDexterity(),
                hero.getPowerStats().getIntelligence(),
                hero.getEnabled()
        );
    }

    public static Hero generateHeroInstanceFullArgs() {
        return new Hero(
                HeroId.from("352eebfa-477f-49ec-a169-12acd5952884"),
                Instant.now(),
                Instant.now(),
                "hero1",
                Race.ALIEN,
                new PowerStats(
                        PowerStatsId.from("6195d234-27d6-4edd-be2a-104f2edb5cba"),
                        Instant.now(),
                        Instant.now(),
                        (short) 10,
                        (short) 8,
                        (short) 9,
                        (short) 7
                ),
                true
        );
    }
}
