package com.shreyas.conditional;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConditionalOnExpressionExample {

    @Bean
    @ConditionalOnExpression("'${app.env}'=='dev' or ${feature.enabled:false}")
    public String conditionalBean() {
        return "Loaded if app.env=dev OR feature.enabled=true";
    }
}

