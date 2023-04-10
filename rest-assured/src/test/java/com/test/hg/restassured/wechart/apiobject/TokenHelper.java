package com.test.hg.restassured.wechart.apiobject;

import static io.restassured.RestAssured.given;

/**
 * header：      header内容
 * queryParam：  url后面的参数
 * formParam：   表单的提交内容
 * <p>
 * then后面的 body 用于断言
 * then后面的 extract().response() 用于返回值
 * extract().response().path()
 */
public class TokenHelper {


    public static String getToken() {
        String url = "https://qyapi.weixin.qq.com/cgi-bin/gettoken";
        //发送消息 【hogtest】
        String json = "{\"corpid\":\"wwe2c3250da5791212\",\"corpsecret\":\"OpSsTg4oTAthDxMtSU6ptmXeDbD7m2q4VrT0yckU9Ks\"}";
        //创建部门
        json = "{\"corpid\":\"wwe2c3250da5791212\",\"corpsecret\":\"oE0Sn1Ol822V9jdK3Pzop9N-gFwsqiu2KHB8Oa6ng74\"}";

//        json = "{\"corpid\":\"wwe2c3250da5791212\",\"corpsecret\":\"eSLtoansjHYDlA9x5CNRkYAa26y-NYg7MJGGze-4Z8M\"}";
        String access_token = given()
                .contentType("application/json;charset=utf-8")
                .body(json)
                .when().post(url)
                .then()
                .statusCode(200)
                .log().all()
                .extract().response().path("access_token");

        return access_token;
    }


    /**
     * param设置url参数
     */
//    @Test
    public void getMethod() {
        String corpid = "wwe2c3250da5791212";
        String corpsecret = "OpSsTg4oTAthDxMtSU6ptmXeDbD7m2q4VrT0yckU9Ks";
        String url = "https://qyapi.weixin.qq.com/cgi-bin/gettoken";
        System.out.println(url);
        given()
                .param("corpid", corpid)
                .param("corpsecret", corpsecret)
                .log().all()
                .when().get(url)
                .then()
                .statusCode(200)
                .log().all();
    }
}
