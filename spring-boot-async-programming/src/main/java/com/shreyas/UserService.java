package com.shreyas;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Service
public class UserService {
    @Async
    public void asyncMethod() {
        System.out.println("asyncMethod: " + Thread.currentThread().getName());
    }

    @Async("my-executor-1")
    public void asyncMethod2() {
        System.out.println("asyncMethod2: " + Thread.currentThread().getName());
    }

    // both methods will use same bean i.e. my-executor
    @Async
    public Future<String> asyncMethod3() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return CompletableFuture.supplyAsync(
                () -> "asyncMethod3 " + Thread.currentThread().getName()
        );
    }

    @Async
    public void asyncMethod4() {
        int i = 0;
        System.out.println(5 / i);
    }
}
