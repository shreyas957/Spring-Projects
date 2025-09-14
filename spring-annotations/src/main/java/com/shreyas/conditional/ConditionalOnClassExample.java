package com.shreyas.conditional;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(name = "com.mysql.cj.jdbc.Driver")
public class ConditionalOnClassExample {

    @Bean
    public String mysqlBean() {
        return "MySQL driver found!";
    }
}

