package editorSeme.model.pojo;

import java.awt.Component;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JOptionPane;
import javax.swing.tree.TreeNode;

import com.fasterxml.jackson.annotation.JsonIgnore;

import editorSeme.model.additional.Prototype;
import editorSeme.model.enums.PackageType;
import editorSeme.model.eventEncapsulation.EventType;
import editorSeme.model.eventEncapsulation.PackageEvent;
import editorSeme.model.eventEncapsulation.TableEvent;

/**
 * Class representing Packages.
 *
 */
public class Package extends TranslatableNObservable implements TreeNode, Prototype, Serializable{

	private PackageType packageType;
	private ArrayList<Package> packages;
	private ArrayList<Table> tables;
	//private NameTranslate naziv;
	@JsonIgnore
	private Object parentModel;
	@JsonIgnore
	private ArrayList<Object> children;

	/**
	 * Method that adds all packages and indirect subpackages into sam list.
	 * @return List of all packages.
	 */
	@JsonIgnore
	public ArrayList<Package> getAllPackages(){
		ArrayList<Package> retVal = new ArrayList<Package>();
		if(packages!=null) {
			for(Package p:packages) {
				retVal.add(p);
				retVal.addAll(p.getAllPackages());
			}
		}
		return retVal;
		
	}	
	
	/**
	 * Method that validates current package structure according to predefined rules.
	 * @return True - structure is valid, False - structure is not valid.
	 */
	public boolean validate() {
		if(packages==null||tables==null||naziv==null) {
			JOptionPane.showMessageDialog(null, Sistem.getInstance().getTranslate("pacValMsg1"));
			return false;
		}
		for(Package p:packages) {
			if(!p.validate()) {
				//System.out.println("PUKNE KOD PAKETA U PAKETU");
				return false;
			}
		}
		for(Table t:tables) {
			if(!t.validate()) {
				//System.out.println("PUKNE KOD TABELA U PAKETU");
				return false;
			}
		}
		if(!naziv.validate()) {
			//System.out.println("PUKNE KOD NAZIVA U PAKETU");
			return false;
		}else if(!tablesUnique()||!packagesUnique()) {
			JOptionPane.showMessageDialog(null, Sistem.getInstance().getTranslate("pacValMsg2"));
			return false;
		}
		return true;
	}
	
	private boolean tablesUnique() {
		for(int i=0;i<tables.size();i++) {
			for(int j=0;j<tables.size();j++) {
				Table a = tables.get(i);
				Table b = tables.get(j);
				if(a.getNaziv().getCode().equals(b.getNaziv().getCode())&&j!=i) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	private boolean packagesUnique() {		
		for(int i=0;i<packages.size();i++) {
			for(int j=0;j<packages.size();j++) {
				Package a = packages.get(i);
				Package b = packages.get(j);
				if(a.getNaziv().getCode().equals(b.getNaziv().getCode())&&j!=i) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	/**
	 * Method that gets parent system.
	 * @return Sistem object.
	 */
	@JsonIgnore
	public Sistem getParentSistem() {
		if(parentModel instanceof Sistem) {
			return (Sistem)parentModel;
		}else {
			return ((Package)parentModel).getParentSistem();
		}
		
	}

	/**
	 * Method that gets parent object.
	 * @return Sistem or Package object.
	 */
	@JsonIgnore
	public Object getParentModel() {
		return parentModel;
	}

	/**
	 * Constructor without parameters. Initializes lists.
	 */
	public Package() {
		this.packages = new ArrayList<Package>();
		this.tables = new ArrayList<Table>();
		this.naziv = new NameTranslate();
	}

	/**
	 * Constructor with parameters.
	 * @param parent Defines parent object.
	 * @param obs Defines GUI observers.
	 */
	public Package(Object parent, ArrayList<Observer> obs) {
		this.packageType = null;
		this.packages = new ArrayList<Package>();
		this.tables = new ArrayList<Table>();
		this.naziv = new NameTranslate();
		this.parentModel = parent;
		addObservers(obs);
	}
	
	/**
	 * Method that updates observer lists.
	 * @param obs List of new observers to be added.
	 */
	public void addObservers(ArrayList<Observer> obs) {
		if(obs == null)
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
		for(Package p:packages) {
			p.reloadObservers(obs);
		}
		for(Table t:tables) {
			t.reloadObservers(obs);
		}
	}
	
	/**
	 * Method that updates parent-child relations as well as parent-child relations in childs of this object.
	 */
	public void reloadChildren() {
		for(Package p:packages) {
			p.setParentModel(this);
			p.reloadChildren();
		}
		for(Table t:tables) {
			t.setParentModel(this);
		}
	}
	
	/**
	 * Method that generates list of all tables insite this packages and its subpackages.
	 * @return List of relevant tables.
	 */
	@JsonIgnore
	public ArrayList<Table> getAllTables(){
		ArrayList<Table> retVal = new ArrayList<Table>();
		retVal.addAll(tables);
		if(packages!=null) {
			for(Package p:packages) {
				retVal.addAll(p.getAllTables());
			}
		}
		return retVal;
		
	}
	
	
	// Dodati u class, vraca true ako je moguce ubaciti objekat
	private boolean canInsert(PackageType p){
		if(packageType == PackageType.SUBSYSTEM && p != PackageType.SUBSYSTEM)
			return true; 
		if(p == PackageType.PACKAGE)
			return true; 
		return false;
	}
	
	// Dodati u class
	/**
	 * Method that adds package inside this one.
	 * @param p Package to be added.
	 * @return Success of operation as boolean.
	 */
	public boolean addPackage(Package p){
		if(!checkIfPackageExists(p.getNaziv().getCode())) {
			JOptionPane.showMessageDialog((Component)null, Sistem.getInstance().getTranslate("PackageEx"));
			return false;
		}
		if(this.canInsert(p.getPackageType())) {
			boolean retVal = packages.add(p);
			if(retVal) {
				PackageEvent event = new PackageEvent(this, p, EventType.ADD);
				setChanged();
				notifyObservers(event);
			}
			return retVal;
		}
		
		return false;
	}
	
	private boolean checkIfPackageExists(String code) {
		for(Package temp:Sistem.getInstance().getAllPackages()) {
			//System.out.println("yo"+code+temp.getNaziv().getCode());
			if(temp.getNaziv().getCode().equals(code)) {
				return false;
			}
		}
		return true;
	}
	
	// Dodati u class
	/**
	 * Method that removes package inside this one.
	 * @param p Package to be removed.
	 * @return Success of operation as boolean.
	 */
	public boolean removePackage(Package p){
		boolean retVal = packages.remove(p);
		if(retVal) {
			PackageEvent event = new PackageEvent(this, p, EventType.DELETE);
			setChanged();
			notifyObservers(event);
		}
		return retVal;
	}
	
	// Dodati u class
	/**
	 * Method that adds table inside this package.
	 * @param t Table to be added.
	 * @return Success of operation as boolean.
	 */
	public boolean addTable(Table t){
			boolean retVal = tables.add(t);
			if(retVal) {
				for(Table t1:Sistem.getInstance().getAllTables()) {
					t1.children();
					children();
				}
				TableEvent event = new TableEvent(t, this, EventType.ADD);
				setChanged();
				notifyObservers(event);
			}
			return retVal;
	}
		
		
	// Dodati u class
	/**
	 * Method that removes table inside this package.
	 * @param t Table to be removed.
	 * @return Success of operation as boolean.
	 */
	public boolean removeTable(Table t){
			boolean retVal = tables.remove(t);
			if(retVal) {
				TableEvent event = new TableEvent(t, this, EventType.DELETE);
				setChanged();
				notifyObservers(event);
			}
			return false;
	}
	//Search 
	/**
	 * Method that finds package inside this one.
	 * @param p Package to be found.
	 * @return Success of operation as boolean.
	 */
	public Package findPackage(Package p) {
		for(Package pac:packages) {
			if(pac.getNaziv().getCode().equals(p.getNaziv().getCode())) {
				return pac;
			}
		}
		return null;
	}
	//Search
	/**
	 * Method that finds table inside this package.
	 * @param t Table to be found.
	 * @return Success of operation as boolean.
	 */
	public Table findTable(String t) {
		for(Table tabs:tables) {
			if(tabs.getNaziv().getCode().equals(t)) {
				return tabs;
			}
		}
		return null;
	}
	
	/**
	 * Method that sets parent model.
	 * @param parent New parent for this object.
	 */
	public void setParentModel(Object parent) {
		this.parentModel = parent;
	}

	/**
	 * Method that gets package type.
	 * @return PackageType enumeration.
	 */
	public PackageType getPackageType() {
		return packageType;
	}

	/**
	 * Method that sets package type.
	 * @param packageType PackageType enumeration.
	 */
	public void setPackageType(PackageType packageType) {
		this.packageType = packageType;
		if(parentModel instanceof Package) {
			PackageEvent event = new PackageEvent((Package)parentModel, this, EventType.UPDATE);
			setChanged();
			notifyObservers(event);
		}
		else if(parentModel instanceof Sistem) {
			PackageEvent event = new PackageEvent((Sistem)parentModel, this, EventType.UPDATE);
			setChanged();
			notifyObservers(event);
		}
	}
	
	/**
	 * Returns all direct package children of this package.
	 * @return List of packages.
	 */
	public ArrayList<Package> getPackages() {
		return packages;
	}

	/**
	 * Sets all direct package children of this package.
	 * @param packages List of packages.
	 */
	public void setPackages(ArrayList<Package> packages) {
		this.packages = packages;
	}

	/**
	 * Returns all direct table children of this package.
	 * @return List of tables.
	 */
	public ArrayList<Table> getTables() {
		return tables;
	}
	
	/**
	 * Sets all direct table children of this package.
	 * @param tables List of tables.
	 */
	public void setTables(ArrayList<Table> tables) {
		this.tables = tables;
	}

	/**
	 * Method that sets NameTranslate field in this class
	 * @param naziv New name for this package.
	 */
	@Override
	public void setNaziv(NameTranslate naziv) {
		this.naziv = naziv;
		if(parentModel instanceof Package) {
			PackageEvent event = new PackageEvent((Package)parentModel, this, EventType.UPDATE);
			setChanged();
			notifyObservers(event);
		}
		else if(parentModel instanceof Sistem) {
			PackageEvent event = new PackageEvent((Sistem)parentModel, this, EventType.UPDATE);
			setChanged();
			notifyObservers(event);
		}
	}
	/**
	 * Method for geting all children Packages and Tables.
	 * @return List of Tables and Packages as Objects.
	 */
	@JsonIgnore
	public ArrayList<Object> getPackagesAndTables(){
		ArrayList<Object> retVal = new ArrayList<Object>();
		ArrayList<Package> packs = this.getPackages();
		for(int i=0; i<packs.size(); i++)
			if(packs.get(i)!=null)
				retVal.add((Object)packs.get(i));
		ArrayList<Table> t = this.getTables();
		for(int j=0; j<t.size(); j++)
			if(t.get(j)!=null)
				if(checkIfTop(t.get(j)))
					retVal.add((Object)t.get(j));
		children = retVal;
		return retVal;
	}
	
	/**
	 * Method that checks is table is on the top of hiearcy, that is if it dosnt have children.
	 * @param t Table for checking.
	 * @return Boolean value for result. 
	 */
	public boolean checkIfTop(Table t) {
		for(Table tt:tables) {
			if(tt.childrenTest().contains(t)&&!tt.getNaziv().getCode().equals(t.getNaziv().getCode())) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Override of TreeNode method children()
	 */
	@Override
	@JsonIgnore
	public Enumeration children() {
		getPackagesAndTables();
		return Collections.enumeration(children);
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
		return (TreeNode) getPackagesAndTables().get(childIndex);
	}

	/**
	 * Override of TreeNode method getChildCount()
	 */
	@Override
	@JsonIgnore
	public int getChildCount() {
		return getPackagesAndTables().size();
	}

	/**
	 * Override of TreeNode method getIndex(TreeNode node)
	 */
	@Override
	@JsonIgnore
	public int getIndex(TreeNode node) {
		return getPackagesAndTables().indexOf(node);
	}

	/**
	 * Override of TreeNode method getParent()
	 */
	@Override
	@JsonIgnore
	public TreeNode getParent() {
		return (TreeNode) parentModel;
	}

	/**
	 * Override of TreeNode method isLeaf()
	 */
	@Override
	@JsonIgnore
	public boolean isLeaf() {
		return false;
	}

	/**
	 * Method that makes copy of this package.
	 * @return Returns the copy.
	 */
	public Package clone(){
		
		Package p = new Package();
		p.setPackageType(this.packageType);
		p.setPackages(this.packages);
		p.setTables(this.tables);
		p.setNaziv(this.naziv);
		return p;
	}
}
