package com.hg.auto.junit5.datastream;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.List;

/**
 * 数据驱动-json文件
 */
public class TestJson {

    @ParameterizedTest
    @MethodSource
    public void testDDTFromJson(User user) {
        Assert.assertTrue(user.getName().length() >= 3);
    }

    static List<User> testDDTFromJson() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference typeReference = new TypeReference<List<User>>() {
        };
        List<User> users = objectMapper.readValue(
                TestJson.class.getResourceAsStream("/user.json"),
                typeReference
        );
        System.out.println(users);
        return users;
    }
}
