package com.hg.auto.junit4.suite;

import org.junit.Test;

public class BuyTest extends BaseClassTest{

    @Test
    public void buy(){
        System.out.println(map.get("login")+"-------------");
        if(map.get("login").equals("SUCCESS")){
            System.out.println( "购买成功");
        }
        System.out.println( "请先登录，然后才能购买");
    }
}
