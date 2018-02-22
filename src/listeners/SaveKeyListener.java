package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JList;

import dialogs.CreateKeyFrame;
import dialogs.NewTableFrame;
import editorSeme.controller.concreetBuilders.DefaultTableBuilder;
import editorSeme.model.pojo.Atribut;
import editorSeme.model.pojo.Id_Id;
import editorSeme.model.pojo.Key;

/**
 * Action listener class that enables the addition of a key to a table.
 * It tries to save a key to a table.
 *
 */
public class SaveKeyListener implements ActionListener {

	private JList<Atribut> list;
	private CreateKeyFrame ckf;
	private DefaultTableBuilder dtb;
	private NewTableFrame ntf;
	
	/**
	 * 	
	 * Saves the key and closes the frame.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		List<Atribut> selected = list.getSelectedValuesList();
		if(!selected.isEmpty()){
			ArrayList<Id_Id> ids = new ArrayList<Id_Id>();
			for (Atribut a : selected) {
				Id_Id temp = new Id_Id();
				temp.setAtributeKey(a.getName().getCode());
				temp.setTableKey("");
				ids.add(temp);
			}
			dtb.buildNewKey(ids);
			
		}
		ntf.updateKeys();
		
		ckf.dispose();
		
	}
	/**
	 * Constructor for the SaveKeyListener.
	 * @param ckf The frame that should be closed. 
	 * @param lista The list of attributes that make the key.
	 * @param dtb The builder that is used to add the key to the table.
	 * @param ntf The frame that should be updated after the addition. 
	 */
	public SaveKeyListener(CreateKeyFrame ckf, JList lista, DefaultTableBuilder dtb, NewTableFrame ntf){
		this.ckf=ckf;
		this.list=lista;
		this.ntf=ntf;
		this.dtb= dtb;
	}

}
