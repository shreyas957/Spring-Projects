package dev.shreyas;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
    @Before("execution(public String dev.shreyas.Controller.aop())")
    public void beforeMethod() {
        System.out.println("before method invoked");
    }
}
