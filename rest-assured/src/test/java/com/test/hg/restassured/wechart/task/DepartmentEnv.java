package com.test.hg.restassured.wechart.task;

import io.restassured.response.Response;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;

public class DepartmentEnv {


    //环境初始化=方法执行前删除所有id>10的部门
    public static void env_clear(String access_token) {
        String url = "https://qyapi.weixin.qq.com/cgi-bin/department/list";
        Response response = given()
                .param("access_token", access_token)
                .when().get(url)
                .then()
//                .log().all()
                .extract().response();
        ArrayList<Integer> ids = response.path("department.id");
        ids = response.path("department.id.findAll{id -> id>10}");
//        Response deleteResponse = null;
        ids.forEach(id -> {
            String urlDelete = "https://qyapi.weixin.qq.com/cgi-bin/department/delete";
            Response deleteResponse = given()
                    .param("access_token", access_token)
                    .param("id", id)
                    .when().get(urlDelete)
                    .then()
//                    .log().all()
                    .extract().response();
        });
    }
}
