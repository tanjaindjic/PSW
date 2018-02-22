package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dialogs.CreateKeyFrame;

/**
 * Action listener class that enables cancellation  of an action.
 * It closes the of a frame that is used for adding keys. 
 *
 */
public class CancelKeyListener implements ActionListener {

	private CreateKeyFrame ckf;
	/**
	 * Closes the create key frame.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		ckf.dispose();
	}
	/**
	 * Constructor for the CancelKeyListener.
	 * @param ckf The frame that should be closed.
	 */
	public CancelKeyListener(CreateKeyFrame ckf){
		this.ckf = ckf;
	}

}
