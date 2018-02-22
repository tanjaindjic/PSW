package start;

import java.util.ArrayList;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import db.connection.DBChooser;
import db.connection.DBConnection;
import editorSeme.model.additional.JSONSerialize;
import editorSeme.model.pojo.Sistem;
import login.LoginFrame;
import login.User;
/**
 * 
 * Class that start up the InfViewer program.
 *
 */
public class Start {

	/**
	 * Constructs the Login, Model, View and Controller of the InfViewer program.
	 * @param args Potential arguments of the main method.
	 */
	public static void main(String[] args) {	
		//fsgsf
	
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*Test t = new Test();
		Sistem.getInstance().setPath("sema.json");
		JSONSerialize.saveStructure(Sistem.getInstance());*/
		//DBConnection db=DBConnection.getInstance(); 
		LoginFrame lf = LoginFrame.getInstance();
		InfViewModel model = InfViewModel.getInstance();
	//	ArrayList<User> users = Users.getInstance();
		DBConnection.getInstance();
		ArrayList<User> users = DBConnection.getInstance().getUsers();
		DBChooser.getInstance();
		
		
	}

}
