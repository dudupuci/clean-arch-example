package br.com.gubee.interview.infrastructure;

import br.com.gubee.interview.domain.entities.hero.Hero;
import br.com.gubee.interview.domain.entities.hero.HeroId;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.relational.core.sql.In;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.time.Instant;
import java.util.logging.Logger;

@SpringBootApplication
@EnableTransactionManagement
@EnableCaching
public class InfrastructureApplication {

    public static void main(String[] args) {
        SpringApplication.run(InfrastructureApplication.class, args);
    }

}
