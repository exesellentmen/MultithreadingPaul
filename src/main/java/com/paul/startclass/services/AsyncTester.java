package com.paul.startclass.services;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAsync
public class AsyncTester {
    @Async
    public void saveTest(int i) throws InterruptedException {
        Thread.sleep(1000 - i*100);
        System.out.println(i);
    }
}
