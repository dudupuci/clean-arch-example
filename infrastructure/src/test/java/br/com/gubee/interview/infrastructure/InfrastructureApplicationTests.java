package br.com.gubee.interview.infrastructure;

import br.com.gubee.interview.application.usecases.hero.create.CreateHeroCommand;
import br.com.gubee.interview.domain.entities.hero.Hero;
import br.com.gubee.interview.domain.entities.powerstats.PowerStats;
import br.com.gubee.interview.domain.enums.Race;
import br.com.gubee.interview.infrastructure.configuration.usecases.HeroFacade;
import br.com.gubee.interview.infrastructure.configuration.usecases.impl.HeroFacadeImpl;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


//@SpringBootTest
class InfrastructureApplicationTests {

    //@Autowired
    private HeroFacade facade;

   /* @Test
    public void test() {
        var hero = Hero.instantiate("name", Race.ALIEN,
                PowerStats.instantiate(
                        (short)1,
                        (short)1,
                        (short)1,
                        (short)1
                )
        );

        facade.create(new CreateHeroCommand(hero.getName(), hero.getRace(), hero.getPowerStats()));
    }

    */

}
