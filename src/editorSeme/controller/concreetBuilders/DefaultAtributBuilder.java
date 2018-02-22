package editorSeme.controller.concreetBuilders;

import java.util.ArrayList;
import java.util.Observer;

import editorSeme.controller.builders.AtributBuilder;
import editorSeme.controller.builders.Builder;
import editorSeme.model.enums.Tip;
import editorSeme.model.pojo.Atribut;
import editorSeme.model.pojo.Domain;
import editorSeme.model.pojo.NameTranslate;
import editorSeme.model.pojo.Table;
import editorSeme.model.pojo.Translation;

public class DefaultAtributBuilder extends DefaultBuilder  implements AtributBuilder{

	private Atribut newAtribut;
	
	/**
	 * Constructor that initializes parent object.
	 * @param p Table for this atribute.
	 */
	public DefaultAtributBuilder(Table p) {
		this.newAtribut = new Atribut(p);
	}

	/**
	 * Method that creates code name for object.
	 */
	@Override
	public void BuildName(String code) {
		newAtribut.getNaziv().setCode(code);	
		
	}

	/**
	 * Method that adds new translations for object.
	 */
	@Override
	public void BuildTranslate(ArrayList<Translation> trs) {
		newAtribut.getName().setTranslate(trs); 
		
	}

	/**
	 * Method that adds single new translation for object.
	 */
	@Override
	public void BuildNewTranslate(String tr, String lang) {
		newAtribut.getName().getTranslate().add(new Translation(tr, lang));
		
	}

	/**
	 * Method that adds new observes on object.
	 */
	@Override
	public void BuildNewObservers(ArrayList<Observer> obs) {
		newAtribut.getName().setTranslate(null);
		
	}

	/**
	 * Method that removes all translates from object.
	 */
	@Override
	public void UnBuildTransalte() {
		newAtribut.getName().setTranslate(null);
		
	}

	/**
	 * Method that builds domain of atribute.
	 */
	@Override
	public void buildDomen(int length, Tip tip) {
		Domain nov = new Domain(length, tip);
		newAtribut.setDomain(nov);
		
	}

	/**
	 * Method that empties current domain.
	 */
	@Override
	public void unBuildDomen() {
		newAtribut.setDomain(null);
		
	}

	/**
	 * Get atribut that is being built.
	 */
	@Override
	public Atribut getAtribute() {
		if(newAtribut.validate()) {
			return newAtribut;
		}
		return null;
	}

	/**
	 * Method that sets flag for if atribute can be null.
	 */
	@Override
	public void buildisNull(boolean b) {
		newAtribut.setNull(b);
		
	}

	/**
	 * Method that sets flag for if atribute is mandatory.
	 */
	@Override
	public void buildUnique(boolean b) {
		newAtribut.setUnique(b);
		
	}

	

}
