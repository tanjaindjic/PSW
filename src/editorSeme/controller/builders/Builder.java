package editorSeme.controller.builders;

import java.util.ArrayList;
import java.util.Observer;

import editorSeme.model.pojo.Translation;

/**
 * Builder interface for creating Atributes, Packages and Tables.
 * Concrete interfaces inherit this one.
 *
 */
public interface Builder {
	/**
	 * Method that creates code name for object.
	 * @param code Desired code value.
	 */
	public void BuildName(String code);
	/**
	 * Method that adds new translations for object.
	 * @param trs Desired translations.
	 */
	public void BuildTranslate(ArrayList<Translation> trs);
	/**
	 * Method that adds single new translation for object.
	 * @param tr Translation string.
	 * @param lang Language to be translated.
	 */
	public void BuildNewTranslate(String tr, String lang);
	/**
	 * Method that adds new observes on object.
	 * @param obs List of desired observers.
	 */
	public void BuildNewObservers(ArrayList<Observer> obs);
	/**
	 * Method that removes all translates from object.
	 */
	public void UnBuildTransalte();
}
