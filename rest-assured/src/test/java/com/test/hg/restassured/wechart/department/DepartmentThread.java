package com.test.hg.restassured.wechart.department;

import com.test.hg.restassured.wechart.apiobject.DepartmentApiObject;
import com.test.hg.restassured.wechart.apiobject.TokenHelper;
import com.test.hg.restassured.wechart.comment.TimerTools;
import com.test.hg.restassured.wechart.task.DepartmentEnv;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 多线程运行：配置 junit-platform.properties
 * @Execution(ExecutionMode.CONCURRENT) 配置并发
 * @RepeatedTest(10) 配置执行顺序
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DepartmentThread {

    static String departmentId;
    static String access_token;

    @DisplayName("获取Token")
    @BeforeAll
    public static void beforeAll() {
        access_token = TokenHelper.getToken();
    }

    /**
     * 这个方法要单独运行
     * 为了测试重复插入相同名称的线程 时错误
     * 并行： @Execution(ExecutionMode.CONCURRENT)
     * 执行10次： @RepeatedTest(10)
     */
    @DisplayName("创建部门1")
    @Execution(ExecutionMode.CONCURRENT)
    @RepeatedTest(10)
    public void createDepartMent1() {
        long id = Thread.currentThread().getId();
        String createName = "C_xdd"+id;
        String createNameEN = "C_xde"+id;
        System.out.println("------  "+createName);
        Response response = DepartmentApiObject.createDepartment(createName, createNameEN, access_token);
        //取值
        departmentId = response.path("id") != null ? response.path("id").toString() : null;
        //断言
        response.then().body("errmsg", equalTo("created"));
    }

    /**
     * 测试 创建跟更新 操作时候，库是否有锁🔒影响
     * 创建部门跟更新部门 并行执行 都执行10次，并发设置配置文件5个
     * 为了保证唯一性随机id+线程id
     */
    @DisplayName("创建部门")
    @Execution(ExecutionMode.CONCURRENT)
    @RepeatedTest(10)
    public void createDepartMent() {
        long id = Thread.currentThread().getId();
        String createName = "C_" + TimerTools.selectAChar()+id;
        String createNameEN = "C_" + TimerTools.selectAChar()+id;
        Response response = DepartmentApiObject.createDepartment(createName, createNameEN, access_token);
        //取值
        departmentId = response.path("id") != null ? response.path("id").toString() : null;
        //断言
        response.then().body("errmsg", equalTo("created"));
    }

    @DisplayName("更新部门")
    @Execution(ExecutionMode.CONCURRENT)
    @RepeatedTest(10)
    public void updateDepartMent() {
        /**
         * 先创建部门，然后修改，形成闭环
         */
        long id = Thread.currentThread().getId();
        String createName = "C_" + TimerTools.selectAChar()+id;
        String createNameEN = "C_" + TimerTools.selectAChar()+id;
        Response response = DepartmentApiObject.createDepartment(createName,createNameEN,access_token);
        departmentId = response.path("id") != null ? response.path("id").toString() : null;

        String updateName = "U_" + TimerTools.selectAChar()+id;
        String updateNameEN = "U_" + TimerTools.selectAChar()+id;
        Response updateResponse = DepartmentApiObject.updateDepartment(updateName, updateNameEN, departmentId, access_token);
        //断言
        updateResponse.then().body("errmsg", equalTo("updated"));
    }
}
