package com.test.junit5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalcTest {

    @Test
    void dvi() {
        Calc calc = new Calc();
        assertEquals(2,calc.dvi(2,1));
    }
}