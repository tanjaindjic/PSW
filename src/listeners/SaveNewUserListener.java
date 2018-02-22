package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import db.connection.DBConnection;
import db.crud.RelationalCreateTuple;
import db.model.Torka;
import db.model.Value;
import editorSeme.model.enums.Tip;
import editorSeme.model.pojo.Sistem;
import login.AddUserFrame;
import login.User;
import login.UserType;
import workingsection.MainWindow;


/**
 * Saves new User in Database.
 *
 */
public class SaveNewUserListener implements ActionListener {
	private AddUserFrame addUserFrame;
	public SaveNewUserListener(AddUserFrame addUserFrame) {
		// TODO Auto-generated constructor stub
		this.addUserFrame = addUserFrame;
	}

	
	/**
	 * Checks credentials and saves new User in Database.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		ArrayList<User> users = DBConnection.getInstance().getUsers();
		for(User u : users)
			if(u.getUsername().equals(addUserFrame.getUsernameTextField().getText())){
				JOptionPane.showMessageDialog(MainWindow.getInstance(),
						Sistem.getInstance().getTranslate("Username_taken"));
				return;
			}
		User us = null;
		if(addUserFrame.getTypeComboBox().getSelectedItem()!=null){
			
			if(addUserFrame.getTypeComboBox().getSelectedItem().equals("Administrator"))
				 us = new User(addUserFrame.getUsernameTextField().getText(), addUserFrame.getPassTextField().getText(),UserType.ADMIN );
			else if(addUserFrame.getTypeComboBox().getSelectedItem().equals("Projectant"))
				 us = new User(addUserFrame.getUsernameTextField().getText(), addUserFrame.getPassTextField().getText(),UserType.PROJEKTANT );
			else 
				 us = new User(addUserFrame.getUsernameTextField().getText(), addUserFrame.getPassTextField().getText(),UserType.REG_KOR );
		}else{
			JOptionPane.showMessageDialog(MainWindow.getInstance(),
					Sistem.getInstance().getTranslate("User_type_err"));
			return;
		}
		if(us!=null){
			Torka t = new Torka();
			ArrayList<Value> values = new ArrayList<>();
			Value v1 = new Value(us.getUsername(), Tip.VARCHAR, "USERNAME");
			
			values.add(v1);
			Value v2 = new Value(us.getPassword(), Tip.VARCHAR, "PASSWORD");
			
			values.add(v2);
			
			if(us.getTypeOfUser().equals(UserType.ADMIN)){
				Value v3 = new Value("a", Tip.CHAR, "USER_TYPE");
				
				values.add(v3);
			}
				
			else if(us.getTypeOfUser().equals(UserType.PROJEKTANT)){
				Value v3 =new Value("p", Tip.CHAR, "USER_TYPE");
			
				values.add(v3);
			}
				
			else {
				Value v3 =new Value("u", Tip.CHAR, "USER_TYPE");
				
				values.add(v3);
			}

			t.setTorka(values);
			RelationalCreateTuple rct = new RelationalCreateTuple(t, "KORISNICI");
			rct.doCommand();
		}
		
				

	}

}
