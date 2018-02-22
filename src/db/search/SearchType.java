package db.search;

/**
 * Types of possible searches.
 * Min - The value has to be a grater value than the sent value.
 * Max - The value has to be a lesser value than the sent value.
 * Boolean - Searching for a true, false, null boolean state.
 * String - Searching if for a substring.
 */
public enum SearchType {

	MIN,MAX,BOOLEAN,STRING
}
