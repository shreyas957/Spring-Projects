package com.shreyas.conditional;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConditionalOnPropertyExample {
    @Bean
    @ConditionalOnProperty(
            name = "feature.enabled",
            havingValue = "true",
            matchIfMissing = false
    )
    public String featureBean() {
        return "Feature is Enabled!";
    }
}
