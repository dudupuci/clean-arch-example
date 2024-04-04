package br.com.gubee.interview.domain.entities.powerstats;

import br.com.gubee.interview.domain.Identifier;
import lombok.Getter;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class PowerStatsId extends Identifier implements Serializable {

    protected final String value;

    public PowerStatsId(final String value) {
        Objects.requireNonNull(value);
        this.value = value;
    }

    public static PowerStatsId unique() {
        return PowerStatsId.from(UUID.randomUUID());
    }

    public static PowerStatsId from(final String anId) {
        return new PowerStatsId(anId);
    }

    private static PowerStatsId from(final UUID anId) {
        return new PowerStatsId(anId.toString().toLowerCase());
    }

    @Override
    public String getValue() {
        return this.value;
    }
}
