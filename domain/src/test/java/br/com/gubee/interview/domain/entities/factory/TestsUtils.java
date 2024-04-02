package br.com.gubee.interview.domain.entities.factory;

import br.com.gubee.interview.domain.entities.hero.Hero;
import br.com.gubee.interview.domain.entities.hero.HeroId;
import br.com.gubee.interview.domain.entities.powerstats.PowerStats;
import br.com.gubee.interview.domain.entities.powerstats.PowerStatsId;
import br.com.gubee.interview.domain.enums.Race;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

public class TestsUtils {

    private static final Instant NOW = Instant.now().truncatedTo(ChronoUnit.SECONDS);
    private static final String HERO_ID = "659f1b4e-f2b0-4a7a-a34d-b0aea64a0358";
    private static final String POWER_STATS_ID = "c04e76e0-00f3-439a-ba04-95c7e4bc8c33";
    private static final Boolean ENABLED = true;

    public static HeroId getHeroIdInstance() {
        return new HeroId(HERO_ID);
    }

    public static PowerStatsId getPowerStatsIdInstance() {
        return new PowerStatsId(POWER_STATS_ID);
    }

    public static Hero getHeroInstance() {
        return new Hero(HeroId.from(HERO_ID), NOW, NOW);
    }

    public static Hero getHeroInstanceWithFullArgs() {
        return new Hero(
                HeroId.from(HERO_ID),
                NOW,
                NOW,
                "hero_name",
                Race.ALIEN,
                getPowerStatsInstance(),
                ENABLED
        );
    }
    public static PowerStats getPowerStatsInstance() {
        return new PowerStats(PowerStatsId.from(POWER_STATS_ID), NOW, NOW);
    }

    public static PowerStats getPowerStatsInstanceWithFullArgs() {
        return new PowerStats(
                PowerStatsId.unique(),
                NOW,
                NOW,
                (short) 10, // exemplo de valor para strength
                (short) 8,  // exemplo de valor para agility
                (short) 7,  // exemplo de valor para dexterity
                (short) 9   // exemplo de valor para intelligence
        );
    }
}
