 package workingsection;

import java.util.ArrayList;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
/**
 * Class that makes JMenu element for menuBar out of MenuItems
 *
 */
public class Menu extends JMenu {
	private ArrayList<MenuItem> menuItems;
	private String name;
	/**
	 * Constructor that makes final JMenu element
	 * @param fileMenuItems - MenuItems that are going to be added to JMenu
	 * @param string - name for the JManu element
	 */
	public Menu(ArrayList<MenuItem> fileMenuItems, String string) {
		menuItems = fileMenuItems;
		name = string;
		setText(name);
		for (MenuItem mi : menuItems) {
			JMenuItem jmi = new MenuItem(mi.getName());
			add(jmi);
		}
	}

}
