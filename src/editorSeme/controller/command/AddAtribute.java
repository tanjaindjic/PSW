package editorSeme.controller.command;

import editorSeme.model.pojo.Atribut;
import editorSeme.model.pojo.Table;
/**
 * Adds an new attribute to a table in the JSON database schema.
 *
 */
public class AddAtribute extends AddCommand {

	/**
	 * Overrides the interface so that it could add an attribute to a table. 
	 */
	@Override
	public boolean doCommand() {
	
		Table t = (Table) parent; 
		return t.addAtribute((Atribut) child);
	}

	/**
	 * Overrides the interface so that it could remove an attribute from a table and thereby undo the operation. 
	 */
	@Override
	public boolean undoCommand() {

		Table t = (Table) parent; 
		return t.removeAtribute((Atribut) child);
	}

	/**
	 * Constructs a new AddAtribute object with the given parameters.
	 * @param parent The table in which the attribute should be added.
	 * @param child The attribute that should be added.
	 */
	public AddAtribute(Table parent, Atribut child) {
		this.parent=parent;
		this.child=child;
	} 
	
}
