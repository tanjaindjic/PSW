package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import db.connection.DBConnection;
import editorSeme.model.additional.JSONSerialize;
import editorSeme.model.pojo.Sistem;
import login.AccountSettings;
import start.InfViewModel;
/**
 * Class that saves changes made to user profile
 *
 */
public class SaveAccountChangesListener implements ActionListener {
	private AccountSettings frame;
	/**
	 * Constructor with reference to the AccountSettings
	 * @param frame Frame for listener.
	 */
	public SaveAccountChangesListener(AccountSettings frame) {
		this.frame = frame;
	}
	/**
	 * Method that validate old password and saves new password
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String pass1 = frame.passwordField.getText().toString();
		String pass2 = frame.passwordField_1.getText().toString();
		if(pass1.isEmpty() || pass2.isEmpty())
			JOptionPane.showMessageDialog(null,Sistem.getInstance().getTranslate("Pass_fill"),
				    "", JOptionPane.PLAIN_MESSAGE);
		else if(pass1.equals(pass2)){
			InfViewModel.getInstance().getCurrentUser().setPassword(pass1);
			frame.dispose();
			DBConnection.getInstance().changePass(InfViewModel.getInstance().getCurrentUser(), pass1);
			JOptionPane.showMessageDialog(null,Sistem.getInstance().getTranslate("Pass_change"),
				    "", JOptionPane.PLAIN_MESSAGE);
		}
		else{
			JOptionPane.showMessageDialog(null,Sistem.getInstance().getTranslate("Pass_match"),
				    "", JOptionPane.PLAIN_MESSAGE);
		}
			
	}

}
