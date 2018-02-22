package editorSeme.controller.command;

/**
 * 
 * Any AbstractCommand that updates objects.
 *
 */
public abstract class UpdateCommand implements AbstractCommand {

	/**
	 * The current state of the object that should be updated.
	 */
	protected Object currentState; 
	/**
	 * The next state of the object to which the object should be updated.
	 */
	protected Object nextState;
	/**
	 * The object in which the child should be updated. 
	 */
	protected Object parent;
}
