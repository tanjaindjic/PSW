package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dialogs.AddAttributeInTableFrame;
import dialogs.NewTableFrame;
import editorSeme.controller.concreetBuilders.DefaultAtributBuilder;
import editorSeme.controller.concreetBuilders.DefaultTableBuilder;

/**
 * Action listener class that enables the addition of an attribute to a table.
 * It creates a frame for adding attributes. 
 *
 */
public class AddAttributeToTableListener implements ActionListener {

	private DefaultAtributBuilder dab;
	private DefaultTableBuilder dtb;
	private NewTableFrame ntf;
	
	/**
	 * Creates the frame that adds an attribute and shows it.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.dab = new DefaultAtributBuilder(null);
		AddAttributeInTableFrame aaf = new AddAttributeInTableFrame(dab, dtb, ntf);
		aaf.setVisible(true);
	}
	
	/**
	 * Constructor for the AddAtributeToTableListener.
	 * @param dtb2 Table builder that is used for making a model of the table.
	 * @param ntf The main frame for adding tables.
	 */
	public AddAttributeToTableListener(DefaultTableBuilder dtb2, NewTableFrame ntf){
		this.dtb= dtb2;
		
		this.ntf = ntf;
		
		
	}

}