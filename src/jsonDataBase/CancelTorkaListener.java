package jsonDataBase;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Class that is connected to cancel button in AddTorkaFrame/EditTorkaFrame
 *
 */
public class CancelTorkaListener implements ActionListener {
	private boolean add;
	public CancelTorkaListener(boolean add){
		this.add=add;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(add)
			AddTorkaFrame.getInstance(null).destroy();
		else
			EditTorkaFrame.getInstance(null).destroy();
	}

}
