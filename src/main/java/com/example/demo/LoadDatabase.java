package com.example.demo;

import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

    private static final Logger log=org.slf4j.LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(TaskRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new Task("Task 1", "Description 1")));
            log.info("Preloading " + repository.save(new Task("Task 2", "Description 2",false)));
            log.info("Preloading " + repository.save(new Task("Task 3", "Description 3", true)));
        };
    }
    
}
