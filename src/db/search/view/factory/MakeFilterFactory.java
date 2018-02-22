package db.search.view.factory;

import java.awt.Component;

import editorSeme.model.pojo.Atribut;
import jsonDataBase.factory.ComponentPart;
/**
 * Part of abstract factory pattern
 *
 */
public abstract class MakeFilterFactory extends Component {
	
	protected abstract AbsFilter makeComponent(Atribut a, int i);
	/**
	 * Method that makes an object by calling a responsible method
	 * @param a - atribut for which factory elements are made
	 * @param i - indicator of grid position in the dialog filter frame
	 * @return - instance of AbsFilter
	 */
	public AbsFilter build(Atribut a, int i){
		AbsFilter buildPart = makeComponent(a, i);
		return buildPart;
	}
}