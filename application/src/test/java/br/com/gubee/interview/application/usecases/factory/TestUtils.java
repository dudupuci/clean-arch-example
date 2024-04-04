package br.com.gubee.interview.application.usecases.factory;

import br.com.gubee.interview.domain.entities.hero.Hero;
import br.com.gubee.interview.domain.entities.hero.HeroId;
import br.com.gubee.interview.domain.entities.powerstats.PowerStats;
import br.com.gubee.interview.domain.entities.powerstats.PowerStatsId;
import br.com.gubee.interview.domain.enums.Race;

import java.time.Instant;

public class TestUtils {
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
