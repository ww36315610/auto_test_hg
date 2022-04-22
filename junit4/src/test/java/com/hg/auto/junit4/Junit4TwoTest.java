package com.hg.auto.junit4;

import org.junit.*;

public class Junit4TwoTest {

    /**
     * Suit 测试集
     */
    @BeforeClass
    public static void beforeClass() {
        System.out.println("Junit4_TwoTest.beforeClass1");
    }

    @Before
    public void before() {
        System.out.println("Junit4_TwoTest.before2-for");
    }

    @Test
    public void testJunit01() {
        System.out.println("Junit4_TwoTest.testJunit01-3");
    }

    @Test
    public void testJunit02() {
        System.out.println("Junit4_TwoTest.testJunit02-4");
    }

    @Test
    @Ignore
    public void testJunit03() {
        System.out.println("Junit4_TwoTest.testJunit03");
    }

    @After
    public void after() {
        System.out.println("Junit4_TwoTest.after5-for");
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("Junit4_TwoTest.afterClass-6");
    }

}
