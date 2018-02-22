package editorSeme.controller.command;

import editorSeme.model.pojo.Package;
import editorSeme.model.pojo.Table;
import workingsection.tree.Tree;

/**
 * Adds a new Table to the JSON database schema.
 *
 */
public class AddTable extends AddCommand {

	/**
	 * Overrides the interface so that it could add a table to the given Package. 
	 */
	@Override
	public boolean doCommand() {
	
		Package p = (Package) parent;
		
		return p.addTable((Table) child);
		/*boolean r = p.getTables().add(((Table) child));
		Tree.getInstance().getTreeModel().nodeStructureChanged(Tree.getInstance().getRootNode());
		return r;*/
		
	}

	/**
	 * Overrides the interface so that it could remove a Table and thereby undo the add operation.  
	 */
	@Override
	public boolean undoCommand() {
		
		Package p = (Package) parent;
		
		return p.removeTable((Table) child);
	}
	
	/**
	 * Constructs a new AddTable object with the given parameters.
	 * @param parent The Package in which the attribute should be added.
	 * @param child The Table that should be added.
	 */
	public AddTable(Package parent, Table child){
		
		this.parent = parent; 
		this.child = child;
		
	}

}
