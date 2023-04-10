package com.test.hg.restassured.wechart.message;

import com.test.hg.restassured.wechart.apiobject.TokenHelper;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class SedMsg extends TokenHelper {

    /**
     * 替换内容：
     * touser = @all
     * agentid= 第三方应用的agentid
     */
    @Test
    public void messageSend(String access_token) {
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
}
