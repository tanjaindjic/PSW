package db.search.view.factory;

import editorSeme.model.enums.Tip;
import editorSeme.model.pojo.Atribut;
/**
 * Part of abstract factory pattern
 *
 */
public class FilterFactory extends MakeFilterFactory{
	/**
	 * Constructor that decides which constructor for AbsFilter needs to be called
	 */

	@Override
	public AbsFilter makeComponent(Atribut a, int i) {
		AbsFilter part = null;
		if(a.getDomain().getTip().equals(Tip.BOOLEAN)){
			part = new BooleanFilter(a, i);
		}
		else if(a.getDomain().getTip().equals(Tip.VARCHAR) || a.getDomain().getTip().equals(Tip.CHAR)){
			part = new AlfanumFilter(a, i);
		}
		else{
			part = new NumDateFilter(a, i);
		}
		return part;
	}

}
