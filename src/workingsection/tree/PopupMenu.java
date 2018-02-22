package workingsection.tree;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;

/**
 * PopUp Menu for Tree that offers editing options for Nodes.
 *
 */
public class PopupMenu extends JPopupMenu{

	/**
	 * PopUp Menu for Tree that offers editing options for Nodes.
	 *@param tree Instance.
	 */
	public PopupMenu(Tree tree) {
	      JMenuItem itemEdit = new JMenuItem("Edit");
	      
	      itemEdit.addActionListener(new EditNodeListener());
	  
	      add(itemEdit);
	      
	   }
}
