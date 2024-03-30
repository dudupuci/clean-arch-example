package br.com.gubee.interview.domain.entities.hero;

import br.com.gubee.interview.domain.Entity;
import br.com.gubee.interview.domain.entities.powerstats.PowerStats;
import br.com.gubee.interview.domain.enums.Race;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class Hero extends Entity<HeroId> {

    private String name;
    private Race race;
    private PowerStats powerStats;
    private Boolean enabled;

    public static Hero instantiate(
            final String name,
            final Race race,
            final PowerStats powerStats
    ) {
        final var id = HeroId.unique();
        final var now = Instant.now();

        return new Hero(
                id,
                now,
                now,
                name,
                race,
                powerStats,
                true
        );
    }

    public Hero(
            final HeroId heroId,
            final Instant createdAt,
            Instant updatedAt
    ) {
        super(heroId, createdAt, updatedAt);
    }

    public Hero(
            final HeroId heroId,
            final Instant createdAt,
            Instant updatedAt,
            String name,
            Race race,
            PowerStats powerStats,
            Boolean enabled
    ) {
        super(heroId, createdAt, updatedAt);
        this.name = name;
        this.race = race;
        this.powerStats = powerStats;
        this.enabled = enabled;
    }

    @Override
    public void validate() {

    }

    public void update(Race race, PowerStats powerStats, Boolean enabled) {
        this.race = race;
        this.powerStats = powerStats;
        this.enabled = enabled;
        this.updatedAt = Instant.now();
    }

    @Override
    public String toString() {
        return "Hero{" +
                "name='" + name + '\'' +
                ", race=" + race +
                ", powerStats=" + powerStats +
                ", enabled=" + enabled +
                ", id=" + id +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
