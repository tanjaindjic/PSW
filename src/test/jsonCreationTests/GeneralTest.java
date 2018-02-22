package test.jsonCreationTests;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeoutException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.internal.runners.statements.FailOnTimeout;
import org.junit.rules.Timeout;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import editorSeme.model.pojo.Sistem;
import test.TestInit;
import test.loginTests.BadCredentialsTest;
import test.loginTests.NoCredentialsTest;

public class GeneralTest {

	@Test
	public void test() {
		TestInit testini = new TestInit();
		testini.initializeTestModel();
		assertEquals(7, Sistem.getInstance().getAllTables().size());
		
		NoCredentialsTest testNoCred = new NoCredentialsTest();
		testNoCred.start();
		
		//BadCredentialsTest testBadCred = new BadCredentialsTest();
		//testBadCred.start();
	}

}
