package workingsection.tree;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dialogs.EditPackageFrame;
import dialogs.EditSystemFrame;
import dialogs.EditTableFrame;
import editorSeme.model.pojo.Sistem;
import editorSeme.model.pojo.Table;
import start.DatabaseType;
import start.InfViewModel;
import editorSeme.model.pojo.Package;

/**
 * Calls a frame for editing an Objects depending of its' type.
 *
 */
public class EditNodeListener implements ActionListener {

	
	/**
	 * Calls a frame for editing an Objects depending of its' type.
	 *@param e is Object for editing.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(Tree.getInstance().getSelected() instanceof Sistem && InfViewModel.getInstance().getDatabaseType().equals(DatabaseType.JSON)){
				EditSystemFrame esf = new EditSystemFrame();
				esf.setVisible(true);
			}
		
		else if(Tree.getInstance().getSelected() instanceof Package){
			EditPackageFrame epf = new EditPackageFrame();
			epf.setVisible(true);
		}
		else if(Tree.getInstance().getSelected() instanceof Table){
			EditTableFrame etf = new EditTableFrame();
			etf.setVisible(true);
		}
		else{
			System.out.println("Nije selektovano nista za desni klik");
		}
		
	}

}
