package workingsection.tree;

import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.DefaultMutableTreeNode;
/**
 * Listener that provides actions on three model
 *
 */
public class MyTreeModelListener implements TreeModelListener {
	/**
	 * Method that changes the look of the three if components of the node are changed
	 */
	public void treeNodesChanged(TreeModelEvent e) {
		DefaultMutableTreeNode node;
		node = (DefaultMutableTreeNode) (e.getTreePath().getLastPathComponent());
		try {
			int index = e.getChildIndices()[0];
			node = (DefaultMutableTreeNode) (node.getChildAt(index));
		} catch (NullPointerException exc) {
			}
	}
	/**
	 * usused method
	 */
	public void treeNodesInserted(TreeModelEvent e) {
		//System.out.println(e);
	}
	/**
	 * usused method
	 */
	public void treeNodesRemoved(TreeModelEvent e) {
		//System.out.println(e);
	}
	/**
	 * usused method
	 */
	public void treeStructureChanged(TreeModelEvent e) {
		//System.out.println(e);
	}
}