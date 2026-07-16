package org.practice.springcrudmovie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SpringCrudMovieApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCrudMovieApplication.class, args);
    }

}
