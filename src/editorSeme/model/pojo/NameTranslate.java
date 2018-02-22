package editorSeme.model.pojo;

import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 * Class representing codes of Atributes, Tables, Packages as well as their translations in various languages.
 *
 */
public class NameTranslate implements Serializable{

	private String code;
	private ArrayList<Translation> translate;

	
	/**
	 * Constructor without parameters. Initializes list of trasnaltions.
	 */
	public NameTranslate() {
		this.translate = new ArrayList<Translation>();
	}

	/**
	 * Constructor which also sets starting code.
	 * @param code Code of relevant entity as String.
	 */
	public NameTranslate(String code) {
		this.code = code;
		this.translate = new ArrayList<Translation>();
	}
	
	/**
	 * Validation of predefined name structure.
	 * @return True - structure is valid, False - structure is not valid.
	 */
	public boolean validate() {
		if(code==null||code.equals("")) {
			JOptionPane.showMessageDialog(null, Sistem.getInstance().getTranslate("nTrnansMsg1"));
			return false;
		}
		return true;
	}

	/**
	 * Method that gets Code.
	 * @return Code of entity as String.
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Method that sets Code.
	 * @param code Desired code of entity as String.
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Method that gets all translations.
	 * @return List of translations.
	 */
	public ArrayList<Translation> getTranslate() {
		return translate;
	}

	/**
	 * Method that sets all translations.
	 * @param translate Desired list of translations.
	 */
	public void setTranslate(ArrayList<Translation> translate) {
		this.translate = translate;
	}

	/**
	 * Method that adds new language translation for code.
	 * @param lang Language for which u want to add translate.
	 * @param tr Translation for code.
	 * @return Success flag.
	 */
	public boolean addTranslate(String lang, String tr) {
		for(Translation t1:translate) {
			if(t1.getLang().equals(lang)) {
				return false;
			}
		}
		Translation ret = new Translation(tr, lang);
		translate.add(ret);
		return true;
	}
	/**
	 * Method that edits language translation for code.
	 * @param lang Language for which u want to edit translate.
	 * @param tr Translation for code.
	 * @return Success flag.
	 */
	public boolean editTranslate(String lang, String tr) {
		for(Translation t1:translate) {
			if(t1.getLang().equals(lang)) {
				t1.setTr(tr);
				return true;
			}
		}
		return false;
	}
	/**
	 * Method that adds new language translation for code, overwrites onlt serbian langage (app default).
	 * @param lang Language for which u want to add translate.
	 * @param tr Translation for code.
	 * @return Success flag.
	 */
	public boolean addTranslateSerbian(String lang, String tr) {
		for(Translation t1:translate) {
			if(t1.getLang().equals(lang)) {
				t1.setTr(tr);
				return true;
			}
		}
		Translation ret = new Translation(tr, lang);
		translate.add(ret);
		return true;
	}
}
