package br.com.gubee.interview.domain.entities.hero;

import br.com.gubee.interview.domain.Entity;
import br.com.gubee.interview.domain.Identifier;
import br.com.gubee.interview.domain.entities.factory.TestsUtils;
import br.com.gubee.interview.domain.enums.Race;
import org.junit.Test;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static org.junit.Assert.*;

public class HeroTest {
    private static final Instant NOW = Instant.now().truncatedTo(ChronoUnit.SECONDS);
    private static final String CREATED_AT_MUST_BE_EQUALS_NOW = "Horario de criação não bate com horário atual";
    private static final String UPDATED_AT_MUST_BE_EQUALS_NOW = "Horario de atualização não bate com horário atual";
    private static final String HERO_NAME_MUST_BE_EQUALS = "Nome de heroi não bate com nome de heroi criado";
    private static final String HERO_RACE_MUST_BE_EQUALS = "Raca de heroi não bate com raca de heroi criado";
    private static final String HERO_ENABLED_VALUE_MUST_BE_TRUE = "Heroi deve estar ativo por padrão após sua criação";
    private static final Boolean HERO_ENABLED_VALUE_AFTER_CREATION = true;
    private static final String HERO_NAME_AT_TEST_UTILS = "hero_name";
    private static final Race HERO_RACE_AT_TEST_UTILS = Race.ALIEN;

    @Test
    public void testCreateHeroInstanceWithDefaultConstructor() {
        var instance = TestsUtils.getHeroInstance();
        assertNotNull(instance);
        assertEquals("659f1b4e-f2b0-4a7a-a34d-b0aea64a0358", instance.getId().getValue());
        assertEquals(CREATED_AT_MUST_BE_EQUALS_NOW, NOW, instance.getCreatedAt());
        assertEquals(UPDATED_AT_MUST_BE_EQUALS_NOW, NOW, instance.getUpdatedAt());
    }

    @Test
    public void testCreateHeroInstanceWithFullArgsConstructor() {
        var instance = TestsUtils.getHeroInstanceWithFullArgs();
        assertNotNull(instance);
        assertEquals("659f1b4e-f2b0-4a7a-a34d-b0aea64a0358", instance.getId().getValue());
        assertEquals(CREATED_AT_MUST_BE_EQUALS_NOW, NOW, instance.getCreatedAt());
        assertEquals(UPDATED_AT_MUST_BE_EQUALS_NOW, NOW, instance.getUpdatedAt());
        assertEquals(HERO_NAME_MUST_BE_EQUALS, HERO_NAME_AT_TEST_UTILS, instance.getName());
        assertEquals(HERO_RACE_MUST_BE_EQUALS, HERO_RACE_AT_TEST_UTILS, instance.getRace());

        assertNotNull(instance.getPowerStats());
        assertEquals("c04e76e0-00f3-439a-ba04-95c7e4bc8c33", instance.getPowerStats().getId().getValue());
        assertEquals(NOW, instance.getPowerStats().getCreatedAt().truncatedTo(ChronoUnit.SECONDS));
        assertEquals(NOW, instance.getPowerStats().getUpdatedAt().truncatedTo(ChronoUnit.SECONDS));
        assertEquals(HERO_ENABLED_VALUE_MUST_BE_TRUE, HERO_ENABLED_VALUE_AFTER_CREATION, instance.getEnabled());
    }

    @Test
    public void testHeroId() {
        var id = TestsUtils.getHeroIdInstance();
        assertNotNull(id);
        assertEquals("659f1b4e-f2b0-4a7a-a34d-b0aea64a0358", id.getValue());
    }

}
