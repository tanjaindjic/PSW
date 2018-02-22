package listeners;

import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import editorSeme.model.pojo.Sistem;
import editorSeme.model.pojo.Table;
import workingsection.Tabs;

/**
 * Changes Tabs using mouse selection.
 */
public class SelectedTabListener implements ChangeListener {

	/**
	 * Loads children Tabs for selected Tab.
	 */
	@Override
	public void stateChanged(ChangeEvent arg0) {
		// TODO Auto-generated method stub
		JTabbedPane sourceTabbedPane = (JTabbedPane) arg0.getSource();
        int index = sourceTabbedPane.getSelectedIndex();
        Tabs.getChildren().removeAll();
        Table chosen = null;
        if(index!=-1)	
	        for(Table t : Sistem.getInstance().getAllTables()){
	        	if(t.toString().equals( sourceTabbedPane.getTitleAt(index))){
	        		chosen = t;
	        		break;
	        	}
	        }
        if(chosen!=null){
			Tabs.getChildren().removeAll();
        	Tabs.createChildrenTabs(chosen);
        }
        
	}

}
