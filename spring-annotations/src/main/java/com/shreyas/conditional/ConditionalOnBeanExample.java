package com.shreyas.conditional;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConditionalOnBeanExample {

    @Bean
    public String parentBean() {
        return "Parent Bean";
    }

    @Bean
    @ConditionalOnBean(name = "parentBean")
    public String childBean() {
        return "Child Bean (loaded because parentBean exists)";
    }
}

