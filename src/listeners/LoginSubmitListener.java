package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import db.connection.DBChooser;
import db.connection.DBConnection;
import editorSeme.model.pojo.Sistem;
import login.AdminFrame;
//import login.AdminFrame;
import login.LoginFrame;
import login.User;
import login.UserType;
import start.InfViewModel;
import workingsection.MainWindow;

/**
 * Submits entered information for logging in and calls next dialog if information is correct.
 */
public class LoginSubmitListener implements ActionListener {

	
	private boolean gotIt;

	
	/**
	 * Submits entered information for logging in, checks credentials and calls next dialog if information is correct.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		gotIt = false;
		ArrayList<User> users = DBConnection.getInstance().getUsers();
		for(int i=0; i<users.size(); i++){
			if(LoginFrame.getInstance().textName.getText().toString().equals(users.get(i).getUsername())){
				if(LoginFrame.getInstance().passField.getText().equals(users.get(i).getPassword())){
					//za dizajnera
					if(users.get(i).getTypeOfUser()==UserType.PROJEKTANT){
						InfViewModel.getInstance().setCurrentUser(users.get(i));
						DBChooser ch = DBChooser.getInstance();
						ch.show();
						System.out.println("projektant ulogovan: " + InfViewModel.getInstance().getCurrentUser().getUsername());
						LoginFrame.getInstance().dispose();
						gotIt=true;
					}
					else if(users.get(i).getTypeOfUser()==UserType.ADMIN){
						InfViewModel.getInstance().setCurrentUser(users.get(i));
						AdminFrame af = AdminFrame.getInstance();
						af.show();
						System.out.println("admin ulogovan: " + InfViewModel.getInstance().getCurrentUser().getUsername());
						LoginFrame.getInstance().dispose();
						gotIt=true;
					}else if(users.get(i).getTypeOfUser()==UserType.REG_KOR){
						InfViewModel.getInstance().setCurrentUser(users.get(i));
						DBChooser ch = DBChooser.getInstance();
						ch.show();
						System.out.println("reg_korisnik ulogovan: " + InfViewModel.getInstance().getCurrentUser().getUsername());
						LoginFrame.getInstance().dispose();
						gotIt=true;
					}
				}
			}
		}
		if(!gotIt){
			JOptionPane.showMessageDialog(null,Sistem.getInstance().getTranslate("Username_pass_wrong"));		
			return;
		}
		LoginFrame.getInstance().dispose();		
		
		
	}

	public boolean isGotIt() {
		return gotIt;
	}

	public void setGotIt(boolean gotIt) {
		this.gotIt = gotIt;
	}

}
