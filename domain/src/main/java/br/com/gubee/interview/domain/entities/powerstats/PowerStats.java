package br.com.gubee.interview.domain.entities.powerstats;

import br.com.gubee.interview.domain.Entity;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class PowerStats extends Entity<PowerStatsId> {
    private Short strength;
    private Short agility;
    private Short dexterity;
    private Short intelligence;

    public PowerStats(
            final PowerStatsId powerStatsId,
            final Instant createdAt,
            Instant updatedAt
    ) {
        super(powerStatsId, createdAt, updatedAt);
    }

    public PowerStats(
            final PowerStatsId powerStatsId,
            final Instant createdAt,
            Instant updatedAt,
            Short strength,
            Short agility,
            Short dexterity,
            Short intelligence
    ) {
        super(powerStatsId, createdAt, updatedAt);
        this.strength = strength;
        this.agility = agility;
        this.dexterity = dexterity;
        this.intelligence = intelligence;
    }

    @Override
    public void validate() {

    }


}
