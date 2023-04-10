package com.test.hg.restassured.wechart.apiobject;

import com.test.hg.restassured.wechart.comment.TimerTools;
import io.restassured.response.Response;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;

public class DepartmentApiObject {

    //创建部门
    public static Response createDepartment(String createName, String createNameEN, String access_token) {
        String url = "https://qyapi.weixin.qq.com/cgi-bin/department/create";
        String body = "{\n" +
                "   \"name\": \"" + createName + "\",\n" +
                "   \"name_en\": \"" + createNameEN + "\",\n" +
                "   \"parentid\": 1\n" +
                "}\n" +
                "\n";
        System.out.println(body);
        Response response = given()
                .contentType("application/json;chartset=utf-8")
                .queryParam("access_token", access_token)
                .body(body)
                .when().post(url)
                .then()
                .log().all()
//                .body("errmsg", equalTo("created"))
                .extract().response();
        return response;
    }

    //创建部门-参数简写
    public static Response createDepartment(String access_token) {
        String createName = "C_" + TimerTools.selectAChar();
        String createNameEN = "C_" + TimerTools.selectAChar();
        return createDepartment(createName, createNameEN, access_token);
    }

    //更新部门
    public static Response updateDepartment(String updateName, String updateNameEN, String departmentId, String access_token) {
        String url = "https://qyapi.weixin.qq.com/cgi-bin/department/update";
        String body = "{\n" +
                "   \"id\": " + departmentId + ",\n" +
                "   \"name\": \"" + updateName + "_U" + "\",\n" +
                "   \"name_en\": \"" + updateNameEN + "_U" + "\",\n" +
                "   \"parentid\": 1,\n" +
                "   \"order\": 1\n" +
                "}\n" +
                "\n";
        Response response = given()
                .contentType("application/json;chartset=utf-8")
                .queryParam("access_token", access_token)
                .body(body)
//                .log().all()
                .when().post(url)
                .then()
//                .body("",equalTo(""))
//                .log().all()
                .extract().response();
        return response;
    }

    //删除部门
    public static Response deleteDepartment(String departmentId, String access_token) {
        String url = "https://qyapi.weixin.qq.com/cgi-bin/department/delete";
        Response response = given()
                .param("access_token", access_token)
                .param("id", departmentId)
                .when().get(url)
                .then()
//                .log().all()
                .extract().response();
        return response;
    }

    //查询部门
    public static Response selectDepartment(String departmentId, String access_token) {
        String url = "https://qyapi.weixin.qq.com/cgi-bin/department/get";
        Response response = given()
                .param("access_token", access_token)
                .param("id", departmentId)
//                .log().all()
                .when().get(url)
                .then()
//                .log().all()
                .extract().response();
        return response;
    }

}
