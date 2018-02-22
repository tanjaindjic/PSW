package jsonDataBase.factory;

import editorSeme.model.enums.Tip;
import editorSeme.model.pojo.Atribut;
/**
 * Class that is part of abstract factory pattern
 *
 */
public class ComponentFactory extends MakeComponentFactory{
	/**
	 * Method that depending on the atribut-type makes diferent types of Components
	 */
	@Override
	public ComponentPart makeComponent(Atribut a, int i) {
		ComponentPart part = null;
		if(a.isForeignKey()){
			part = new ForeignKeyPart(a, i);
		}
		else if(a.getDomain().getTip().equals(Tip.BOOLEAN)){
			part = new JRadioButtonPart(a, i);
		}
		else if(a.getDomain().getTip().equals(Tip.NUMERIC)){
			part = new JSpinnerPart(a, i);
		}
		else{
			part = new JTextFieldPart(a, i);
		}
		return part;
	}

}
