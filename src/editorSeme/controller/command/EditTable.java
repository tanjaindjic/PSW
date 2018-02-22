package editorSeme.controller.command;

import editorSeme.model.pojo.Table;

/**
 * Edits an existing table from in the JSON database schema.
 *
 */
public class EditTable extends UpdateCommand {

	/**
	 * Overrides the interface so that it could edit an table and set its values to the new state. 
	 */
	@Override
	public boolean doCommand() {
		Object temp = currentState; 
		currentState = nextState;
		nextState = temp;
		return true;
	}

	/**
	 * Overrides the interface so that it could edit an table and set its values to the previous state. 
	 */
	@Override
	public boolean undoCommand() {
		
		Object temp = currentState; 
		currentState = nextState;
		nextState = temp;
		return true;
	}
	
	/**
	 * Constructs a new EditTable object with the given parameters.
	 * @param oldState Old state of the table that should be changed.
	 * @param newState New state of the table, the values of the old table should change to the values of the new state.
	 * @param par The parent in which the table should be changed.
	 */
	public EditTable(Table oldState, Table newState, Object par){
		this.currentState = oldState;
		this.nextState = newState;
		this.parent = par;
	}
	
}
