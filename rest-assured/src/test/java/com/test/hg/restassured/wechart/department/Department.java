package com.test.hg.restassured.wechart.department;

import com.test.hg.restassured.wechart.apiobject.TokenHelper;
import com.test.hg.restassured.wechart.comment.TimerTools;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 * @Order 必须在class前面加入注解 @TestMethodOrder
 * 脚本独立性：update里面 添加增加的 代码可以独立运行
 * 脚本重复性：引入时间戳untils
 * 环境初始化：AfterAll 删除所有大约10的ID
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Department extends TokenHelper {

    static int departmentId;
    static int access_token;

    @Test
    @DisplayName("创建部门")
    @Order(1)
    public void createDepartMent() {
        String url = "https://qyapi.weixin.qq.com/cgi-bin/department/create";
        String createName = "C_" + TimerTools.selectAChar();
        String createNameEN = "C_" + TimerTools.selectAChar();

        String body = "{\n" +
                "   \"name\": \"" + createName + "\",\n" +
                "   \"name_en\": \"" + createNameEN + "\",\n" +
                "   \"parentid\": 1\n" +
                "}\n" +
                "\n";
        System.out.println(body);
        departmentId = given()
                .contentType("application/json;chartset=utf-8")
                .queryParam("access_token", access_token)
                .body(body)
                .when().post(url)
                .then()
                .body("errmsg", equalTo("created"))
                .extract().response().path("id");
        System.out.println("====== " + departmentId);
    }

    @Test
    @DisplayName("更新部门")
    @Order(2)
    public void updateDepartMent() {
        String urlCreate = "https://qyapi.weixin.qq.com/cgi-bin/department/create";
        String createName = "C_" + TimerTools.selectAChar();
        String createNameEN = "C_" + TimerTools.selectAChar();

        String bodyCreate = "{\n" +
                "   \"name\": \"" + createName + "\",\n" +
                "   \"name_en\": \"" + createNameEN + "\",\n" +
                "   \"parentid\": 1\n" +
                "}\n" +
                "\n";
        System.out.println(bodyCreate);
        int updateId = given()
                .contentType("application/json;chartset=utf-8")
                .queryParam("access_token", access_token)
                .body(bodyCreate)
                .when().post(urlCreate)
                .then()
                .body("errmsg", equalTo("created"))
                .extract().response().path("id");

        String url = "https://qyapi.weixin.qq.com/cgi-bin/department/update";
        String body = "{\n" +
                "   \"id\": " + updateId + ",\n" +
                "   \"name\": \"" + createName + "_U" + "\",\n" +
                "   \"name_en\": \"" + createNameEN + "_U" + "\",\n" +
                "   \"parentid\": 1,\n" +
                "   \"order\": 1\n" +
                "}\n" +
                "\n";
        given()
                .contentType("application/json;chartset=utf-8")
                .queryParam("access_token", access_token)
                .body(body)
                .log().all()
                .when().post(url)
                .then()
//                .body("",equalTo(""))
                .log().all();
    }

    @Test
    @DisplayName("查询部门")
    @Order(3)
    public void getDepartMent() {
        String url = "https://qyapi.weixin.qq.com/cgi-bin/department/get";
        given()
                .param("access_token", access_token)
                .param("id", departmentId)
                .log().all()
                .when().get(url)
                .then()
//                .body("",equalTo(""))
                .log().all();
    }

    @Test
    @DisplayName("删除部门")
    @Order(4)
    public void deleteDepartMent() {
        String url = "https://qyapi.weixin.qq.com/cgi-bin/department/delete";
        given()
                .param("access_token", access_token)
                .param("id", departmentId)
                .log().all()
                .when().get(url)
                .then()
//                .body("",equalTo(""))
                .log().all();
    }


    @DisplayName("环境初始化")
    @AfterAll
    public static void env_clear() {
        String url= "https://qyapi.weixin.qq.com/cgi-bin/department/list";
       Response response = given()
               .param("access_token",access_token)
                .when().get(url)
                .then()
               .log().all()
                .extract().response();
        ArrayList<Integer> ids = response.path("department.id");
        ids = response.path("department.id.findAll{id -> id>10}");
        ids.forEach(id ->{
            String urlDelete = "https://qyapi.weixin.qq.com/cgi-bin/department/delete";
            given()
                    .param("access_token", access_token)
                    .param("id", id)
                    .when().get(urlDelete)
                    .then()
                .body("errmsg",equalTo("deleted"))
                    .log().all();
        });

    }


}
