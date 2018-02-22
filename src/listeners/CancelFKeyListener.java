package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dialogs.ConnectTableFrame;

/**
 * Action listener class that enables cancellation of an action.
 * It closes the of a frame that is used for adding foreign keys. 
 *
 */
public class CancelFKeyListener implements ActionListener {

	private ConnectTableFrame ctf;
	
	/**
	 * Closes the connected table frame.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		ctf.dispose();
		
	}
	/**
	 * Constructor for the CancelFKeyListener.
	 * @param ctf The frame that should be closed.
	 */
	public CancelFKeyListener(ConnectTableFrame ctf){
		this.ctf= ctf;
	}

}
