package com.hg.auto.junit4.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @RunWith(Suite.class)
 * @Suite.SuiteClasses({class1,class2})
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        LoginTest.class,
        BuyTest.class
})
public class SuiteDemoTest {
}
