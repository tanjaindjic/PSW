package db.model;

import java.util.ArrayList;
/**
 * 
 * This class represents a model for relational database tables.
 * It contains a list of database entries (tuples).
 *
 */
public class TableModel {
	
	private ArrayList<Torka> torke;

	
	/**
	 * Empty constructor. Constructs the TableModel with an empty list of entries(tuples).
	 */
	public TableModel(){
		torke = new ArrayList<Torka>();
	}
	/**
	 * Constructor for the TableModel class which takes in an already filed list of table entries(tuples).
	 * @param table A list of database entries.
	 * 
	 */
	public TableModel( ArrayList<Torka> table){
		torke = table;
	}
	
	/**
	 * Adds a new table entry(tuple) to the TableModel.
	 * @param t A table entry that should be added to the TableModel.
	 */
	public void addTorka(Torka t){
		torke.add(t);
	}
	
}
