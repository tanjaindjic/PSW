package editorSeme.controller.concreetBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

import javax.swing.JOptionPane;

import editorSeme.controller.builders.Builder;
import editorSeme.controller.builders.TableBuilder;
import editorSeme.model.pojo.Atribut;
import editorSeme.model.pojo.ConstraintOperation;
import editorSeme.model.pojo.ContraintTorka;
import editorSeme.model.pojo.FKey;
import editorSeme.model.pojo.Id_Id;
import editorSeme.model.pojo.Key;
import editorSeme.model.pojo.NameTranslate;
import editorSeme.model.pojo.Table;
import editorSeme.model.pojo.Package;
import editorSeme.model.pojo.Translation;

public class DefaultTableBuilder extends DefaultBuilder implements TableBuilder{

	private Table newTable;
	private Package p=null;
	
	
	/**
	 * Method that returns parent package.
	 * @return Parent package.
	 */
	public Package getP() {
		return p;
	}

	/**
	 * Method that sets parent package.
	 * @param p Parent package.
	 */
	public void setP(Package p) {
		this.p = p;
	}

	/**
	 * Method that get atributs from building object.
	 * @return List of atriubuts inside this table.
	 */
	public ArrayList<Atribut> getAtributes(){
		return newTable.getPolja();
	}
	
	/**
	 * Constructor that initializes parent for new table.
	 * @param parent Parent package for this table.
	 */
	public DefaultTableBuilder(Package parent) {
		this.newTable = new Table(parent, new ArrayList<Observer>());
	}

	/**
	 * Method that gets keys from this table.
	 * @return List of keys.
	 */
	public ArrayList<Key> getKeys(){
		return newTable.getKeys();
	}
	
	/**
	 * Method that sets primary key index.
	 * @param i New value for primary key
	 */
	public void buildPKey(int i){
		newTable.setpKey(i);
	}


	/**
	 * Method that creates code name for object.
	 */
	@Override
	public void BuildName(String code) {
		newTable.getNaziv().setCode(code);
		
	}

	/**
	 * Method that adds new translations for object.
	 */
	@Override
	public void BuildTranslate(ArrayList<Translation> trs) {
		newTable.getNaziv().setTranslate(trs); 
		
	}

	/**
	 * Method that adds single new translation for object.
	 */
	@Override
	public void BuildNewTranslate(String tr, String lang) {
		newTable.getNaziv().getTranslate().add(new Translation(tr, lang));
		
	}

	/**
	 *  Method that adds new observes on object.
	 */
	@Override
	public void BuildNewObservers(ArrayList<Observer> obs) {
		for(Observer o:obs) {
			newTable.addObserver(o);
		}
		
	}

	/**
	 * Method that removes all translates from object.
	 */
	@Override
	public void UnBuildTransalte() {
		newTable.getNaziv().setTranslate(null);
		
	}

	/**
	 * Method that adds atributes into table that is being created.
	 */
	@Override
	public void buildAtributes(ArrayList<Atribut> atrs) {
		newTable.setPolja(atrs);
		
	}

	/**
	 *  Method that adds foreign keys into table that is being created.
	 */
	@Override
	public void buildFKeys(ArrayList<FKey> fks) {
		newTable.setfKeys(fks);
		
	}

	/**
	 * Method that adds keys into table that is being created.
	 */
	@Override
	public void buildKeys(ArrayList<Key> ks) {
		newTable.setKeys(ks);
		
	}



	/*@Override
	public void buildContraints(ArrayList<ContraintTorka> conss) {
		newTable.setConstraint(conss);
		
	}*/

	/**
	 * Method that adds atribute into table that is being created.
	 */
	@Override
	public void buildNewAtribute(Atribut atr) {
		newTable.getPolja().add(atr);
		
	}

	/**
	 * Method that adds foreign key into table that is being created.
	 */
	@Override
	public void buildNewFKey(String conected, ArrayList<String> homeIds, ArrayList<String> foreignIds) {
		FKey nov = new FKey(conected, homeIds, foreignIds);
		newTable.getfKeys().add(nov);
		
	}

	/**
	 * Method that adds key into table that is being created.
	 */
	@Override
	public void buildNewKey(ArrayList<Id_Id> ids) {
		Key nov = new Key(ids);
		newTable.getKeys().add(nov);
	}



	/*@Override
	public void buildNewContraint(ArrayList<String> firstId, ArrayList<String> secondId, ConstraintOperation consType) {
		// TODO Auto-generated method stub
		
	}*/

	/**
	 * Method that removes all currently added atributes.
	 */
	@Override
	public void unBuildAtributes() {
		newTable.setPolja(null);
		
	}
	
	/**
	 * Method that removes all currently added foreign keys.
	 */
	@Override
	public void unBuildFKeys() {
		newTable.setfKeys(null);
		
	}

	/**
	 * Method that removes all currently added keys.
	 */
	@Override
	public void unBuildKeys() {
		newTable.setKeys(null);
		
	}



	/*@Override
	public void unBuildContraints() {
		// TODO Auto-generated method stub
		
	}*/

	/**
	 * Get table that is being built and validate it beforehand.
	 * @return Built table object.
	 */
	@Override
	public Table getTable() {
		if(newTable.getKeys()!=null) {
			for(Key k:newTable.getKeys()) {
				for(Id_Id i:k.getIds()) {
					i.setTableKey(newTable.getNaziv().getCode());
				}
			}
		}
		for(FKey f:newTable.getfKeys()) {
			if(!validateFK(f)) {
				return null;
			}
		}
		if(newTable.validate()/*&&newTable.validateRecursivePaths(count, tabele)*/) {
			for(Key k:newTable.getKeys()) {
				for(FKey fk:newTable.getfKeys()) {
					if(compKeys(k.getAtributs(), fk.getHomeIds())){
						ArrayList<Id_Id> retVal = k.getRelevantIds(fk.getHomeIds());
						//System.out.println("PRONASLI ZA ZAMENU:"+retVal.size());
						for(Id_Id pronadjeni:retVal) {
							pronadjeni.setTableKey(fk.getConnectedTable());
						}
					}
				}
			}
			return newTable;
		}
	    return null;
	}
	/**
	 * Validates Foreign Key.
	 * @param f is Foreign Key that needs to be validated.
	 * @return if Key passed validation.
	 */
	private boolean validateFK(FKey f) {
		if(f.getHomeIds().size()!=f.getForeignIds().size()) {
			JOptionPane.showMessageDialog(null, "Neuskladjene duzine nizova za FKey.");
			return false;
		}else {
			Table t = p.findTable(f.getConnectedTable());
			for(int i=0;i<f.getHomeIds().size();i++) {
				if(!newTable.findAtribut(f.getHomeIds().get(i)).getDomain().equals(t.findAtribut(f.getForeignIds().get(i)).getDomain())) {
					JOptionPane.showMessageDialog(null, "Neuskladjeni tipovi za FKey.");
					return false;
				}
			}
		}
		return true;
	}
	
	
	private boolean compKeys(ArrayList<String> ka, ArrayList<String> hids) {
		for(String home:hids) {
			if(!containsString(ka, home)) {
				return false;
			}
		}
		return true;
	}
	
	private boolean containsString(ArrayList<String> a, String s) {
		for(String aa:a) {
			if(aa.equals(s)) {
				return true;
			}
		}
		return false;
	}


	
}
