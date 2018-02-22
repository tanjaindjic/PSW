package editorSeme.controller.builders;

import java.util.ArrayList;

import editorSeme.model.enums.PackageType;
import editorSeme.model.pojo.Package;
import editorSeme.model.pojo.Table;

/**
 * Builder interface for creating packages.
 *
 */
public interface PackageBuilder extends Builder{
	/**
	 * Method for defining child packages.
	 * @param pkgs List of packages that
	 */
	public void buildPackages(ArrayList<Package> pkgs);
	/**
	 * Method for defining child tables.
	 * @param tables List of tables that
	 */
	public void buildTabels(ArrayList<Table> tables);
	/**
	 * Method for adding single child table.
	 * @param table New table.
	 */
	public void buildNewTable(Table table);
	/**
	 * Method for adding single child package.
	 * @param pkg New package.
	 */
	public void buildNewPackage(Package pkg);
	/**
	 * Method for defining package type.
	 * @param pt Enumeration value from PackageType enum.
	 */
	public void buildType(PackageType pt);
	/**
	 * Method that removes all currently added tables.
	 */
	public void unbuildTables();
	/**
	 * Method that removes all currently added packages.
	 */
	public void unbuildPackages();
	/**
	 * Get object that is being built.
	 * @return Built atribute object.
	 */
	public Package getPackage();
}
