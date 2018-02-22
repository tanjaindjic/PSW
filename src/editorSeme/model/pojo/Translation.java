package editorSeme.model.pojo;

import java.io.Serializable;

/**
 * Class representing translation of code.
 *
 */
public class Translation implements Serializable{
	private String tr;
	private String lang;

	
	/**
	 * Default empty constructor.
	 */
	public Translation() {
		super();
	}
	
	/**
	 * Constructor with parameters.
	 * @param tr Translation to lang language.
	 * @param lang Desired language.
	 */
	public Translation(String tr, String lang) {
		super();
		this.tr = tr;
		this.lang = lang;
	}

	/**
	 * Method that gets translation.
	 * @return Translation as string.
	 */
	public String getTr() {
		return tr;
	}
	
	/**
	 * Method that sets translation.
	 * @param tr Translation as string.
	 */
	public void setTr(String tr) {
		this.tr = tr;
	}

	/**
	 * Method that gets language.
	 * @return Language code as string.
	 */
	public String getLang() {
		return lang;
	}

	/**
	 * Method that sets language.
	 * @param lang Language code as string.
	 */
	public void setLang(String lang) {
		this.lang = lang;
	}

}
