package listeners;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import login.AccountSettings;
/**
 * Class that initialize AccountSettings - frame
 *
 */
public class AccountSettingsListener implements ActionListener{
	/**
	 * Method thet opens up a frame for editing profile settings
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		AccountSettings as = AccountSettings.getInstance();
		as.setVisible(true);
	}

}
