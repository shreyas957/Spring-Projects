package com.shreyas.conditional;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConditionalOnMissingBeanExample {

    @Bean
    @ConditionalOnMissingBean(name = "customBean")
    public String defaultBean() {
        return "Default Bean Loaded (no customBean found)";
    }
}

