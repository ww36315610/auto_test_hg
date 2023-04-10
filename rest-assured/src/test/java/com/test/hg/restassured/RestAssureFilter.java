package com.test.hg.restassured;

import io.restassured.builder.ResponseBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Base64;
import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

public class RestAssureFilter {
    /**
     * given() when() then()
     */
    @Test
    public void testFilter01() {
        String url = "http://127.0.0.1:8000/book.json";
        given().
                when().get(url)
                .then().body("store.book[0].category", equalTo("reference"));
    }

    @Test
    public void testFilter02() {
        String url = "http://127.0.0.1:8000/book64.json";
        given().filter((req, res, ctx) -> {
            System.out.println(req.getBaseUri());
            //返回的response不具备set方法，无法修改body
            Response response = ctx.next(req,res);
            //ResponseBuilder的主要功能是在response的基础上构建处一个可以更高body的对象
            ResponseBuilder responseBuilder = new ResponseBuilder().clone(response);
            System.out.println(res.getStatusCode());
            //response的body更改为string类型
            String encodeBody = response.getBody().asString();
            //base64解密过程
            byte[] base64Body = Base64.getDecoder().decode(encodeBody.replace("\n","").trim());
            //response无法直接修改，所以间接使用responseBuilder构建
            responseBuilder.setBody(base64Body);
            //ResponseBuilder在最后使用build方法直接创建一个用于返回的不可修改的response
            Response responseNew = responseBuilder.build();
            return responseNew;
        }).when().get(url)
                .then().body("store.book[0].category", equalTo("reference"));
    }

    @Test
    public void testFilter03() {
        String url = "http://127.0.0.1:8000/bb64.json";
        given().filter((req, res, ctx) -> {
            System.out.println(req.getBaseUri());
            //返回的response不具备set方法，无法修改body
            Response response = ctx.next(req,res);
            //ResponseBuilder的主要功能是在response的基础上构建处一个可以更高body的对象
            ResponseBuilder responseBuilder = new ResponseBuilder().clone(response);
            System.out.println(res.getStatusCode());
            //response的body更改为string类型
            String encodeBody = response.getBody().asString();
            //base64解密过程
            byte[] base64Body = Base64.getDecoder().decode(encodeBody.replace("\n","").trim());
            //response无法直接修改，所以间接使用responseBuilder构建
            responseBuilder.setBody(base64Body);
            //ResponseBuilder在最后使用build方法直接创建一个用于返回的不可修改的response
            Response responseNew = responseBuilder.build();
            return responseNew;
        }).when().get(url)
                .then().log().all();
    }
}
