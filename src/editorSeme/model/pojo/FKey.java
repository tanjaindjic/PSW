package editorSeme.model.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Class that represents foreign key structure.
 *
 */
public class FKey implements Serializable{
	@JsonIgnore
	private UUID id;
	private String connectedTable;
	@JsonIgnore
	private String code = "";
	private ArrayList<String> homeIds;
	private ArrayList<String> foreignIds;

	/**
	 * Method that gets foreign key code.
	 * @return String representing foreign key code.
	 */
	@JsonIgnore
	public String getCode() {
		return code;
	}
	
	/**
	 * Method that sets foreign key code.
	 * @param code Desired String for new foreign key code.
	 */
	@JsonIgnore
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Constructor without parameters. Initializes lists and generates ID.
	 */
	public FKey() {
		id = UUID.randomUUID();
		homeIds = new ArrayList<String>();
		foreignIds = new ArrayList<String>();
	}
	
	/**
	 * Constructor with parameters. 
	 * @param connectedTable Table to whom u want to connect.
	 * @param homeIds  Codes of atributes from starting table.
	 * @param foreignIds Codes of atributes from table to whom u want to connect.
	 */
	public FKey(String connectedTable, ArrayList<String> homeIds, ArrayList<String> foreignIds) {
		super();
		this.connectedTable = connectedTable;
		this.homeIds = homeIds;
		this.foreignIds = foreignIds;
	}

	
	/**
	 * Method that gets generated ID.
	 * @return ID in UUID format.
	 */
	@JsonIgnore
	public UUID getId() {
		return id;
	}

	/**
	 * Method that gets table to which is foreign key connecting starting table.
	 * @return Code of table as String.
	 */
	public String getConnectedTable() {
		return connectedTable;
	}

	/**
	 * Method that sets table to which is foreign key connecting starting table.
	 * @param connectedTable Code of desired table as String.
	 */
	public void setConnectedTable(String connectedTable) {
		this.connectedTable = connectedTable;
	}

	/**
	 * Method that gets list of codes from starting(parent) table.
	 * @return ArrayList of string, representing codes from starting table.
	 */
	public ArrayList<String> getHomeIds() {
		return homeIds;
	}

	/**
	 * Method that sets list of codes from starting(parent) table.
	 * @param homeIds ArrayList of string, representing desired codes for starting table.
	 */
	public void setHomeIds(ArrayList<String> homeIds) {
		this.homeIds = homeIds;
	}
	/**
	 * Method that gets list of codes from target table.
	 * @return ArrayList of string, representing codes from target table.
	 */
	public ArrayList<String> getForeignIds() {
		return foreignIds;
	}

	/**
	 * Method that sets list of codes from target table.
	 * @param foreignIds ArrayList of string, representing desired codes for target table.
	 */
	public void setForeignIds(ArrayList<String> foreignIds) {
		this.foreignIds = foreignIds;
	}

	

}
