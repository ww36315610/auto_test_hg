package com.test.hg.restassured.wechart.department;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

public class ThreadDemo {

    @DisplayName("thread_demo1")
    @Execution(ExecutionMode.CONCURRENT)
    @RepeatedTest(10)
    public void threadDemo1() throws InterruptedException {
        Thread.sleep(1000);
        long id = Thread.currentThread().getId();
        System.out.println("Thread1::: "+id);
    }

    @DisplayName("thread_demo2")
    @Execution(ExecutionMode.CONCURRENT)
    @RepeatedTest(10)
    public void threadDemo2() throws InterruptedException {
        Thread.sleep(1000);
        long id = Thread.currentThread().getId();
        System.out.println("Thread2::: "+id);
    }
}
