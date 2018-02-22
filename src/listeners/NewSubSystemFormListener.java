package listeners;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import dialogs.NewSubSystemFrame;
import editorSeme.model.pojo.Package;
import editorSeme.model.pojo.Sistem;
import editorSeme.view.EditorWorkbench;
import workingsection.MainWindow;
import workingsection.tree.Tree;

/**
 * Enables creation of new SubSystem in System.
 */
public class NewSubSystemFormListener implements ActionListener {

	/**
	 * Opens a Form for creation of new SubSystem.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Tree t = Tree.getInstance();
		
		
		if(t.getSelected() instanceof Sistem){
			Sistem s = (Sistem) t.getLastSelectedPathComponent();
		
				EditorWorkbench.setActiveFormPanel(new NewSubSystemFrame());
				EditorWorkbench.getInstance().validate();
				EditorWorkbench.getInstance().repaint();
				CardLayout cardLayout = (CardLayout) EditorWorkbench.getOptPanel().getLayout();
				cardLayout.show(EditorWorkbench.getOptPanel(), "forme");
				EditorWorkbench.reloadSplitPane();
			
		
		}else if(t.getSelected()==null){
			JOptionPane.showMessageDialog(MainWindow.getInstance(),  Sistem.getInstance().getTranslate("SubSystem_err"));
		}
	}

}
