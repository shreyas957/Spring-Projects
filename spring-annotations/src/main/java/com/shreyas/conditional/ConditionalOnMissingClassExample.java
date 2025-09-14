package com.shreyas.conditional;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnMissingClass("com.mysql.cj.jdbc.Driver")
public class ConditionalOnMissingClassExample {

    @Bean
    public String fallbackBean() {
        return "Fallback loaded because MySQL driver NOT found.";
    }
}

