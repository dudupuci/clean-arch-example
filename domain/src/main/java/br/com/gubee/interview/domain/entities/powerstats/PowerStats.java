package br.com.gubee.interview.domain.entities.powerstats;

import br.com.gubee.interview.domain.Entity;
import br.com.gubee.interview.domain.exceptions.InvalidPowerValueException;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class PowerStats extends Entity<PowerStatsId> {
    private Short strength;
    private Short agility;
    private Short dexterity;
    private Short intelligence;

    public static PowerStats instantiate(
            final Short strength,
            final Short agility,
            final Short dexterity,
            final Short intelligence
    ) {
        final var id = PowerStatsId.unique();
        final var now = Instant.now();

        return new PowerStats(
                id,
                now,
                now,
                strength,
                agility,
                dexterity,
                intelligence
        );
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
        if (anyValueIsLessThanThreshold(this.strength, this.agility, this.dexterity, this.intelligence)) {
            throw new InvalidPowerValueException(getFieldNameWithValueLessThanThreshold(
                    this.strength,
                    this.agility,
                    this.dexterity,
                    this.intelligence
            ) + " should not be zero");
        }
    }

    private boolean anyValueIsLessThanThreshold(Short... values) {
        for (Short value : values) {
            if (value < (short) 1) {
                return true;
            }
        }
        return false;
    }

    private String getFieldNameWithValueLessThanThreshold(Short... values) {
        String[] fieldNames = {"strength", "agility", "dexterity", "intelligence"};
        for (int i = 0; i < values.length; i++) {
            if (values[i] < (short) 1) {
                return fieldNames[i];
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "PowerStats{" +
                "strength=" + strength +
                ", agility=" + agility +
                ", dexterity=" + dexterity +
                ", intelligence=" + intelligence +
                ", id=" + id.getValue() +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
