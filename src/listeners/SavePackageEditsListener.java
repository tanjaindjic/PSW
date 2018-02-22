package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dialogs.EditPackageFrame;
import editorSeme.model.additional.JSONSerialize;
import editorSeme.model.pojo.Sistem;
import editorSeme.view.EditorWorkbench;
import start.DatabaseType;
import start.InfViewModel;
import workingsection.tree.Tree;
import editorSeme.model.pojo.Package;

/**
 * Saves edits of Package.
 */
public class SavePackageEditsListener implements ActionListener {

	private EditPackageFrame editPackageFrame;
	
	/** 
	 * Propagation of parameters.
	 * @param editPackageFrame containing information for edited Package.
	 */
	public SavePackageEditsListener(EditPackageFrame editPackageFrame) {
		// TODO Auto-generated constructor stub
		this.editPackageFrame = editPackageFrame;
	}

	/** 
	 * Saves edits made for chosen Package.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
/*
		Package selected = (Package) Tree.getInstance().getSelected();
		if(InfViewModel.getInstance().getDatabaseType().equals(DatabaseType.JSON)){
			if(!editPackageFrame.getCodeTextField().getText().isEmpty())
				selected.getNaziv().setCode(editPackageFrame.getCodeTextField().getText());
	*/		
			Tree.getInstance().reload();
			EditorWorkbench.getInstance().getCodeArea().validate();
			editPackageFrame.dispose();	
		
		
	}

}
