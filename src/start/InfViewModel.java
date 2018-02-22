package start;

import java.util.ArrayList;

import db.connection.DBConnection;
import editorSeme.model.additional.Clipboard;
import editorSeme.model.pojo.Sistem;
import login.User;
/**
 * Class that contains all necessary informations about application options
 *
 */
public class InfViewModel {
	private static InfViewModel instance;
	private User currentUser;
	private Sistem activeSistem;
	private DatabaseType databaseType;
	private ArrayList<User> users;
	private  Clipboard clipboard;
	
	/**
	 * getter for databaseType
	 * @return - databaseType parameter from InfViewModel
	 */
	public DatabaseType getDatabaseType() {
		return databaseType;
	}
	/**
	 * setter for databaseType
	 * @param databaseType - type of database to be set
	 */
	public void setDatabaseType(DatabaseType databaseType) {
		this.databaseType = databaseType;
	}
	/**
	 * getter for clipboard
	 * @return - clipboard parameter from InfViewModel
	 */
	public Clipboard getClipboard(){
		return clipboard;
	}
	/**
	 * getter for currentUser
	 * @return - currentUser parameter from InfViewModel
	 */
	public User getCurrentUser() {
		return currentUser;
	}
	/**
	 * setter for currentUser
	 * @param currentUser - instance of user that is logged in
	 */
	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}
	/**
	 * getter for activeSistem
	 * @return - activeSistem parameter from InfViewModel
	 */
	public Sistem getActiveSistem() {
		return activeSistem;
	}
	/**
	 * setter for activeSistem
	 * @param activeSistem - instance of activeSistem used in application
	 */
	public void setActiveSistem(Sistem activeSistem) {
		this.activeSistem = activeSistem;
	}
	/**
	 * getter for users
	 * @return - users parameter from InfViewModel
	 */
	public ArrayList<User> getUsers() {
		return users;
	}
	/**
	 * Method that sets params currentUser and activeSistem, when user logs out
	 */
	public void logout(){
		currentUser=null;
		activeSistem=null;
	}
	
	
	/**
	 * Singleton pattern, method creates just one instance of this class 
	 * @return - instance of infViewModel class
	 */
	public static InfViewModel getInstance() {
		
		if (instance == null){
			instance = new InfViewModel();
			
		}
		
		return instance;
	}
	/**
	 * Empty constructor
	 */
	private InfViewModel(){
		currentUser = null;
		activeSistem = null;
		clipboard=Clipboard.getInstance();
		//UsersJson uj = new UsersJson();
		users = DBConnection.getInstance().getUsers();
		databaseType = null;
	}
	/**
	 * Check if there is an user with username and password sent as params
	 * If there is, sets the atribute user to value found as loged in user
	 * @param username - typed in username
	 * @param password - typed in password
	 * @return
	 */
	private boolean logInUser(String username, String password){
		User user = User.login(username, password);
		if(user==null){
			return false;
		}
		
		currentUser = user;
		/*if(user.getTypeOfUser()==UserType.DESIGNER){
		loadData(user.getPathToUsersFolder());
		}		*/
		return true;
	}
	/**
	 * Method that registers a new user by calling responsible method for that action
	 * @param username - name of registered user
	 * @return True - user added successfully, False - user not added successfully
	 */
	public boolean registerUser(String username){
		return DBConnection.addUser(username);
	}
	
}
