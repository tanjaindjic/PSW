package test.jsonCreationTests;

import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeoutException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.internal.runners.statements.FailOnTimeout;
import org.junit.rules.Timeout;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import editorSeme.model.pojo.Sistem;
import test.TestInit;

public class SistemValidationTest {

	@Test
	public void test() {
		TestInit testModel = new TestInit();
		testModel.initializeTestModel();
		assertTrue(Sistem.getInstance().validate());
	}

}
