package editorSeme.model.pojo;

import java.io.Serializable;

import editorSeme.model.enums.ConstraintType;


/**
 * Class that represents operational constraints. Currently unimplmented.
 *
 */
public class ConstraintOperation implements Serializable{
	
	private String description;
	private ConstraintType constraintType;
	
	
	/**
	 * Default constructor without fields.
	 */
	public ConstraintOperation() {
		super();
	}

	/**
	 * Constructor with parameters.
	 * @param description Mathematical description of constraint.
	 * @param constraintType Value from ConstraintType enumeration.
	 */
	public ConstraintOperation(String description, ConstraintType constraintType) {
		super();
		this.description = description;
		this.constraintType = constraintType;
	}

	/**
	 * Method that gets constraint description.
	 * @return Returns constraint description.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Method that sets constraint description.
	 * @param description Desired new description.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Method that gets constraint type.
	 * @return Returns constraint type.
	 */
	public ConstraintType getConstraintType() {
		return constraintType;
	}
	
	/**
	 * Method that sets constraint type.
	 * @param constraintType Desired new type.
	 */
	public void setConstraintType(ConstraintType constraintType) {
		this.constraintType = constraintType;
	}
	   
	
	
	   
}
