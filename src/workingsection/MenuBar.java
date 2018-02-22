package workingsection;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.KeyStroke;

import editorSeme.model.pojo.Sistem;
import listeners.AccountSettingsListener;
import listeners.HelpLaunchListener;
import listeners.HelpMouseListener;
import listeners.SignOutListener;
import start.DatabaseType;
import start.InfViewModel;
/**
 * Class that makes the JMenuBar for main window
 *
 */

@SuppressWarnings("serial")
public class MenuBar extends JMenuBar {
	private static MenuBar menuBar = null;
	/**
	 * Empty constructor
	 */
	private MenuBar() {

	}
	/**
	 * Singleton pattern for this class
	 * adds all elements
	 * @return Instance of Menu Bar.
	 */
	public static MenuBar getInstance() {
		if (menuBar == null) {
			menuBar = new MenuBar();

			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // Odredjivanje velicine ekrana
			double width = screenSize.getWidth(); // da bi dialog izgledao normalno na svim ekranima
			double height = screenSize.getHeight();

			menuBar.setSize((int) width, (int) (height * 0.8));
		
		//file
			ArrayList<MenuItem> FileMenuItems = new ArrayList<MenuItem>();
			MenuItem m0 = new MenuItem(Sistem.getInstance().getTranslate("New_System"));
			if(InfViewModel.getInstance().getCurrentUser().getTypeOfUser().toString().equals("PROJEKTANT"))
				FileMenuItems.add(m0);
			MenuItem m5 = new MenuItem(Sistem.getInstance().getTranslate("Load"));
			FileMenuItems.add(m5);
			
			MenuItem m1 = new MenuItem(Sistem.getInstance().getTranslate("Save"));
			FileMenuItems.add(m1);
			
			MenuItem m2 = new MenuItem(Sistem.getInstance().getTranslate("Save_As"));
			FileMenuItems.add(m2);
			MenuItem m3 = new MenuItem(Sistem.getInstance().getTranslate("Properties"));
			FileMenuItems.add(m3);
			MenuItem m4 = new MenuItem(Sistem.getInstance().getTranslate("Exit"));
			FileMenuItems.add(m4);

			JMenu fileMenu = new Menu(FileMenuItems, Sistem.getInstance().getTranslate("File"));
			menuBar.add(fileMenu);
			
			
		//edit	
			ArrayList<MenuItem> EditMenuItems = new ArrayList<MenuItem>();		
			MenuItem m10 = new MenuItem(Sistem.getInstance().getTranslate("Undo"));
			EditMenuItems.add(m10);				
			MenuItem m11 = new MenuItem(Sistem.getInstance().getTranslate("Redo"));
			EditMenuItems.add(m11);
			MenuItem m12 = new MenuItem(Sistem.getInstance().getTranslate("Cut"));
			EditMenuItems.add(m12);
			MenuItem m13 = new MenuItem(Sistem.getInstance().getTranslate("Copy"));
			EditMenuItems.add(m13);
			MenuItem m14 = new MenuItem(Sistem.getInstance().getTranslate("Paste"));
			EditMenuItems.add(m14);
			MenuItem m15 = new MenuItem(Sistem.getInstance().getTranslate("Delete"));
			EditMenuItems.add(m15);
			MenuItem m16 = new MenuItem(Sistem.getInstance().getTranslate("Find"));
			EditMenuItems.add(m16);
			
			JMenu EditMenu = new Menu(EditMenuItems,Sistem.getInstance().getTranslate("Edit"));
			menuBar.add(EditMenu);
			
		//navigate	
			ArrayList<MenuItem> NavigateMenuItems = new ArrayList<MenuItem>();	
			MenuItem m20 = new MenuItem(Sistem.getInstance().getTranslate("Promote"));
			NavigateMenuItems.add(m20);
			MenuItem m21 = new MenuItem(Sistem.getInstance().getTranslate("Demote"));
			NavigateMenuItems.add(m21);
			MenuItem m22 = new MenuItem(Sistem.getInstance().getTranslate("Show_Relations"));
			NavigateMenuItems.add(m22);
			JMenu NavigateMenu = new Menu(NavigateMenuItems,Sistem.getInstance().getTranslate("Navigate"));
			menuBar.add(NavigateMenu);
			
		//show	
			ArrayList<MenuItem> ShowMenuItems = new ArrayList<MenuItem>();	
			MenuItem m32 = new MenuItem(Sistem.getInstance().getTranslate("Table_Relations"));
			ShowMenuItems.add(m32);
			
			MenuItem m30 = new MenuItem(Sistem.getInstance().getTranslate("System_Schema"));
			if(InfViewModel.getInstance().getCurrentUser().getTypeOfUser().toString().equals("PROJEKTANT"))
				ShowMenuItems.add(m30);
			MenuItem m31 = new MenuItem(Sistem.getInstance().getTranslate("JSON_Meta_Schema"));
			if(InfViewModel.getInstance().getCurrentUser().getTypeOfUser().toString().equals("PROJEKTANT"))
			ShowMenuItems.add(m31);
			
			JMenu ShowMenu = new Menu(ShowMenuItems,Sistem.getInstance().getTranslate("Show"));
			menuBar.add(ShowMenu);
			
		//help
			menuBar.add(Box.createGlue());
			ArrayList<MenuItem> HelpMenuItems = new ArrayList<MenuItem>();	
			JMenu HelpMenu = new Menu(HelpMenuItems,Sistem.getInstance().getTranslate("Help"));
			HelpMenu.getInputMap().put(KeyStroke.getKeyStroke("F1"),"login");
			HelpMenu.getActionMap().put("login",new HelpLaunchListener());
			HelpMenu.addMouseListener(new HelpMouseListener());
			menuBar.add(HelpMenu);
			
			
			ArrayList<MenuItem> ProfileMenuItems = new ArrayList<MenuItem>();	
			MenuItem m3i0 = new MenuItem(Sistem.getInstance().getTranslate("Settings"));
			MenuItem m3i3 = new MenuItem(Sistem.getInstance().getTranslate("Sign_out"));
			ProfileMenuItems.add(m3i0);
			ProfileMenuItems.add(m3i3);
			JMenu ProfileMenu = new Menu(ProfileMenuItems,Sistem.getInstance().getTranslate("Account"));
			menuBar.add(ProfileMenu);
			

		}
		return menuBar;
	}
	/**
	 * Destructor 
	 */
	public void destroy() {
		menuBar = null;
	}
}
