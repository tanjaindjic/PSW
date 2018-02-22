package db.search.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Cancel listener of Filter dialog frame cancel button
 *
 */
public class CancelFilterListener implements ActionListener {
	/**
	 * Method that closes the dialog by destroying it
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		FilterDialog.getInstance().destroy();
	}

}
