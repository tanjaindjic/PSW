package editorSeme.model.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Class representing tuple constraints. Currently unimplemented.
 *
 */
public class ContraintTorka implements Serializable{
	@JsonIgnore
	private UUID id;
	private List<String> firstId;
	private List<String> secondId;
	private ConstraintOperation consType;

	/**
	 * Empty constructor. Initializes lists and generates ID.
	 */
	public ContraintTorka() {
		id = UUID.randomUUID();
		firstId = new ArrayList<String>();
		secondId = new ArrayList<String>();
	}
	
	/**
	 * Constructor with fields. Sets all fields to desired values.
	 * @param firstId List of starting atributes.
	 * @param secondId List of target atributes.
	 * @param consType Constraint between starting and target atributes.
	 */
	public ContraintTorka(ArrayList<String> firstId, ArrayList<String> secondId, ConstraintOperation consType) {
		super();
		this.firstId = firstId;
		this.secondId = secondId;
		this.consType = consType;
	}
	
	/**
	 * 
	 * @return Returns tuple constraint ID.
	 */
	@JsonIgnore
	public UUID getId() {
		return id;
	}
	
	/**
	 * Method that gets starting atributes.
	 * @return List containing codes of starting atributes.
	 */
	public List<String> getFirstId() {
		return firstId;
	}

	/**
	 * Method that sets starting atributes.
	 * @param firstId List containing desired codes of starting atributes.
	 */
	public void setFirstId(List<String> firstId) {
		this.firstId = firstId;
	}
	
	/**
	 * Method that gets target atributes.
	 * @return List containing codes of target atributes.
	 */
	public List<String> getSecondId() {
		return secondId;
	}
	
	/**
	 * Method that sets target atributes.
	 * @param secondId List containing desired codes of target atributes.
	 */
	public void setSecondId(List<String> secondId) {
		this.secondId = secondId;
	}

	/**
	 * Method that gets constraint type.
	 * @return ConstraintOperation enumeration.
	 */
	public ConstraintOperation getConsType() {
		return consType;
	}
	/**
	 * Method that sets constraint type.
	 * @param consType New type.
	 */
	public void setConsType(ConstraintOperation consType) {
		this.consType = consType;
	}

}
