package test.loginTests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import db.connection.DBConnection;
import editorSeme.model.pojo.Sistem;
import login.LoginFrame;
import login.User;
import login.UserType;
import start.InfViewModel;

public class ProjLoginTest {
private ArrayList<User> users;
private LoginFrame lf;

@Test
public void start() {
    	
    	users = DBConnection.getInstance().getUsers();
		lf = LoginFrame.getInstance();
    		lf.getTextName().setText("proj");
    		lf.getPassField().setText("proj");
    		lf.getSignIn().doClick();
    		assertNotNull(InfViewModel.getInstance().getCurrentUser());
    		assertEquals(InfViewModel.getInstance().getCurrentUser().getTypeOfUser(), UserType.PROJEKTANT);
    		assertEquals(InfViewModel.getInstance().getCurrentUser().getUsername(), "proj");
    	}
    	
   
}
