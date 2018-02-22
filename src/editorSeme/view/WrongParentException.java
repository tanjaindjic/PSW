package editorSeme.view;

/**
 * 
 * Exception that is cast when an object is tried to be added to a non supported parent.
 *
 */
public class WrongParentException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * A wrong parent warning is shown.
	 */
	public WrongParentException(){super("Wrong parent"); }
}
