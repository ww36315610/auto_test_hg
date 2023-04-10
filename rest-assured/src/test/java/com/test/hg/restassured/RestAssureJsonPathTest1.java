package com.test.hg.restassured;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class RestAssureJsonPathTest1 {
    /**
     * given() when() then()
     *   given()后面添加格式，参数等
     *   when() 后面post get
     *   then() 后面主要添加断言
     * body 用于断言：hamcrest
     * 数组 hasItems()
     * find 查找后 再去某个值 可以使用 it.xx
     * findAll 返回数组，使用hasItems断言
     * path 也可以使用上面语法 跟 body 相同
     * response 返回实体类，path()方法解析后 使用junit5 或者 hamcrest断言
     */
    @Test
    public void testJsonPahe() {
        given()
                .when().get("http://127.0.0.1:8000/book.json")
                .then()
                .statusCode(200)
                .body("expensive", equalTo(10))
                .body("store.book[0].category", equalTo("reference"))
                .body("store.book.category", hasItems("reference", "fiction"))
                .body("store.book.find{book ->book.price==12.99f}.category", equalTo("fiction"))
                .body("store.book.find{it.price==12.99f}.category", equalTo("fiction"))
                .body("store.book.findAll{ book -> book.price==12.99f}.title", hasItems("Sword of Honour"))
                .log().all();
    }

    /**
     * json模式提交：
     */
    @Test
    public void testJsonTest(){
        HashMap<String,String> map =  new HashMap<>();
            map.put("aa","AA");
            map.put("bb","BB");
        given()
                .contentType(ContentType.JSON)
                .body(map).log().all()
                .when().get("http://127.0.0.1:8000/book.json")
                .then()
                .log().all();
    }

    static String access_token;

    /**
     * 返回结果，
     * extract().response() 取全量 get() 方法获取其他的内容
     * extract().response().path(""）取固定的值
     */
    @BeforeAll
    public static void getToken() {
        String url = "https://qyapi.weixin.qq.com/cgi-bin/gettoken";
        String json = "{\"corpid\":\"wwe2c3250da5791212\",\"corpsecret\":\"OpSsTg4oTAthDxMtSU6ptmXeDbD7m2q4VrT0yckU9Ks\"}";
        access_token = given()
                .contentType("application/json;charset=utf-8")
                .body(json)
                .when().post(url)
                .then()
                .statusCode(200)
                .log().all()
                .extract().response().path("access_token");

        System.out.println(access_token);
    }

    /**
     * 替换内容：
     * touser = @all
     * agentid= 第三方应用的agentid
     */
    @Test
    public void messageSend() {
        String contentType = "application/json;charset=utf-8";
        String url = "https://qyapi.weixin.qq.com/cgi-bin/message/send";
        String body = "{\n" +
                "  \"touser\": \"@all\",\n" +
                "  \"msgtype\": \"text\",\n" +
                "  \"agentid\": 1000005,\n" +
                "  \"text\": {\n" +
                "    \"content\": \"你的快递已到，请携带工卡前往邮件中心领取。\\n出发前可查看<a href=\\\"http://work.weixin.qq.com\\\">邮件中心视频实况</a>，聪明避开排队。\"\n" +
                "  }\n" +
                "}";
//        System.out.println(url);
        given()
                .contentType(contentType)
                .body(body)
                .queryParam("access_token", access_token)
                .when().post(url)
                .then()
                .statusCode(200)
                .log().all();
    }

    /**
     * 暂时没有调通
     */
    @Test
    public void getMethod() {
        String corpid = "we2c3250da5791212";
        String corpsecret = "OpSsTg4oTAthDxMtSU6ptmXeDbD7m2q4VrT0yckU9Ks";
        String url = "https://qyapi.weixin.qq.com/cgi-bin/gettoken";
        System.out.println(url);
        given()
                .param(corpid, corpsecret)
                .params("corpid", corpid, "corpsecret", corpsecret)
                .get(url)
                .then()
                .statusCode(200)
                .log().all();
    }
}
