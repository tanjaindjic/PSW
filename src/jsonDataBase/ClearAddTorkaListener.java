package jsonDataBase;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import editorSeme.model.pojo.Table;
/**
 * Class that clears all fields and provides to add new tuple in the same frame again
 *
 */
public class ClearAddTorkaListener implements ActionListener {
	private Table t;
	/**
	 * Constructor that makes actionlistener valid
	 * @param t Table to be cleared.
	 */
	public ClearAddTorkaListener(Table t) {
		this.t=t;
	}
	/**
	 * Method that clears all fields and 'restarts' the frame
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		AddTorkaFrame.getInstance(t).destroy();
		AddTorkaFrame.getInstance(t).setVisible(true);

	}

}
