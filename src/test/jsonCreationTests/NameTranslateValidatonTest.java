package test.jsonCreationTests;

import static org.junit.Assert.assertFalse;

import java.util.concurrent.TimeoutException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.internal.runners.statements.FailOnTimeout;
import org.junit.rules.Timeout;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import editorSeme.model.pojo.NameTranslate;

public class NameTranslateValidatonTest {

	private static final int MIN_TIMEOUT = 1000;

    @Rule
    public Timeout timeout = new Timeout(MIN_TIMEOUT) {
        public Statement apply(Statement base, Description description) {
            return new FailOnTimeout(base, MIN_TIMEOUT) {
                @Override
                public void evaluate() throws Throwable {
                    try {
                        super.evaluate();
                        throw new TimeoutException();
                    } catch (Exception e) {}
                }
            };
        }
    };

    @Test(expected = TimeoutException.class)
	public void test() {
		NameTranslate test = new NameTranslate();
		test.setCode("");
		NameTranslate test1 = new NameTranslate();
		test1.setCode(null);
		assertFalse(/*"NameTranslate validation invalid.(Empty string case)", */test.validate());
		assertFalse(/*"NameTranslate validation invalid.(null value for code case)", */test1.validate());
	}

}
