package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import workingsection.Tabs;

/**
 * 
 * Deletes current search query.
 *
 */
public class DeleteSearchListener implements ActionListener{
	private JTextField tf;
	public DeleteSearchListener(JTextField tf) {
		this.tf=tf;
	}

	/**
	 * Refreshes view after deleting search query.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Tabs.getInstance().refreshTab();		
	}

}
