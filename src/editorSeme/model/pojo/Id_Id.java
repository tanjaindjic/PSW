package editorSeme.model.pojo;

import java.io.Serializable;

/**
 * Class representing part of identifier structure.
 *
 */
public class Id_Id implements Serializable{
	private String tableKey;
	private String atributeKey;

	
	/**
	 * Default constructor.
	 */
	public Id_Id() {
		super();
	}

	/**
	 * Constructor with parameters.
	 * @param tableKey Table in which is this key.
	 * @param atributeKey Code of atribut in key.
	 */
	public Id_Id(String tableKey, String atributeKey) {
		super();
		this.tableKey = tableKey;
		this.atributeKey = atributeKey;
	}

	/**
	 * Method that gets table in which is this part of the key.
	 * @return String representing code of wanted table.
	 */
	public String getTableKey() {
		return tableKey;
	}
	/**
	 * Method that sets table in which is this part of the key.
	 * @param tableKey String representing code of wanted table.
	 */
	public void setTableKey(String tableKey) {
		this.tableKey = tableKey;
	}

	/**
	 * Method that gets code of atribut.
	 * @return String representing code atribut.
	 */
	public String getAtributeKey() {
		return atributeKey;
	}

	/**
	 * Method that sets code of atribut.
	 * @param atributeKey String representing code atribut.
	 */ 
	public void setAtributeKey(String atributeKey) {
		this.atributeKey = atributeKey;
	}

}
