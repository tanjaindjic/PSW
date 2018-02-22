package editorSeme.controller.command;

import editorSeme.model.pojo.Package;
import editorSeme.model.pojo.Table;
import editorSeme.view.WrongParentException;
/**
 * Removes a existing Table from the JSON database schema.
 *
 */
public class RemoveTable extends DeleteCommand {

	/**
	 * Overrides the interface so that it could remove a table from the given Package. 
	 */
	@Override
	public boolean doCommand() {
		
		AddTable atp = new AddTable( (Package)parent, (Table) child);
		return atp.undoCommand();

	}

	/**
	 * Overrides the interface so that it could add a Table and thereby undo the remove operation.  
	 */
	@Override
	public boolean undoCommand() {
		
		AddTable atp = new AddTable( (Package)parent, (Table) child);
		return atp.doCommand();
	
	}
	
	/**
	 * Constructs a new RemoveTable object with the given parameters.
	 * @param parent The Package in which the Table that should be removed is found.
	 * @param child The Table that should be removed.
	 */
	public RemoveTable(Package parent, Table child) {
		this.parent = parent;
		this.child = child;
		// TODO Auto-generated constructor stub
	}
	
	
}
