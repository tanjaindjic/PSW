package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dialogs.EditCodeTable;
import dialogs.EditTableFrame;
import editorSeme.model.pojo.Atribut;
import editorSeme.model.pojo.Table;


/**
 * Edits code for an Attribute.
 */
public class EditCodeInTableListener implements ActionListener {

	private Table selected;
	private Atribut selectedValue;
	private EditTableFrame editTableFrame;
	
	/**
	 * Edits code for Attribute.
	 * @param editTableFrame contains information what Table is being edited.
	 * @param selected is selected Table.
	 * @param selectedValue is selected Attribute.
	 */
	public EditCodeInTableListener(EditTableFrame editTableFrame, Table selected, Atribut selectedValue) {
		// TODO Auto-generated constructor stub
		this.selectedValue = selectedValue;
		this.selected = selected;
		this.editTableFrame = editTableFrame;
	}

	/**
	 * Calls a dialog for editing code of an Attribute.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		EditCodeTable ect = new EditCodeTable(editTableFrame, selected, selectedValue);
		ect.setVisible(true);
	}

}
