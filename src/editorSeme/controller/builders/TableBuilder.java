package editorSeme.controller.builders;

import java.util.ArrayList;
import java.util.List;

import editorSeme.model.pojo.Atribut;
import editorSeme.model.pojo.ConstraintOperation;
import editorSeme.model.pojo.ContraintTorka;
import editorSeme.model.pojo.FKey;
import editorSeme.model.pojo.Id_Id;
import editorSeme.model.pojo.Key;
import editorSeme.model.pojo.Table;

/**
 * Builder interface for tables.
 *
 */
public interface TableBuilder extends Builder{
	
	/**
	 * Method that adds atributes into table that is being created.
	 * @param atrs List of new atributes.
	 */
	public void buildAtributes(ArrayList<Atribut> atrs);
	/**
	 * Method that adds foreign keys into table that is being created.
	 * @param fks List of new foreign keys
	 */
	public void buildFKeys(ArrayList<FKey> fks);
	/**
	 * Method that adds keys into table that is being created.
	 * @param ks List of new keys
	 */
	public void buildKeys(ArrayList<Key> ks);
	
	// Ne treba 
	//public void buildContraints(ArrayList<ContraintTorka> conss);
	/**
	 * Method that adds atribute into table that is being created.
	 * @param atr List of new atribute.
	 */
	public void buildNewAtribute(Atribut atr);
	/**
	 * Method that adds foreign key into table that is being created.
	 * @param conected Table to whom is this foreign key connecting.
	 * @param homeIds Atributes from starting table.
	 * @param foreignIds  Atributes from table to whom is this foreign key connecting.
	 */
	public void buildNewFKey(String conected, ArrayList<String> homeIds, ArrayList<String> foreignIds);
	/**
	 * Method that adds key into table that is being created.
	 * @param ids list of table-atribute pairs. 
	 */
	public void buildNewKey(ArrayList<Id_Id> ids);
	
	// Ne treba
	//public void buildNewContraint(ArrayList<String> firstId, ArrayList<String> secondId, ConstraintOperation consType);
	/**
	 * Method that removes all currently added atributes.
	 */
	public void unBuildAtributes();
	/**
	 * Method that removes all currently added foreign keys.
	 */
	public void unBuildFKeys();
	/**
	 * Method that removes all currently added keys.
	 */
	public void unBuildKeys();
	//public void unBuildContraints();
	/**
	 * Get object that is being built.
	 * @return Built table object.
	 */
	public Table getTable();
}
