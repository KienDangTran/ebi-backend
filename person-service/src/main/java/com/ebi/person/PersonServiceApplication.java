package com.ebi.person;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.ebi.person", "com.ebi.common"})
@EntityScan({"com.ebi.person"})
@EnableJpaRepositories({"com.ebi.person"})
public class PersonServiceApplication {
  public static void main(String[] args) {
    new SpringApplicationBuilder(PersonServiceApplication.class)
        .properties("spring.config.additional-location:classpath:application-common.yml")
        .build().run(args);
  }
}
