package com.shreyas;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Configuration
public class AsyncConfig implements AsyncConfigurer {

    private final AsyncUncaughtExceptionHandler asyncUncaughtExceptionHandler;

    public AsyncConfig(AsyncUncaughtExceptionHandler asyncUncaughtExceptionHandler) {
        this.asyncUncaughtExceptionHandler = asyncUncaughtExceptionHandler;
    }

    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return this.asyncUncaughtExceptionHandler;
    }
}

@Component
class DefaultAsyncExceptionHandler implements AsyncUncaughtExceptionHandler {
    @Override
    public void handleUncaughtException(Throwable ex, Method method, Object... params) {
        // handling exception here thrown by @Async method
        System.out.println("Handling exception : " + ex.getMessage() + " from method : " + method.getName());
    }
}
