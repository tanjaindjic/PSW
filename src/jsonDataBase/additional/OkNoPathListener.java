package jsonDataBase.additional;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import editorSeme.model.additional.JSONSerialize;
import editorSeme.model.pojo.Sistem;
import editorSeme.model.pojo.Table;
import editorSeme.view.EditorWorkbench;
import workingsection.MainWindow;
import workingsection.Tabs;
import workingsection.WorkArea;
import workingsection.tree.Tree;

/**
 * Class that represents listener which adds path to model
 *
 */
public class OkNoPathListener implements ActionListener {
	
	/**
	 * Method that sets location to path, initialize creation of files and sets mainWindow content
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		NoPathFrame ntf = NoPathFrame.getInstance(null);
		if(!ntf.tf.getText().isEmpty()){
			Sistem.getInstance().setPath(ntf.tf.getText());
			JSONSerialize.saveObj(Sistem.getInstance());
			
			WorkArea wa = MainWindow.getWorkArea();
			wa.remove(EditorWorkbench.getInstance());
			wa.remove(Tabs.getInstance());
			wa.repaint();
			wa.validate();
			Tabs.getChildren().removeAll();
			
			for(Table t0: Sistem.getInstance().getAllTables())
				Tabs.addTab(t0);
			wa.add(Tabs.getInstance());
			wa.repaint();
			wa.validate();
			MainWindow.getInstance().validate();
			MainWindow.getInstance().repaint();
			ntf.destroy();
		}
			
	}

}
