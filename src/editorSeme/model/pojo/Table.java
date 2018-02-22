package editorSeme.model.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JOptionPane;
import javax.swing.tree.TreeNode;

import com.fasterxml.jackson.annotation.JsonIgnore;

import editorSeme.model.additional.Prototype;
import editorSeme.model.eventEncapsulation.AtributEvent;
import editorSeme.model.eventEncapsulation.EventType;
import editorSeme.model.eventEncapsulation.TableEvent;
import jsonDataBase.additional.JSONDataSerialize;
import jsonDataBase.model.Data;
import jsonDataBase.model.DataTabela;
import start.DatabaseType;
import start.InfViewModel;
import workingsection.tree.Tree;

/**
 * Class that represents tables.
 *
 */
public class Table extends TranslatableNObservable implements TreeNode, Prototype, Serializable{
	//private NameTranslate naziv;
	private ArrayList<Atribut> polja;
	private ArrayList<Key> keys;
	private ArrayList<FKey> fKeys;
	private ArrayList<ContraintTorka> constraint;
	private int pKey;
	@JsonIgnore
	private Package parentModel;
	@JsonIgnore
	private ArrayList<Object> children;
	
	/**
	 * Method that gets atribut list of primary key.
	 * @return List of atribute codes as Strings.
	 */
	@JsonIgnore
	public ArrayList<String> getPkeyAtrs(){
		return keys.get(pKey).getAtributs();
	}

	/**
	 * Constructor without parameters. Initializes lists.
	 */
	public Table() {
		this.naziv= new NameTranslate();
		this.polja = new ArrayList<Atribut>();
		this.keys = new ArrayList<Key>();
		this.fKeys = new ArrayList<FKey>();
		this.constraint = new ArrayList<ContraintTorka>();
		this.children = new ArrayList<Object>();
	}

	/**
	 * Constructor with parameters.
	 * @param p Package desired to be parent of this table.
	 * @param obs List of observers for this table.
	 */
	public Table(Package p, ArrayList<Observer> obs) {
		this.naziv = new NameTranslate();
		this.polja = new ArrayList<Atribut>();
		this.keys = new ArrayList<Key>();
		this.fKeys = new ArrayList<FKey>();
		this.constraint = new ArrayList<ContraintTorka>();
		this.children = new ArrayList<Object>();
		this.pKey = 0;
		this.parentModel = p;
		addObservers(obs);
	}
	
	/**
	 * Method that updates observer lists.
	 * @param obs List of new observers to be added.
	 */
	public void addObservers(ArrayList<Observer> obs) {
		if(obs==null)
			return;
		for(Observer o:obs) {
			addObserver(o);
		}
	}
	
	/**
	 * Method that updates observer lists as well as observer list in childs of this object.
	 * @param obs List of new observers to be added.
	 */
	public void reloadObservers(ArrayList<Observer> obs) {
		addObservers(obs);
		for(Atribut a:polja) {
			a.addObservers(obs);
		}
	}
	
	/**
	 * Method that updates parent-child relations as well as parent-child relations in childs of this object.
	 */
	public void reloadChildren() {
		for(Atribut a:polja) {
			a.setParentModel(this);
		}
	}
	
	/**
	 * Method that validates current table structure according to predefined rules.
	 * @return True - structure is valid, False - structure is not valid.
	 */
	public boolean validate() {
		if(polja==null||polja.size()==0||naziv==null||keys==null||keys.size()==0||fKeys==null) {
			//System.out.println("NESTO JE NULL---tabela:"+naziv.getCode());
			JOptionPane.showMessageDialog(null, Sistem.getInstance().getTranslate("tabValMsg1"));
			return false;
		}
		else {
			for(Atribut a:polja) {
				if(!a.validate()) {
					//System.out.println("PUKNE KOD ATRIBUT U TABLE");
					return false;
				}
			}
			if(!naziv.validate()) {
				//System.out.println("PUKNE KOD NAZIV U TABLE");
				return false;
			}else if(pKey>keys.size()||pKey<0) {
				JOptionPane.showMessageDialog(null, Sistem.getInstance().getTranslate("tabValMsg2"));
				return false;
			}else if(!atributesUnique()) {
				JOptionPane.showMessageDialog(null, Sistem.getInstance().getTranslate("tabValMsg3"));
				return false;
			}
			return true;
		}
	}
	
	/*private boolean validateRecursivePaths(int count, HashMap<String, Table> tabele) {
		if(count<1) {
			return true;
		}else {
			if(tabele.containsKey(getNaziv().getCode())) {
				return false;
			}else {
				tabele.put(getNaziv().getCode(), this);
				for(Table t:(ArrayList<Table>)children()) {
					if(!t.validateRecursivePaths(count-1, tabele)) {
						return false;
					}
				}
			}
		}
		return true;
		
	}*/
	
	private boolean atributesUnique() {
		for(int i=0;i<polja.size();i++) {
			for(int j=0;j<polja.size();j++) {
				Atribut a = polja.get(i);
				Atribut b = polja.get(j);
				if(a.getName().getCode().equals(b.getName().getCode())&&j!=i) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	//Dodati u class
	/**
	 * Method that adds atribut inside table.
	 * @param a Atribut to be added.
	 * @return Success of operation as boolean.
	 */
	public boolean addAtribute(Atribut a){
		boolean retVal = polja.add(a);
		if(retVal) {
			AtributEvent event = new AtributEvent(this, a, EventType.ADD);
			setChanged();
			notifyObservers(event);
		}
		return retVal;
	}
	
	//Dodati u class
	/**
	 * Method that removes atribut inside table.
	 * @param a Atribut to be removed.
	 * @return Success of operation as boolean.
	 */
	public boolean removeAtribute(Atribut a){
		boolean retVal = polja.remove(a);
		if(retVal) {
			AtributEvent event = new AtributEvent(this, a, EventType.DELETE);
			setChanged();
			notifyObservers(event);
		}
		return retVal;
	}
	//search atr
	/**
	 * Method that finds atribut inside table.
	 * @param a Atribut to be found.
	 * @return Success of operation as boolean.
	 */
	public Atribut findAtribut(String a) {
		for(Atribut atr:polja) {
			if(atr.getName().getCode().equals(a)) {
				return atr;
			}
		}
		return null;
	}
	//add
	/**
	 * Method that adds key inside table.
	 * @param k Key to be added.
	 * @return Success of operation as boolean.
	 */
	public boolean addKey(Key k) {
		boolean retVal = keys.add(k);	
		if(retVal) {
			AtributEvent event = new AtributEvent(this, k, EventType.ADD);
			setChanged();
			notifyObservers(event);
		}
		return retVal;
	}
	//remove
	/**
	 * Method that removes key inside table.
	 * @param k Key to be removed.
	 * @return Success of operation as boolean.
	 */
	public boolean removeKey(Key k) {
		boolean retVal = keys.remove(k);
		if(retVal) {
			AtributEvent event = new AtributEvent(this, k, EventType.DELETE);
			setChanged();
			notifyObservers(event);
		}
		return retVal;
	}
	//search
	/**
	 * Method that finds key inside table.
	 * @param k Key to be found.
	 * @return Success of operation as boolean.
	 */
	public Key findKey(Key k) {
		for(Key ks:keys) {
			if(ks.getId().toString().equals(k.getId().toString())) {
				return ks;
			}
		}
		return null;
	}
	//add
	/**
	 * Method that adds foreign key inside table.
	 * @param k Foreign key to be added.
	 * @return Success of operation as boolean.
	 */
	public boolean addFKey(FKey k) {
		boolean retVal = fKeys.add(k);
		if(retVal) {
			AtributEvent event = new AtributEvent(this, k, EventType.ADD);
			setChanged();
			notifyObservers(event);
		}
		return retVal;
	}
	//remove
	/**
	 * Method that removes foreign key inside table.
	 * @param k Foreign key to be added.
	 * @return Success of operation as boolean.
	 */
	public boolean removeFKey(FKey k) {
		boolean retVal = fKeys.remove(k);
		if(retVal) {
			AtributEvent event = new AtributEvent(this, k, EventType.DELETE);
			setChanged();
			notifyObservers(event);
		}
		return retVal;
	}
	//search
	/**
	 * Method that finds foreign key inside table.
	 * @param k Foreign key to be found.
	 * @return Success of operation as boolean.
	 */
	public FKey findFKey(FKey k) {
		for(FKey ks:fKeys) {
			if(ks.getId().toString().equals(k.getId().toString())) {
				return ks;
			}
		}
		return null;
	}
	//add
	@Deprecated
	private boolean addConstraint(ContraintTorka c) {
		return constraint.add(c);
	}
	//remove
	@Deprecated
	private boolean removeConstraint(ContraintTorka c) {
		return constraint.remove(c);
	}
	//search
	@Deprecated
	private ContraintTorka findContraint(ContraintTorka c) {
		for(ContraintTorka con:constraint) {
			if(con.getId().toString().equals(c.getId().toString())) {
				return con;
			}
		}
		return null;
	}
	//edit
	/**
	 * Method that updates table info acording to data in param table.
	 * @param t Table containing data for updateing.
	 * @return Success of operation as boolean.
	 */
	public boolean editTable(Table t) {
		setNaziv(t.getNaziv());
		setpKey(t.getpKey());

		TableEvent event = new TableEvent(this, parentModel, EventType.UPDATE);
		setChanged();
		notifyObservers(event);

		return true;
	}
	
	/**
	 * Method that updates name of table.
	 * @param naziv NameTranslate object for new name.
	 */
	public void setNaziv(NameTranslate naziv) {
		this.naziv = naziv;
		TableEvent event = new TableEvent(this, parentModel, EventType.UPDATE);
		setChanged();
		notifyObservers(event);
	}

	/**
	 * Method that gets list of atributs.
	 * @return List of atribut objects.
	 */
	public ArrayList<Atribut> getPolja() {
		return polja;
	}

	/**
	 * Method that sets list of atributs.
	 * @param polja List of atribut objects.
	 */
	public void setPolja(ArrayList<Atribut> polja) {
		this.polja = polja;
	}

	/**
	 * Method that gets list of keys.
	 * @return List of Key objects.
	 */
	public ArrayList<Key> getKeys() {
		return keys;
	}
	
	/**
	 * Method that sets list of keys.
	 * @param keys List of Key objects.
	 */
	public void setKeys(ArrayList<Key> keys) {
		this.keys = keys;
	}

	/**
	 * Method that gets list of foreign keys.
	 * @return List of FKey objects.
	 */
	public ArrayList<FKey> getfKeys() {
		return fKeys;
	}

	/**
	 * Method that sets list of foreign keys.
	 * @param fKeys List of FKey objects.
	 */
	public void setfKeys(ArrayList<FKey> fKeys) {
		this.fKeys = fKeys;
	}

	/**
	 * Method that gets list of constraints.
	 * @return List of ContraintTorka objects.
	 */
	public ArrayList<ContraintTorka> getConstraint() {
		return constraint;
	}

	/**
	 * Method that sets list of constraints.
	 * @param constraint List of ContraintTorka objects.
	 */
	public void setConstraint(ArrayList<ContraintTorka> constraint) {
		this.constraint = constraint;
	}

	/**
	 * Method that gets index of primary key.
	 * @return Index of primary key as integer.
	 */
	public int getpKey() {
		return pKey;
	}

	/**
	 * Method that sets index of primary key.
	 * @param pKey Index of primary key as integer.
	 */
	public void setpKey(int pKey) {
		this.pKey = pKey;
		TableEvent event = new TableEvent(this, parentModel, EventType.UPDATE);
		setChanged();
		notifyObservers(event);
	}
	
	/**
	 * Method that gets parent system.
	 * @return Sistem object.
	 */
	@JsonIgnore
	public Sistem getParentSistem() {
		return parentModel.getParentSistem();
	}

	/**
	 * Method that sets children list for this table.
	 * @return Enumeration for list of children (requried as part of TreeNode interface)
	 */
	//tree methods
	@Override
	@JsonIgnore
	public Enumeration children() {
		children.clear();
		//ArrayList<Table> retVal = new ArrayList<Table>();
		ArrayList<Table> allTables = Sistem.getInstance().getAllTables();
		
		/*System.out.println(allTables.size());
		for(Table t:allTables) {
			System.out.println(t.getNaziv().getCode()+"---\n");
		}*/
		
		for(Table t:allTables){
			//System.out.println("YOYO:"+t.getNaziv().getCode()+"---"+naziv.getCode());
			if(!t.getNaziv().getCode().equals(naziv.getCode())) {
				//System.out.println("YOYO2:"+t.getNaziv().getCode()+"---"+naziv.getCode());
				//System.out.println("NASAO FKEY:"+t.getfKeys().size()+"---"+naziv.getCode());
				for(FKey key:t.getfKeys()){
					//System.out.println("NASAO FKEY:"+key.getConnectedTable()+"---"+naziv.getCode());
					if(key.getConnectedTable().equals(naziv.getCode())){
						//System.out.println("NASAO FKEY:"+key.getConnectedTable()+"---"+naziv.getCode());
						if(fkIsKeyInTable(key.getHomeIds(), t.getKeys())&&alreadyExists(t)){
							children.add(t);
							
						}
					}
				}
			}
		}
		
		//System.out.println("Ima dece:" + children.size());
		return Collections.enumeration(children);
	}
	
	private boolean fkIsKeyInTable(ArrayList<String> homeids, ArrayList<Key> keys) {
		for(Key k:keys) {
			if(subStringList(homeids, k.getAtributs())) {
				return true;
			}
		}
		return false;
	}
	
	private boolean subStringList(ArrayList<String> smaler, ArrayList<String> bigger) {
		for(String s:smaler) {
			if(!subString(s, bigger)) {
				return false;
			}
		}
		return true;
	}
	
	private boolean subString(String small, ArrayList<String> big) {
		for(String s:big) {
			if(s.equals(small)) {
				return true;
			}
		}
		return false;
	}
	
	/*private boolean listInKeys(ArrayList<String> s) {
		for(String s1:s) {
			if(!tableInKeys(s1)) {
				return false;
			}
		}
		return true;
	}*/
	
	/*private boolean tableInKeys(String table) {
		for(Key k:keys) {
			for(Id_Id i:k.getIds()) {
				if(i.getTableKey().equals(table)) {
					return true;
				}
			}
		}
		return false;
	}*/
	
	/**
	 * Method that sets children list for this table.
	 * @return Rather than enumeration, this version returns List for the purposes of tesing.
	 */
	@JsonIgnore
	public ArrayList<Object> childrenTest() {
		children.clear();
		//ArrayList<Table> retVal = new ArrayList<Table>();
		ArrayList<Table> allTables = Sistem.getInstance().getAllTables();
		
		/*System.out.println(allTables.size());
		for(Table t:allTables) {
			System.out.println(t.getNaziv().getCode()+"---\n");
		}*/
		
		for(Table t:allTables){
			//System.out.println("YOYO:"+t.getNaziv().getCode()+"---"+naziv.getCode());
			if(!t.getNaziv().getCode().equals(naziv.getCode())) {
				//System.out.println("YOYO2:"+t.getNaziv().getCode()+"---"+naziv.getCode());
				//System.out.println("NASAO FKEY:"+t.getfKeys().size()+"---"+naziv.getCode());
				for(FKey key:t.getfKeys()){
					//System.out.println("NASAO FKEY:"+key.getConnectedTable()+"---"+naziv.getCode());
					if(key.getConnectedTable().equals(naziv.getCode())){
						//System.out.println("NASAO FKEY:"+key.getConnectedTable()+"---"+naziv.getCode());
						if(fkIsKeyInTable(key.getHomeIds(), t.getKeys())&&alreadyExists(t)){
							//children.add(t);
							children.add(t);
						}
					}
				}
			}
		}
		
		//System.out.println("Ima dece:" + children.size());
		return children;
	}
	
	private boolean alreadyExists(Table t) {
		for(Object t1:children) {
			if(((Table)t1).getNaziv().getCode().equals(t.getNaziv().getCode())) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Override of TreeNode method getAllowsChildren()
	 */
	@Override
	@JsonIgnore
	public boolean getAllowsChildren() {
		return true;
	}

	/**
	 * Override of TreeNode method getChildAt(int childIndex)
	 */
	@Override
	@JsonIgnore
	public TreeNode getChildAt(int childIndex) {
		return (TreeNode)(children.get(childIndex));
	}

	/**
	 * Override of TreeNode method getChildCount()
	 */
	@Override
	@JsonIgnore
	public int getChildCount() {
		return children.size();
	}

	/**
	 * Override of TreeNode method getIndex(TreeNode node)
	 */
	@Override
	@JsonIgnore
	public int getIndex(TreeNode node) {
		return children.indexOf(node);
	}

	/**
	 * Override of TreeNode method getParent()
	 */
	@Override
	@JsonIgnore
	public TreeNode getParent() {
		return parentModel;
	}
	

	/**
	 * Method that returns parent package of this table
	 * @return Parent package.
	 */
	@JsonIgnore
	public Package getParentPackage() {
		return parentModel;
	}

	/**
	 * Override of TreeNode method isLeaf()
	 */
	@Override
	@JsonIgnore
	public boolean isLeaf() {
		return false;
	}
	
	/*private void findAndSetParent(){
		Workspace w = Workspace.getInstance();
		ArrayList<Sistem> str = (ArrayList<Sistem>) w.getSchemes();
		for(int i=0; i<str.size(); i++){
			ArrayList<Package> packs = str.get(i).getPackages();
			for(int j=0; j<packs.size(); j++){
				if(packs.get(j).getAllTables().size()>0){
					ArrayList<Table> t = packs.get(j).getAllTables();
					for(int k=0; k<t.size(); k++){
						if(t.get(k).equals(this)){
							this.parentModel = packs.get(j);
							return;
						}
					}
				}
				if(packs.get(j).getPackages().size()>0){
					int retVal = 0;
					goThroughtPacks(packs.get(j), retVal);
					if(retVal>0)
						return;
				}
			}
			
			
		}
	}*/

	/*private void goThroughtPacks(Package package1, int retVal) {
		for(int i=0; i<package1.getPackages().size(); i++){
			if(package1.getPackages().get(i).getAllTables().size()>0){
				ArrayList<Table> t = package1.getPackages().get(i).getAllTables();
				for(int k=0; k<t.size(); k++){
					if(t.get(k).equals(this)){
						this.parentModel =package1.getPackages().get(i);
						retVal++;
					}
				}
			}
			if(package1.getPackages().get(i).getPackages().size()>0){
				goThroughtPacks(package1.getPackages().get(i), retVal);				
			}
		}		
	}*/
	
	/**
	 * Method that returns parent package code of this table
	 * @return Parent package code as String.
	 */
	@JsonIgnore
	public String getStringParent() {
		return parentModel.getNaziv().getCode();
	}
	
	/**
	 * Method that returns parent package of this table
	 * @return Parent package.
	 */
	@JsonIgnore
	public Package getParentModel() {
		return parentModel;
	}
	/**
	 * Method that sets parent package of this table
	 * @param parentModel Desired parent package.
	 */
	@JsonIgnore
	public void setParentModel(Package parentModel) {
		this.parentModel = parentModel;
		for(Atribut a : polja){
			a.setParentModel(this);
		}
	}

	/**
	 * Method that makes copy of this table.
	 * @return Returns the copy.
	 */
	public Table clone(){
		
		Table t = new Table();
		t.setNaziv(this.naziv);
		t.setPolja(this.polja);
		t.setKeys(this.keys);
		t.setfKeys(this.fKeys);
		t.setConstraint(this.constraint);
		t.setpKey(this.pKey);
		t.addObserver(Tree.getInstance());
		return t;
	}
	/**
	 * Method that checks if value n can be or can not be used, if an atribut is described as unique
	 * @param a - atribut whose values should be unique
	 * @param n - value that need to be checked as unique
	 * @return True - if value n is not unique, False - value n is unique
	 */
	public boolean isValueNotUnique(Atribut a, Object n) {
		if(InfViewModel.getInstance().getDatabaseType().equals(DatabaseType.RELATIONAL))
			return false;
		DataTabela dt = JSONDataSerialize.getDataTable(this);
		for(Data d0 : dt.getTorke()){
			if(d0.getTorka().get(a.getName().getCode()).equals(n))
					return true;
		}
		return false;
	}
	/**
	 * Method that checks if atribute is a part of a key
	 * @param a - Atribut that needs to be checked
	 * @return - True - if atribut is key, False - atribut a is not a key
	 */
	public boolean isKey(Atribut a) {
		for(Key k0 : keys){
			for(String s0 : k0.getAtributs()){
				if(s0==null)
					continue;
				else if(s0.equals(a.getName().getCode()))
					return true;
			}
		}
		return false;
	}
	/**
	 * Method that checks if value n can be or can not be used and is possibly already used because the value can be edited or not
	 * It is called if an atribut is described as unique
	 * @param a - atribut whose values should be unique
	 * @param n - value that need to be checked as unique
	 * @param data - Set of data which contains value this is possibly going to get changed
	 * @return True - if value n is not unique, False - value n is unique
	 */
	public boolean isEditedValueNotUnique(Atribut a, Object n, Data data) {
		if(InfViewModel.getInstance().getDatabaseType().equals(DatabaseType.RELATIONAL))
			return false;
		DataTabela dt = JSONDataSerialize.getDataTable(this);
		for(Data d0 : dt.getTorke()){
			if(d0.getKodTabele().equals(data.getKodTabele()))
				continue;
			if(d0.getTorka().get(a.getName().getCode()).equals(n))
				return true;
		}
		return false;
	}
	/**
	 * Method is called in case that value is a foreign key and has to allow the value to be used
	 * The value has to be contained in parent table
	 * @param a - atribut whose value needs to be checked
	 * @param n - value that could be written in dataBase, if it is contained in parent table
	 * @return True - if value n is not contained in parent table, False - value n is contained in parent table
	 */
	public boolean isNotValidFKey(Atribut a, Object n) {
		if(InfViewModel.getInstance().getDatabaseType().equals(DatabaseType.RELATIONAL))
			return false;
		for(FKey fk0 : fKeys){
			for(int i=0; i<fk0.getHomeIds().size(); i++){
				String ss = fk0.getHomeIds().get(i);
				if(ss.equals(a.getName().getCode())){
					String code=fk0.getConnectedTable();
					Table t0 = Sistem.getInstance().getTableByCodeName(code);
					if(t0==null)
						return true;
					DataTabela dt = JSONDataSerialize.getDataTable(t0);
					for(Data d0 : dt.getTorke()){
						if(d0.getTorka().get(fk0.getForeignIds().get(i)).equals(n))
							return false;
					}
				}
			}
		}
		return true;
	}


}
