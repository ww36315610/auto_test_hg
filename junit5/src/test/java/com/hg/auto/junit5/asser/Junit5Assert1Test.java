package com.hg.auto.junit5.asser;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * assertAll 可以跳过错误，执行所有的断言
 */
public class Junit5Assert1Test {

    //会断言所有失败的内容
    @Test
    void assertion() {
        assertAll("assert demo",
                () -> assertEquals(1, 2),
                () -> assertEquals(2, 2),
                () -> assertEquals(2, 3)
        );
    }

// 这种有失败的后面不会走
    @Test
    void asserttion1() {
        assertAll("assert demo{}",
                () -> {
                    assertEquals(1, 2);
                    assertEquals(2, 2);
                    assertEquals(2, 3);
                }
        );
    }

}
