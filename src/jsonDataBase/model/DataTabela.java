package jsonDataBase.model;

import java.util.ArrayList;
/**
 * Class that is used as Model for saving in json-shaped dataBase 
 *
 */
public class DataTabela {
	private String kodTabele;
	private ArrayList<Data> torke;
	
	
	public String getKodTabele() {
		return kodTabele;
	}
	public void setKodTabele(String kodTabele) {
		this.kodTabele = kodTabele;
	}
	public ArrayList<Data> getTorke() {
		return torke;
	}
	public void setTorke(ArrayList<Data> torke) {
		this.torke = torke;
	}
	
	/**
	 * regular constructor
	 * @param kodTabele - code of the table that is presented by instance of this class
	 * @param torke - array of tuples
	 */
	public DataTabela(String kodTabele, ArrayList<Data> torke) {
		super();
		this.kodTabele = kodTabele;
		this.torke = torke;
	}
	/**
	 * empty constructor
	 */
	public DataTabela() {
		
	}
	
}
