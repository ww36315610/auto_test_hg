package com.hg.auto.junit4.suite;

import org.junit.Test;

public class LoginTest extends BaseClassTest{

    @Test
    public void login(){
       map.put("login","SUCCESS");
    }
}
