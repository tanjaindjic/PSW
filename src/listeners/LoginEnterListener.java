package listeners;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import db.connection.DBChooser;
import db.connection.DBConnection;
import dialogs.AdminLang;
import editorSeme.model.pojo.Sistem;
import login.LoginFrame;
import login.User;
import login.UserType;
import start.InfViewModel;
/**
 * Enables logging to InfViewer with Enter Key.
 */
public class LoginEnterListener implements KeyListener {

	/**
	 * Checks what Key has been typed.
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * Opens next dialog after logging in with Enter pressed.
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode()==KeyEvent.VK_ENTER){
			
			boolean gotIt = false;
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
							AdminLang al = new AdminLang();
							al.setVisible(true);
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
	}

	/**
	 * Action when key is released.
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
