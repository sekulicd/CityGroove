package com.sekulicd.citygroove;

import com.sekulicd.citygroove.infrastracture.persistance.h2.H2JpaRepositoryConfiguration;
//import com.sekulicd.citygroove.infrastracture.persistance.mysql.MySqlJpaRepositoryConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EntityScan(
        basePackageClasses = { CityGrooveApplication.class }
)
@Import({SecurityConfigurator.class, H2JpaRepositoryConfiguration.class})
public class CityGrooveApplication {

    public static void main(String[] args) {
        SpringApplication.run(CityGrooveApplication.class, args);
    }

}
