package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dialogs.ChooseParentTableFrame;
import editorSeme.model.pojo.Table;
import workingsection.Tabs;


/**
 * Enables option for choosing parent Table.
 *
 */
public class ChooseParentTableListener implements ActionListener {
	private ChooseParentTableFrame cptf;
	private Table chosen;
	
	/**
	 * Propagation of parameters.
	 * @param selectedItem is Table that needs parent to be chosen for opening.
	 * @param cptf Frame for choosing parent Table.
	 */
	public ChooseParentTableListener(Table selectedItem, ChooseParentTableFrame cptf) {
		// TODO Auto-generated constructor stub
		this.chosen = selectedItem;
		this.cptf = cptf;
	}

	
	/**
	 * Opens a parent Table.
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		Tabs.getChildren().removeAll();
		Tabs.getInstance().addTab(chosen);
		cptf.setVisible(false);

	}

}
