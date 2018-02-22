package test.loginTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.junit.Test;

import db.connection.DBConnection;
import login.LoginFrame;
import login.User;
import login.UserType;
import start.InfViewModel;

public class AdminLoginTest {

	private ArrayList<User> users;
	private LoginFrame lf;
	@Test
	public void start(){
		users = DBConnection.getInstance().getUsers();
		lf = LoginFrame.getInstance();
    		lf.getTextName().setText("admin");
    		lf.getPassField().setText("admin");
    		lf.getSignIn().doClick();
    		assertNotNull(InfViewModel.getInstance().getCurrentUser());
    		assertEquals(InfViewModel.getInstance().getCurrentUser().getTypeOfUser(), UserType.ADMIN);
    		assertEquals(InfViewModel.getInstance().getCurrentUser().getUsername(), "admin");
    	}
	
}
