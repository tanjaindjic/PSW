package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dialogs.DomainFrame;
import editorSeme.controller.concreetBuilders.DefaultAtributBuilder;

/**
 * Action listener class that enables the addition of a domain to an attribute.
 * It crates a frame for adding domains. 
 *
 */
public class AddDomainListener implements ActionListener {

	
	private DefaultAtributBuilder dab;
	/**
	 * Creates the frame that adds a domain and shows it.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		DomainFrame df = new DomainFrame(dab);
		df.setVisible(true);
	}
	
	/**
	 * Constructor for the AddAtributeToTableListener.
	 * @param dab Attribute builder that is used for making a model of the attribute.
	 */
	public AddDomainListener(DefaultAtributBuilder dab){
		this.dab=dab;
	}

}
