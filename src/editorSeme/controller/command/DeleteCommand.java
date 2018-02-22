package editorSeme.controller.command;

/**
 * 
 * Any AbstractCommand that deletes objects.
 *
 */
public abstract class DeleteCommand implements AbstractCommand {

	/**
	 * Object that should be deleted from the parent.
	 */
	protected Object child;
	
	/**
	 * The object from which the child should be deleted. 
	 */
	protected Object parent;

}
