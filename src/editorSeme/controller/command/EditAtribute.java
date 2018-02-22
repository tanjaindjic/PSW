package editorSeme.controller.command;

import editorSeme.model.pojo.Atribut;
import editorSeme.model.pojo.Table;

/**
 * Edits an existing attribute from a table in the JSON database schema.
 *
 */
public class EditAtribute extends UpdateCommand {

	/**
	 * Overrides the interface so that it could edit an attribute in a table. 
	 */
	@Override
	public boolean doCommand() {
		Object temp = currentState; 
		currentState = nextState;
		nextState = temp;
		return true;
	}

	/**
	 * Overrides the interface so that it could edit an attribute and set its values to the previous state. 
	 */
	@Override
	public boolean undoCommand() {
		
		Object temp = currentState; 
		currentState = nextState;
		nextState = temp;
		return true;
	}
	/**
	 * Constructs a new EditAtribute object with the given parameters.
	 * @param oldState Old state of the attribute that should be changed.
	 * @param newState New state of the attribute, the values of the old attribute should change to the values of the new state.
	 * @param tab The table in which the attribute should be changed.
	 */
	public EditAtribute(Atribut oldState, Atribut newState, Table tab){
		this.currentState = oldState;
		this.nextState = newState;
		this.parent = tab;
	}
}
