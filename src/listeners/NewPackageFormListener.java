package listeners;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import dialogs.NewPackageFrame;
import editorSeme.model.enums.PackageType;
import editorSeme.model.pojo.Package;
import editorSeme.model.pojo.Sistem;
import editorSeme.view.EditorWorkbench;
import workingsection.MainWindow;
import workingsection.tree.Tree;


/**
 * Enables creation of new Package in System.
 */
public class NewPackageFormListener implements ActionListener {

	/**
	 * Opens a Form for creating new Package.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Tree t = Tree.getInstance();
	
		
		if(t.getSelected() instanceof Package){
			Package p = (Package) t.getLastSelectedPathComponent();
			if(p.getPackageType().equals(PackageType.PACKAGE) || p.getPackageType().equals(PackageType.SUBSYSTEM)){
				EditorWorkbench.setActiveFormPanel(new NewPackageFrame());
				EditorWorkbench.getInstance().validate();
				EditorWorkbench.getInstance().repaint();
				CardLayout cardLayout = (CardLayout) EditorWorkbench.getOptPanel().getLayout();
				cardLayout.show(EditorWorkbench.getOptPanel(), "forme");
				EditorWorkbench.reloadSplitPane();
			}
		}else{
			JOptionPane.showMessageDialog(MainWindow.getInstance(), Sistem.getInstance().getTranslate("Package_err"));
				  
		}
	}

}
