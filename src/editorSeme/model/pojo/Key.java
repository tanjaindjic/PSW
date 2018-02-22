package editorSeme.model.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Class representing whole structure of keys.
 *
 */
public class Key implements Serializable{
	@JsonIgnore
	public UUID id;
	private ArrayList<Id_Id> ids;

	/**
	 * Constructor without parameters. Initializes new list and generates ID.
	 */
	public Key() {
		this.id = UUID.randomUUID();
		this.ids = new ArrayList<Id_Id>();
	}
	
	/**
	 * Constructor with parameters.
	 * @param ids List of atribut codes which are included in thid key.
	 */
	public Key(ArrayList<Id_Id> ids) {
		this.id = UUID.randomUUID();
		this.ids = ids;
	}
	
	/**
	 * Method that gets ID. 
	 * @return ID in UUID format.
	 */
	@JsonIgnore
	public UUID getId() {
		return id;
	}
	
	/**
	 * Method that returns only list of atribut codes, without table codes.
	 * @return Returns list of atributs.
	 */
	@JsonIgnore
	public ArrayList<String> getAtributs() {
		ArrayList<String> retVal = new ArrayList<String>();
		for(Id_Id i:ids) {
			retVal.add(i.getAtributeKey());
		}
		return retVal;
	}
	
	/**
	 * Method that returns list od Id_Id-s for relevant list of Strings which are codes of atributes. 
	 * @param atrs List of atribute codes as Strings.
	 * @return List of Id_Id-s.
	 */
	@JsonIgnore
	public ArrayList<Id_Id> getRelevantIds(ArrayList<String> atrs) {
		ArrayList<Id_Id> retVal = new ArrayList<Id_Id>();
		for(Id_Id i:ids) {
			for(String s:atrs) {
				if(i.getAtributeKey().equals(s)) {
					retVal.add(i);
				}
			}
		}
		return retVal;
	}

	/**
	 * Method that gets IDs.
	 * @return IDs as list of Id_Id-s.
	 */
	public ArrayList<Id_Id> getIds() {
		return ids;
	}

	/**
	 * Method that sets IDs.
	 * @param ids IDs as list of Id_Id-s.
	 */
	public void setIds(ArrayList<Id_Id> ids) {
		this.ids = ids;
	}

}
