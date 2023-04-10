package com.hg.auto.testng;

import org.testng.annotations.*;

/**
 * 分组测试group
 * 需要结合xml
 */
public class TestNGGroupDemoTest {
    @BeforeSuite
    public void BeforeSuite() {
        System.out.println("BeforeSuite is 111");
    }

    @BeforeTest
    public void BeforeTest() {
        System.out.println("BeforeTest is 222");
    }

    @BeforeClass
    public void BeforeClass() {
        System.out.println("BeforeClass is 333");
    }

    @BeforeGroups
    public void BeforeGroups() {
        System.out.println("BeforeGroups is 444");
    }

    @BeforeMethod
    public void BeforeMethod() {
        System.out.println("BeforeMethod is 555");
    }

    @Test(groups = "group01")
    public void TestDemo001() {
        System.out.println("TestDemo001 is ########");
    }

    @Test(groups = "group01")
    public void TestDemo002() {
        System.out.println("TestDemo002 is ########");
    }

    @Test(groups = {"group01", "group02"})
    public void TestDemo003() {
        System.out.println("TestDemo003 is ########");
    }

    @Test(groups = "group02")
    public void TestDemo004() {
        System.out.println("TestDemo004 is ########");
    }

    @Test(groups = "group02")
    public void TestDemo005() {
        System.out.println("TestDemo005 is ########");
    }

    @AfterMethod
    public void AfterMethod() {
        System.out.println("AfterMethod is 666");
    }

    @AfterGroups
    public void AfterGroups() {
        System.out.println("AfterGroups is 777");
    }

    @AfterClass
    public void AfterClass() {
        System.out.println("AfterClass is 888");
    }

    @AfterTest
    public void AfterTest() {
        System.out.println("AfterTest is 999");
    }

    @AfterSuite
    public void AfterSuite() {
        System.out.println("AfterSuite is 100");
    }

}
