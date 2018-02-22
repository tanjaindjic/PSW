package editorSeme.controller.builders;

import editorSeme.model.enums.Tip;
import editorSeme.model.pojo.Atribut;

/**
 * Builder interface fore creating atribute objects.
 *
 */
public interface AtributBuilder extends Builder{
	/**
	 * Method that builds domain of atribute.
	 * @param length Length of domain.
	 * @param tip Domain type.
	 */
	public void buildDomen(int length, Tip tip);
	/**
	 * Method that empties current domain.
	 */
	public void unBuildDomen();
	/**
	 * Method that sets flag for if atribute can be null.
	 * @param b Logical value for allowing of null
	 */
	public void buildisNull(boolean b);
	/**
	 * Method that sets flag for if atribute is mandatory.
	 * @param b Logical value for if atribute is mandatory.
	 */
	public void buildUnique(boolean b);
	/**
	 * Get object that is being built.
	 * @return Built atribute object.
	 */
	public Atribut getAtribute();
}
