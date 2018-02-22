package editorSeme.controller.command;

import editorSeme.model.pojo.Atribut;
import editorSeme.model.pojo.Table;
/**
 * Removes an existing attribute from a table in the JSON database schema.
 *
 */
public class RemoveAtribute extends DeleteCommand {

	/**
	 * Overrides the interface so that it could remove an attribute from a table. 
	 */
	@Override
	public boolean doCommand() {
		
		AddAtribute atb = new AddAtribute( (Table)parent, (Atribut) child);
		return atb.undoCommand();

	}

	/**
	 * Overrides the interface so that it could add an attribute to a table and thereby undo the operation. 
	 */
	@Override
	public boolean undoCommand() {
		
		AddAtribute atb = new AddAtribute( (Table)parent, (Atribut) child);
		return atb.doCommand();
	
	}
	
	/**
	 * Constructs a new RemoveAtribute object with the given parameters.
	 * @param parent The table from which the attribute should be removed.
	 * @param child The attribute that should be removed.
	 */
	public RemoveAtribute(Table parent, Atribut child) {
		this.parent = parent;
		this.child = child;
		// TODO Auto-generated constructor stub
	}
	
}
