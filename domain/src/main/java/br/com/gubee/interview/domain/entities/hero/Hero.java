package br.com.gubee.interview.domain.entities.hero;

import br.com.gubee.interview.domain.Entity;
import br.com.gubee.interview.domain.entities.powerstats.PowerStats;
import br.com.gubee.interview.domain.enums.Race;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@Getter
@Setter
public class Hero extends Entity<HeroId> implements Serializable {

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

    public Hero(HeroId id, Instant createdAt, Instant updatedAt) {
        super(id, createdAt, updatedAt);
    }

    @JsonCreator
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
        Objects.requireNonNull(this.id, "Hero must have an ID");
    }

    public Hero update(String name, Race race, Boolean enabled, Short strength, Short agility, Short dexterity, Short intelligence) {
        this.name = name;
        this.race = race;
        this.enabled = enabled;
        this.powerStats.update(strength, agility, dexterity, intelligence);
        this.updatedAt = Instant.now();
        return this;
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
