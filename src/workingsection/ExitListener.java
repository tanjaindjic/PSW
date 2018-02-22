package workingsection;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Exits the Application.
 *
 */
public class ExitListener implements ActionListener {

	/**
	 * Exits the Application on mouse click.
	 * @param arg0 is Object that invoked exit.
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.exit(0);
	}

}
