package editorSeme.controller.command;
/**
 * Any command that can be done in the application that should/could be undone should implement this interface.
 * Forces the designer to enable undo and redo options for every action. 
 */
public interface AbstractCommand {

	/**
	 * The action that should be done.
	 * @return True if the action was successful and false otherwise.
	 */
	public abstract boolean doCommand();
	/**
	 * A way to reverse the doCommand action.
	 * @return True if the action was successful and false otherwise.
	 */
	public abstract boolean undoCommand();
	
	
}
