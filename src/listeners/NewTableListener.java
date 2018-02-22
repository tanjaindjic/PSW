package listeners;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import dialogs.NewTableFrame;
import editorSeme.model.enums.PackageType;
import editorSeme.model.pojo.Package;
import editorSeme.model.pojo.Sistem;
import editorSeme.model.pojo.Table;
import editorSeme.view.EditorWorkbench;
import workingsection.MainWindow;
import workingsection.tree.Tree;

/**
 * Enables creation of new Table in System.
 */
public class NewTableListener implements ActionListener {


	/**
	 * Opens a Form for creation of new Table.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		Tree t = Tree.getInstance();
	
		
		if(t.getSelected() instanceof Sistem){
			JOptionPane.showMessageDialog(MainWindow.getInstance(),  Sistem.getInstance().getTranslate("Tab_to_sys_err"));
		
	
	
		}else if(t.getSelected() instanceof Package){
			Package p = (Package) t.getLastSelectedPathComponent();
			
			if(p.getPackageType().equals(PackageType.SUBSYSTEM)){
				JOptionPane.showMessageDialog(MainWindow.getInstance(),  Sistem.getInstance().getTranslate("Tab_to_ssys_err"));
			
			}else if(p.getPackageType().equals(PackageType.PACKAGE)){
				EditorWorkbench.setActiveFormPanel(new NewTableFrame());
				EditorWorkbench.getInstance().validate();
				EditorWorkbench.getInstance().repaint();
				CardLayout cardLayout = (CardLayout) EditorWorkbench.getOptPanel().getLayout();
				cardLayout.show(EditorWorkbench.getOptPanel(), "forme");
				EditorWorkbench.reloadSplitPane();
			}
		}else if(t.getSelected() instanceof Table) {
			EditorWorkbench.setActiveFormPanel(new NewTableFrame());
			EditorWorkbench.getInstance().validate();
			EditorWorkbench.getInstance().repaint();
			CardLayout cardLayout = (CardLayout) EditorWorkbench.getOptPanel().getLayout();
			cardLayout.show(EditorWorkbench.getOptPanel(), "forme");
			EditorWorkbench.reloadSplitPane();
			
		}else{
			JOptionPane.showMessageDialog(MainWindow.getInstance(),
					Sistem.getInstance().getTranslate("Tab_err"));
		}
		
	}

}
