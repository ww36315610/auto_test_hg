package com.hg.auto.junit5.datastream;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.Assert.assertTrue;

/**
 * 数据驱动-yaml文件
 */
public class TestYaml {

    @ParameterizedTest
    @MethodSource
    public void testDDTFromYaml(User user) {
        assertTrue(user.getName().length() >= 3);

    }

    static List<User> testDDTFromYaml() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        TypeReference typeReference = new TypeReference<List<User>>() {
        };

        List<User> users = (List<User>) objectMapper.readValue(
                TestYaml.class.getResourceAsStream("/user.yaml"),  // 本类名反射
                typeReference
        );

        return users;
    }
}
