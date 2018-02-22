package db.crud;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import db.connection.DBConnection;
import db.model.Torka;
import db.model.Value;
import editorSeme.controller.command.UpdateCommand;
/**
 * This class updates a tuple to new values in an external relational database.
 *
 */
public class RelationalUpdateTuple extends UpdateCommand {

	private ArrayList<String> pkey;
	
	/**
	 * Constructor for the class. Sets the tuple to the currentState object and the code of the table to the parent object.
	 * @param t Tuple that should be updated.
	 * @param tab Code (name) of the table from which the tuple should be updated.
	 * @param pkey Codes (names) of all of the primary keys in the table.
	 */
	public RelationalUpdateTuple(Torka t, String tab, ArrayList<String> pkey) {
	
		currentState = t;
		parent = tab;
		this.pkey = pkey;
		
	}
	/**
	 * This method tries to update the the tuple to the new values. It uses the tuple and table set in the constructor, using the tuples primary key values for the search. 
	 */
	@Override
	public boolean doCommand() {
		Torka t = (Torka) currentState;
		String tab = (String) parent;
			
		StringBuilder sb = new StringBuilder();
		sb.append( "UPDATE "+ tab + " SET");
		
		
		
		if(t==null)
			return false;
		for (Value v : t.getVrednosti()) {  
			boolean b = true;
			for (String string : pkey) {
				if(string.equals(v.getCode())){
					b=false;
					break;
				}
			}
			if(b){
				sb.append(" "+v.getCode()+"=?,");
		
			}
		}
		sb.setCharAt(sb.length()-1, ' ');
	
		sb.append("WHERE");
		
		for (String string : pkey) {
			sb.append(" "+string+ "=? AND");			
		}
		sb.setCharAt(sb.length()-1, ' ');
		sb.setCharAt(sb.length()-2, ' ');
		sb.setCharAt(sb.length()-3, ' ');
	
		 
		try {
			PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sb.toString());
			int	number =1;
			for (Value v : t.getVrednosti()) {
				if(!isContained(v.getCode())){
					PreparedDomain.setPrepared(statement, v, number);
					number++;
				}
			}
			
			for (String string : pkey) {
				Value v = getPkey(string);
				if(v==null)
					return false;
				
				PreparedDomain.setPrepared(statement, v, number);
				number++;
			}

			System.out.println(sb.toString());
			System.out.println(statement.toString());
			
			int rowsUpdated = statement.executeUpdate();
			if (rowsUpdated > 0) {
				System.out.println("Odradio neki update");
				return true;
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			//System.out.println("klasa relationUpdateTuple - nije izvrsen update - linija 85");
			JOptionPane.showMessageDialog(null, "Update failed. Probably, some value is not valid.");
		}

		return false;
	}
	/**
	 * Returns Primary Key of Tuple.
	 * @param s contains code of Primary Key
	 * @return Primary Key.
	 */
	private Value getPkey(String s){
		Torka t = (Torka) currentState;
		
		for (Value v : t.getVrednosti()) {
			if(s.equals(v.getCode()))
				return v;
		}
		
		return null;
	}
	/**
	 * Checks if String is contained in Primary Keys.
	 * @param s is String that needs to be checked.
	 * @return if sent String is contained in Primary Keys.
	 */
	private boolean isContained(String s){
		
		for (String string : pkey) {
			if(string.equals(s))
				return true;
		}
		
		return false;
	}

	/**
	 * Unimplemented
	 */
	@Override
	public boolean undoCommand() {
		// TODO Auto-generated method stub
		return false;
	}

	


}
