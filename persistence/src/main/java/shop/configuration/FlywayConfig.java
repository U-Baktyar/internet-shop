package shop.configuration;


import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:application.properties")
public class FlywayConfig {

    @Value("${flyway.migration_file}")
    private String flywayMigrationFile;


    @Bean(initMethod = "migrate")
    public Flyway flyway(DataSource dataSource) {
        return Flyway
                .configure()
                .dataSource(dataSource)
                .locations(flywayMigrationFile)
//                .validateMigrationNaming(true)
                .baselineOnMigrate(true)
                .validateOnMigrate(true)
                .load();

    }
}
