package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;

import dialogs.ChooseParentTableFrame;
import editorSeme.model.pojo.FKey;
import editorSeme.model.pojo.Sistem;
import editorSeme.model.pojo.Table;
import workingsection.Tabs;

/**
 * Opens all parent Tables of current Table.
 */
public class DemoteListener implements ActionListener {

	private ChooseParentTableFrame cptf;

	
	/**
	 * Finds all parent Tables of current Table and opens them.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		  int index = Tabs.getTabele().getSelectedIndex();
	        
	        Table chosen = null;
	        if(index!=-1)	
		        for(Table t : Sistem.getInstance().getAllTables()){
		        	if(t.toString().equals( Tabs.getTabele().getTitleAt(index))){
		        		chosen = t;
		        		break;
		        	}
		        }
	        if(chosen!=null){
	        	ArrayList<Table> parentTables = new ArrayList<>();
				
				for(Table t : Sistem.getInstance().getAllTables()){
					ArrayList<FKey> fkeys = chosen.getfKeys();
					if(fkeys!=null){
						for(int j=0; j<fkeys.size(); j++){
							if( (fkeys.get(j).getConnectedTable().equals( t.getNaziv().getCode() ) && Tabs.fkIsKeyInTable(fkeys.get(j).getHomeIds(), chosen.getKeys() ) )){						
								parentTables.add(t);
							}
						}	
					}
				}
				if(parentTables.size()>0){
					Tabs.getChildren().removeAll();
					for(int i = 0; i < parentTables.size(); i++){
						
						Tabs.getInstance().addTab(parentTables.get(i));
					}
				}
					
			
	        }
	        
	}

}
