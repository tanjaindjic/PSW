package workingsection.tree;

import java.util.Observable;
import java.util.Observer;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;

import editorSeme.model.eventEncapsulation.AtributEvent;
import editorSeme.model.eventEncapsulation.EventType;
import editorSeme.model.eventEncapsulation.PackageEvent;
import editorSeme.model.eventEncapsulation.SistemEvent;
import editorSeme.model.eventEncapsulation.TableEvent;
import editorSeme.model.pojo.Sistem;
/**
 * Class that provides and adapts tree in view section
 *
 */
public class ModelTree extends DefaultTreeModel implements Observer{

	private MyTreeModelListener treeModelListener;
	private DefaultMutableTreeNode root;
    private Tree tree;
    
    /**
     * Constructir that sets the model to the tree
     * @param root - Object that represents the root of the tree
     * @param tree - JTree object the is going to be set to the model we make
     */
	public ModelTree(DefaultMutableTreeNode root, Tree tree) {
		super(root);
		this.tree = tree;
		this.root=root;
		treeModelListener = new MyTreeModelListener();
		this.addTreeModelListener(treeModelListener);
	}
	/**
	 * Method that reloads/update the view and structure of the tree
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		if(arg1 instanceof SistemEvent){
			SistemEvent sis = (SistemEvent) arg1;
			if (sis.getEventType()==EventType.ADD) {
				Tree.getInstance().setModel((TreeModel) sis);
				this.reload();
			}
			else if(sis.getEventType()==EventType.DELETE)
				Tree.getInstance().removeNode((Sistem)sis.getStruc());
			else if(sis.getEventType()==EventType.UPDATE)
				this.reload();
		}
		else if(arg1 instanceof PackageEvent){
			PackageEvent pak = (PackageEvent) arg1;
			if (pak.getEventType()==EventType.ADD) {
				this.reload(tree.addNode(pak.getStruc(), pak.getPack()));
			}
			else if(pak.getEventType()==EventType.DELETE)
				Tree.getInstance().removeNode((Object)pak.getPack());
			else if(pak.getEventType()==EventType.UPDATE)
				this.reload();
		}
		else if(arg1 instanceof TableEvent){
			TableEvent tab = (TableEvent) arg1;
			if (tab.getEventType()==EventType.ADD) {
				this.reload(tree.addNode(tab.getPack(), tab.getTable()));
			}
			else if(tab.getEventType()==EventType.DELETE)
				Tree.getInstance().removeNode((Object)tab.getTable());
			else if(tab.getEventType()==EventType.UPDATE)
				this.reload();
		}
		else if(arg1 instanceof AtributEvent){
			AtributEvent atr = (AtributEvent) arg1;
			if (atr.getEventType()==EventType.ADD) {
				this.reload(tree.addNode(atr.getTable(), atr.getAtribut()));
			}
			else if(atr.getEventType()==EventType.DELETE)
				Tree.getInstance().removeNode((Object)atr.getAtribut());
			else if(atr.getEventType()==EventType.UPDATE)
				this.reload();
		}
		
		else
			System.out.println("greska u proceni eventa u modelTree klasi");
	}
	

}
