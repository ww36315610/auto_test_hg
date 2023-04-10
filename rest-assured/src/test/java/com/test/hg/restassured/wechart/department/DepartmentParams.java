package com.test.hg.restassured.wechart.department;

import com.test.hg.restassured.wechart.apiobject.DepartmentApiObject;
import com.test.hg.restassured.wechart.apiobject.TokenHelper;
import com.test.hg.restassured.wechart.comment.TimerTools;
import com.test.hg.restassured.wechart.task.DepartmentEnv;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @Order 必须在class前面加入注解 @TestMethodOrder
 * 脚本独立性：update里面 添加增加的 代码可以独立运行
 * 脚本重复性：引入时间戳untils
 * 环境初始化：AfterAll 删除所有大约10的ID
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DepartmentParams {

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

    @ParameterizedTest
    @DisplayName("创建部门")
    @CsvFileSource(resources = "/department.csv",numLinesToSkip = 1)
    @Order(1)
    public void createDepartMent(String createName,String createNameEN,String returncode) {
//        String createName = "C_" + TimerTools.selectAChar();
//        String createNameEN = "C_" + TimerTools.selectAChar();
        System.out.println(createName);
        System.out.println(createNameEN);
        Response response = DepartmentApiObject.createDepartment(createName, createNameEN, access_token);
        //取值
        departmentId = response.path("id") != null ? response.path("id").toString() : null;
        //断言
//        response.then().body("errcode".toString(), equalTo(returncode));
        assertEquals(returncode,response.path("errcode").toString());
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

}
