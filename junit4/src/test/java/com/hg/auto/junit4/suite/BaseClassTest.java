package com.hg.auto.junit4.suite;

import java.util.HashMap;

public class BaseClassTest {
    /**
     * 设置static 避免多个类继承后，父类的多次初始化
     */
    protected static HashMap<String, String> map = new HashMap<String, String>();
}
