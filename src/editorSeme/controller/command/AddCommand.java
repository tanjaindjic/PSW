package editorSeme.controller.command;
/**
 * 
 * Any AbstractCommand that adds objects.
 *
 */
public abstract class AddCommand implements AbstractCommand {

	/**
	 * Object that should be added to the parent.
	 */
	protected Object child;
	
	/**
	 * The object in which the child should be added. 
	 */
	protected Object parent;
	
	
}
