package db.search.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Action listener that clears values of all elements in filterDialog
 *
 */
public class ClearFilterListener implements ActionListener {
	/**
	 * Method that cleans all text fileds and radioButtons in filterDialog frame 
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		FilterDialog.getInstance().destroy();
		FilterDialog.getInstance().setVisible(true);;		
	}

}
