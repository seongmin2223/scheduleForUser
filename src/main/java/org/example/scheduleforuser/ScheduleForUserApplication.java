package org.example.scheduleforuser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ScheduleForUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScheduleForUserApplication.class, args);
    }

}
