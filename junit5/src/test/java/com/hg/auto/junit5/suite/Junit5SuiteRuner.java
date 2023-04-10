package com.hg.auto.junit5.suite;

import com.hg.auto.junit5.Junit5Demo1Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.*;
import org.junit.runner.RunWith;

import java.util.HashMap;

/**
 * @RunWith：
 * @SelectPackages = IncludePackages 通过包执行[可以搭配使用，selectPackages > includePckages]
 * @SelectClasses 通过类执行
 * @IncludeTags 通过Tag执行[gen SlectClassess 搭配使用]
 */

@DisplayName("suiterunerClass")
@RunWith(JUnitPlatform.class)
@SuiteDisplayName("suiterunerClass")

//通过pacage执行 includePages 可以做为补充到 细包
/**
 * @SelectPackages({
 *         "com.hg.auto"
 * })
 * @IncludePackages(
 *        "com.hg.auto.junit5.suite"
 * )
 */

//通过class执行
@SelectClasses({
        Junit5Suite1Test.class,
        Junit5Suite2Test.class
})

//通过tag执行 -[必须配合class]
/**
 * @SelectClasses({
 *         Junit5Demo1Test.class
 * })
 * @IncludeTags("junit5Tag001")
 * @ExcludeTags("")
 */

public class Junit5SuiteRuner {

}
