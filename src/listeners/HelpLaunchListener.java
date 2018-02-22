package listeners;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.AbstractAction;

import editorSeme.model.pojo.Sistem;

/**
 * Launches help when triggered.
 *
 */
public class HelpLaunchListener extends AbstractAction {

	/**
	 * Opens help in a new window.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		String s = Sistem.getInstance().getTranslate("help_l");
		
		File file = new File("Help/"+s);
		//System.out.println(file.getAbsolutePath());
		
		try {
			Runtime.getRuntime().exec("HH.EXE ms-its:" + file.getAbsolutePath());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
