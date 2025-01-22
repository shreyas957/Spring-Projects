package com.shreyas.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.SQLOutput;

@SpringBootApplication
public class LearnLogging {
    private static final Logger logger = LoggerFactory.getLogger(LearnLogging.class);

    public static void main(String[] args) {
        SpringApplication.run(LearnLogging.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            System.out.println("Hello Shreyas!");
            logger.info("Hello, World!");
            logger.info("Hi {}!", "there");  // using placeholders
            LogUtil.debug(LearnLogging.class, "This is a debug message");   // to print debug level I need to set logging level to DEBUG or above i.e. TRACE
        };
    }
}
