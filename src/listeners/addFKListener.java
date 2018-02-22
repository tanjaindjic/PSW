package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dialogs.ConnectTableFrame;
/**
 * Action listener class that enables the addition of a foreign key to a table.
 * It crates the connected table window and lets you choose foreign keys. 
 *
 */
public class addFKListener implements ActionListener {

	private ConnectTableFrame c;
	
	@Override

	/**
	 * Make a connection between the foreign key code and the home key.
	 */
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		c.addFor(c.getSelectedFor());
		c.addHome(c.getSelectedHome());
	}
	/**
	 * Constructor for the addFkey class.
	 * @param c The connected table 
	 */
	public addFKListener(ConnectTableFrame c){
		this.c=c;
		
	}

}
