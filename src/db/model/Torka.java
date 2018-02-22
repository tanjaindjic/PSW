package db.model;

import java.util.ArrayList;
/**
 * 
 * This class represents a model for relational database entry(tuple).
 * It contains a list of values of that entry.
 *
 */
public class Torka {

	private ArrayList<Value> vrednosti;
	
	/**
	 * This method gets the list of values that represent the tuple.
	 * @return All of the values in the tuple.
	 */
	public ArrayList<Value> getVrednosti() {
		return vrednosti;
	}

	/**
	 * This method set the list of values for the entry.
	 * @param torka List of values that represent the tuple.
	 */
	public void setTorka(ArrayList<Value> torka) {
		this.vrednosti = torka;
	}

	/**
	 * Empty constructor. Constructs the Torka with an empty list of values.
	 */
	public Torka() {
		vrednosti = new ArrayList<Value>();
	}
	
	/**
	 * Adds a new value to the list of values.
	 * @param v The value that should be added.
	 * @return Returns true if the operation was successful, otherwise it returns false.
	 */
	public boolean add(Value v){
		
		for (Value value : vrednosti) {
			if(value.getCode().equals(v.getCode()))
				return false;
		}
		
		return vrednosti.add(v);
	
	}
	/**
	 * Edits one value from the list of values.
	 * @param v The value that should be edited.
	 * @return Returns true if the operation was successful, otherwise it returns false.
	 */
	public boolean edit(Value v){
		
		for (Value value : vrednosti) {
			if(value.getCode().equals(v.getCode()))
			{
				value.setValue(v.getValue());
				return true;
			}
		}
		
		return false;
		
	}
	
	
}
