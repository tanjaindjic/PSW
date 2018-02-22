package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTabbedPane;

import editorSeme.model.pojo.Sistem;
import editorSeme.model.pojo.Table;
import workingsection.Tabs;

/**
 * Opens selected child Table and its' children.
 */
public class PromoteListener implements ActionListener {

	/**
	 * Promotes child Table and opens its' children.
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
        int index = Tabs.getChildren().getSelectedIndex();
        
        Table chosen = null;
        if(index!=-1)	
	        for(Table t : Sistem.getInstance().getAllTables()){
	        	if(t.toString().equals( Tabs.getChildren().getTitleAt(index))){
	        		chosen = t;
	        		break;
	        	}
	        }
        if(chosen!=null){
			Tabs.getChildren().removeAll();
			Tabs.addTab(chosen);
        }
        
	
	}

}
