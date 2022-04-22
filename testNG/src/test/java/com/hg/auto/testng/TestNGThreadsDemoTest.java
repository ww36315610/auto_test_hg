package com.hg.auto.testng;

import org.testng.annotations.*;

/**
 * 多线程
 */
public class TestNGThreadsDemoTest {

    @Test
    public void TestDemo001(){
        System.out.println("TestDemo001 is ########");
    }

    @Test
    public void TestDemo002(){
        System.out.println("TestDemo002 is ########");
    }

    @Test(threadPoolSize = 5,invocationCount = 5,timeOut = 60000)
    public void TestDemo003(){
        long tId = Thread.currentThread().getId();
        System.out.println("ThreadID= "+ tId + "TestDemo003 is ########");
    }

    @Test
    public void TestDemo004(){
        System.out.println("TestDemo004 is ########");
    }

    @Test
    public void TestDemo005(){
        System.out.println("TestDemo005 is ########");
    }
}
