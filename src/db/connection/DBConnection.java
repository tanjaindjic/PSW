package db.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import login.User;
import login.UserType;

/**
 * 
 * This class is used to establish a connection to a remote database.
 *
 */
public class DBConnection {

	private static DBConnection instance = null;
	private static String ip ="147.91.175.155";
	private static String dbport = "1433";
	private static String dbname = "psw-2017-tim5-1";
	private static String dbusername ="psw-2017-tim5-1";
	private static String dbpassword = "tim5-15919808";
	private static String connection = "jdbc:jtds:sqlserver://";
	private static Connection conn;
	
	/**
	 * If a instance of the DBConnection class didn't exist before this method has been called a new connection with the predefined or later setup parameters will be created. 
	 * On the other hand if a connection has already existed the current instance of the DBConnection class will be returned.
	 * @return Returns the current DBConnection object
	 * 
	 */

	public static DBConnection getInstance(){
		if (instance == null){
			instance = new DBConnection();
			try {
				 conn = DriverManager.getConnection(connection+ip+"/"+dbname, dbusername, dbpassword);
				
				  if (conn != null) {
				        System.out.println("Connected");
				    }
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return instance;
	}
	
	 /**
	  * Deletes the instance of the DBConnection object that has invoked this method.
	  */
	public void deleteInstance() { 
		instance = null;
	}
	
	/**
	 * 
	 * Returns the connection to the database class
	 * @return Connection object that represents the connection to a remote database.
	 */
	public Connection getConnection(){
		return conn;
	}
	
	private DBConnection(){}
	
	
	/**
	 * Sets the parameters for a connection to a database and tries to connect to it. 
	 * @param ip Internet Protocol address of the host device.
	 * @param dbname Name of the database
	 * @param dbusername User name of the account that is trying to establish a connection to the database.
	 * @param dbpassword Password of the account that is trying to establish a connection to the database.
	 * @param connection Url of the database that on which the connection should occur.
	 */
	@SuppressWarnings("static-access")
	public void setParams(String ip, String dbname,String dbusername, String dbpassword, String connection){
		this.ip=ip;
		this.dbname=dbname;
		this.dbusername=dbusername;
		this.dbpassword=dbpassword;
		this.connection=connection;
		 try {
			conn = DriverManager.getConnection(connection+ip+"/"+dbname, dbusername, dbpassword);
			 if (conn != null) {
			        System.out.println("Connected");
			    }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Returns the Internet Protocol address of the host device.
	 * @return Internet Protocol address of the host device.
	 */
	public String getDbconnection() {
		return ip;
	}
	/**
	 * Sets Internet Protocol address of the host device to the provided value.
	 * @param dbconnection Internet Protocol address of the host device.
	 */
	public void setDbconnection(String dbconnection) {
		ip = dbconnection;
	}
	/**
	 * Returns the string that represents the port number on which the connection should occur. 
	 * @return Port number 
	 */
	public String getDbport() {
		return dbport;
	}
	/**
	 * Sets the port number on which the connection should occur to the provided value.
	 * @param dbport1 Port number
	 */
	public void setDbport(String dbport1) {
		dbport = dbport1;
	}
	/**
	 * Returns the name of the database.
	 * @return Name of the database.
	 */
	public String getDbname() {
		return dbname;
	}
	/**
	 * Sets the name of the database to the provided value.
	 * @param dbname1 Name of the database.
	 */
	public void setDbname(String dbname1) {
		dbname = dbname1;
	}
	/**
	 * Returns the Username of the account that is trying to establish a connection.
	 * @return Username of the account.
	 */
	public String getDbusername() {
		return dbusername;
	}
	/**
	 * Sets the Username of the account that is trying to establish a connection.
	 * @param dbusername1 Username of the account whit which you are trying to establish a connection with.
	 */
	public void setDbusername(String dbusername1) {
		dbusername = dbusername1;
	}
	/**
	 * Sets the password for the database connection. 
	 * @param dbpassword2 password that will be set.
	 */
	public void setDbpassword(String dbpassword2) {
		dbpassword = dbpassword2;
	}

	
/**
 * Gets list of all Users
 * @return list of Users
 */
	public ArrayList<User> getUsers() {
		ArrayList<User> users = new ArrayList<>();
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Nije ostvarena konekcija");
		}
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery("SELECT * FROM KORISNICI");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while (rs.next()){
			String username=rs.getString(1);
			String pass=rs.getString(2);
			String type = rs.getString(3);
			User u = null;
			if(type.equals("a")){
				 u = new User(username, pass, UserType.ADMIN);
			}
			else if(type.equals("p")){
				 u = new User(username, pass, UserType.PROJEKTANT);
			}
			else if(type.equals("u")){
				 u = new User(username, pass, UserType.REG_KOR);
			}
			else{
				System.out.println("greska kod korisnika");
			}
			if(u!=null)
				users.add(u);
			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//obavezno je zatvaranje Statement i ResultSet objekta
		try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
		
	}

	/**
	 * Changes password of an User
	 * @param currentUser whose Password needs to be changed.
	 * @param pass1 new Password.
	 */
	public void changePass(User currentUser, String pass1) {
		
		
	}

	/**
	 * Returns if User was successfully added to Database.
	 * @param username of new User.
	 * @return result of adding to Database.
	 */
	public static boolean addUser(String username) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
