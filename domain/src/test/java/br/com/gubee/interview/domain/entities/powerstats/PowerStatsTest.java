package br.com.gubee.interview.domain.entities.powerstats;

import br.com.gubee.interview.domain.entities.factory.TestsUtils;
import br.com.gubee.interview.domain.exceptions.InvalidPowerValueException;
import org.junit.Test;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static org.junit.Assert.*;

public class PowerStatsTest {
    private static final Instant NOW = Instant.now().truncatedTo(ChronoUnit.SECONDS);
    private static final String CREATED_AT_MUST_BE_EQUALS_NOW = "Horario de criação não bate com horário atual";
    private static final String UPDATED_AT_MUST_BE_EQUALS_NOW = "Horario de atualização não bate com horário atual";

    @Test
    public void testCreatePowerStatsInstanceWithDefaultConstructor() {
        var instance = TestsUtils.getPowerStatsInstance();
        assertNotNull(instance);
        assertEquals(CREATED_AT_MUST_BE_EQUALS_NOW, NOW, instance.getCreatedAt());
        assertEquals(UPDATED_AT_MUST_BE_EQUALS_NOW, NOW, instance.getUpdatedAt());
    }

    @Test
    public void testCreatePowerStatsInstanceWithFullArgsConstructor() {
        var instance = TestsUtils.getPowerStatsInstanceWithFullArgs();
        assertNotNull(instance);
        assertEquals(CREATED_AT_MUST_BE_EQUALS_NOW, NOW, instance.getCreatedAt());
        assertEquals(UPDATED_AT_MUST_BE_EQUALS_NOW, NOW, instance.getUpdatedAt());
        // Adicione asserções adicionais conforme necessário para verificar os valores dos campos de PowerStats
    }

    @Test
    public void testValidateMethod() {
        PowerStats powerStats = new PowerStats(
                PowerStatsId.unique(),
                Instant.now(),
                Instant.now(),
                (short) 0,
                (short) 10,
                (short) 20,
                (short) 30
        );
        assertThrows(InvalidPowerValueException.class, powerStats::validate);
    }

    @Test
    public void testEqualityBetweenTwoObjects() {
        var id = TestsUtils.getPowerStatsIdInstance();
        PowerStats powerStats = new PowerStats(
                id,
                NOW,
                NOW,
                (short) 1,
                (short) 2,
                (short) 3,
                (short) 4
        );

        PowerStats anotherPowerStats = new PowerStats(
                id,
                NOW,
                NOW,
                (short) 1,
                (short) 2,
                (short) 3,
                (short) 4
        );
        assertNotNull(powerStats);
        assertNotNull(anotherPowerStats);
        assertEquals(powerStats, anotherPowerStats);
        assertEquals("c04e76e0-00f3-439a-ba04-95c7e4bc8c33", id.getValue());
    }

    @Test
    public void testPowerStatsId() {
        var id = TestsUtils.getPowerStatsIdInstance();
        assertNotNull(id);
        assertEquals("c04e76e0-00f3-439a-ba04-95c7e4bc8c33", id.getValue());
    }
}
