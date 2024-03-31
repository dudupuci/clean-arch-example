package br.com.gubee.interview.infrastructure.persistence.hero;

import br.com.gubee.interview.domain.entities.hero.Hero;
import br.com.gubee.interview.domain.entities.hero.HeroId;
import br.com.gubee.interview.domain.entities.hero.HeroRepository;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class JdbcHeroRepository implements HeroRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public JdbcHeroRepository(final NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = Objects.requireNonNull(jdbcTemplate);
    }

    @Override
    @Transactional
    public Hero save(Hero anHero) {
        String sql = "INSERT INTO interview_service.hero(" +
                "id, " +
                "created_at, " +
                "updated_at, " +
                "race, " +
                "power_stats_id, " +
                "enabled) " +
                "VALUES (:id, :created_at, :updated_at, :race, :power_stats_id, :enabled)";

        final var now = Instant.now();
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", HeroId.unique().getValue())
                .addValue("created_at", now)
                .addValue("updated_at", now)
                .addValue("race", anHero.getRace())
                .addValue("power_stats_id", anHero.getPowerStats().getId().getValue())
                .addValue("enabled", anHero.getEnabled());
        jdbcTemplate.update(sql, params);

        return anHero;
    }

    @Override
    public void update(Hero anHero) {

    }

    @Override
    public Optional<Hero> findById(String id) {
        return Optional.empty();
    }

    @Override
    public void deleteById(String id) {

    }

    @Override
    public List<Hero> findAll(String name) {
        return null;
    }
}
