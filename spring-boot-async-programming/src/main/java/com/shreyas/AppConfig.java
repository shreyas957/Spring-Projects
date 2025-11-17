package com.shreyas;

import org.springframework.boot.task.SimpleAsyncTaskExecutorBuilder;
import org.springframework.boot.task.SimpleAsyncTaskExecutorCustomizer;
import org.springframework.boot.task.ThreadPoolTaskExecutorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.lang.Nullable;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

@Configuration
public class AppConfig {

    @Bean("my-executor-1")
    public Executor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(10);
        executor.setKeepAliveSeconds(60);
        executor.setThreadNamePrefix("AsyncDemo1-");
        executor.initialize();
        return executor;
    }

    @Bean("my-executor-4")
    public Executor myExecutor(ThreadPoolTaskExecutorBuilder builder) {  // we also have SimpleAsyncTaskExecutorBuilder
        return builder
                .corePoolSize(5)
                .maxPoolSize(10)
                .queueCapacity(10)
                .threadNamePrefix("AsyncDemo4-")
                .build();
    }

    @Bean("my-executor-2")
    public Executor threadPoolTaskExecutor2(ThreadFactory threadFactory) {
        return Executors.newThreadPerTaskExecutor(threadFactory);
    }

    @Bean
    public ThreadFactory threadFactory() {
        final AtomicInteger threadNum = new AtomicInteger(1);
        return new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setName("AsyncDemo2-" + threadNum.getAndIncrement());
                return t;
            }
        };
    }

    @Bean("my-executor-3")
    public Executor threadPoolTaskExecutor3() {

        return new ThreadPoolExecutor(
                5,
                10,
                60,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10, true)
        );
    }
}
