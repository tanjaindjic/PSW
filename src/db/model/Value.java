package db.model;

import editorSeme.model.enums.Tip;
/**
 * 
 * This class represents a model for relational database individual values of attributes.
 * It contains a list of values of that entry.
 *
 */
public class Value {
	private Object value;
	private Tip tip;
	private String code;
	/**
	 * Gets the code of the attribute to whom this value belongs. 
	 * @return The code that should be set which uniquely identifies the attribute.
	 */
	public String getCode() {
		return code;
	}
	/**
	 * Sets the code of the attribute to whom this value belongs. 
	 * @param code The code that should be set which uniquely identifies the attribute.
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * Gets the value of the attribute. 
	 * @return The concrete value.
	 */
	public Object getValue() {
		return value;
	}
	/**
	 * Sets the value of the attribute. 
	 * @param value The concrete value to which the field should be set.
	 */
	public void setValue(Object value) {
		this.value = value;
	}
	/**
	 * Gets the type of value for the attribute. 
	 * @return The data type of the value.
	 */
	public Tip getTip() {
		return tip;
	}
	/**
	 * Sets the type of value for the attribute.
	 * @param tip The data type of the value.
	 */
	public void setTip(Tip tip) {
		this.tip = tip;
	}

	/**
	 * Constructor for the Value class.
	 * @param value The concrete value to which the field should be set.
	 * @param tip The data type of the value.
	 * @param code The code that should be set which uniquely identifies the attribute.
	 */
	public Value(Object value, Tip tip, String code) {
		super();
		this.value = value;
		this.tip = tip;
		this.code=code;
	}
	
	/**
	 * Empty constructor. Constructs the Value with all fields set to null.
	 */
	public Value() {
		// TODO Auto-generated constructor stub
	}
	
	
}
