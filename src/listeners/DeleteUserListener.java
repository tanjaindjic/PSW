package listeners;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import db.connection.DBConnection;
import db.crud.RelationalDeleteTuple;
import db.model.Torka;
import db.model.Value;
import editorSeme.model.enums.Tip;
import login.AdminFrame;
import login.EditUserFrame;
import login.User;
import login.UserType;


/**
 * Deletes Users from System.
 * 
 */
public class DeleteUserListener implements ActionListener {
	private AdminFrame adminFrame;
	
	/**
	 * Propagation of parameters
	 * @param adminFrame containing chosen User.
	 */
	public DeleteUserListener(AdminFrame adminFrame) {
		// TODO Auto-generated constructor stub
		this.adminFrame = adminFrame;
	}

	/**
	 * Deletes an User from System.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		ArrayList<User> users = DBConnection.getInstance().getUsers();
		if(adminFrame.getList().getSelectedValue()!=null){
			
			for(User u : users)
				if(u.getUsername().equals(adminFrame.getList().getSelectedValue())){

					/*
					RelationalRead rr = new RelationalRead();
					ResultSet rs = (ResultSet) rr.readTable("KORISNICI");
					*/
					Torka t = new Torka();
					ArrayList<Value> values = new ArrayList<>();
					Value v1 = new Value(u.getUsername(), Tip.VARCHAR, "USERNAME");
					
					values.add(v1);
					values.add(new Value(u.getPassword(), Tip.VARCHAR, "PASSWORD"));
					if(u.getTypeOfUser().equals(UserType.ADMIN))
						values.add(new Value("a", Tip.CHAR, "USER_TYPE"));
					else if(u.getTypeOfUser().equals(UserType.PROJEKTANT))
						values.add(new Value("p", Tip.CHAR, "USER_TYPE"));
					else 
						values.add(new Value("u", Tip.CHAR, "USER_TYPE"));
					ArrayList<String> pkey = new ArrayList<>();
					pkey.add("USERNAME");
					t.setTorka(values);
					RelationalDeleteTuple rdt = new  RelationalDeleteTuple(t, "KORISNICI", pkey);
					rdt.doCommand();
					
					ArrayList<User> users2 = DBConnection.getInstance().getUsers();
					DefaultListModel<String> dlm = new DefaultListModel<String>();
					for(User u2 : users2)
						dlm.addElement(u.getUsername());
					JList<String> list = new JList<String>();
					list.setModel(dlm);
					list.setFont(new Font( "Arial", Font.CENTER_BASELINE,  12) );
						
					
					adminFrame.setList(list);
					break;
				}
					
			
		}

	}

}
