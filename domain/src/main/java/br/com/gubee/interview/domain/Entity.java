package br.com.gubee.interview.domain;

import lombok.Getter;

import java.time.Instant;
import java.util.Objects;

@Getter
public abstract class Entity<ID extends Identifier> {
    protected final ID id;
    protected final Instant createdAt;
    protected Instant updatedAt;

    protected Entity(final ID id, final Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public abstract void validate();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Entity<?> entity)) return false;
        return Objects.equals(id, entity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
