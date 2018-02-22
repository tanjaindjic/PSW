package db.search;

import java.util.ArrayList;

/**
 * 
 * Defines all the types of search that must be implemented. 
 *
 */
public interface Search {

	/**
	 * Searches the the database for for entries that have a numeric attribute between the two sent values.
	 * @param atributeName The attribute on the basis of which the search should be done.
	 * @param from The minimal value.
	 * @param to The maximal value.
	 * @return The result set of the search.
	 */
	public Object NumericSearchRange(String atributeName, float from, float to);
	/**
	 * Searches the the database for for entries that have a numeric attribute with a value that is less then the given value.
	 * @param atributeName The attribute on the basis of which the search should be done.
	 * @param to The maximal value.
	 * @return The result set of the search.
	 */
	public Object NumericSearchMax(String atributeName, float to);
	/**
	 * Searches the the database for for entries that have a numeric attribute with a value that is grater then the given value.
	 * @param atributeName The attribute on the basis of which the search should be done.
	 * @param from The minimal value.
	 * @return The result set of the search.
	 */
	public Object NumericSearchMin(String atributeName, float from);
	/**
	 * Searches the the database for for entries that have a boolean attribute with a value that is equal to the given value.
	 * @param atributeName The attribute on the basis of which the search should be done.
	 * @param b The value of the boolean attribute.
	 * @return The result set of the search.
	 */
	public Object BooleanSearch(String atributeName, Boolean b);
	/**
	 * Searches the the database for for entries that have a string(char, varchar) attribute that contains the given value.
	 * @param atributeName The attribute on the basis of which the search should be done.
	 * @param atributeValue The value which should be in contained.
	 * @return The result set of the search.
	 */
	public Object StringSearch(String atributeName, String atributeValue);
	
	/**
	 * Searching by multiple attributes at once.
	 * @param atributteName List of all attribute names.
	 * @param attributeValue List of all values.
	 * @param type The type of search that should be executed.
	 * @return Result set of distinct tuples in the given table.
	 */
	public Object MultiObjectSearch(ArrayList<String> atributteName, ArrayList<Object> attributeValue, ArrayList<SearchType> type);
}
