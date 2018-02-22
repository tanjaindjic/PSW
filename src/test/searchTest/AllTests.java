package test.searchTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ PrepareRelTest.class, SearchRelVarcharBoolean.class, SearchRelInt.class, SearchRelNumeric.class, DeleteRelTest.class })
public class AllTests {
	
}
