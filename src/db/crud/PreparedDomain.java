package db.crud;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;

import db.model.Value;


/**
 * 
 * Sets up the PreparedDomain statement depending on the type of Object that should be added to the database.
 *
 */
public class PreparedDomain {

/**
 * Adds the values of attributes to the PreparedStatement object which will be used to execute the SQL statement.
 * @param stat The PreparedStatement object which contains a placeholder value.
 * @param v The value of the attribute which should be added.
 * @param number The ordinal number of the placeholder value which should be exchanged with the actual value in v.
 */
public static void setPrepared( PreparedStatement stat, Value v, int number){
		
		switch (v.getTip()) {
		case INT:
			try {
				stat.setInt(number, (int)v.getValue());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case BOOLEAN:
			try {
				
				stat.setBoolean(number, (boolean)v.getValue() );
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			break;
		case BIGINT:
			try {
				stat.setBigDecimal(number, (BigDecimal)v.getValue() );
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case CHAR:
			try {
				stat.setString(number, v.getValue().toString());
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case DATE:
			try {
				stat.setDate(number, (Date)v.getValue() ); //TODO : VIDETI DA LI JE OVO OK 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
			break;
		case DATETIME:
			
			try {
				stat.setTimestamp(number, (Timestamp)v.getValue() ); //TODO : VIDETI DA LI JE OVO OK 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
			break;
		case DOUBLE:
			try {
				stat.setDouble(number, (Double)v.getValue() );  
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
			break;
		case FLOAT:
			try {
				stat.setFloat(number, (Float)v.getValue() );  
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			break;
		case NUMERIC:
			try {
				stat.setBigDecimal(number, (BigDecimal)v.getValue() );  // TODO : Videti da li je ovo ok
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case SMALINT:
			
			try {
				stat.setShort(number, (Short)v.getValue() );  
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
			break;
		case TIME:
			try {
				stat.setTime(number, (Time)v.getValue() );  // TODO : Videti da li je ovo ok 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			break;
		case VARCHAR:
			try {
				stat.setString(number, (String)v.getValue() );  
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			break;
		case DECIMAL:
			try {
				stat.setBigDecimal(number, (BigDecimal)v.getValue());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;

		default:
			System.out.println("Domain not supported");
			break;
		}
		
	}
	

}
