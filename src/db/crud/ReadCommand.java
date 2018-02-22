package db.crud;

import db.model.Torka;
import db.model.Value;

/**
 * 
 * This interface should be implemented by every object that does any type of read that Read command which retrieves existing entries
 *
 */
public interface ReadCommand {
	/**
	 * Retrieves a single Tuple.
	 * @param s Code of the table where the tuple should be found.
	 * @param t The tuple that should be read.
	 * @return The tuple if the query was successful and null if a tuple with the inputed parameters was not found.
	 */
	public Object readTuple(String s, Torka t);
	/**
	 * Retrieves a single Value.
	 * @param s Code of the table where the tuple should be found.
	 * @param v The value that should be read.
	 * @param t The tuple in which the value should be found.
	 * @return The value if the query was successful and null if a value with the inputed parameters was not found.
	 */
	public Object readValue(String s, Value v, Torka t);
	
	/**
	 * Retrieves a single Table.
	 * @param s Code of the table that should be read.
	 * @return All of the tuples in the table or null if the query was not successful. 
	 */
	public Object readTable(String s);
}
