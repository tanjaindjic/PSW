package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JList;

import dialogs.ConnectTableFrame;
import dialogs.NewTableFrame;
import editorSeme.model.pojo.Atribut;
import editorSeme.model.pojo.Key;
import editorSeme.model.pojo.Table;


/**
 * Connects chosen Tables.
 */
public class ChosenTableListener implements ActionListener {
	private ConnectTableFrame ntf;
	
	/**
	 * Propagation of parameters.
	 * @param connectTableFrame contains information what Table is being connected.
	 */
	public ChosenTableListener(ConnectTableFrame connectTableFrame) {
		// TODO Auto-generated constructor stub
		this.ntf = connectTableFrame;
	}

	/**
	 * Refreshes the list of Foreign Keys of connected Table.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JComboBox<Table> cbx = (JComboBox<Table>) e.getSource();
		Table t = (Table) cbx.getSelectedItem();
		
		DefaultListModel dlm  = new DefaultListModel<>();
		
		for(Atribut a : t.getPolja())
			dlm.addElement(a);
		
		ntf.setFKlist(dlm);
		ntf.setTablesComboBox(false);
		
	
	}

}
