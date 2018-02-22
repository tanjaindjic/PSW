package workingsection.tree;

import java.awt.Component;
import java.awt.Toolkit;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

import editorSeme.model.pojo.Atribut;
import editorSeme.model.pojo.Package;
import editorSeme.model.pojo.Sistem;
import editorSeme.model.pojo.Table;
import editorSeme.model.pojo.SistemModel;
/**
 * Class that adapts JTree class
 *
 */
@SuppressWarnings("serial")
public class Tree extends JTree implements Observer{
	
	private static Tree instance = null;
	
	protected DefaultMutableTreeNode rootNode;
	protected DefaultTreeModel treeModel;
	//public ModelTree treeModel;
	
	//public JTree tree;
	private Toolkit toolkit;
	protected TreeListener treeListener;
	protected TreeMouseListener treeMouse;
	public DefaultMutableTreeNode lastSelectedTreeNode;
	public TreeRenderer treeRender;
	
	public DefaultTreeModel getTreeModel() {
		return treeModel;
	}
	

	public DefaultMutableTreeNode getRootNode() {
		return rootNode;
	}


	/**
	 * Empty constructor
	 */
	private Tree() {
		/*rootNode = new DefaultMutableTreeNode("praotac ringe");
		treeModel = new ModelTree(rootNode, this);
		tree = new JTree(treeModel);
		tree.setRootVisible(false);
		treeRender = new TreeRender();
		treeListener = new TreeListener();
		treeMouse = new TreeMouseListener();
        tree.setCellRenderer(treeRender);
		tree.addTreeSelectionListener(treeListener);
        tree.addMouseListener(treeMouse);
		tree.setEditable(false);
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        tree.setShowsRootHandles(true);
        toolkit= Toolkit.getDefaultToolkit();*/
	}
	/**
	 * Singleton pattern of tree class
	 * @return Instance of singlton.
	 */
	public static Tree getInstance() {
		if (instance == null){
			instance = new Tree();
			instance.addTreeSelectionListener(new TreeListener());
			instance.addMouseListener(new TreeMouseListener());
			instance.setCellRenderer(new TreeRenderer());
			//instance.setLook();	
		}		
		return instance;
	}
	
	
	
	/*public void setLook() {
		setLayout(new GridLayout(1, 1));
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

        tree.setShowsRootHandles(true);
        tree.setVisible(true);

		JScrollPane scrollPane = new JScrollPane(tree);
        add(scrollPane);
        Dimension screenSize = toolkit.getScreenSize();
        int screenHeight = screenSize.height;
        setPreferredSize(new Dimension(350, 30));
	}*/

	/**
	 * Adds child-node to a compatible node
	 * @param parent - parent object to be parent node
	 * @param child - child object to be child node
	 * @return Result of adding node.
	 */
	public TreeNode addNode(Object parent, Object child) {
		if(parent instanceof Sistem){
			if(child instanceof Package){
				Sistem w = (Sistem)parent;  
				rootNode.setUserObject(w);
			//	w.getSchemes().add((Structure)child);
			}
		}
		if(parent instanceof Package){
			if(child instanceof Package){				
				Package s = (Package) parent;  
			//	s.getPackages().add((Package) child);
			}
			else if(child instanceof Table){
				Package s = (Package) parent;  
			//	s.getTables().add((Table) child);
			}
		}
		if(parent instanceof Table){
			if(child instanceof Atribut){
				Table t = (Table) parent;  
			//	t.getPolja().add((Atribut) child);
			}
		}
		
		return null;
	}
	/**
	 * Removes node from the tree
	 * @param obj - object/node that needs to be removed
	 */
	public void removeNode(Object obj) {
		this.remove((Component)obj);
	}
	/**
	 * returns the object that is selected in the tree
	 * @return - value of selected node as an Object
	 */
	public Object getSelected() {
		Object selectedNode = (Object)this.getLastSelectedPathComponent();
		 return selectedNode;
	}
	/**
	 * updates a look of the tree
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * reloads the tree
	 */
	public void reload() {
		((SistemModel)(this.getModel())).reload();
	}
}
