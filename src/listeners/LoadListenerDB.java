package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Observer;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import db.connection.DBChooser;
import editorSeme.model.parse.ParseJsonDB;
import editorSeme.model.pojo.Sistem;
import editorSeme.model.pojo.SistemModel;
import editorSeme.model.pojo.Table;
import editorSeme.view.EditorWorkbench;
import start.DatabaseType;
import start.InfViewModel;
import workingsection.MainWindow;
import workingsection.Tabs;
import workingsection.WorkArea;
import workingsection.tree.Tree;

/**
 * 
 * Loads JSON Database from File System via File Chooser.
 * Sets up chosen language.
 */
public class LoadListenerDB implements ActionListener {

	private static DBChooser dbc;
	public LoadListenerDB(DBChooser dbChooser) {
		dbc = dbChooser;
	}

	/**
	 * Opens File Chooser for selection of JSON Database.
	 */
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
			InfViewModel.getInstance().getDatabaseType().equals(DatabaseType.JSON);
			ParseJsonDB parser = new ParseJsonDB(path);
			parser.parseModel();
			
			dbc.dispose();
			
			JComboBox<String> lb = DBChooser.getInstance().getLangComboBox();
			String chosenLang = "";
			if(lb.getSelectedItem().equals("English")){
				//Language temp = new Language("enUS", "enUS");
				//Sistem.getInstance().addLang(temp);
				//Sistem.getInstance().setSelectedLang(temp);
				Locale.setDefault(new Locale("en", "US"));
				//System.out.println(Sistem.getInstance().getTranslate("test"));
			}
				
			else if(lb.getSelectedItem().equals("Serbian")){
				//Language temp = new Language("srRS", "srRS");
				//Sistem.getInstance().addLang(temp);
				//Sistem.getInstance().setSelectedLang(temp);
				Locale.setDefault(new Locale("sr", "RS"));
				//System.out.println(Sistem.getInstance().getTranslate("test"));
			}
				
			else{
				//Language temp = new Language("enUS", "enUS");
				//Sistem.getInstance().addLang(temp);
				//Sistem.getInstance().setSelectedLang(temp);
				Locale.setDefault(new Locale("en", "USs"));
				//System.out.println(Sistem.getInstance().getTranslate("test"));
			}
				
			
				/*int idx = -1;
				for(int i = 0; i < Sistem.getInstance().getLang().size(); i++){
					if(Sistem.getInstance().getLang().get(i).getLangId().equals(chosenLang)){
						idx = i;
						break;
					}
					
				}
				Sistem.getInstance().setSelectedLang(Sistem.getInstance().getLang().get(idx));
			*/
				
		//	Language l = new Language("ENG", "ENGLISH");
			//Sistem.getInstance().setSelectedLang(l);
			ArrayList<Observer> obs = new ArrayList<Observer>();
			EditorWorkbench.getInstance();
			MainWindow.getInstance().setVisible(true);	
			obs.add(Tree.getInstance());
			Tree.getInstance().setModel(new SistemModel());
			
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
