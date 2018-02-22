package workingsection.tree;

import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import editorSeme.model.enums.PackageType;
import editorSeme.model.pojo.Atribut;
import editorSeme.model.pojo.Package;
import editorSeme.model.pojo.Sistem;
import editorSeme.model.pojo.Table;
import editorSeme.view.EditorWorkbench;
import start.DatabaseType;
import start.InfViewModel;
/**
 * Class that provides actions on the tree
 *
 */
public class TreeListener implements TreeSelectionListener{
	/**
	 * Method that changes the availability of EditorWorkbench based on the selected node in the tree 
	 */
	@Override
	public void valueChanged(TreeSelectionEvent arg0) {
		JTree t=(JTree)arg0.getSource();
		 Object node = (Object) t.getLastSelectedPathComponent();
		 if(InfViewModel.getInstance().getDatabaseType().equals(DatabaseType.RELATIONAL))
			 return;
		 if(EditorWorkbench.isNull())
			 return;
		 
		if(node instanceof Sistem){
		/*  EditorWorkbench.getBtnNewPackage().setEnabled(false);
			EditorWorkbench.getBtnNewSubSys().setEnabled(false);
			EditorWorkbench.getBtnNewSys().setEnabled(true);
			for(Package p : ((Sistem) node).getPackages()){
				if(p.getPackageType().equals(PackageType.SYSTEM)){
					EditorWorkbench.getBtnNewSys().setEnabled(false);
					break;
				}*/
			EditorWorkbench.getBtnNewPackage().setEnabled(false);
			EditorWorkbench.getBtnNewSubSys().setEnabled(true);

			EditorWorkbench.getBtnNewTable().setEnabled(false);
		}else if(node instanceof Package){
			Package p = (Package) node;
			 if(p.getPackageType().equals(PackageType.SUBSYSTEM)){
				EditorWorkbench.getBtnNewPackage().setEnabled(true);
				EditorWorkbench.getBtnNewSubSys().setEnabled(true);

				EditorWorkbench.getBtnNewTable().setEnabled(false);
			}else if(p.getPackageType().equals(PackageType.PACKAGE)){
				EditorWorkbench.getBtnNewPackage().setEnabled(true);
				EditorWorkbench.getBtnNewSubSys().setEnabled(false);
		
				EditorWorkbench.getBtnNewTable().setEnabled(true);
			}
		}else if(node instanceof Table){
			EditorWorkbench.getBtnNewPackage().setEnabled(false);
			EditorWorkbench.getBtnNewSubSys().setEnabled(false);

			EditorWorkbench.getBtnNewTable().setEnabled(true);
		}else if(node instanceof Atribut){
			EditorWorkbench.getBtnNewPackage().setEnabled(false);
			EditorWorkbench.getBtnNewSubSys().setEnabled(false);
	
			EditorWorkbench.getBtnNewTable().setEnabled(false);
		}else{
			System.out.println("nije nijedan tip");
		}
	}

}
