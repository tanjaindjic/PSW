package workingsection;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import editorSeme.model.pojo.Sistem;
import jsonDataBase.DataDeleteListener;
import listeners.AddFilterListener;
import listeners.DataListener;
import listeners.DeleteSearchListener;
import listeners.DemoteListener;
import listeners.OpenWorkbenchListener;
import listeners.PromoteListener;
import listeners.SearchListener;
import start.DatabaseType;
import start.InfViewModel;

/**
 * Class that initialize toolBar
 *
 */
public class ToolBar extends JToolBar {

	private static final long serialVersionUID = 1L;
private static ToolBar toolbar = null;
	/**
	 * Empty constructor
	 */
	private ToolBar(){
	
	}
	/**
	 * Singleton pattern, gets the instance for a toolBar
	 * @return instance of this class
	 */
	public static ToolBar getInstance(){
		if(toolbar==null){
			toolbar = new ToolBar();
			
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //Odredjivanje velicine ekrana 
			double width =  screenSize.getWidth();
			
			String myLoc = (System.getProperty("user.dir")+"/src/workingsection/images/");
			
			Tool tb1 = new Tool(Sistem.getInstance().getTranslate("Workbench"),myLoc + "tool1.png");
			tb1.setSize((int)width, 40);
			tb1.addActionListener(new OpenWorkbenchListener());
			if(InfViewModel.getInstance().getCurrentUser().getTypeOfUser().toString().equals("PROJEKTANT"))
				toolbar.add(tb1, BorderLayout.LINE_START);
			if(InfViewModel.getInstance().getDatabaseType().equals(DatabaseType.RELATIONAL))
				tb1.setEnabled(false);
			toolbar.addSeparator();
			
			Tool tb2 = new Tool(Sistem.getInstance().getTranslate("Add_Data"),myLoc + "tool2.png");
			tb2.addActionListener(new DataListener("Add"));
			tb2.setSize((int)width, 40);
			toolbar.add(tb2, BorderLayout.LINE_START);
			
			Tool tb11 = new Tool(Sistem.getInstance().getTranslate("Edit_Data"),myLoc + "tool11.png");
			tb11.addActionListener(new DataListener("Edit"));
			tb11.setSize((int)width, 40);
			toolbar.add(tb11, BorderLayout.LINE_START);
			
			Tool tb3 = new Tool(Sistem.getInstance().getTranslate("Delete_Data"),myLoc + "tool3.png");
			tb3.addActionListener(new DataDeleteListener());
			tb3.setSize((int)width, 40);
			toolbar.add(tb3, BorderLayout.LINE_START);
			
		/*	toolbar.addSeparator();
			
			Tool tb4 = new Tool(Sistem.getInstance().getTranslate("Cut"),myLoc + "tool4.png");
			tb4.setSize((int)width, 40);
			toolbar.add(tb4, BorderLayout.LINE_START);
			
			Tool tb5 = new Tool(Sistem.getInstance().getTranslate("Copy"),myLoc + "tool5.png");
			tb5.setSize((int)width, 40);
			toolbar.add(tb5, BorderLayout.LINE_START);
			
			Tool tb6 = new Tool(Sistem.getInstance().getTranslate("Paste"),myLoc + "tool6.png");
			tb6.setSize((int)width, 40);
			toolbar.add(tb6, BorderLayout.LINE_START);
			
			toolbar.addSeparator();
			
			Tool tb7 = new Tool(Sistem.getInstance().getTranslate("Undo"),myLoc + "tool7.png");
			tb7.setSize((int)width, 40);
			toolbar.add(tb7, BorderLayout.LINE_START);
			
			Tool tb8 = new Tool(Sistem.getInstance().getTranslate("Redo"),myLoc + "tool8.png");
			tb8.setSize((int)width, 40);
			toolbar.add(tb8, BorderLayout.LINE_START);
			*/
			toolbar.addSeparator();
			
			Tool tb9 = new Tool(Sistem.getInstance().getTranslate("Promote"),myLoc + "tool9.jpg");
			tb9.setSize((int)width, 40);
			tb9.addActionListener(new PromoteListener());
			toolbar.add(tb9, BorderLayout.LINE_START);
			
			Tool tb10 = new Tool(Sistem.getInstance().getTranslate("Demote"),myLoc + "tool10.jpg");
			tb10.setSize((int)width, 40);
			tb10.addActionListener(new DemoteListener());
			toolbar.add(tb10, BorderLayout.LINE_START);
			
			toolbar.add(Box.createGlue());
			toolbar.addSeparator();
			toolbar.addSeparator();
			toolbar.addSeparator();
			

			JButton tb14 = new JButton("                                                                                 ");
			tb14.setSize((int)width, 30);
			tb14.setEnabled(false);
			toolbar.add(tb14, BorderLayout.LINE_END);

			toolbar.addSeparator();
			toolbar.addSeparator();
			toolbar.addSeparator();
			
			JTextField tf = new JTextField("", 30);	
			toolbar.add(tf);
			
			toolbar.addSeparator();
			Tool tb12 = new Tool(Sistem.getInstance().getTranslate("Search"),myLoc + "tool12.png");
			tb12.setSize((int)width, 30);
			tb12.addActionListener(new SearchListener(tf));
			toolbar.add(tb12, BorderLayout.LINE_END);
			
			Tool tb13 = new Tool(Sistem.getInstance().getTranslate("Delete_Search"),myLoc + "tool13.png");
			tb13.setSize((int)width, 30);
			tb13.addActionListener(new DeleteSearchListener(tf));			
			toolbar.add(tb13, BorderLayout.LINE_END);
			
			Tool tb15 = new Tool(Sistem.getInstance().getTranslate("Filters"),myLoc + "tool15.png");
			tb15.setSize((int)width, 30);
			tb15.addActionListener(new AddFilterListener());			
			toolbar.add(tb15, BorderLayout.LINE_END);
		}
		return toolbar;
	}
	/**
	 * Destructor
	 */
	public void destroy() {
		toolbar = null;
		
	}
}
