package editorSeme.model.pojo;

import java.awt.Component;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JOptionPane;
import javax.swing.tree.TreeNode;

import com.fasterxml.jackson.annotation.JsonIgnore;

import editorSeme.model.additional.Prototype;
import editorSeme.model.eventEncapsulation.EventType;
import editorSeme.model.eventEncapsulation.PackageEvent;
import editorSeme.model.eventEncapsulation.SistemEvent;
import workingsection.tree.Tree;

import java.util.ResourceBundle;

/**
 * Class representing single system.
 *
 */
public class Sistem extends TranslatableNObservable implements TreeNode, Prototype, Serializable{

	private String path;
	private ArrayList<Package> packages;	
	//private ArrayList<Language> langs;
	//private NameTranslate naziv;
	/*@JsonIgnore
	private Language selectedLang=null;*/
	@JsonIgnore
	private static Sistem sistem = null;
	
	/**
	 * Method for translating codes.
	 * @param s Code u wish to translate.
	 * @return Respective translation on current Locale.
	 */
	public String getTranslate(String s) {
		return ResourceBundle.getBundle("workingsection.localize.translate", Locale.getDefault()).getString(s);
	}
	
	/**
	 * Method that validates current package structure according to predefined rules.
	 * @return True - structure is valid, False - structure is not valid.
	 */
	public boolean validate() {
		if(packages==null||naziv==null||!naziv.validate()) {
			JOptionPane.showMessageDialog(null, Sistem.getInstance().getTranslate("sysValMsg1"));
			return false;
		}
		for(Package p:packages) {
			if(!p.validate()) {
				return false;
			}
		}
		if(!packagesUnique()) {
			JOptionPane.showMessageDialog(null, Sistem.getInstance().getTranslate("pacValMsg2"));
			return false;
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
	 * Constructor with parameters.
	 * @param path Path on disk for system.
	 * @param packages List of starting packages.
	 * @param naziv Name as NameTranslate object.
	 */
	public Sistem(String path, ArrayList<Package> packages, /*ArrayList<Language> langs,*/ NameTranslate naziv/*,*/
			/*Language selectedLang*/) {
		super();
		if(sistem==null){
			sistem.path = path;
			sistem.packages = packages;
			//sistem.langs = langs;
			sistem.naziv = naziv;
			//sistem.selectedLang = selectedLang;
		}
	}

	/**
	 * Get single instance of singlton.
	 * @return Sistem object.
	 */
	public static Sistem getInstance(){
		if(sistem==null){
			sistem = new Sistem();
		}
		return sistem;
	}

	/*public Language getSelectedLang() {
		return selectedLang;
	}

	public void setSelectedLang(Language selectedLang) {
		this.selectedLang = selectedLang;
	}*/

	/*public ArrayList<Language> getLang() {
		return langs;
	}*/

	/*public void setLang(ArrayList<Language> lang) {
		this.langs = lang;
	}*/
	
	/*public void addLang(Language l){
		if(l!=null)
			langs.add(l);
	}*/

	/**
	 * Constructor without parameters. Initializes lists and sets english as default Locale.
	 */
	private Sistem() {
		//this.path = "C:\\Users\\ilija\\Desktop\\testJSON\\sis.json";
		this.packages = new ArrayList<Package>();
		//this.selectedLang = null;
		//this.langs = new ArrayList<Language>();
		this.naziv = new NameTranslate();
		Locale.setDefault(new Locale("en", "US"));
	}

	/**
	 * Constructor with parameters.
	 * @param path Path on disk for system.
	 * @param obs List of observers to be added.
	 */
	private Sistem(String path, ArrayList<Observer> obs) {
		this.path = path;
		this.packages = new ArrayList<Package>();
		//this.langs = new ArrayList<Language>();
		this.naziv = null;
		//this.selectedLang=null;
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
		for(Package p:packages) {
			p.reloadObservers(obs);
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
	}

	/**
	 * Method that generates list of all tables insite this packages and its subpackages.
	 * @return List of relevant tables.
	 */
	@JsonIgnore
	public ArrayList<Table> getAllTables(){
		ArrayList<Table> retVal = new ArrayList<Table>();
		if(packages!=null) {
			for(Package p:packages) {
				retVal.addAll(p.getAllTables());
			}
		}
		return retVal;
		
	}
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
		
	// Dodati u class
	/**
	 * Method that adds package inside system.
	 * @param p Package to be added.
	 * @return Success of operation as boolean.
	 */
	public boolean addPackage( Package p ){
		if(!checkIfPackageExists(p.getNaziv().getCode())) {
			JOptionPane.showMessageDialog((Component)null, Sistem.getInstance().getTranslate("PackageEx"));
			return false;
		}
		boolean retVal = packages.add(p);
		if(retVal) {
			PackageEvent event = new PackageEvent(this, p, EventType.ADD);
			setChanged();
			notifyObservers(event);
		}
		return retVal;
	}
	
	private boolean checkIfPackageExists(String code) {
		for(Package temp:getAllPackages()) {
			//System.out.println("yo"+code+temp.getNaziv().getCode());
			if(temp.getNaziv().getCode().equals(code)) {
				return false;
			}
		}
		return true;
	}
	// Dodati u class	
	/**
	 * Method that removes package inside system.
	 * @param p Package to be removed.
	 * @return Success of operation as boolean.
	 */
	public boolean removePackage( Package p ){
		boolean retVal = packages.remove(p);
		if(retVal) {
			PackageEvent event = new PackageEvent(this, p, EventType.DELETE);
			setChanged();
			notifyObservers(event);
		}
		return retVal;
	}
	//Search u class
	/**
	 * Method that finds package inside system.
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
	
	/**
	 * Method that gets path of this system.
	 * @return Path as String.
	 */
	@JsonIgnore
	public String getPath() {
		return path;
	}

	/**
	 * Method that sets path of this system.
	 * @param path Path as String.
	 */
	public void setPath(String path) {
		this.path = path;
		SistemEvent event = new SistemEvent(this, EventType.UPDATE);
		setChanged();
		notifyObservers(event);
	}
		
	/**
	 * Returns all direct package children of this system.
	 * @return List of packages.
	 */
	public ArrayList<Package> getPackages() {
		return packages;
	}
	
	/**
	 * Sets all direct package children of this system.
	 * @param packages List of packages.
	 */
	public void setPackages(ArrayList<Package> packages) {
		this.packages = packages;
	}

	//tree methods
	/**
	 * Override of TreeNode method children()
	 */
	@Override
	@JsonIgnore
	public Enumeration children() {
		return (Enumeration<Package>)packages;
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
		return (TreeNode) packages.get(childIndex);
	}

	/**
	 * Override of TreeNode method getChildCount()
	 */
	@Override
	@JsonIgnore
	public int getChildCount() {
		return packages.size();
	}

	/**
	 * Override of TreeNode method getIndex(TreeNode node)
	 */
	@Override
	@JsonIgnore
	public int getIndex(TreeNode node) {
		return packages.indexOf(node);
	}

	/**
	 * Override of TreeNode method getParent()
	 */
	@Override
	@JsonIgnore
	public TreeNode getParent() {
		return null;
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
	 * Method that makes copy of this system.
	 * @return Returns the copy.
	 */
	public Sistem clone(){
		
		Sistem s = new Sistem();
		s.setPackages(this.packages);
		//s.setLang(this.langs);
		//s.setSelectedLang(this.selectedLang);
		return s;
	}
	/**
	 * sets atributes of System that is read form JSON file, to the instance of Sistem that is used in program
	 * @param s - Sistem that is loaded
	 */

	public void addSistem(Sistem s) {
		//sistem.setLang(s.getLang());
		sistem.setNaziv(s.getNaziv());
		sistem.setPackages(s.getPackages());
		sistem.setPath(s.getPath());
		//sistem.setSelectedLang(s.getSelectedLang());
		//addParentsToPackages(sistem);
		reloadChildren();
		//for(Package p : sistem.getPackages())
		//	addParentsToTables(p);
	}


	/*private void addParentsToTables(Object parent) {
		for(Package p:packages){
			if(p.getPackages().size()>0){
				for(Package p1:p.getPackages()){
					addParentsToTables(p);
				}
			}
			for(Table t:p.getTables()){
				t.setParentModel((Package) parent);
			}
		}
	}


	private void addParentsToPackages(Object parent) {
		
		for(Package p:packages){
			p.setParentModel(parent);
			if(p.getPackages().size()>0)
				for(Package p1: p.getPackages())
					addParentsToPackages(p);
			
		}
	}*/
	/**
	 * Method that finds the table that is presented in the using the name of the tab
	 * @param tabName - name of the tab that contains table
	 * @return table that is presented by the tab whose name is sent
	 */

	public Table getTableByTabName(String tabName) {
		ArrayList<Table> t = getAllTables();
		for(Table t1 : t){
			if(tabName.equals(t1.toString())){
				return t1;
			}
		}
		return null;
	}

	/**
	 * Method that finds table by its code and returns it
	 * @param code - code of the table that neddes to be found and returned
	 * @return table whose code is sent as param
	 */
	public Table getTableByCodeName(String code) {
		ArrayList<Table> t = getAllTables();
		for(Table t1 : t){
			if(code.equals(t1.getNaziv().getCode())){
				return t1;
			}
		}
		return null;
	}

	public void destroy() {
		sistem = null;		
		Tree.getInstance().setModel(null);
	}
	
}
