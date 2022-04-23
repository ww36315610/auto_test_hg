package com.hg.auto.testng;

import org.testng.annotations.*;

public class TestNGDataProviderTest {

    @DataProvider(name="data01")
    public Object[][] makeData(){
        return new Object[][]{
                {"zhangsan",18,"北京市"},
                {"lisi",28,"上海市"},
                {"wangwu",38,"天津市"},
        };
    }

    @Test(dataProvider = "data01")
    public void TestDemo002(String name,int age,String address){
        System.out.println("姓名：" + name + ",年龄：" + age + "，地址：" + address);
    }
}
