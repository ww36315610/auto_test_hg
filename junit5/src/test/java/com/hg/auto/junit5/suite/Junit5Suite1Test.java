package com.hg.auto.junit5.suite;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@DisplayName("Junit5Suite1Test")
public class Junit5Suite1Test {

    @Test
    @Tag("junit5Tag001")
    public void Junit5Suite1Test() {
        System.out.println("Junit5Suite1Test");
    }
}
