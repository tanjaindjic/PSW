package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;

import dialogs.ConnectTableFrame;
import editorSeme.controller.concreetBuilders.DefaultTableBuilder;
import editorSeme.model.pojo.Table;

/**
 * Action listener class that enables the addition of an foreign key to a table.
 * It tries to save a foreign key to a table.
 */
public class saveFKListener implements ActionListener {

	private ConnectTableFrame ctf;
	private DefaultTableBuilder dtb;
	private JComboBox table;
	private ArrayList<String> fat;
	private ArrayList<String> at;
	
	/**
	 * 	
	 * Saves the foreign key to the table and closes the frame.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
	
		Table conected = (Table)table.getSelectedItem();
		
		dtb.buildNewFKey(conected.getNaziv().getCode(), at, fat);
		
		ctf.dispose();
		
	}
	/**
	 * Constructor for the SaveFKListener.
	 * @param ctf The frame that should be closed.
	 * @param dtb The builder that is used to create the foreign key in the table.
	 * @param table The table in which the foreign key should be added.
	 * @param fat The list of foreign attribute codes
	 * @param at The list of attributes in the table which become foreign keys.
	 */
	public saveFKListener(ConnectTableFrame ctf, DefaultTableBuilder dtb, JComboBox table, ArrayList<String> fat, ArrayList<String> at){
		this.ctf=ctf;
		this.dtb=dtb;
		this.table=table;
		this.fat=fat;
		this.at=at;
	}

}
