package om.hg.auto.junit4;

import org.junit.*;

public class Junit4OneTest {

    /**
     * BeforeClass
     * Before
     * Test
     * After
     * AfterClass
     * @Ignore = 过滤不执行
     */
    @BeforeClass
    public static void beforeClass() {
        System.out.println("Junit4_OneTest.beforeClass1");
    }

    @Before
    public void before() {
        System.out.println("Junit4_OneTest.before2-for");
    }

    @Test
    public void testJunit01() {
        System.out.println("Junit4_OneTest.testJunit01-3");
    }

    @Test
    public void testJunit02() {
        System.out.println("Junit4_OneTest.testJunit02-4");
    }

    @Test
    @Ignore
    public void testJunit03() {
        System.out.println("Junit4_OneTest.testJunit03");
    }

    @After
    public void after() {
        System.out.println("Junit4_OneTest.after5-for");
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("Junit4_OneTest.afterClass-6");
    }

}
