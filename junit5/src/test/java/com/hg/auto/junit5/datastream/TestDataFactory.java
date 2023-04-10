package com.hg.auto.junit5.datastream;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;

public class TestDataFactory {

    static ObjectMapper objectMapper;

    <T> T testDDTFromFile(String file) throws IOException {
        makeMapper(file);
        TypeReference typeReference = new TypeReference<T>() {
        };
        T users = objectMapper.readValue(
                TestDataFactory.class.getResourceAsStream("/" + file),
                typeReference
        );
        System.out.println(users);
        return (T) users;
    }

    void makeMapper(String file) {
        if (file.endsWith(".yaml")) {
            objectMapper = new ObjectMapper(new YAMLFactory());
        } else if (file.endsWith(".json")) {
            objectMapper = new ObjectMapper();
        }
    }

}
