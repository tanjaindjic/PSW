package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import editorSeme.model.pojo.NameTranslate;
import editorSeme.model.pojo.Table;
import editorSeme.model.pojo.Translation;
import editorSeme.view.EditorWorkbench;
import workingsection.MainWindow;
import workingsection.Tabs;
import workingsection.WorkArea;

/**
 * Cancels creation of a new System.
 */
public class CancelNewSystemListener implements ActionListener {

	
	/**
	 * Goes back from active Form for creating new System to Options panel.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		WorkArea wa = MainWindow.getWorkArea();
		wa.remove(EditorWorkbench.getInstance());
		wa.remove(Tabs.getInstance());
		wa.repaint();
		wa.validate();
		EditorWorkbench.destroy();
	
		MainWindow.getInstance().validate();
	}

}
