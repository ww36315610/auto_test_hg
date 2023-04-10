package com.test.hg.restassured.wechart.department;

import com.test.hg.restassured.wechart.apiobject.DepartmentApiObject;
import com.test.hg.restassured.wechart.apiobject.TokenHelper;
import com.test.hg.restassured.wechart.comment.TimerTools;
import com.test.hg.restassured.wechart.task.DepartmentEnv;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @Order 必须在class前面加入注解 @TestMethodOrder
 * 脚本独立性：update里面 添加增加的 代码可以独立运行
 * 脚本重复性：引入时间戳untils
 * 环境初始化：AfterAll 删除所有大约10的ID
 */
@Epic("企业微信-创建部门-Epic")
@Feature("企业微信-创建部门-Fearure")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DepartmentObjectAllure {

    static String departmentId;
    static String access_token;

    @DisplayName("获取Token")
    @BeforeAll
    public static void beforeAll() {
        access_token = TokenHelper.getToken();
    }

    @DisplayName("环境初始化")
    @BeforeEach
    @AfterEach
    public void env_clear() {
        DepartmentEnv.env_clear(access_token);
    }

    @Description("DepartMent--创建部门-Description")
    @Story("DepartMent--创建部门-story")
    @Issue("DepartMent--创建部门-Issue")
    @TmsLink("DepartMent--创建部门-TmsLink")
    @Order(1)
    @Test
    public void createDepartMent() {
        String createName = "C_" + TimerTools.selectAChar();
        String createNameEN = "C_" + TimerTools.selectAChar();
        Response response = DepartmentApiObject.createDepartment(createName, createNameEN, access_token);
        //取值
        departmentId = response.path("id") != null ? response.path("id").toString() : null;
        //断言
        response.then().body("errmsg", equalTo("created"));
    }


    @Description("DepartMent--更新部门-Description")
    @Story("DepartMent--更新部门-story")
    @Issue("DepartMent--更新部门-Issue")
    @TmsLink("DepartMent--更新部门-TmsLink")
    @Order(2)
    @Test
    public void updateDepartMent() {
        /**
         * 先创建部门，然后修改，形成闭环
         */
        Response response = DepartmentApiObject.createDepartment(access_token);
        departmentId = response.path("id") != null ? response.path("id").toString() : null;

        String updateName = "U_" + TimerTools.selectAChar();
        String updateNameEN = "U_" + TimerTools.selectAChar();
        Response updateResponse = DepartmentApiObject.updateDepartment(updateName, updateNameEN, departmentId, access_token);
        //断言
        updateResponse.then().body("errmsg", equalTo("updated"));
    }

    @Description("DepartMent--查询部门-Description")
    @Story("DepartMent--查询部门-story")
    @Issue("DepartMent--查询部门-Issue")
    @TmsLink("DepartMent--查询部门-TmsLink")
    @Order(3)
    @Test
    public void getDepartMent() {
        /**
         * 先创建部门，然后查询，形成闭环
         */
        String createName = "C_" + TimerTools.selectAChar();
        String createNameEN = "C_" + TimerTools.selectAChar();
        Response response = DepartmentApiObject.createDepartment(createName, createNameEN, access_token);
        departmentId = response.path("id") != null ? response.path("id").toString() : null;

        Response selectResponse = DepartmentApiObject.selectDepartment(departmentId, access_token);
        //断言 hamcrest
        selectResponse.then().body("errmsg", equalTo("ok"));
        selectResponse.then().body("department.name", equalTo(createName));
        selectResponse.then().body("department.name_en", equalTo(createNameEN));

        System.out.println(selectResponse.path("department.name").toString());

        //junit5 容错断言=遇到错误会跳过，继续执行下面的
        assertAll(
                () -> assertEquals(selectResponse.path("errmsg"), "ok"),
                () -> assertEquals(createName, selectResponse.path("department.name")),
                () -> assertEquals(createNameEN, selectResponse.path("department.name_en"))
        );
        assertEquals("", "");

    }

    @Description("DepartMent--删除部门-Description")
    @Story("DepartMent--删除部门-story")
    @Issue("DepartMent--删除部门-Issue")
    @TmsLink("DepartMent--删除部门-TmsLink")
    @Order(4)
    @Test
    public void deleteDepartMent() {
        /**
         * 先创建部门，然后删除，形成闭环
         */
        Response response = DepartmentApiObject.createDepartment(access_token);
        departmentId = response.path("id") != null ? response.path("id").toString() : null;

        Response deleteResponse = DepartmentApiObject.deleteDepartment(departmentId, access_token);
        //断言
        deleteResponse.then().body("errmsg", equalTo("deleted"));
    }


}
