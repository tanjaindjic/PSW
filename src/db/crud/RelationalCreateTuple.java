package db.crud;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.connection.DBConnection;
import db.model.Torka;
import db.model.Value;
import editorSeme.controller.command.AddCommand;
import editorSeme.model.additional.JSONSerialize;
import editorSeme.model.pojo.Sistem;

/**
 * 
 * This class adds a tuple to an external relational database.
 *
 */
public class RelationalCreateTuple extends AddCommand {

	
	/**
	 * Constructor for the class. Sets the tuple to the child object and the code of the table to the parent object.
	 * @param t Tuple that should be added. 
	 * @param tab Code (name) of the table in which the tuple should be added.
	 */
	public RelationalCreateTuple(Torka t, String tab) {
		child = t;
		parent = tab;
		
	}
	
	/**
	 * This method tries to add the the tuple to a table. It uses the tuple and table set in the constructor. 
	 */
	@Override
	public boolean doCommand() {
		Torka t = (Torka) child;
		String tab = (String) parent;
		StringBuilder sb = new StringBuilder();
		sb.append( "INSERT INTO "+ tab + "(");
		
		int number = 0;
		
		if(t==null)
			return false;
		for (Value v : t.getVrednosti()) {
			sb.append(v.getCode()+",");
			number++;
		}
		sb.setCharAt(sb.length()-1, ')');
		sb.append("VALUES (");
		for (int i = 0; i < number; i++) {
			sb.append("?,");
		}
		sb.setCharAt(sb.length()-1, ')');
		
		 
		try {
			PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sb.toString());
			number =1;
			for (Value v : t.getVrednosti()) {
				PreparedDomain.setPrepared(statement, v, number);
				number++;
			}
			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				System.out.println("Odradio neki insert");
			}
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//JSONSerialize.saveStructure(Sistem.getInstance());
		return false;
	}
	
	
	/**
	 * Unimplemented 
	 */
	@Override
	public boolean undoCommand() {
		Torka t = (Torka) child;
		String tab = (String) parent;
		//RelationalDeleteTuple rdt =RelationalDeleteTuple(t, tab);
		return false;
	}



}
