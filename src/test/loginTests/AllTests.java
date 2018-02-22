package test.loginTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ BadCredentialsTest.class, NoCredentialsTest.class, ProjLoginTest.class, AdminLoginTest.class, UserLoginTest.class })
public class AllTests {

}
