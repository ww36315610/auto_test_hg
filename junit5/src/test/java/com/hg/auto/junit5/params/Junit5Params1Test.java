package com.hg.auto.junit5.params;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * 参数化：
 *  1。valueSouce
 *  2。csvSource CsvFileSource
 *  3。methodSource
 *  4。ArgumentsSource 类依赖
 *  5。动态测试
 */
public class Junit5Params1Test {

    //------- ValueSource  ---  START  -------
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    public void testParam001(int argument) {
        assertTrue(argument > 0 && argument < 4);
    }

    @ParameterizedTest
    @NullSource
    @EmptySource
    @ValueSource(strings = {"", "    ", "\t", "\n"})
    public void testParam002(String text) {
        assertTrue(text == null || text.trim().isEmpty());
    }
    //------- ValueSource  ---  END  -------

    //------- MethodSource  ---  START  -------
    @ParameterizedTest
    @MethodSource("stringProvider")
    void testParamsWithMethodSource(String argument) {
        System.out.println(argument);
        assertNotNull(argument);
    }
    static Stream<String> stringProvider() {
        return Stream.of("apple", "banana");
    }

    @ParameterizedTest
    @MethodSource
    void testParamsWithMethodSource1(String str, int num, List<String> list) {
        assertEquals(5, str.length());
        assertTrue(num >= 1 && num <= 2);
        assertEquals(2, list.size());
    }
    static Stream<Arguments> testParamsWithMethodSource1() {
        return Stream.of(
                arguments("apple", 1, Arrays.asList("a", "b")),
                arguments("lemon", 2, Arrays.asList("x", "y"))
        );

    }


    @ParameterizedTest
    @ArgumentsSource(MyArgumentsProvider.class)
    void testWithArgumentSource(String arguments) {
        assertNotNull(arguments);
    }

    @Nested
    public static class MyArgumentsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
            return Stream.of("apple", "banana").map(Arguments::of);
        }
    }
    //------- MethodSource  ---  END    -------


    //------- CSVSource  ---  START  -------
    @ParameterizedTest
    @CsvSource({
            "apple,	1",
            "banana,	2",
            "'lemon,lime'	,0xF1"
    })
    void testWithACSVSource(String fruit, int rank) {
        assertNotNull(fruit);
        assertNotEquals(0, rank);
    }

    @ParameterizedTest                      //过滤第一行
    @CsvFileSource(resources = "/file.csv", numLinesToSkip = 1)
    void testWithACSVFileSource(String country, int num) {
        assertNotNull(country);
        assertNotEquals(0, num);
    }
    //------- CSVSource  ---  END  -------

    //------- 动态测试  ---  START  -------
    @TestFactory
    DynamicTest[] dynamicTestsFromArray() {
        return new DynamicTest[]{

        };
    }

    @TestFactory
    Stream<DynamicTest> dynamicTestFromStream() {
        return null;
//        return Stream.of("racecar", "radar", "mom", "dad")
//                .map(text ->dynamicTest(text,() ->);
    }


    //------- 动态测试  ---  END  -------
}
