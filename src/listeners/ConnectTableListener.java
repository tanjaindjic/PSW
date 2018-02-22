package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;

import dialogs.ConnectTableFrame;
import dialogs.NewTableFrame;
import editorSeme.controller.concreetBuilders.DefaultTableBuilder;
import editorSeme.model.pojo.Atribut;

/**
 * Action listener class that creates a frame which shows all of the tables you can connect to and select a attribute for the foreign key.
 *
 */
public class ConnectTableListener implements ActionListener {

	private DefaultTableBuilder dtb; 
	private NewTableFrame newTableFrame; 
	private DefaultListModel<Atribut> dlm;
	/**
	 * Constructor for the ConnectedTableListener.
	 * @param dtb Table builder that is used for making a model of the table.
	 * @param newTableFrame The main frame for adding tables.
	 * @param dlm A list of potential foreign keys.
	 */
	public ConnectTableListener(DefaultTableBuilder dtb, NewTableFrame newTableFrame, DefaultListModel<Atribut> dlm) {
		// TODO Auto-generated constructor stub3.
		this.dtb = dtb;
		this.newTableFrame = newTableFrame;
		this.dlm = dlm;
	}

	/**
	 * Creates the frame that gives you an option to select a foreign key.
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
			ConnectTableFrame ctf = new	ConnectTableFrame(this.dtb,  this.newTableFrame, this.dlm);
			ctf.setModal(true);
			ctf.setVisible(true);
	}

}
