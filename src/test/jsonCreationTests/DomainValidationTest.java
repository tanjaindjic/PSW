package test.jsonCreationTests;

import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeoutException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.internal.runners.statements.FailOnTimeout;
import org.junit.rules.Timeout;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import editorSeme.model.enums.Tip;
import editorSeme.model.pojo.Domain;

public class DomainValidationTest {

	@Test
	public void test() {
		Domain test = new Domain(10, Tip.CHAR);
		assertTrue(test.validate());
	}

}
