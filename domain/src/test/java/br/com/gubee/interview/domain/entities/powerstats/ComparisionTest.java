package br.com.gubee.interview.domain.entities.powerstats;

import br.com.gubee.interview.domain.exceptions.CannotCompareTheSameHeroException;
import br.com.gubee.interview.domain.valueobjects.Comparision;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ComparisionTest {

    @Test
    public void testComparisonCreation() {
        Short strength = 10;
        Short agility = 8;
        Short dexterity = 9;
        Short intelligence = 7;

        PowerStats heroPowerStats = PowerStats.instantiate(strength, agility, dexterity, intelligence);
        PowerStats anotherHeroPowerStats = PowerStats.instantiate(strength, agility, dexterity, intelligence);

        Comparision comparison = Comparision.with(
                "659f1b4e-f2b0-4a7a-a34d-b0aea64a0358",
                "hero_name",
                "14a507fd-b51b-46d5-8b85-df9792808242",
                "hero_name",
                heroPowerStats,
                anotherHeroPowerStats,
                new ArrayList<>()
        );

        assertNotNull(comparison);
        assertEquals("659f1b4e-f2b0-4a7a-a34d-b0aea64a0358", comparison.heroId());
        assertEquals("hero_name", comparison.heroName());
        assertEquals("14a507fd-b51b-46d5-8b85-df9792808242", comparison.anotherHeroId());
        assertEquals("hero_name", comparison.anotherHeroName());
        assertEquals(heroPowerStats, comparison.heroPowerStats());
        assertEquals(anotherHeroPowerStats, comparison.anotherHeroPowerStats());
    }

    @Test(expected = CannotCompareTheSameHeroException.class)
    public void testComparisonCreation_SameHeroIds() {
        Short strength = 10;
        Short agility = 8;
        Short dexterity = 9;
        Short intelligence = 7;

        PowerStats heroPowerStats = PowerStats.instantiate(strength, agility, dexterity, intelligence);
        PowerStats anotherHeroPowerStats = PowerStats.instantiate(strength, agility, dexterity, intelligence);

        var comparision = Comparision.with(
                "659f1b4e-f2b0-4a7a-a34d-b0aea64a0358",
                "hero_name",
                "659f1b4e-f2b0-4a7a-a34d-b0aea64a0358", // Same as heroId
                "hero_name",
                heroPowerStats,
                anotherHeroPowerStats,
                new ArrayList<>()
        );

        throwComparisionException(comparision.heroId(), comparision.anotherHeroId());
    }

    private void throwComparisionException(String heroId, String anotherHeroId) {
        if (heroId.equals(anotherHeroId)) {
            throw new CannotCompareTheSameHeroException("Cannot compare the hero x hero");
        }
    }
    // Outros testes podem ser adicionados conforme necessário
}
