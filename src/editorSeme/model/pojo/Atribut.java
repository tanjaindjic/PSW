package editorSeme.model.pojo;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JOptionPane;

import com.fasterxml.jackson.annotation.JsonIgnore;

import db.crud.RelationalRead;
import editorSeme.model.eventEncapsulation.AtributEvent;
import editorSeme.model.eventEncapsulation.EventType;
import jsonDataBase.additional.JSONDataSerialize;
import jsonDataBase.model.Data;
import jsonDataBase.model.DataTabela;
import start.DatabaseType;
import start.InfViewModel;

/**
 * Class that represents an atribute in table.
 * This class is used only as a part of internal metascheme, for tuple storage there are other classes.
 *
 */
public class Atribut extends TranslatableNObservable implements Serializable{
	private NameTranslate name;
	private Domain domain;
	private boolean isNull;
	private boolean unique;
	@JsonIgnore
	private Table parentModel;
	
	/**
	 * Constructor without parameters. Only initializes emmpty subobjects.
	 */
	public Atribut() {
		this.name = new NameTranslate();
		this.domain = new Domain();
		this.name= new NameTranslate();
		this.naziv = this.name;
	}
	
	/**
	 * Constructor with parameters that automaticaly sets parentModel to desired table.
	 * @param p Desired parent table.
	 */
	public Atribut(Table p) {
		this.parentModel=p;
		this.name = new NameTranslate();
		this.naziv = this.name;
	}

	/**
	 * Constructor which sets all fields to desiered values.
	 * @param name New name for atribut
	 * @param domain New domain for atribut
	 * @param isNull Declaration weather is atribut mandatory
	 * @param unique Declaration weather is atribut unique
	 * @param parent Parent for atribut
	 */
	public Atribut(NameTranslate name, Domain domain, boolean isNull, boolean unique, Table parent) {
		super();
		this.name = name;
		this.domain = domain;
		this.isNull = isNull;
		this.unique = unique;
		this.parentModel = parent;
		this.naziv = this.name;
	}
	
	/**
	 * Constructor wich sets all fields to desiered values. Also sets GUI observers.
	 * @param name New name for atribut
	 * @param name New name for atribut
	 * @param domain New domain for atribut
	 * @param isNull Declaration weather is atribut mandatory
	 * @param unique Declaration weather is atribut unique
	 * @param parent Parent for atribut
	 * @param obs List of observes to be added
	 */
	public Atribut(NameTranslate name, Domain domain, boolean isNull, boolean unique, Table parent, ArrayList<Observer> obs) {
		super();
		this.name = name;
		this.domain = domain;
		this.isNull = isNull;
		this.unique = unique;
		this.parentModel = parent;
		this.naziv = this.name;
		addObservers(obs);
	}

	/**
	 * Method that adds observers into internal observer list
	 * @param obs List of desired observers to be added.
	 */
	public void addObservers(ArrayList<Observer> obs) {
		if(obs==null)
			return;
		for(Observer o:obs) {
			addObserver(o);
		}
	}
	
	
	/**
	 * Returns parent for this atribute.
	 * @return Returns parent Table.
	 */
	public Table getParentModel() {
		return parentModel;
	}

	/**
	 * Set parent model method
	 * @param parentModel Desired Table to become parent of this atribute.
	 */
	public void setParentModel(Table parentModel) {
		this.parentModel = parentModel;
	}

	/**
	 * Method that returns NameTranslate class, that represents code and translations.
	 * @return Returns name.
	 */
	@JsonIgnore
	public NameTranslate getName() {
		return name;
	}

	/**
	 * Method that sets NameTranslate field in this class
	 * @param name New name for this atribute.
	 */
	public void setName(NameTranslate name) {
		this.name = name;
		AtributEvent event = new AtributEvent(parentModel, this, EventType.UPDATE);
		this.naziv = this.name;
		setChanged();
		notifyObservers(event);
	}

	/**
	 * Method that gets domain constraint.
	 * @return Returns defined Domain.
	 */
	public Domain getDomain() {
		return domain;
	}

	/**
	 * Method that sets domain constraint.
	 * @param domain Desired domain for this atribut.
	 */
	public void setDomain(Domain domain) {
		this.domain = domain;
		AtributEvent event = new AtributEvent(parentModel, this, EventType.UPDATE);
		setChanged();
		notifyObservers(event);
	}

	/**
	 * Method that gets if atribut is mandatory.
	 * @return Boolean value. True - is mandatory, False - is not mandatory.
	 * 
	 */
	public boolean getisNull() {
		return isNull;
	}

	/**
	 * Method that sets if atribut is mandatory.
	 * @param isNull Boolean value. True - is mandatory, False - is not mandatory.
	 * 
	 */
	public void setNull(boolean isNull) {
		this.isNull = isNull;
		AtributEvent event = new AtributEvent(parentModel, this, EventType.UPDATE);
		setChanged();
		notifyObservers(event);
	}

	/**
	 * Methot that gets if atribut is unique.
	 * @return Boolean value. True - is unique, False - is not unique.
	 * 
	 */
	public boolean isUnique() {
		return unique;
	}
	
	/**
	 * Method that sets if atribut is unique.
	 * @param unique Boolean value. True - is unique, False - is not unique.
	 * 
	 */
	public void setUnique(boolean unique) {
		this.unique = unique;
		AtributEvent event = new AtributEvent(parentModel, this, EventType.UPDATE);
		setChanged();
		notifyObservers(event);
	}
	
	/**
	 * Method that validates current atribut structure according to predefined rules.
	 * @return True - structure is valid, False - structure is not valid.
	 */
	public boolean validate() {
		if(domain==null||name==null) {
			JOptionPane.showMessageDialog(null, Sistem.getInstance().getTranslate("atrValMsg1"));
			return false;
		}
		else if(!domain.validate()||!name.validate()) {
			if(!name.validate()) {
				//System.out.println("PUKNE KOD NAME U ATRIBUT");
			}else {
				//System.out.println("PUKNE KOD DOMAIN U ATRIBUT"+naziv.getCode());
			}
			return false;
		}
		
		//JOptionPane.showMessageDialog(null, name.getCode());
		return true;
	}
	
	//tree methods
	/*@Override
	@JsonIgnore
	public Enumeration children() {
		return null;
	}

	@Override
	@JsonIgnore
	public boolean getAllowsChildren() {
		return false;
	}

	@Override
	@JsonIgnore
	public TreeNode getChildAt(int childIndex) {
		return null;
	}

	@Override
	@JsonIgnore
	public int getChildCount() {
		return 0;
	}

	@Override
	@JsonIgnore
	public int getIndex(TreeNode node) {
		return -1;
	}

	@Override
	@JsonIgnore
	public TreeNode getParent() {
		return parentModel;
	}

	@Override
	@JsonIgnore
	public boolean isLeaf() {
		return true;
	}*/
	/**
	 * checks if atribut is foreign key, so that values from other table(s) should be used as its value
	 * @return true-its value is "borrowed" from other (parent) table, false - it's not a foreign key
	 */
	@JsonIgnore
	public boolean isForeignKey() {
		ArrayList<FKey> fk = parentModel.getfKeys();
		for(FKey fk0 : fk){
			for(String s : fk0.getHomeIds())
				if(s.equals(name.getCode())){				
					return true;
				}
		}
		return false;
	}
	/**
	 * Method that provides table whose values of the atribut are used as foreign key to this instance of atribut
	 * (provides connected table)
	 * @return Table - parent table with atribut used as foreig key, Null - if atribut is not foreign key
	 */
	@JsonIgnore
	public Table getTableForFKey() {
		Atribut as = this;
		ArrayList<FKey> fk = parentModel.getfKeys();
		String code="";
		for(FKey fk0 : fk){
			for(String s : fk0.getHomeIds())
				if(s.equals(name.getCode())){				
					code=fk0.getConnectedTable();
					ArrayList<Table> t = Sistem.getInstance().getAllTables();
					for(Table t0 : t){
						if(t0.getNaziv().getCode().equals(code))
							return t0;				
					}
				}
		}
		
		return null;
	}
	/**
	 * checks if atribut is used as foreign key, so that its values should be used as another atribut's values
	 * @return true-its value is "lend" to other (children) table, false - it's not used as foreign key
	 */
	@JsonIgnore
	public boolean isConnectedAsKey() {
		ArrayList<Table> t = Sistem.getInstance().getAllTables();
		for(Table t0 : t){
			for(FKey fk0 : t0.getfKeys()){
				for(String ss : fk0.getForeignIds()){
					if(ss.equals(name.getCode())){
						return true;
					}
				}
			}
		}
		return false;		
	}
	
	/**
	 * Checks if object is used as foreign key in another table (mostly to decide if it can/should be deleted)
	 * In case it is relational dataBase, it is redirected to isUsedAsKeyRel-method  
	 * @param object - value of a concrete atribut in table, that might be used as foreign key in another table 
	 * @return True - if it is used in (connected to) child table, False - object is not used in any of child table
	 */
	@JsonIgnore
	public boolean isUsedAsKey(Object object) {
		if(InfViewModel.getInstance().getDatabaseType().equals(DatabaseType.RELATIONAL)){
			return isUsedAsKeyRel(object);
		}
		ArrayList<Table> t = Sistem.getInstance().getAllTables();
		for(Table t0 : t){
			for(FKey fk0 : t0.getfKeys()){
				int i=0;
				for(String ss : fk0.getForeignIds()){
					if(ss.equals(name.getCode())){
						DataTabela dt = JSONDataSerialize.getDataTable(t0);
						for(Data d0 : dt.getTorke()){
							if(d0.getTorka().get(fk0.getHomeIds().get(i)).equals(object))
								return true;
						}
					}
					i++;
				}
			}
		}
		return false;
		
	}
	/**
	 * Is called in case that aplication works with relational dataBase
	 * Checks if object is used as foreign key in another table (mostly to decide if it can/should be deleted)
	 * @param object - value of a concrete atribut in table, that might be used as foreign key in another table 
	 * @return True - if it is used in (connected to) child table, False - object is not used in any of child table
	 */
	@JsonIgnore
	private boolean isUsedAsKeyRel(Object object) {
		ArrayList<Table> t = Sistem.getInstance().getAllTables();
		int kolona = -1;
		for(int i=0; i< parentModel.getPolja().size(); i++){
			if(name.getCode().equals(parentModel.getPolja().get(i).getName().getCode()))
				kolona=i;
		}
		if(kolona==-1){
			return false;
		}
		for(Table t0 : t){
			for(FKey fk0 : t0.getfKeys()){
				int i=0;
				for(String ss : fk0.getForeignIds()){
					if(ss.equals(name.getCode())){
						RelationalRead rr = new RelationalRead();
						ResultSet rs = (ResultSet) rr.readTable(parentModel.getNaziv().getCode());
						try {
							while(rs.next()){
								if(rs.getObject(kolona).equals(object))
									return true;
							}
						} catch (SQLException e) {
							System.out.println("greska u komunikaciji sa bazom(Atribut->293)");
						}
					}
					i++;
				}
			}
		}
		return false;
	}
}
