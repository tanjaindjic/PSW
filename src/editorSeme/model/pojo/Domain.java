package editorSeme.model.pojo;

import java.io.Serializable;

import javax.swing.JOptionPane;

import com.fasterxml.jackson.annotation.JsonIgnore;

import editorSeme.model.enums.Tip;

/**
 * Class that represents domain constraint
 *
 */
public class Domain implements Serializable{
	private int length=0;
	private Tip tip;
	
	
	/**
	 * Default empty constructor.
	 */
	public Domain() {
		super();
	}
	
	/**
	 * Constructor with necessary parameters.
	 * @param length Type length.
	 * @param tip Data type.
	 */
	public Domain(int length, Tip tip) {
		super();
		this.length = length;
		//System.out.println("jesam li ovde ?");
		this.tip = tip;
	}
	
	/**
	 * Method that validates current domain structure according to predefined rules.
	 * @return True - structure is valid, False - structure is not valid.
	 */
	public boolean validate() {		
		if(tip==null) {
			JOptionPane.showMessageDialog(null, Sistem.getInstance().getTranslate("domValMsg1"));
			return false;
		}
		else if((tip.equals(Tip.CHAR)||(tip.equals(Tip.VARCHAR)))){
			if(length<1) {
				JOptionPane.showMessageDialog(null, Sistem.getInstance().getTranslate("domValMsg2"));
				return false;
			}
			
		}else if(length<0){
			JOptionPane.showMessageDialog(null, Sistem.getInstance().getTranslate("domValMsg3"));
			return false;
		}else if(tip.equals(Tip.INT)&&length>10) {
			JOptionPane.showMessageDialog(null, Sistem.getInstance().getTranslate("domValMsg5"));
			return false;
		}
		return true;
	}

	/**
	 * Method that gets current domain length.
	 * @return integer representing domain length.
	 */
	public int getLength() {
		return length;
	}

	/**
	 * Method that sets domain length.
	 * @param length Desired integer for domain length.
	 */
	public void setLength(int length) {
		this.length = length;
	}

	/**
	 * Method that gets current domain type.
	 * @return Tip enumeration representing domain type.
	 */
	public Tip getTip() {
		return tip;
	}
	
	/**
	 * Method that sets domain type.
	 * @param tip Tip enumeration representing new domain type.
	 */
	public void setTip(Tip tip) {
		this.tip = tip;
	}
	
	/**
	 * Override of default java method equals.
	 */
	@Override
	public boolean equals(Object obj) {
		if((length==((Domain)obj).getLength())) {			
			if((tip.equals(((Domain)obj).getTip()))){
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
	}
	/**
	 * Method that converts tip into String.
	 * @return equivalent String.
	 */
	@JsonIgnore
	public String getTipString() {
		if(tip==null) return "";
		switch(tip){
		case INT:
			return "int";
		case BIGINT:
			return "bigint";
		case BOOLEAN:
			return "boolean";
		case CHAR:
			return "char";
		case DATE:
			return "date";
		case DATETIME:
			return "dateTime";
		case DOUBLE:
			return "double";
		case FLOAT:
			return "float";
		case NUMERIC:
			return "numeric";
		case SMALINT:
			return "smalint";
		case TIME:
			return "time";
		case VARCHAR:
			return "varchar";
		default:
			return "";
		}
	}
}
