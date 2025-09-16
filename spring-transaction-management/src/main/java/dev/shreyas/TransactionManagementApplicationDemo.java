package dev.shreyas;

import dev.shreyas.propagation.PropagationExample;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TransactionManagementApplicationDemo {
    public static void main(String[] args) {
        SpringApplication.run(TransactionManagementApplicationDemo.class, args);
    }

    @Bean
    CommandLineRunner init(PropagationExample propagationExample) {
        return args -> {
            propagationExample.updateUser();
            propagationExample.updateUserFromNonTransactionMethod();
        };
    }
}
