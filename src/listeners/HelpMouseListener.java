package listeners;

import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import editorSeme.model.pojo.Sistem;

/**
 * Mouse listener that launches help.
 *
 */
public class HelpMouseListener implements MouseListener {

	/**
	 * @deprecated Unimplemented
	 */
	@Override
	public void mouseClicked(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @deprecated Unimplemented
	 */
	@Override
	public void mouseEntered(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @deprecated Unimplemented
	 */
	@Override
	public void mouseExited(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Launches help on a mouse press.
	 */
	@Override
	public void mousePressed(java.awt.event.MouseEvent e) {
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

	/**
	 * @deprecated Unimplemented
	 */
	@Override
	public void mouseReleased(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
