package listeners;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import db.connection.DBChooser;
import dialogs.NewSubSystemFrame;
import editorSeme.model.pojo.Sistem;
import editorSeme.model.pojo.SistemModel;
import editorSeme.view.EditorWorkbench;
import workingsection.MainWindow;
import workingsection.Tabs;
import workingsection.WorkArea;
import workingsection.tree.Tree;

/**
 * Creates a new JSON Database.
 */
public class NewJSONListener implements ActionListener {

	
	/**
	 * Sets up Main Window for new JSON Database.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(!DBChooser.getInstance().getCodeTextField().getText().isEmpty()) {
			// TODO Auto-generated method stub
			EditorWorkbench.getInstance();
			CardLayout cardLayout = (CardLayout) EditorWorkbench.getOptPanel().getLayout();
			cardLayout.show(EditorWorkbench.getOptPanel(), "opcije");
			MainWindow main = MainWindow.getInstance();
			WorkArea wa = MainWindow.getWorkArea();
			Tree.getInstance().setModel(new SistemModel(DBChooser.getInstance().getCodeTextField().getText()));
			Sistem.getInstance().getNaziv().setCode(DBChooser.getInstance().getCodeTextField().getText());
			Sistem s = Sistem.getInstance();
			wa.remove(EditorWorkbench.getInstance());
			wa.remove(Tabs.getInstance());
			EditorWorkbench.destroy();
			wa.repaint();
			wa.validate();
			wa.add(EditorWorkbench.getInstance(), BorderLayout.CENTER);
			wa.repaint();
			wa.validate();
			//s.getNaziv().setCode(DBChooser.getInstance().getCodeTextField().getText());
			
	
			Tree.getInstance().setVisible(true);
			DBChooser.getInstance().dispose();
		}else {
			JOptionPane.showMessageDialog(null, Sistem.getInstance().getTranslate("emptySystem"));
		}
	}

}
