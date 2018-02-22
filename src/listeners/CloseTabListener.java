package listeners;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import editorSeme.model.pojo.Table;
import editorSeme.view.EditorWorkbench;
import workingsection.MainWindow;
import workingsection.Tabs;
import workingsection.WorkArea;
import editorSeme.model.pojo.Table;

/**
 * Creates custom action for closing a Tab.
 */
public class CloseTabListener implements ActionListener {

	 private String tabName;

	 /**
	  * Sets the name of Tab for closing.
	  * @param tabName name of Tab that is being closed.
	  */
	    public CloseTabListener(String tabName) {
	        this.tabName = tabName;
	    }

	    /**
		  * Gets the name of Tab for closing.
		  * @return tabName name of Tab that is being closed.
		  */
	    public String getTabName() {
	        return tabName;
	    }

	    
	    /**
	     * Closes chosen Tab.
	     */
	    public void actionPerformed(ActionEvent evt) {

	        int index = Tabs.getTabele().indexOfTab(getTabName());
	        if (index >= 0) {

	        	Tabs.getTabele().removeTabAt(index);
	        	
	            // It would probably be worthwhile getting the source
	            // casting it back to a JButton and removing
	            // the action handler reference ;)

	        }
	        if(Tabs.getTabele().getComponentCount()==1){
	        	WorkArea wa = MainWindow.getWorkArea();
				wa.remove(EditorWorkbench.getInstance());
				wa.remove(Tabs.getInstance());
				EditorWorkbench.destroy();
				wa.repaint();
				wa.validate();
				wa.add(EditorWorkbench.getInstance(), BorderLayout.CENTER);
				wa.repaint();
				wa.validate();
	        }
	       

	    }
}
