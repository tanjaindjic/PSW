package test.loginTests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.concurrent.TimeoutException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.internal.runners.statements.FailOnTimeout;
import org.junit.rules.Timeout;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import db.connection.DBConnection;
import login.LoginFrame;
import login.User;

public class NoCredentialsTest {

	private LoginFrame lf;
	private ArrayList<User> users;

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
	public void start() {
		// TODO Auto-generated method stub
		users = DBConnection.getInstance().getUsers();
		lf = LoginFrame.getInstance();
		testInstance();
		testUsersList();
		testNoCredentials();
	}


	public void testInstance(){
		assertNotNull(lf);
	}
	

	public void testUsersList(){
		assertNotNull(users);
	}
	
	public void testNoCredentials(){
		lf.getTextName().setText("");
		lf.getPassField().setText("");
		lf.getSignIn().doClick();
		assertTrue(lf.isVisible());
		
	}
/*	
	
	public void testAdminCredentials(){
		lf.getTextName().setText("admin");
		lf.getPassField().setText("admin");
		lf.getSignIn().doClick();
		assertTrue(!lf.isVisible());
	}
	
	public void editUser(){
		
	}
	
	
	
	*/
	
	
}
