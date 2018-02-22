package editorSeme.controller.memento;

/**
 * Represents one version of the JSON database schema. It is used for version control of the JSON database schema.
 *
 */
public class Memento {

	private String code;
	/**
	 * Constructs a new version.
	 * @param s The value of the JSON schema in a string format.
	 */
	public Memento(String s){
		code=s;
	}
	/**
	 * Gets the value of the JSON schema in a string format.
	 * @return The value of the JSON schema in a string format.
	 */
	public String getCode(){
		if(code == null)
			return "";
		return code;
	}
}
