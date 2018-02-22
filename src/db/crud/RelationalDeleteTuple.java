package db.crud;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import db.connection.DBConnection;
import db.model.Torka;
import db.model.Value;
import editorSeme.controller.command.DeleteCommand;
/**
 * This class deletes a tuple from an external relational database.
 *
 */
public class RelationalDeleteTuple extends DeleteCommand {

	private ArrayList<String> pkey;
	/**
	 * Constructor for the class. Sets the tuple to the child object and the code of the table to the parent object.
	 * @param t Tuple that should be deleted. 
	 * @param tab Code (name) of the table from which the tuple should be deleted.
	 * @param pkey Codes (names) of all of the primary keys in the table.
	 */
	public RelationalDeleteTuple(Torka t, String tab, ArrayList<String> pkey) {
		// TODO Auto-generated constructor stub
		this.pkey=pkey;
		this.parent=tab;
		this.child=t;
	}
	/**
	 * This method tries to delete the the tuple from the table. It uses the tuple and table set in the constructor. 
	 */
	@Override
	public boolean doCommand() {
		Torka t = (Torka) child;
		String tab = (String) parent;
			
		StringBuilder sb = new StringBuilder();
		sb.append( "DELETE FROM "+ tab + " WHERE ");
		
		for (String string : pkey) {
			sb.append(" " + string+"=? AND");
		}		
		 
		sb.setCharAt(sb.length()-1, ' ');
		sb.setCharAt(sb.length()-2, ' ');
		sb.setCharAt(sb.length()-3, ' ');
	
		
		try {
			PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sb.toString());
			int number =1;
			
			for (String string : pkey) {
				Value v = getPkey(string);
				if(v==null)
					return false;
				
				PreparedDomain.setPrepared(statement, v, number);
				number++;
			}

			int rowsDeleted = statement.executeUpdate();
			if (rowsDeleted > 0) {
				System.out.println("Odradio neki delete");
				return true;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}
	
	/**
	 * Returns Primary Key of Tuple.
	 * @param s contains code of Primary Key
	 * @return Primary Key.
	 */
	private Value getPkey(String s){
		Torka t = (Torka) child;
		
		for (Value v : t.getVrednosti()) {
			if(s.equals(v.getCode()))
				return v;
		}
		
		return null;
	}
	
	/**
	 * This method tries to add the the tuple to a table. It uses the tuple and table set in the constructor. It tries to reverse the effects doCommand method.
	 */
	@Override
	public boolean undoCommand() {
		Torka t = (Torka) child;
		String tab = (String) parent;
		RelationalCreateTuple rct = new RelationalCreateTuple(t, tab);
		return rct.doCommand();
		
	}

}
