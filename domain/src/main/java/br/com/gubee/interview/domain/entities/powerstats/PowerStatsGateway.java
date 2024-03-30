package br.com.gubee.interview.domain.entities.powerstats;

import java.util.List;
import java.util.Optional;

public interface PowerStatsGateway {
    PowerStats create(PowerStats anPowerStats);
    void update(PowerStats anPowerStats);
    Optional<PowerStats> findById(String id);
    void deleteById(String id);
    List<PowerStats> findAll();
}
