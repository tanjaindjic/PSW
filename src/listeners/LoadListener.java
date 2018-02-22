package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observer;

import javax.swing.JFileChooser;
import javax.swing.JMenuBar;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.DefaultTreeModel;

import db.connection.DBChooser;
import editorSeme.model.additional.JSONSerialize;
import editorSeme.model.pojo.Sistem;
import editorSeme.model.pojo.SistemModel;
import editorSeme.model.pojo.Table;
import editorSeme.view.EditorWorkbench;
import start.DatabaseType;
import start.InfViewModel;
import workingsection.MainWindow;
import workingsection.MenuBar;
import workingsection.Tabs;
import workingsection.ToolBar;
import workingsection.WorkArea;
import workingsection.tree.Tree;

public class LoadListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		JFileChooser chooser = new JFileChooser();
		FileFilter filter =new FileNameExtensionFilter("json", "JSON");
		
		chooser.setAcceptAllFileFilterUsed(false);
		chooser.setFileFilter(filter);
		
		chooser.setAcceptAllFileFilterUsed(false);
		int response=chooser.showOpenDialog(null);
		if( response ==JFileChooser.APPROVE_OPTION){
			String path = chooser.getSelectedFile().getAbsolutePath();
			Sistem s = JSONSerialize.loadStructure(path);
			InfViewModel.getInstance().setDatabaseType(DatabaseType.JSON);
			if(s==null)
				return;
			s.setPath(path);
		/*	ArrayList<Observer> obs = new ArrayList<Observer>();
			obs.add(Tree.getInstance());
			Sistem.getInstance().addSistem(s);
			Tree.getInstance().setModel(new SistemModel());
			s.reloadObservers(obs);
			s.reloadChildren();
			//Workspace w = Workspace.getInstance();
			//w.getSchemes().add(s);
			//Tree.getInstance().setModel(new DefaultTreeModel(w));
			Tabs.getInstance().destroy();
			Tree.getInstance().setVisible(true);*/

			ArrayList<Observer> obs = new ArrayList<Observer>();
			obs.add(Tree.getInstance());

			s.reloadObservers(obs);
			s.reloadChildren();
			
			s.setPath(path);
			Sistem.getInstance().addSistem(s);
			Tree.getInstance().setModel(new SistemModel());
			ToolBar.getInstance().destroy();
			MenuBar.getInstance().destroy();
			WorkArea.getInstance().destroy();
			EditorWorkbench.getInstance().destroy();
			MainWindow.getInstance().destroy();
			Tabs.getInstance().destroy();
			
			EditorWorkbench.getInstance();
			MainWindow.getInstance().setVisible(true);	
			
			
			
			
			Sistem.getInstance().reloadObservers(obs);
			Tree.getInstance().setVisible(true);
		
			WorkArea wa = MainWindow.getWorkArea();
		
			for (Table t : Sistem.getInstance().getAllTables() ){
				Tabs.getInstance().addTab(t);
			}
			wa.add(Tabs.getInstance());
			wa.setVisible(true);
			wa.repaint();
			wa.validate();
			MainWindow.getInstance().validate();
			
		}
	}

}
