package test.loginTests;

import java.util.ArrayList;
import java.util.concurrent.TimeoutException;
import static org.junit.Assert.assertTrue;
import org.junit.Rule;
import org.junit.Test;
import org.junit.internal.runners.statements.FailOnTimeout;
import org.junit.rules.Timeout;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import db.connection.DBConnection;
import login.LoginFrame;
import login.User;

public class BadCredentialsTest {
	private LoginFrame lf;
	private ArrayList<User> users;

	private static final int MIN_TIMEOUT = 2000;

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
	public void start() {
    	
    	users = DBConnection.getInstance().getUsers();
		lf = LoginFrame.getInstance();
    		lf.getTextName().setText("asdf");
    		lf.getPassField().setText("");
    		lf.getSignIn().doClick();
    		assertTrue(lf.isVisible());
    	}
    	
   }

