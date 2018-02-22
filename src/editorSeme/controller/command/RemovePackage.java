package editorSeme.controller.command;
import editorSeme.model.pojo.Package;
import editorSeme.view.WrongParentException;

public class RemovePackage extends DeleteCommand {

	/**
	 * Overrides the interface so that it could remove an package. 
	 */
	@Override
	public boolean doCommand() {
		try {
			AddPackage adp = new AddPackage(parent, (Package) child);
			return adp.undoCommand();
		} catch (WrongParentException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Overrides the interface so that it could add an package whit all of its children and thereby undo the remove operation. 
	 */
	@Override
	public boolean undoCommand() {
		try {
			AddPackage adp = new AddPackage(parent, (Package) child);
			return adp.doCommand();
		} catch (WrongParentException e) {
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * Constructs a new RemovePackage object with the given parameters.
	 * @param parent The system or subsystem from which the package should be removed.
	 * @param child The Package that should be removed.
	 */
	public RemovePackage(Object parent, Package child) {
		this.parent = parent;
		this.child = child;
	}
}
