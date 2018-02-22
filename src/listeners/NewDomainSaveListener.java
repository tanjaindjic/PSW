package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dialogs.DomainFrame;
import editorSeme.controller.concreetBuilders.DefaultAtributBuilder;
import editorSeme.model.enums.Tip;
import editorSeme.model.pojo.Sistem;

/**
 * Action listener class that enables the addition of a domain  to an attribute.
 * It tries to set the domain to a attribute and closes the Domain frame. 
 *
 */
public class NewDomainSaveListener implements ActionListener {

	private DefaultAtributBuilder dab;
	private JComboBox tip;
	private JTextField leng;
	private DomainFrame df;

	/**
	 * If the inputed values are correct the domain is added to the selected attribute. 
	 * Otherwise it shows a warning message and informs the user that the inputed values aren't valid.
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		//dab.buildDomen(length, tip);
		/*if(tip.getSelectedIndex()==1){
			dab.buildDomen(1, comboToTip(tip));
			df.dispose();
			return;
		}*/
		int len=0;
		if(leng.getText().equals("")) {
			len=10;
		}else {
			try {
				 len = Integer.parseInt(leng.getText());
		    }catch (Exception e) {
		    	JOptionPane.showMessageDialog(null, Sistem.getInstance().getTranslate("domValMsg4"));
		    	return;
		    }
		}
		/*if(((comboToTip(tip)!=Tip.VARCHAR)&&(comboToTip(tip)!=Tip.CHAR))&&len>0) {
			len=0;
		}*/
		dab.buildDomen(len, comboToTip(tip));
		df.dispose();
		/*if(len!=-1){
			dab.buildDomen(len, comboToTip(tip));
			df.dispose();
			return;
		}*/
		
		/*JOptionPane.showMessageDialog(df,
				Sistem.getInstance().getTranslate("Wrong_number"),
			    Sistem.getInstance().getTranslate("Error"),
			    JOptionPane.WARNING_MESSAGE);*/
	}
	/**
	 * Constructor for the NewDomainSaveListener.
	 * @param df The main frame for adding domains.
	 * @param d Attribute builder that is used for making a model of the table.
	 * @param domainComboBox The type of domain that is selected.
	 * @param jtf The maximum length of the attribute if the type of domain supports a length.
	 */
	public NewDomainSaveListener(DomainFrame df, DefaultAtributBuilder d, JComboBox domainComboBox, JTextField jtf ){
		this.dab = d;
		this.tip = domainComboBox;
		this.leng = jtf;
		this.df = df;
	
	}
	
	private Tip comboToTip( JComboBox jcb){
		String s = jcb.getSelectedItem().toString();
		
		
		if(s=="Varchar"){
			return Tip.VARCHAR;
			
		}else if(s =="Boolean"){
			return Tip.BOOLEAN;	
			
		}else if(s== "Integer"){
			return Tip.INT;
			
		}else if(s=="Float"){
			return Tip.FLOAT;
		}else if(s=="Bigint"){
				return Tip.BIGINT;
		}else if(s=="Char"){
			return Tip.CHAR;
		}else if(s=="Date"){
			return Tip.DATE;
		}else if(s=="Time"){
			return Tip.TIME;
		}else if(s=="Datetime"){
			return Tip.DATETIME;
		}else if(s=="Decimal"){
			return Tip.DECIMAL;
		}else if(s=="Numeric"){
			return Tip.NUMERIC;
		}else if(s=="Smalint"){
			return Tip.SMALINT;
		}else if(s=="Double"){
			return Tip.DOUBLE;	
		}else{
			System.out.println("WARNING NE VALJA DOMEN");
		}
		
		
		return null;
	}

}