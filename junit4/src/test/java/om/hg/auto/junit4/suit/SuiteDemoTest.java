package om.hg.auto.junit4.suit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        LoginTest.class,
        BuyTest.class
})
public class SuiteDemoTest {
}
