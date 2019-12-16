package com.sekulicd.citygroove;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(
        basePackageClasses = { CityGrooveApplication.class }
)
@Import({SecurityConfigurator.class})
@EnableJpaRepositories
public class CityGrooveApplication {

    public static void main(String[] args) {
        SpringApplication.run(CityGrooveApplication.class, args);
    }

}
