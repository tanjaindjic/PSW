package listeners;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import editorSeme.model.additional.JSONSerialize;
import editorSeme.model.pojo.Sistem;
import editorSeme.view.EditorWorkbench;
import workingsection.MainWindow;
import workingsection.Tabs;
import workingsection.WorkArea;

/**
 * Opens workbench in work area.
 */
public class OpenWorkbenchListener implements ActionListener {

	/**
	 * Creates an Instance of workbench if it's Null and sets it in work area.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		MainWindow main = MainWindow.getInstance();
		
		if(EditorWorkbench.isNull())
		{
			
			WorkArea wa = MainWindow.getWorkArea();
			wa.remove(Tabs.getInstance());
			wa.add(EditorWorkbench.getInstance(), BorderLayout.CENTER);
			wa.validate();
			main.validate();
			EditorWorkbench.reloadSplitPane();
		}
		else
		{
			//JSONSerialize.saveStructure(Sistem.getInstance());
			WorkArea wa = MainWindow.getWorkArea();
			wa.remove(EditorWorkbench.getInstance());
			wa.remove(Tabs.getInstance());
			EditorWorkbench.destroy();
			wa.repaint();
			wa.validate();
			wa.add(EditorWorkbench.getInstance(), BorderLayout.CENTER);
			wa.repaint();
			wa.validate();
			EditorWorkbench.reloadSplitPane();
		}
		
	}

}
