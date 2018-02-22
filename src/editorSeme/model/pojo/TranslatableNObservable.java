package editorSeme.model.pojo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Locale;
import java.util.Observable;

import db.connection.DBConnection;

/**
 * Superclass for all translatable and observable entities in this package.
 *
 */
public class TranslatableNObservable extends Observable{
	/**
	 * Name object for trasnalatable classes.
	 */
	protected NameTranslate naziv;
	
	/**
	 * Method that returns NameTranslate class, that represents code and translations.
	 * @return Returns name.
	 */
	public NameTranslate getNaziv() {
		return naziv;
	}
	
	/**
	 * Method that sets NameTranslate field in this class
	 * @param naziv New name for this entity.
	 */
	public void setNaziv(NameTranslate naziv) {
		this.naziv = naziv;
	}
	
	/**
	 * Override of default java method toString.
	 */
	@Override
	public String toString(){	
		for(Translation tt:naziv.getTranslate()) {
			if(tt.getLang().equals(Locale.getDefault().getLanguage()+Locale.getDefault().getCountry())) {
				return tt.getTr();
			}
		}
		return naziv.getCode();
	}
	
	/**
	 * Loads translations for calling object for desired language.
	 * @param lang Desired language for translation.
	 * @return True - successful, False - unsuccessful.
	 */
	public boolean loadLocal(String lang) {
		try {
			Connection con = null;
			con = DBConnection.getInstance().getConnection();
			Statement stmt = null;
			ResultSet rs = null;
			stmt = con.createStatement();
			String statement = "SELECT *\r\n" + 
					"FROM JEZICI\r\n" + 
					"WHERE JEZ_CODE = '"+naziv.getCode()+"'&&JEZ_LANGUAGE='"+lang+"'";
	
			rs = stmt.executeQuery(statement);
			
			while(rs.next()) {
				if(rs.getString("JEZ_LANGUAGE").equals("srRS")) {
					this.naziv.addTranslateSerbian(rs.getString("JEZ_LANGUAGE"),rs.getString("JEZ_TRANSLATION"));
				}else {
					this.naziv.addTranslate(rs.getString("JEZ_LANGUAGE"),rs.getString("JEZ_TRANSLATION"));
				}
				
			}
			
			return true;
			
		}
		catch (Exception e) {
			return false;
		}
	}
	/**
	 * Loads translations for calling object for all languages.
	 * @return True - successful, False - unsuccessful.
	 */
	public boolean loadLocals() {
		try {
			Connection con = null;
			con = DBConnection.getInstance().getConnection();
			Statement stmt = null;
			ResultSet rs = null;
			stmt = con.createStatement();
			String statement = "SELECT *\r\n" + 
					"FROM JEZICI\r\n" + 
					"WHERE JEZ_CODE = '"+naziv.getCode()+"'";
	
			rs = stmt.executeQuery(statement);
			while(rs.next()) {
				if(rs.getString("JEZ_LANGUAGE").equals("srRS")) {
					this.naziv.addTranslateSerbian(rs.getString("JEZ_LANGUAGE"),rs.getString("JEZ_TRANSLATION"));
				}else {
					this.naziv.addTranslate(rs.getString("JEZ_LANGUAGE"),rs.getString("JEZ_TRANSLATION"));
				}
				
			}
			
			return true;
			
		}
		catch (Exception e) {
			return false;
		}
	}
}
