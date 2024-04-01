package br.com.gubee.interview.infrastructure.persistence.hero;

import br.com.gubee.interview.domain.entities.hero.Hero;
import br.com.gubee.interview.domain.entities.hero.HeroId;
import br.com.gubee.interview.domain.entities.hero.HeroRepository;
import br.com.gubee.interview.domain.entities.powerstats.PowerStats;
import br.com.gubee.interview.domain.entities.powerstats.PowerStatsId;
import br.com.gubee.interview.domain.enums.Race;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class JdbcHeroRepository implements HeroRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public JdbcHeroRepository(final NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = Objects.requireNonNull(jdbcTemplate);
    }

    @Override
    @Transactional
    public void update(final Hero anHero) {
        StringBuilder sql = new StringBuilder("UPDATE interview_service.hero SET ");
        MapSqlParameterSource params = new MapSqlParameterSource();

        boolean needComma = false;

        if (anHero.getName() != null) {
            sql.append("name = :name");
            params.addValue("name", anHero.getName());
            needComma = true;
        }

        if (anHero.getRace() != null) {
            if (needComma) {
                sql.append(", ");
            }
            sql.append("race = :race");
            params.addValue("race", anHero.getRace().toString());
            needComma = true;
        }

        if (needComma) {
            sql.append(", ");
        }
        sql.append("updated_at = :updated_at ");
        sql.append("WHERE id = :id");
        params.addValue("updated_at", Timestamp.from(Instant.now()))
                .addValue("id", UUID.fromString(HeroId.from(anHero.getId().getValue()).getValue()));

        jdbcTemplate.update(sql.toString(), params);

        PowerStats powerStats = anHero.getPowerStats();

        if (powerStats != null) {
            StringBuilder powerStatsSql = new StringBuilder("UPDATE interview_service.power_stats SET ");
            MapSqlParameterSource powerStatsParams = new MapSqlParameterSource();

            if (powerStats.getStrength() != null) {
                powerStatsSql.append("strength = :strength, ");
                powerStatsParams.addValue("strength", powerStats.getStrength());
            }

            if (powerStats.getAgility() != null) {
                powerStatsSql.append("agility = :agility, ");
                powerStatsParams.addValue("agility", powerStats.getAgility());
            }

            if (powerStats.getDexterity() != null) {
                powerStatsSql.append("dexterity = :dexterity, ");
                powerStatsParams.addValue("dexterity", powerStats.getDexterity());
            }

            if (powerStats.getIntelligence() != null) {
                powerStatsSql.append("intelligence = :intelligence, ");
                powerStatsParams.addValue("intelligence", powerStats.getIntelligence());
            }

            if (powerStatsSql.lastIndexOf(", ") != -1) {
                powerStatsSql.delete(powerStatsSql.lastIndexOf(", "), powerStatsSql.length());
            }

            powerStatsSql.append(", updated_at = :updated_at");
            powerStatsSql.append(" WHERE id = :id");
            powerStatsParams.addValue("id", UUID.fromString(powerStats.getId().getValue()))
                    .addValue("updated_at", Timestamp.from(Instant.now()));


            jdbcTemplate.update(powerStatsSql.toString(), powerStatsParams);
        }
    }


    @Override
    @Transactional
    public Hero save(final Hero anHero) {
        final var now = Timestamp.from(Instant.now());

        String powerStatsSql = "INSERT INTO interview_service.power_stats(" +
                "id, " +
                "created_at, " +
                "updated_at, " +
                "strength, " +
                "agility, " +
                "dexterity, " +
                "intelligence) " +
                "VALUES (:id, :created_at, :updated_at, :strength, :agility, :dexterity, :intelligence)";

        MapSqlParameterSource powerStatsParams = new MapSqlParameterSource()
                .addValue("id", UUID.fromString(PowerStatsId.from(anHero.getPowerStats().getId().getValue()).getValue()))
                .addValue("created_at", now)
                .addValue("updated_at", now)
                .addValue("strength", anHero.getPowerStats().getStrength())
                .addValue("agility", anHero.getPowerStats().getAgility())
                .addValue("dexterity", anHero.getPowerStats().getDexterity())
                .addValue("intelligence", anHero.getPowerStats().getIntelligence());
        jdbcTemplate.update(powerStatsSql, powerStatsParams);

        String sql = "INSERT INTO interview_service.hero(" +
                "id, " +
                "created_at, " +
                "updated_at, " +
                "name, " +
                "race, " +
                "power_stats_id, " +
                "enabled) " +
                "VALUES (:id, :created_at, :updated_at, :name, :race, :power_stats_id, :enabled)";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", UUID.fromString(HeroId.from(anHero.getId().getValue()).getValue()))
                .addValue("created_at", now)
                .addValue("updated_at", now)
                .addValue("name", anHero.getName())
                .addValue("race", anHero.getRace().name())
                .addValue("power_stats_id", UUID.fromString(anHero.getPowerStats().getId().getValue()))
                .addValue("enabled", anHero.getEnabled());
        jdbcTemplate.update(sql, params);

        return anHero;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Hero> findAll(String name) {
        String sql = "SELECT h.*, p.* FROM interview_service.hero h " +
                "JOIN interview_service.power_stats p ON h.power_stats_id = p.id";

        MapSqlParameterSource params = new MapSqlParameterSource();

        if (name != null && !name.isEmpty()) {
            sql += " WHERE h.name = :name";
            params.addValue("name", name);
        }

        return jdbcTemplate.query(sql, params, (rs, rowNum) -> new Hero(
                HeroId.from(rs.getString("id")),
                rs.getTimestamp("created_at").toInstant(),
                rs.getTimestamp("updated_at").toInstant(),
                rs.getString("name"),
                Race.valueOf(rs.getString("race")),
                new PowerStats(
                        PowerStatsId.from(rs.getString("power_stats_id")),
                        rs.getTimestamp("created_at").toInstant(),
                        rs.getTimestamp("updated_at").toInstant(),
                        rs.getShort("strength"),
                        rs.getShort("agility"),
                        rs.getShort("dexterity"),
                        rs.getShort("intelligence")
                ),
                rs.getBoolean("enabled")
        ));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Hero> findById(final String id) {
        String sql = "SELECT h.*, p.* FROM interview_service.hero h " +
                "JOIN interview_service.power_stats p ON h.power_stats_id = p.id " +
                "WHERE h.id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", UUID.fromString(id));

        return jdbcTemplate.queryForObject(sql, params, (rs, rowNum) -> {
            Hero hero = new Hero(
                    HeroId.from(rs.getString("id")),
                    rs.getTimestamp("created_at").toInstant(),
                    rs.getTimestamp("updated_at").toInstant(),
                    rs.getString("name"),
                    Race.valueOf(rs.getString("race")),
                    new PowerStats(
                            PowerStatsId.from(rs.getString("power_stats_id")),
                            rs.getTimestamp("created_at").toInstant(),
                            rs.getTimestamp("updated_at").toInstant(),
                            rs.getShort("strength"),
                            rs.getShort("agility"),
                            rs.getShort("dexterity"),
                            rs.getShort("intelligence")
                    ),
                    rs.getBoolean("enabled")
            );

            return Optional.of(hero);
        });
    }

    @Override
    public void deleteById(String id) {

    }
}
