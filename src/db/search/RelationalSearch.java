package db.search;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import db.connection.DBConnection;

/**
 * Implements the Search interface for a relational database.
 */
public class RelationalSearch implements Search {

	private String tableName;
	
	/**
	 * Constructor for the search on a specific table.
	 * @param tableName The table on which the search should be done.
	 */
	public RelationalSearch(String tableName) {
		// TODO Auto-generated constructor stub
		this.tableName=tableName;
		
	}

	/**
	 * Searches the the table in a relational database for distinct values.
	 */
	@Override
	public Object MultiObjectSearch(ArrayList<String> atributteName, ArrayList<Object> attributeValue, ArrayList<SearchType> type){

		StringBuilder sb = new StringBuilder();
		
		sb.append("SELECT DISTINCT *");
		sb.append(System.getProperty("line.separator"));
		sb.append("FROM " + tableName);
		sb.append(System.getProperty("line.separator"));
		sb.append("WHERE ");
		int i =0;
		for (String attN : atributteName) {
			sb.append(attN);
			switchByType(type.get(i), sb, attributeValue.get(i));
			i++;
		}
		sb.setCharAt(sb.length()-1, ' ');
		sb.setCharAt(sb.length()-2, ' ');
		sb.setCharAt(sb.length()-3, ' ');
		sb.setCharAt(sb.length()-4, ' ');
		
		
		ResultSet result = null;
		try {
			Statement statement = DBConnection.getInstance().getConnection().createStatement();
			result = statement.executeQuery(sb.toString());
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Pukao search multi");
		}
		
		return null;
	}
	
	/**
	 * Switch case for Search.
	 * @param st contains type of Search.
	 * @param sb is String Builder for entered String.
	 * @param value is Object containing value that is being searched.
	 */
	private void switchByType(SearchType st, StringBuilder sb, Object value){
		switch (st) {
		case BOOLEAN:
			
			Boolean b = (Boolean)value;
			String val = "";
			if(b==false){
				val="0";
			}else if (b==true){
				val = "1";
			}else{
				val = "NULL";
			}
			sb.append(" = " + val + " AND ");
			
			break;
		case MIN:
			float f = (float) value;
			sb.append(" >= " + f + " AND " ); // Nisam siguran da li ce raditi sa ovim to string
			break;
		case MAX:
			float f2 = (float) value;
			sb.append(" <= " + f2 + " AND " ); // Nisam siguran da li ce raditi sa ovim to string
			break;
		case STRING:
			String s = (String) value;
			sb.append(" LIKE '%" + s +"%'"+ " AND ");
			break;

		default:
			break;
		}
	}
	
	
	/**
	 *  Searches the the table in a relational database for distinct values.
	 */
	@Override
	public Object NumericSearchRange(String atributeName, float from, float to) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("SELECT DISTINCT *");
		sb.append(System.getProperty("line.separator"));
		sb.append("FROM " + tableName);
		sb.append(System.getProperty("line.separator"));
		sb.append("WHERE "+atributeName + " BETWEEN " + from + " AND " + to);
		
		ResultSet result = null;
		try {
			Statement statement = DBConnection.getInstance().getConnection().createStatement();
			result = statement.executeQuery(sb.toString());
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Pukao search numeric");
		}
		
		return null;
	}
	/**
	 *  Searches the the table in a relational database for distinct values.
	 */
	@Override
	public Object BooleanSearch(String atributeName, Boolean b) {
		// TODO Auto-generated method stub
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("SELECT DISTINCT *");
		sb.append(System.getProperty("line.separator"));
		sb.append("FROM " + tableName);
		sb.append(System.getProperty("line.separator"));
		
		String value = "";
		if(b==false){
			value="0";
		}else if (b==true){
			value = "1";
		}else{
			value = "NULL";
		}
		
		
		sb.append("WHERE "+atributeName + " = " + value );
		
		ResultSet result = null;
		try {
			Statement statement = DBConnection.getInstance().getConnection().createStatement();
			result = statement.executeQuery(sb.toString());
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Pukao search boolean");
		}
		
		return null;
	}
	/**
	 *  Searches the the table in a relational database for distinct values.
	 */
	@Override
	public Object StringSearch(String atributeName, String atributeValue) {
		// TODO Auto-generated method stub
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("SELECT DISTINCT *");
		sb.append(System.getProperty("line.separator"));
		sb.append("FROM " + tableName);
		sb.append(System.getProperty("line.separator"));
		sb.append("WHERE "+atributeName + " LIKE '%" + atributeValue +"%'");
		
		ResultSet result = null;
		try {
			Statement statement = DBConnection.getInstance().getConnection().createStatement();
			result = statement.executeQuery(sb.toString());
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Pukao search string");
		}
		
		return null;
	}
	
	/**
	 * Checks if a translation for an attribute for the the given language exists.
	 * @param result The result set that you get after doing a search operation for translations.
	 * @param lang The language you want to check. 
	 * @return Returns true if a translation exists and false otherwise. 
	 */
	public boolean checkLanguage(Object result, String lang){
		
		ResultSet rs = (ResultSet)result;
		boolean b = false;
		if(rs!=null)
		  try {
			while (rs.next()) {
			        String code = rs.getString("JEZ_CODE");
			        String la = rs.getString("JEZ_LANGUAGE");
			        if(la.equals(lang))
			        	b=true;
			    }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return b;
	}
	
	//usUS
	//rsRS
	
	/**
	 *  Searches the the table in a relational database for distinct values.
	 */
	@Override
	public Object NumericSearchMax(String atributeName, float to) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("SELECT DISTINCT *");
		sb.append(System.getProperty("line.separator"));
		sb.append("FROM " + tableName);
		sb.append(System.getProperty("line.separator"));
		sb.append("WHERE "+atributeName + " <= " + to );
		
		ResultSet result = null;
		try {
			Statement statement = DBConnection.getInstance().getConnection().createStatement();
			result = statement.executeQuery(sb.toString());
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Pukao search numeric");
		}
		
		return null;
	}
	/**
	 *  Searches the the table in a relational database for distinct values.
	 */
	@Override
	public Object NumericSearchMin(String atributeName, float from) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("SELECT DISTINCT *");
		sb.append(System.getProperty("line.separator"));
		sb.append("FROM " + tableName);
		sb.append(System.getProperty("line.separator"));
		sb.append("WHERE "+atributeName + " >= " + from );
		
		ResultSet result = null;
		try {
			Statement statement = DBConnection.getInstance().getConnection().createStatement();
			result = statement.executeQuery(sb.toString());
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Pukao search numeric");
		}
		
		return null;
	}
		
	
}
