package jsonDataBase.search;

import java.util.ArrayList;

import db.search.Search;
import db.search.SearchType;
/**
 * 
 * Search class for the JSON database.
 *
 */
public class JsonSearch implements Search {

	private String tab;
	/**
	 * Constructor for the JSON database.
	 * @param tableName Name of the table where the search should happen.
	 */
	public JsonSearch(String tableName) {
		this.tab = tableName;
	}
	
	
	@Override
	public Object NumericSearchRange(String atributeName, float from, float to) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object NumericSearchMax(String atributeName, float to) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object NumericSearchMin(String atributeName, float from) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object BooleanSearch(String atributeName, Boolean b) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object StringSearch(String atributeName, String atributeValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object MultiObjectSearch(ArrayList<String> atributteName, ArrayList<Object> attributeValue,
			ArrayList<SearchType> type) {
		// TODO Auto-generated method stub
		
		return null;
	}

}
