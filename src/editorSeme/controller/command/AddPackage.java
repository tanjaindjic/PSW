package editorSeme.controller.command;

import editorSeme.model.pojo.Package;
import editorSeme.model.pojo.Sistem;
import editorSeme.view.WrongParentException;

/**
 * Adds an new Package to the JSON database schema.
 *
 */
public class AddPackage extends AddCommand {

	/**
	 * Overrides the interface so that it could add an package. 
	 */
	@Override
	public boolean doCommand() {
		Package c = (Package) child;
		
		if (parent instanceof Package) {
			Package p = (Package) parent;
				return p.addPackage(c);
		}else if(parent instanceof Sistem){
			Sistem s = (Sistem) parent;
			return	s.addPackage(c);
		}
		return false;

	}

	/**
	 * Overrides the interface so that it could remove an package whit all of its children and thereby undo the add operation. 
	 */
	@Override
	public boolean undoCommand() {
		
		
		Package c = (Package) child;
		
		if (parent instanceof Package) {
			Package p = (Package) parent;
			
				return p.removePackage(c);
		}else{
			Sistem s = (Sistem) parent;
			return	s.removePackage(c);
		}
		
	}

	/**
	 * Constructs a new AddPackage object with the given parameters.
	 * @param parent The system or subsystem in which the package should be added.
	 * @param child The Package that should be added.
	 * @throws WrongParentException If you send a System to a System, System to a SubSystem or SubSystem to a SubSystem this error is thrown. You can only add : SubSystems to Systems and Packages to SubSystems.
	 */
	public AddPackage(Object parent, Package child) throws WrongParentException{
		
		this.parent = parent; 
		this.child = child;
		
		
		
	}
	
	
}
