package db.crud;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.connection.DBConnection;
import db.model.Torka;
import db.model.Value;
/**
 * Does a read from Relational Database.
 */
public class RelationalRead implements ReadCommand {

	
	/**
	 * @deprecated Reads from relational database
	 */
	@Override
	public Object readTable(String s) {
		String sql = "SELECT * FROM " + s;
		Statement statement = null;
		ResultSet result = null;
		try {
			statement = DBConnection.getInstance().getConnection().createStatement();
			result = statement.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Nije uspeo zahtev");
		}
		
		/*
		 * 
		 *  Vector<Vector<Object>> data = new Vector<Vector<Object>>();
    		while (rs.next()) {
        Vector<Object> vector = new Vector<Object>();
        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
            vector.add(rs.getObject(columnIndex));
        }
        data.add(vector);
    }
		 * 
		 */

		return result;
	}

	/**
	 * @deprecated There is a RelationalSearch class that does this feature.
	 */
	@Override
	public Object readTuple(String s, Torka t) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @deprecated There is a RelationalSearch class that does this feature.
	 */
	@Override
	public Object readValue(String s, Value v, Torka t) {
		// TODO Auto-generated method stub
		return null;
	}

}
