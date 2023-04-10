package com.test.hg.restassured.wechart.department;

import com.test.hg.restassured.wechart.apiobject.TokenHelper;
import com.test.hg.restassured.wechart.apiobject.DepartmentApiObject;
import com.test.hg.restassured.wechart.comment.TimerTools;
import com.test.hg.restassured.wechart.task.DepartmentEnv;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @Order 必须在class前面加入注解 @TestMethodOrder
 * 脚本独立性：update里面 添加增加的 代码可以独立运行
 * 脚本重复性：引入时间戳untils
 * 环境初始化：AfterAll 删除所有大约10的ID
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DepartmentObject {

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

    @Test
    @DisplayName("创建部门")
    @Order(1)
    public void createDepartMent() {
        String createName = "C_" + TimerTools.selectAChar();
        String createNameEN = "C_" + TimerTools.selectAChar();
        Response response = DepartmentApiObject.createDepartment(createName, createNameEN, access_token);
        //取值
        departmentId = response.path("id") != null ? response.path("id").toString() : null;
        //断言
        response.then().body("errmsg", equalTo("created"));
    }

    @Test
    @DisplayName("更新部门")
    @Order(2)
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

    @Test
    @DisplayName("查询部门")
    @Order(3)
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

    @Test
    @DisplayName("删除部门")
    @Order(4)
    public void deleteDepartMent() {
        /**
         * 先创建部门，然后删除，形成闭环
         */
        Response response = DepartmentApiObject.createDepartment(access_token);
        departmentId = response.path("id") != null ? response.path("id").toString() : null;

        Response deleteResponse = DepartmentApiObject.deleteDepartment(departmentId, access_token);
        //断言
        deleteResponse.then().body("errmsg", equalTo("deleted1"));
    }


}
