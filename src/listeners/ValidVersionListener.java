package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dialogs.LastVersionFrame;
import editorSeme.controller.memento.Originator;

/**
 * 
 * Action listener for the Last valid button.
 */
public class ValidVersionListener implements ActionListener {

	/**
	 * Constructs a frame that shows the last valid version of JSON.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		LastVersionFrame lvf = new LastVersionFrame(Originator.getInstance().getValid());
		lvf.setVisible(true);
	}

}
