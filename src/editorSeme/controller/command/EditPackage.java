package editorSeme.controller.command;
import editorSeme.model.pojo.Package;
import editorSeme.model.pojo.Table;
/**
 * Edits an existing package in the JSON database schema.
 *
 */
public class EditPackage extends UpdateCommand {

	/**
	 * Overrides the interface so that it could edit an package. 
	 */
	@Override
	public boolean doCommand() {
		Object temp = currentState; 
		currentState = nextState;
		nextState = temp;
		return true;
	}

	/**
	 * Overrides the interface so that it could undo the edit of a package. 
	 */
	@Override
	public boolean undoCommand() {
		
		Object temp = currentState; 
		currentState = nextState;
		nextState = temp;
		return true;
	}
	
	/**
	 * Constructs a new EditPackage object with the given parameters.
	 * @param oldState Old state of the package that should be changed.
	 * @param newState New state of the package, the values of the old package should change to the values of the new state.
	 * @param par The parent in which the package should be changed.
	 */
	public EditPackage(Package oldState, Package newState, Object par){
		this.currentState = oldState;
		this.nextState = newState;
		this.parent = par;
	}

	
}
