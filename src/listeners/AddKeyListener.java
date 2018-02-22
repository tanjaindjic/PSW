package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;

import dialogs.CreateKeyFrame;
import dialogs.NewTableFrame;
import editorSeme.controller.concreetBuilders.DefaultTableBuilder;
import editorSeme.model.pojo.Atribut;

/**
 * Action listener class that enables the addition of a key to a table.
 * It crates a frame for adding keys. 
 *
 */
public class AddKeyListener implements ActionListener {
	private DefaultTableBuilder dtb; 
	private NewTableFrame newTableFrame; 
	private DefaultListModel<Atribut> dlm;
	
	/**
	 * Constructor for the AddKeyListener.
	 * @param dtb Table builder that is used for making a model of the table.
	 * @param newTableFrame The main frame that is used to add a new table.
	 * @param dlm List of potential keys.
	 */
	public AddKeyListener(DefaultTableBuilder dtb, NewTableFrame newTableFrame, DefaultListModel<Atribut> dlm) {
		// TODO Auto-generated constructor stub3.
		this.dtb = dtb;
		this.newTableFrame = newTableFrame;
		this.dlm = dlm;
	}

	/**
	 * Creates the frame that adds a attribute key shows it.
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO AChooseKeyFrauto-generated method stub
		CreateKeyFrame ckf = new CreateKeyFrame(dtb, newTableFrame, dlm);
		ckf.setModal(true);
		ckf.setVisible(true);
	}

}
