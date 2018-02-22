package listeners;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import db.connection.DBChooser;
import editorSeme.model.additional.JSONSerialize;
import editorSeme.model.pojo.Sistem;
import editorSeme.view.EditorWorkbench;
import start.DatabaseType;
import start.InfViewModel;
import workingsection.MainWindow;
import workingsection.Tabs;


/**
 * Enables creation of new System.
 */
public class NewSystemListener implements ActionListener {


	/**
	 * Opens a Form for creation of new System.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		if(InfViewModel.getInstance().getDatabaseType().equals(DatabaseType.JSON)){
		
			String[] choices = {  Sistem.getInstance().getTranslate("Yes"),  Sistem.getInstance().getTranslate("No"),  Sistem.getInstance().getTranslate("Cancel") };
	
			int dialogResult = JOptionPane.showConfirmDialog(null, Sistem.getInstance().getTranslate("Save_changes"),  Sistem.getInstance().getTranslate("Warning"),JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
			if(dialogResult == 0) {
				EditorWorkbench.getInstance().codeArea.setText("");
			//	Sistem.getInstance().setPath(System.getProperty("user.dir")+"/"+Sistem.getInstance().getNaziv().getCode()+".json");
			//	System.out.println(System.getProperty("user.dir")+"/"+Sistem.getInstance().getNaziv().getCode()+".json");
				JFileChooser chooser = new JFileChooser();
			    int retrival = chooser.showSaveDialog(null);
			    if (retrival == JFileChooser.APPROVE_OPTION) {
		        	try{
		        	    String path = chooser.getSelectedFile()+".json";
		        	    Sistem.getInstance().setPath(path);
			        } catch (Exception ex) {
			            ex.printStackTrace();
			        }
			    }
				JSONSerialize.saveStructure(Sistem.getInstance());
				MainWindow.getInstance().dispose();
				MainWindow.destroy();
				DBChooser.destroy();
				DBChooser ch = DBChooser.getInstance();
				ch.show();
			}
			else if(dialogResult == 1)
			{
	
				EditorWorkbench.getInstance().codeArea.setText("");
				MainWindow.getInstance().dispose();
				MainWindow.destroy();
				DBChooser.destroy();
				DBChooser ch = DBChooser.getInstance();
				ch.show();
				
			}
		}
		else{
			MainWindow.getInstance().setVisible(false);
			MainWindow.destroy();
			DBChooser.destroy();
			DBChooser ch = DBChooser.getInstance();
			ch.show();
		}
		Sistem.getInstance().destroy();
		Tabs.getInstance().destroy();
		Tabs.getInstance();
		MainWindow.getInstance().repaint();
	/*	// TODO Auto-generated method stub
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
		
*/
	}

}
