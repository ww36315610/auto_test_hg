package com.hg.auto.junit5.suite;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

/**
 * @Nested：依赖执行 执行顺序-主类-》依赖类[倒叙]
 * login =》 buy  =》pay  「自下而上」
 */

@DisplayName("Junit5Suite2Test")
public class Junit5Suite2Test {
    private static HashMap<String, Object> map = new HashMap<>();

    @Test
    public void login() {
        map.put("login", "SUCCESS");
    }

    @Nested
    class Pay {
        @Test
        void pay() {
            if (null != map.get("buy")) {
                System.out.println("购买了：" + map.get("buy") + ",请支付");
                map.put("价格", 78);
            } else {
                System.out.println("请选择商品后在支付……");
            }
        }
    }

    @Nested
    class Buy {
        @Test
        void buy() {
            if (map.get("login").equals("SUCCESS")) {
                System.out.println("登陆成功，正在购买");
                map.put("buy", "《三体》");
            } else {
                System.out.println("请先登录后，在来购买");
            }
        }
    }
}
