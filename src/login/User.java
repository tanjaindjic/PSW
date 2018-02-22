package login;

import java.io.Serializable;
import java.util.ArrayList;

import db.connection.DBConnection;

/**
 * Class for User Accounts. Contains Username, Password and Type of User.
 * @author tanja
 *
 */
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private UserType typeOfUser;
	
	/**
	 * Empty constructor for User Accounts.
	 */
	public User(){
	}

	
	/**
	 * Gets Username of an User.
	 * @return value of User's Username.
	 */
	public String getUsername() {
		return username;
	}

	
	/**
	 * Sets Username of an User.
	 * @param  username value of User's Username.
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	
	/**
	 * Gets Password of an User.
	 * @return value of User's Password.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets Password of an User.
	 * @param password value of User's Password.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets Type of an User.
	 * @return value of User's Type.
	 */
	public UserType getTypeOfUser() {
		return typeOfUser;
	}

	/**
	 * Sets Type of an User.
	 * @param typeOfUser value of User's Type.
	 */
	public void setTypeOfUser(UserType typeOfUser) {
		this.typeOfUser = typeOfUser;
	}
	
	/**
	 * Creates default User Account in Database.
	 * @param username is default value for Username.
	 */
	public User(String username) {
		this.username = username;
		this.password = "grok";
		this.typeOfUser = UserType.PROJEKTANT;
	}
	
	/**
	 * Creates new User Account in Database.
	 * @param username sets User's username.
	 * @param password sets User's password.
	 * @param typeOfUser sets User's type.
	 */
	public User(String username, String password, UserType typeOfUser){
		this.username = username;
		this.password = password;
		this.typeOfUser = typeOfUser;
	}

	/**
	 * Validates password.
	 * @param password for validation.
	 * @return if password is validated.
	 */
	public boolean isPassword(String password){
		return this.password==password;
	}

	/**
	 * Logs in User with entered credentials.
	 * @param username2 contains value for User's username.
	 * @param password2 contains value for User's password.
	 * @return User Account that is logged.
	 */
	public static User login(String username2, String password2) {
		ArrayList<User> users = DBConnection.getInstance().getUsers();
		for(User u : users){
			if(u.getUsername().equals(username2))
				if(u.getPassword().equals(password2))
					return u;
		}
		return null;
	}
}
