package br.com.gubee.interview.application.usecases.hero.comparision;

import br.com.gubee.interview.domain.entities.hero.Hero;
import br.com.gubee.interview.domain.entities.hero.HeroRepository;
import br.com.gubee.interview.domain.exceptions.CannotCompareTheSameHeroException;
import br.com.gubee.interview.domain.exceptions.HeroNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ComparisionUseCaseImpl extends ComparisionUseCase {

    private final HeroRepository heroRepository;

    public ComparisionUseCaseImpl(final HeroRepository heroRepository) {
        this.heroRepository = heroRepository;
    }

    @Override
    public ComparisionOutput execute(final ComparisionCommand anIn) {
        try {
            Optional<Hero> optionalHero = this.heroRepository.findById(anIn.heroId());
            Optional<Hero> optionalAnotherHero = this.heroRepository.findById(anIn.anotherHeroId());

            assert optionalHero.isPresent();
            assert optionalAnotherHero.isPresent();

            Hero hero = optionalHero.get();
            Hero anotherHero = optionalAnotherHero.get();

            if (hero.getId().getValue().equals(anotherHero.getId().getValue())) {
                throw new CannotCompareTheSameHeroException("Impossible to compare the jdbc x jdbc");
            }

            List<String> details = new ArrayList<>();
            var heroPowerStats = hero.getPowerStats();
            var anotherHeroPowerStats = anotherHero.getPowerStats();

            comparePowerStat("strength", heroPowerStats.getStrength(), anotherHeroPowerStats.getStrength(), hero.getName(), anotherHero.getName(), details);
            comparePowerStat("agility", heroPowerStats.getAgility(), anotherHeroPowerStats.getAgility(), hero.getName(), anotherHero.getName(), details);
            comparePowerStat("dexterity", heroPowerStats.getDexterity(), anotherHeroPowerStats.getDexterity(), hero.getName(), anotherHero.getName(), details);
            comparePowerStat("intelligence", heroPowerStats.getIntelligence(), anotherHeroPowerStats.getIntelligence(), hero.getName(), anotherHero.getName(), details);

            return new ComparisionOutput(
                    new HeroComparisionOutput(
                            hero.getId().getValue(),
                            hero.getName(),
                            ComparisionOutput.HeroPowerStatsOutput.from(heroPowerStats)
                    ),
                    new HeroComparisionOutput(
                            anotherHero.getId().getValue(),
                            anotherHero.getName(),
                            ComparisionOutput.HeroPowerStatsOutput.from(anotherHeroPowerStats)
                    ),
                    details
            );
        } catch (EmptyResultDataAccessException err) {
            throw new HeroNotFoundException("Hero not found");
        }
    }

    private void comparePowerStat(
            String statName,
            Short heroStat,
            Short anotherHeroStat,
            String heroName,
            String anotherHeroName,
            List<String> details
    ) {
        if (heroStat > anotherHeroStat) {
            details.add("Hero with name: " + heroName + " has more " + statName + " than " + anotherHeroName);
            details.add("With a difference of " + (heroStat - anotherHeroStat));
        } else {
            details.add("Hero with name: " + anotherHeroName + " has more " + statName + " than " + heroName);
            details.add("With a difference of " + (anotherHeroStat - heroStat));
        }
    }
}
