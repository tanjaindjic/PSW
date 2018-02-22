package editorSeme.model.parse;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import db.connection.DBConnection;
import editorSeme.model.enums.PackageType;
import editorSeme.model.enums.Tip;
import editorSeme.model.pojo.Atribut;
import editorSeme.model.pojo.Domain;
import editorSeme.model.pojo.FKey;
import editorSeme.model.pojo.Id_Id;
import editorSeme.model.pojo.Key;
import editorSeme.model.pojo.NameTranslate;
import editorSeme.model.pojo.Package;
import editorSeme.model.pojo.Sistem;
import editorSeme.model.pojo.Table;
import editorSeme.model.pojo.Translation;

/**
 * Concrete implementation of ParseMetaData interface for metaschemes stored in relational database tables with predefined metastructure.
 *
 */
public class ParseRelationalDB implements ParsemMetadata{

	/**
	 * Method that starts procces of parseing data from predefined DB connection.
	 */
	@Override
	public void parseModel() {
		loadSistem();
		
		
		
	}

	/**
	 * Method that loads system from DB
	 * @return Returns status for wanted operation. True - successful, False - unsuccessful
	 */
	private boolean loadSistem() {
		try {
			Connection con = null;
			con = DBConnection.getInstance().getConnection();
			Statement stmt = null;
			ResultSet rs = null;
			stmt = con.createStatement();
			String statement = "SELECT *\r\n" + 
					"FROM PODSISTEM\r\n" + 
					"WHERE PO_OZNAKA NOT IN (SELECT POD_PO_OZNAKA FROM STRUKTURA_PODSISTEMA) and PO_TIP = 'S'";
	
			rs = stmt.executeQuery(statement);
			
			if(rs.getFetchSize()!=1) {
				//System.out.println("Greska, ima vise od 1 sistema");
			}
			rs.next();
			Sistem.getInstance().setPackages(new ArrayList<Package>());
			NameTranslate n = new NameTranslate(rs.getString("PO_OZNAKA"));
			n.getTranslate().add(new Translation(rs.getString("PO_NAZIV"), "srRS"));
			Sistem.getInstance().setNaziv(n);
			rs.close();
			//Sistem.getInstance().loadLocals();
			
			if(loadPackages(Sistem.getInstance(), Sistem.getInstance().getNaziv().getCode())) {
				return true;
			}else {
				return false;
			}
		}
		catch (Exception e) {
			return false;
		}
	}
	/**
	 * Method that package system from DB for desired system
	 * @return Returns status for wanted operation. True - successful, False - unsuccessful
	 */
	private boolean loadPackages(Object po, String parent) {
		Package pp = null;
		Sistem sp = null;
		if(po instanceof Package) {
			pp=(Package)po;
			sp=null;
		}else {
			sp=(Sistem)po;
			pp=null;
		}
		try {
			Connection con = null;
			con = DBConnection.getInstance().getConnection();
			Statement stmt = null;
			ResultSet rs = null;
			stmt = con.createStatement();
			String statement = "SELECT *\r\n"+
					"FROM PODSISTEM\r\n"+
					"WHERE PO_OZNAKA IN (SELECT POD_PO_OZNAKA FROM STRUKTURA_PODSISTEMA WHERE PO_OZNAKA = '"+parent+"')";
	
			//System.out.println(statement);
			rs = stmt.executeQuery(statement);
			while(rs.next()) {
				Package p = new Package();
				NameTranslate n = new NameTranslate(rs.getString("PO_OZNAKA"));
				n.getTranslate().add(new Translation(rs.getString("PO_NAZIV"), "srRS"));
				p.setNaziv(n);
				p.setPackageType(typeConverter(rs.getString("PO_TIP")));	
				if(pp==null) {
					sp.addPackage(p);
					
				}else {
					pp.addPackage(p);
					p.setParentModel(pp);
				}
				
			}
			rs.close();
			
			ArrayList<Package> temp;
			if(pp==null) {
				temp=sp.getPackages();
				sp.loadLocals();
			}else {
				temp=pp.getPackages();
				pp.loadLocals();
			}
			for(Package p:temp) {
				loadPackages(p, p.getNaziv().getCode());
				loadTables(p, p.getNaziv().getCode());
			}
			
			
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * Method that loads tables from DB for desired package
	 * @return Returns status for wanted operation. True - successful, False - unsuccessful
	 */
	private boolean loadTables(Package pp, String parent) {
		try {
			Connection con = null;
			con = DBConnection.getInstance().getConnection();
			Statement stmt = null;
			ResultSet rs = null;
			stmt = con.createStatement();
			String statement = "SELECT *\r\n"+
					"FROM TABELE\r\n"+
					"WHERE PO_OZNAKA = '"+parent+"'";
	
			rs = stmt.executeQuery(statement);
			while(rs.next()) {
				Table t = new Table();
				NameTranslate n = new NameTranslate(rs.getString("TAB_KOD"));
				n.getTranslate().add(new Translation(rs.getString("TAB_NAZIV"), "srRS"));
				t.setNaziv(n);
				t.setParentModel(pp);
				t.setpKey(0);
				//pp.getTables().add(t);
				pp.addTable(t);
				
			}
			rs.close();
			
			for(Table t:pp.getTables()) {
				loadAtributes(t);
				loadFKs(t);
			}
			
			pp.loadLocals();
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * Method that loads atributes from DB for desired table.
	 * @return Returns status for wanted operation. True - successful, False - unsuccessful
	 */
	private boolean loadAtributes(Table t) {
		try {
			Connection con = null;
			con = DBConnection.getInstance().getConnection();
			Statement stmt = null;
			ResultSet rs = null;
			stmt = con.createStatement();
			String statement = "SELECT *\r\n"+
					"FROM ATRIBUTI\r\n"+
					"WHERE PO_OZNAKA = '"+t.getParentModel().getNaziv().getCode()+"' AND TAB_KOD='"+t.getNaziv().getCode()+"'";
	
			rs = stmt.executeQuery(statement);
			//System.out.println(statement);
			Key k = new Key();
			
			while(rs.next()) {
				Domain d = new Domain(rs.getInt("ATR_DUZINA"), atrTypeConverter(rs.getString("ATR_TIP")));
				NameTranslate n = new NameTranslate(rs.getString("ATR_KOD"));
				n.getTranslate().add(new Translation(rs.getString("ATR_LABELA"), "srRS"));
				Atribut a = new Atribut(n, d, !rs.getBoolean("ATR_OBAVEZNO"), rs.getBoolean("ATR_DEO_PK"), t);
				if(a.isUnique()) {
					Id_Id i = new Id_Id(t.getNaziv().getCode(), a.getName().getCode());
					k.getIds().add(i);
				}
				//System.out.println("Dodajem: "+a.getName().getCode()+" u:"+t.getNaziv().getCode());
				a.loadLocals();
				t.getPolja().add(a);
			}
			rs.close();
			
			if(k.getIds().size()==0) {
				System.out.println("PK NEDEFINISAN");
			}else {
				t.addKey(k);
			}
			
			t.loadLocals();
			
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * Method that loads foreigh keys from DB for desired table
	 * @return Returns status for wanted operation. True - successful, False - unsuccessful
	 */
	private boolean loadFKs(Table t) {
		try {
			Connection con = null;
			con = DBConnection.getInstance().getConnection();
			Statement stmt = null;
			ResultSet rs = null;
			stmt = con.createStatement();
			String statement = "SELECT *\r\n"+
					"FROM STRANI_KLJUC\r\n"+
					"WHERE TAB_PO_OZNAKA = '"+t.getParentModel().getNaziv().getCode()+"' AND TAB_TAB_KOD='"+t.getNaziv().getCode()+"'";
	
			rs = stmt.executeQuery(statement);
			while(rs.next()) {
				FKey f = new FKey();
				f.setConnectedTable(rs.getString("TAB_KOD"));
				f.setCode(rs.getString("SK_KOD"));
				t.getfKeys().add(f);
			}
			rs.close();
			
			for(FKey f:t.getfKeys()) {
				loadHomeAndForeignIds(f, t);
			}
			
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * Method that loads foreign key data from DB for desired foreign key
	 * @return Returns status for wanted operation. True - successful, False - unsuccessful
	 */
	private boolean loadHomeAndForeignIds(FKey f, Table t) {
		try {
			Connection con = null;
			con = DBConnection.getInstance().getConnection();
			Statement stmt = null;
			ResultSet rs = null;
			stmt = con.createStatement();
			String statement = "SELECT *\r\n"+
					"FROM KOLONE_U_STRANOM_KLJUCU\r\n"+
					"WHERE ATR_PO_OZNAKA2 = '"+t.getParentModel().getNaziv().getCode()+"' AND ATR_TAB_KOD='"+t.getNaziv().getCode()+"'"+" AND SK_KOD='"+f.getCode()+"'"+" AND TAB_KOD='"+f.getConnectedTable()+"'";
	
			
		//	System.out.println(statement);
			rs = stmt.executeQuery(statement);
			while(rs.next()) {
				f.getHomeIds().add(rs.getString("ATR_KOD"));
				f.getForeignIds().add(rs.getString("ATR_ATR_KOD"));
			}
			rs.close();
			
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * Method that converts String to enum Tip.
	 * @return Returns Tip enumeration.
	 */
	private Tip atrTypeConverter(String s) {
		if(s.equals("bigint")) {
			return Tip.BIGINT;
		}
		else if(s.equals("char")) {
			return Tip.CHAR;
		}
		else if(s.equals("datetime")) {
			return Tip.DATETIME;
		}
		else if(s.equals("int")) {
			return Tip.INT;
		}
		else if(s.equals("numeric")) {
			return Tip.NUMERIC;
		}
		else if(s.equals("varchar")) {
			return Tip.VARCHAR;
		}
		else if(s.equals("boolean")) {
			return Tip.BOOLEAN;
		}
		else if(s.equals("float")) {
			return Tip.FLOAT;
		}
		else if(s.equals("decimal")) {
			return Tip.DECIMAL;
		}
		else {
			return null;
		}
	}
	
	/**
	 * Method that converts String to enum PackageType.
	 * @return Returns PackageType enumeration.
	 */
	private PackageType typeConverter(String s) {
		if(s.equals("P")) {
			return PackageType.SUBSYSTEM;
		}else {
			return PackageType.PACKAGE;
		}
	}
	
	
	
}
