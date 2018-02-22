package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import login.AddUserFrame;
/**
 * Class that initialize adding user
 *
 */
public class AddUserListener implements ActionListener {
	/**
	 * Method that opens up a frame for adding new user
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		AddUserFrame auf = new AddUserFrame();
		auf.setVisible(true);
		//dodati korisnika na bazu
	}

}
