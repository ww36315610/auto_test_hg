package com.hg.auto.junit4;

import org.junit.*;

public class Junit4One {
    /**
     * BeforeClass
     * Before
     * Test
     * After
     * AfterClass
     *
     * @Ignore = 过滤不执行
     */
    @BeforeClass
    public static void beforeClass() {
        System.out.println("Junit4_OneTest.beforeClass");
    }

    @Before
    public void before() {
        System.out.println("Junit4_OneTest.before");
    }

    @Test
    public void testJunit01() {
        System.out.println("Junit4_OneTest.testJunit01");
    }

    @Test
    public void testJunit02() {
        System.out.println("Junit4_OneTest.testJunit02");
    }

    @Test
    @Ignore
    public void testJunit03() {
        System.out.println("Junit4_OneTest.testJunit03");
    }

    @After
    public void after() {
        System.out.println("Junit4_OneTest.after");
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("Junit4_OneTest.afterClass");
    }
}
