package test.crud;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ CreateTest.class, ReadTest.class, UpdateTest.class , CreateFailTest.class,  DeleteTest.class, ReadFailTest.class,  DeleteFailTest.class, 
		 UpdateFailTest.class })
public class AllTests {

}
