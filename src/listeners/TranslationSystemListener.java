package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dialogs.TranslationFrame;
import editorSeme.model.pojo.Sistem;

/**
 * Calls dialog for System translation.
 */
public class TranslationSystemListener implements ActionListener {

	/**
	 * Opens a dialog for adding System translation.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		TranslationFrame tf = new TranslationFrame();
		tf.setVisible(true);
	}

}
