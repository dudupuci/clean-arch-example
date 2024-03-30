package br.com.gubee.interview.domain.entities.hero;

import br.com.gubee.interview.domain.Identifier;

import java.util.Objects;
import java.util.UUID;

public class HeroId extends Identifier {

    protected final String value;

    public HeroId(final String value) {
        Objects.requireNonNull(value);
        this.value = value;
    }
    public static HeroId unique() {
        return HeroId.from(UUID.randomUUID());
    }

    private static HeroId from(final String anId) {
        return new HeroId(anId);
    }

    private static HeroId from(final UUID anId) {
        return new HeroId(anId.toString().toLowerCase());
    }

    @Override
    public String getValue() {
        return this.value;
    }
}
