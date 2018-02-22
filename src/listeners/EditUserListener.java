package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import db.connection.DBConnection;
import login.AdminFrame;
import login.EditUserFrame;
import login.User;

/**
 * Enables option to edit an User.
 *
 */
public class EditUserListener implements ActionListener {

	private AdminFrame adminFrame;
	
	/**
	 * Propagation of parameters.
	 * @param adminFrame contains selected User.
	 */
	public EditUserListener(AdminFrame adminFrame) {
		// TODO Auto-generated constructor stub
		this.adminFrame = adminFrame;
	}

	/**
	 * Shows dialog for editing an User.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		ArrayList<User> users = DBConnection.getInstance().getUsers();
		if(adminFrame.getList().getSelectedValue()!=null){
			EditUserFrame euf;
			for(User u : users)
				if(u.getUsername().equals(adminFrame.getList().getSelectedValue())){
					euf = new EditUserFrame(u);
					euf.show();
				}
					
			
		}
	}

}
