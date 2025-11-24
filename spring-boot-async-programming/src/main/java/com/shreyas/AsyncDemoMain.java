package com.shreyas;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.Future;

@SpringBootApplication
@EnableAsync
public class AsyncDemoMain {
    public static void main(String[] args) {
        SpringApplication.run(AsyncDemoMain.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(UserService userService) {
        return args -> {
            System.out.println("commandLineRunner: " + Thread.currentThread().getName());
            userService.asyncMethod();
            userService.asyncMethod2();

            Future<String> stringFuture = userService.asyncMethod3();
            System.out.println(stringFuture.get());

            userService.asyncMethod4();
        };
    }
}
