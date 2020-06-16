package com.bot.gachon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;
@EnableJpaAuditing
@EnableCaching
@EnableScheduling
@SpringBootApplication
public class GachonApplication {

    public static final String APPLICATION_LOCATIONS ="spring.config.location="
            +"classpath:application.yml";
//            +"/app/config/springboot-webservice/real-application.yml";

    public static void main(String[] args) {
        new SpringApplicationBuilder(GachonApplication.class)
            .profiles(APPLICATION_LOCATIONS)
                .run(args);
    }

}
