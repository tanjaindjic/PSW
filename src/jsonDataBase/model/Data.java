package jsonDataBase.model;

import java.util.HashMap;
/**
 * Class that presents json tuple
 *
 */
public class Data {
	private String kodTabele;
	private HashMap<String, Object> torka;
	
	
	public String getKodTabele() {
		return kodTabele;
	}
	public void setKodTabele(String kodTabele) {
		this.kodTabele = kodTabele;
	}
	public HashMap<String, Object> getTorka() {
		return torka;
	}
	public void setTorka(HashMap<String, Object> torka) {
		this.torka = torka;
	}
	/**
	 * Constructor  with all params
	 * @param kodTabele - code of the table that the tuple belongs to
	 * @param torka - concrete tuple
	 */
	public Data(String kodTabele, HashMap<String, Object> torka) {
		super();
		this.kodTabele = kodTabele;
		this.torka = torka;
	}
	/**
	 * empty constructor
	 */
	public Data() {
		
	}
	
}
