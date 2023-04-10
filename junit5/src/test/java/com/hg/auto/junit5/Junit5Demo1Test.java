package com.hg.auto.junit5;

import org.junit.jupiter.api.*;

/**
 * BeforeAll   = 类加载前执行
 * BeforeEach  = 每个方法前执行
 * Test   = 测试方法
 * DisplayName  = 说明：会把执行结果重命名
 * Disabled = 过滤不执行
 *
 * @RepeatedTest(3) = 循环执行(次数)[可以不实用@Test注解]
 * AfterEach   = 每个方法后执行
 * AfterAll    = 类结束前执行
 */

@DisplayName("类说明")
public class Junit5Demo1Test {

    @BeforeAll
    public static void beforeAll() {
        System.out.println("BeforeAll is runner^");
    }

    @BeforeEach
    public void beforeEach() {
        System.out.println("BeforeEach is runner ……");
    }

    @Test
    @DisplayName("方法说明001")
    @Tag("junit5Tag001")
    public void testDemo1() {
        System.out.println("testDemo1 method is runner……");
    }

    //    @Test
    @DisplayName("方法说明002")
    @RepeatedTest(3)
    public void testDemo2() {
        System.out.println("testDemo2 method is runner……");
    }

    @Test
    @DisplayName("方法说明003")
    @Disabled //过滤不执行
    public void testDemo3() {
        System.out.println("testDemo3 method is runner……");
    }

    @AfterEach
    public void afterEach() {
        System.out.println("AfterEach is runner ……");
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("AfterAll is runner^");
    }

}
