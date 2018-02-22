package editorSeme.controller.concreetBuilders;

import java.util.ArrayList;
import java.util.Observer;

import editorSeme.controller.builders.Builder;
import editorSeme.controller.builders.PackageBuilder;
import editorSeme.model.enums.PackageType;
import editorSeme.model.pojo.NameTranslate;
import editorSeme.model.pojo.Package;
import editorSeme.model.pojo.Table;
import editorSeme.model.pojo.Translation;

public class DefaultPackageBuilder extends DefaultBuilder implements PackageBuilder{

	private Package newPackage;
	
	/**
	 * Constructor that initializes parent of new object.
	 * @param parent New parent object. Can be either Package or Sistem.
	 */
	public DefaultPackageBuilder(Object parent) {
		this.newPackage = new Package(parent, null);
	}

	/**
	 * Method that creates code name for object.
	 */
	@Override
	public void BuildName(String code) {
		//NameTranslate ime =  new NameTranslate(code);
		newPackage.getNaziv().setCode(code);	
		//System.out.println(newPackage.getNaziv());
	}

	/**
	 * Method that adds new translations for object.
	 */
	@Override
	public void BuildTranslate(ArrayList<Translation> trs) {
		newPackage.getNaziv().setTranslate(trs); 
		
	}

	/**
	 * Method that adds single new translation for object.
	 */
	@Override
	public void BuildNewTranslate(String tr, String lang) {
		System.out.println(newPackage.getNaziv().getTranslate().add(new Translation(tr, lang)));
		//System.out.println(newPackage.getNaziv().getTranslate().size()+"--"+newPackage.getNaziv().getCode()+"--opet proverr pre\n");
		//System.out.println(newPackage.getNaziv().getTranslate().get(0).getLang()+newPackage.getNaziv().getTranslate().get(0).getTr()+"OPET PROVERA");
		/*System.out.println(newPackage.getNaziv());
		System.out.println(newPackage.getNaziv().getTranslate().indexOf(0));*/
	}

	/**
	 * Method that removes all translates from object.
	 */
	@Override
	public void UnBuildTransalte() {
		newPackage.getNaziv().setTranslate(null);
		
	}

	/**
	 * Method for defining child packages.
	 */
	@Override
	public void buildPackages(ArrayList<Package> pkgs) {
		newPackage.setPackages(pkgs);
		
	}

	/**
	 * Method for defining child tables.
	 */
	@Override
	public void buildTabels(ArrayList<Table> tables) {
		newPackage.setTables(tables);
		
	}

	/**
	 * Method for adding single child table.
	 */
	@Override
	public void buildNewTable(Table table) {
		newPackage.getTables().add(table);		
	}

	/**
	 * Method for adding single child package.
	 */
	@Override
	public void buildNewPackage(Package pkg) {
		newPackage.getPackages().add(pkg);
		
	}

	/**
	 * Method that removes all currently added tables.
	 */
	@Override
	public void unbuildTables() {
		newPackage.setTables(null);
		
	}

	/**
	 * Method that removes all currently added packages.
	 */
	@Override
	public void unbuildPackages() {
		newPackage.setPackages(null);
		
	}


	/**
	 * Get object that is being built.
	 * @return Built atribute object.
	 */
	@Override
	public Package getPackage() {
		if(newPackage.validate()) {
		//	System.out.println(newPackage.getNaziv());
		//	System.out.println(newPackage.getNaziv().getTranslate().indexOf(0));
			return newPackage;
		}else {
			return null;
		}
	}


	/**
	 * Method that adds new observes on object.
	 */
	@Override
	public void BuildNewObservers(ArrayList<Observer> obs) {
		for(Observer o:obs) {
			newPackage.addObserver(o);
		}
	}

	/**
	 * Method for defining package type.
	 */
	@Override
	public void buildType(PackageType pt) {
		newPackage.setPackageType(pt);
		
	}


}
