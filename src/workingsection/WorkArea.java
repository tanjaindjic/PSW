package workingsection;

import javax.swing.JPanel;
/**
 * Class that provides panel for tabs or editor
 *
 */
public class WorkArea extends JPanel{
	private static WorkArea workarea = null;
	private Tabs tabs=null;
	
	private WorkArea(){
		
	}
	/**
	 * Singleton pattern, makes instance for this class
	 * @return instance of this class
	 */
	public static WorkArea getInstance(){
		if(workarea==null){
			workarea = new WorkArea();
		//	workarea.setBackground(new Color(255));
		}
		return workarea;
	}
	/**
	 * Destructor
	 */
	public void destroy(){
		workarea=null;
	}
	/**
	 * Method that initializes tab-panel with all tabs opened 
	 */
	public void loadTabs() {
		workarea.add(Tabs.getInstance());
	}
}
