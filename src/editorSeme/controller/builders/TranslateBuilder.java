package editorSeme.controller.builders;

import editorSeme.model.pojo.Translation;

/**
 * Builder interface for translations.
 *
 */
public interface TranslateBuilder extends Builder{
	
	/**
	 * Adds code and translation to new object.
	 * @param code Code as string.
	 * @param tr Translation as string.
	 */
	public void buildTranslation(String code, String tr);
	/**
	 * Method that removes all currently added translations.
	 */
	public void unBuildTranslation();
	/**
	 * Get object that is being built.
	 * @return Built translation object.
	 */
	public Translation getTranslation();
}
