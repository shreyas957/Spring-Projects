package com.shreyas;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

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
}
