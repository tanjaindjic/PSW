package workingsection.tree;

import java.awt.Component;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeNode;

import editorSeme.model.enums.PackageType;
import editorSeme.model.pojo.Atribut;
import editorSeme.model.pojo.FKey;
import editorSeme.model.pojo.Package;
import editorSeme.model.pojo.Sistem;
import editorSeme.model.pojo.Table;
/**
 * Class thet provides different look of the tree, not deffault look
 *
 */
@SuppressWarnings("serial")
public class TreeRenderer extends DefaultTreeCellRenderer {

	Icon wIcon;
	Icon sIcon;
	Icon packIcon;
	Icon sysIcon;
	Icon subsysIcon;
	Icon tIcon;
	//Icon aIcon;
	Icon fkIcon;
	/**
	 * Constructor that reads all the values/pics that are going to be used in the look of the tree
	 */
	public TreeRenderer() {
        super();
        //Ubaciti slike
        String myLoc = (System.getProperty("user.dir")+"\\src\\workingsection\\tree\\icons\\");
      
        packIcon = new ImageIcon(myLoc+"pack.png");
        sysIcon = new ImageIcon(myLoc+"system.png");
        subsysIcon = new ImageIcon(myLoc+"subsystem.png");
        tIcon = new ImageIcon(myLoc+"table.png");
      //  aIcon = new ImageIcon(myLoc+"atribute.png");
    }
	
	/**
	 * Method that sets the icon to the node on the tree, depending on the instance of the node
	 */
    @Override 
    public Component getTreeCellRendererComponent(
                        JTree tree,
                        Object value,
                        boolean sel,
                        boolean expanded,
                        boolean leaf,
                        int row,
                        boolean hasFocus) {

    	super.getTreeCellRendererComponent(tree, value, sel,expanded, leaf, row, hasFocus);
    	
    	if(((TreeNode)value) instanceof Sistem){
    		setIcon(sysIcon);
    	}else if(((TreeNode)value) instanceof Package){
    		Package p = (Package)value;
    		if(p.getPackageType()==PackageType.PACKAGE){
    			setIcon(packIcon);
    		}
    		else if(p.getPackageType()==PackageType.SUBSYSTEM){
    			setIcon(subsysIcon);
    		}
    	}else if(((TreeNode)value) instanceof Table){
    		setIcon(tIcon);
    	}/*else if(((TreeNode)value) instanceof Atribut){
    		setIcon(aIcon);
    	}*/
    	else{
    		setLeafIcon(getLeafIcon());
    		setClosedIcon(getClosedIcon());
    		setOpenIcon(getOpenIcon());
    	}
        return this;
    }

}