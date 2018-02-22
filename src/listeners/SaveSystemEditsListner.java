package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dialogs.EditSystemFrame;
import editorSeme.model.additional.JSONSerialize;
import editorSeme.model.pojo.Sistem;
import editorSeme.view.EditorWorkbench;
import start.DatabaseType;
import start.InfViewModel;
import workingsection.tree.Tree;

/**
 * Saves edits made for System.
 */
public class SaveSystemEditsListner implements ActionListener {

	private EditSystemFrame editSystemFrame;
	/**
	 * Propagation of parameters.
	 * @param editSystemFrame contains information for edited System.
	 */
	public SaveSystemEditsListner(EditSystemFrame editSystemFrame) {
		// TODO Auto-generated constructor stub
		this.editSystemFrame = editSystemFrame;
	}
	

	/**
	 * Saves changes made to System.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(InfViewModel.getInstance().getDatabaseType().equals(DatabaseType.JSON)){
			
			if(!editSystemFrame.gettf().getText().isEmpty()){
				Sistem.getInstance().setPath(editSystemFrame.gettf().getText());
				JSONSerialize.saveObj(Sistem.getInstance());
			}
			Tree.getInstance().reload();
			EditorWorkbench.getInstance().getCodeArea().validate();
			editSystemFrame.dispose();	
		}
		

	}

}
