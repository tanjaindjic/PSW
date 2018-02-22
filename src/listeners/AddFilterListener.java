package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import db.search.view.FilterDialog;
/**
 * Class that initialize dialog for adding filters
 *
 */
public class AddFilterListener implements ActionListener {
	/**
	 * Method that opens up a dialog for setting up filters
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		FilterDialog fd =  FilterDialog.getInstance();
		fd.setVisible(true);
	}

}
