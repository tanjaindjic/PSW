package jsonDataBase;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import db.crud.RelationalUpdateTuple;
import db.model.Torka;
import db.model.Value;
import editorSeme.model.enums.Tip;
import editorSeme.model.pojo.Atribut;
import editorSeme.model.pojo.Id_Id;
import editorSeme.model.pojo.Key;
import editorSeme.model.pojo.Sistem;
import editorSeme.model.pojo.Table;
import jsonDataBase.additional.JSONDataSerialize;
import jsonDataBase.model.Data;
import jsonDataBase.model.DataTabela;
import start.DatabaseType;
import start.InfViewModel;
import workingsection.Tabs;
/**
 * Class that perform all steps that are neccessery for data to be edited
 */
public class OkEditTorkaListener implements ActionListener {
	private Table t;
	private Data data;
	private int index;

	/**
	 * Constructor that sets table that is going to get a tuple
	 * @param t - table that contains edited tuple
	 * @param data - Tuple taht is edited
	 * @param index - index of selected row in the table, the one that is edited
	 */
	public OkEditTorkaListener(Table t, Data data, int index) {
		this.t=t;
		this.data=data;
		this.index=index;
	}
	/**
	 * Go through all Components that contains data, checks if they are valid
	 * and in the end makes a tuple and edits it in json dataBase or redirect it to actionPerformedOnRelational-method
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(InfViewModel.getInstance().getDatabaseType().equals(DatabaseType.RELATIONAL)){
			actionPerformedOnRelational();
			return;
		}
		EditTorkaFrame atf = EditTorkaFrame.getInstance(t);
		HashMap<String, Object> podaci = atf.polja;
		boolean valid = true;
		int i=0;
		if(data.getTorka()==null)
			data.setTorka(new HashMap<>());
		for(Atribut a : t.getPolja()){	
			switch(a.getDomain().getTip()){
			case INT:
				if(((JTextField)podaci.get(a.getName().getCode())).getText().toString().isEmpty()){
					if(!a.getisNull()){
						JOptionPane.showMessageDialog(atf, a.toString()+Sistem.getInstance().getTranslate("Must_be_entered"), "Error", JOptionPane.ERROR_MESSAGE);
						valid = false;
					}
				}else{
					String num = ((JTextField)podaci.get(a.getName().getCode())).getText().toString();
					try{
						int n = Integer.parseInt(num);
						if(a.isUnique()){
							if(t.isEditedValueNotUnique(a, n, data)){
								valid=false;
								JOptionPane.showMessageDialog(atf, a.toString()+Sistem.getInstance().getTranslate("Entered_value_of")+a.toString()+Sistem.getInstance().getTranslate("Is_not_unique"), "Error", JOptionPane.ERROR_MESSAGE);	
							}								
							if(a.isForeignKey()){
								if(t.isNotValidFKey(a, n)){
									valid=false;
									JOptionPane.showMessageDialog(atf, Sistem.getInstance().getTranslate("Entered_value_of")+a.toString()+Sistem.getInstance().getTranslate("Does_not_Exist_in"), "Error", JOptionPane.ERROR_MESSAGE);	
								}
							}
						}
						data.getTorka().put(a.getName().getCode(), n);
					}catch(NumberFormatException n){
						valid = false;
						JOptionPane.showMessageDialog(atf, Sistem.getInstance().getTranslate("Entered_value_of")+a.toString()+Sistem.getInstance().getTranslate("Is_Not_Valid"), "Error", JOptionPane.ERROR_MESSAGE);
					}						
				}
				break;
			case BIGINT:
				if(((JTextField)podaci.get(a.getName().getCode())).getText().toString().isEmpty()){
					if(!a.getisNull()){
						valid = false;
						JOptionPane.showMessageDialog(atf, a.toString()+Sistem.getInstance().getTranslate("Must_be_entered"), "Error", JOptionPane.ERROR_MESSAGE);
					}
				}else{
					String num = ((JTextField)podaci.get(a.getName().getCode())).getText().toString();
					try{
						BigInteger n = new BigInteger(num);
						if(a.isUnique()){
							if(t.isEditedValueNotUnique(a, n, data)){
								valid=false;
								JOptionPane.showMessageDialog(atf, Sistem.getInstance().getTranslate("Entered_value_of")+a.toString()+Sistem.getInstance().getTranslate("Is_not_unique"), "Error", JOptionPane.ERROR_MESSAGE);	
							}								
							if(a.isForeignKey()){
								if(t.isNotValidFKey(a, n)){
									valid=false;
									JOptionPane.showMessageDialog(atf, Sistem.getInstance().getTranslate("Entered_value_of")+a.toString()+Sistem.getInstance().getTranslate("Does_not_Exist_in"), "Error", JOptionPane.ERROR_MESSAGE);	
								}
							}
						}
						data.getTorka().put(a.getName().getCode(), n);
					}catch(NumberFormatException n){
						valid = false;
						JOptionPane.showMessageDialog(atf, Sistem.getInstance().getTranslate("Entered_value_of")+a.toString()+Sistem.getInstance().getTranslate("Is_Not_Valid"), "Error", JOptionPane.ERROR_MESSAGE);
					}						
				}
				break;
			case BOOLEAN:
				if(!a.getisNull()){
					if(((JRadioButton)podaci.get(a.getName().getCode()+"N")).isSelected()){
						JOptionPane.showMessageDialog(atf, a.toString()+" "+Sistem.getInstance().getTranslate("Value_Can_Not_Be_Null"), "Error", JOptionPane.ERROR_MESSAGE);	
						valid = false;
					}
				}
				if(((JRadioButton)podaci.get(a.getName().getCode()+"T")).isSelected())
					data.getTorka().put(a.getName().getCode(), true);
				else if(((JRadioButton)podaci.get(a.getName().getCode()+"F")).isSelected())
					data.getTorka().put(a.getName().getCode(), false);
				else
					data.getTorka().put(a.getName().getCode(), null);						
				i=i+2;
				break;
			case CHAR:
				if(((JTextField)podaci.get(a.getName().getCode())).getText().toString().isEmpty()){
					if(!a.getisNull()){
						JOptionPane.showMessageDialog(atf, a.toString()+Sistem.getInstance().getTranslate("Must_be_entered"), "Error", JOptionPane.ERROR_MESSAGE);
						valid = false;
					}
				}
				else{
					if(((JTextField)podaci.get(a.getName().getCode())).getText().toString().length()!=a.getDomain().getLength()){
						JOptionPane.showMessageDialog(atf, a.toString() + Sistem.getInstance().getTranslate("Char_Value")+Sistem.getInstance().getTranslate("Is_Not_Valid"), "Error", JOptionPane.ERROR_MESSAGE);
						valid = false;
						}
					else{
						String val = ((JTextField)podaci.get(a.getName().getCode())).getText();
						if(a.isUnique()){
							if(t.isEditedValueNotUnique(a, val, data)){
								valid=false;
								JOptionPane.showMessageDialog(atf, Sistem.getInstance().getTranslate("Entered_value_of")+a.toString()+Sistem.getInstance().getTranslate("Is_not_unique"), "Error", JOptionPane.ERROR_MESSAGE);	
							}	
						}								
						if(a.isForeignKey()){
							if(t.isNotValidFKey(a, val)){
								valid=false;
								JOptionPane.showMessageDialog(atf, Sistem.getInstance().getTranslate("Entered_value_of")+a.toString()+Sistem.getInstance().getTranslate("Does_not_Exist_in"), "Error", JOptionPane.ERROR_MESSAGE);	
							}
						}
						data.getTorka().put(a.getName().getCode(), val);						
					}
				}
				break;
			case DATE:
				if(((JTextField)podaci.get(a.getName().getCode())).getText().toString().isEmpty()){
					if(!a.getisNull()){
						valid = false;
						JOptionPane.showMessageDialog(atf, a.toString()+Sistem.getInstance().getTranslate("Must_be_entered"), "Error", JOptionPane.ERROR_MESSAGE);
					}
				}else{
					try{
						Date date = Date.valueOf(((JTextField)podaci.get(a.getName().getCode())).getText().toString());
						if(a.isUnique()){
							if(t.isEditedValueNotUnique(a, date, data)){
								valid=false;
								JOptionPane.showMessageDialog(atf, Sistem.getInstance().getTranslate("Entered_value_of")+a.toString()+Sistem.getInstance().getTranslate("Is_not_unique"), "Error", JOptionPane.ERROR_MESSAGE);	
							}	
						}								
						if(a.isForeignKey()){
							if(t.isNotValidFKey(a, date)){
								valid=false;
								JOptionPane.showMessageDialog(atf, Sistem.getInstance().getTranslate("Entered_value_of")+a.toString()+Sistem.getInstance().getTranslate("Does_not_Exist_in"), "Error", JOptionPane.ERROR_MESSAGE);	
							}
						}
						data.getTorka().put(a.getName().getCode(), date);											
					}catch(DateTimeException e1){
						JOptionPane.showMessageDialog(atf, Sistem.getInstance().getTranslate("Date_")+a.toString() +Sistem.getInstance().getTranslate("Date_valid_Format"), "Error", JOptionPane.ERROR_MESSAGE);
					}
					
				}
				break;
			case DATETIME:
				if(((JTextField)podaci.get(a.toString())).getText().toString().isEmpty()){
					if(!a.getisNull()){
						valid = false;
						JOptionPane.showMessageDialog(atf, a.toString()+Sistem.getInstance().getTranslate("Must_be_entered"), "Error", JOptionPane.ERROR_MESSAGE);
					}
				}else{
					try{
						Timestamp date = Timestamp.valueOf(((JTextField)podaci.get(a.toString())).getText().toString());
						if(a.isUnique()){
							if(t.isValueNotUnique(a, date)){
								valid=false;
								JOptionPane.showMessageDialog(atf, Sistem.getInstance().getTranslate("Entered_value_of")+a.toString()+Sistem.getInstance().getTranslate("Is_not_unique"), "Error", JOptionPane.ERROR_MESSAGE);	
							}								
							if(a.isForeignKey()){
								if(t.isNotValidFKey(a, date)){
									valid=false;
									JOptionPane.showMessageDialog(atf, Sistem.getInstance().getTranslate("Entered_value_of")+a.toString()+Sistem.getInstance().getTranslate("Does_not_Exist_in"), "Error", JOptionPane.ERROR_MESSAGE);	
								}
							}
						}
						data.getTorka().put(a.getName().getCode(), date);											
					}catch(DateTimeException e1){
						JOptionPane.showMessageDialog(atf, Sistem.getInstance().getTranslate("Date_And_Time")+a.toString() +Sistem.getInstance().getTranslate("Date_And_Time_valid_Format"), "Error", JOptionPane.ERROR_MESSAGE);
						valid=false;
					}
				}
				break;
			case TIME:
				if(((JTextField)podaci.get(a.toString())).getText().toString().isEmpty()){
					if(!a.getisNull()){
						valid = false;
						JOptionPane.showMessageDialog(atf, a.toString()+Sistem.getInstance().getTranslate("Must_be_entered"), "Error", JOptionPane.ERROR_MESSAGE);
					}
				}else{
					try{
						Time date = Time.valueOf(((JTextField)podaci.get(a.toString())).getText().toString());
						if(a.isUnique()){
							if(t.isValueNotUnique(a, date)){
								valid=false;
								JOptionPane.showMessageDialog(atf, Sistem.getInstance().getTranslate("Entered_value_of")+a.toString()+Sistem.getInstance().getTranslate("Is_not_unique"), "Error", JOptionPane.ERROR_MESSAGE);	
							}								
							if(a.isForeignKey()){
								if(t.isNotValidFKey(a, date)){
									valid=false;
									JOptionPane.showMessageDialog(atf, Sistem.getInstance().getTranslate("Entered_value_of")+a.toString()+Sistem.getInstance().getTranslate("Does_not_Exist_in"), "Error", JOptionPane.ERROR_MESSAGE);	
								}
							}
						}
						data.getTorka().put(a.getName().getCode(), date);											
					}catch(DateTimeException e1){
						JOptionPane.showMessageDialog(atf,Sistem.getInstance().getTranslate("Time_")+a.toString() +Sistem.getInstance().getTranslate("Time_valid_Format"), "Error", JOptionPane.ERROR_MESSAGE);
						valid=false;
					}
				}
				break;
			case DOUBLE:
				if(((JTextField)podaci.get(a.toString())).getText().toString().isEmpty()){
					if(!a.getisNull()){
						JOptionPane.showMessageDialog(atf, a.toString()+Sistem.getInstance().getTranslate("Must_be_entered"), "Error", JOptionPane.ERROR_MESSAGE);
						valid = false;
					}
				}else{
					String num = ((JTextField)podaci.get(a.toString())).getText().toString();
					try{
						Double n = Double.parseDouble(num);
						if(a.isUnique()){
							if(t.isValueNotUnique(a, n)){
								valid=false;
								JOptionPane.showMessageDialog(atf, Sistem.getInstance().getTranslate("Entered_value_of")+a.toString()+Sistem.getInstance().getTranslate("Is_not_unique"), "Error", JOptionPane.ERROR_MESSAGE);	
							}								
							if(a.isForeignKey()){
								if(t.isNotValidFKey(a, n)){
									valid=false;
									JOptionPane.showMessageDialog(atf, Sistem.getInstance().getTranslate("Entered_value_of")+a.toString()+Sistem.getInstance().getTranslate("Does_not_Exist_in"), "Error", JOptionPane.ERROR_MESSAGE);	
								}
							}
						}
						data.getTorka().put(a.getName().getCode(), n);
					}catch(NumberFormatException n){
						valid = false;
						JOptionPane.showMessageDialog(atf, Sistem.getInstance().getTranslate("Entered_value_of")+a.toString()+Sistem.getInstance().getTranslate("Is_Not_Valid"), "Error", JOptionPane.ERROR_MESSAGE);
					}						
				}
				break;
			case SMALINT:
				if(((JTextField)podaci.get(a.toString())).getText().toString().isEmpty()){
					if(!a.getisNull()){
						JOptionPane.showMessageDialog(atf, a.toString()+Sistem.getInstance().getTranslate("Must_be_entered"), "Error", JOptionPane.ERROR_MESSAGE);
						valid = false;
					}
				}else{
					String num = ((JTextField)podaci.get(a.toString())).getText().toString();
					try{
						Short n = Short.parseShort(num);
						if(a.isUnique()){
							if(t.isValueNotUnique(a, n)){
								valid=false;
								JOptionPane.showMessageDialog(atf, Sistem.getInstance().getTranslate("Entered_value_of")+a.toString()+Sistem.getInstance().getTranslate("Is_not_unique"), "Error", JOptionPane.ERROR_MESSAGE);	
							}								
							if(a.isForeignKey()){
								if(t.isNotValidFKey(a, n)){
									valid=false;
									JOptionPane.showMessageDialog(atf, Sistem.getInstance().getTranslate("Entered_value_of")+a.toString()+Sistem.getInstance().getTranslate("Does_not_Exist_in"), "Error", JOptionPane.ERROR_MESSAGE);	
								}
							}
						}
						data.getTorka().put(a.getName().getCode(), n);
					}catch(NumberFormatException n){
						valid = false;
						JOptionPane.showMessageDialog(atf, Sistem.getInstance().getTranslate("Entered_value_of")+a.toString()+Sistem.getInstance().getTranslate("Is_Not_Valid"), "Error", JOptionPane.ERROR_MESSAGE);
					}						
				}
				break;
			case FLOAT:
				if(((JTextField)podaci.get(a.getName().getCode())).getText().toString().isEmpty()){
					if(!a.getisNull()){
						valid = false;
						JOptionPane.showMessageDialog(atf, a.toString()+Sistem.getInstance().getTranslate("Must_be_entered"), "Error", JOptionPane.ERROR_MESSAGE);
					}
				}else{
					try{
						Float f = Float.parseFloat(((JTextField)podaci.get(a.getName().getCode())).getText().toString());
						if(a.isUnique()){
							if(t.isEditedValueNotUnique(a, f, data)){
								valid=false;
								JOptionPane.showMessageDialog(atf, Sistem.getInstance().getTranslate("Entered_value_of")+a.toString()+Sistem.getInstance().getTranslate("Is_not_unique"), "Error", JOptionPane.ERROR_MESSAGE);	
							}	
						}								
						if(a.isForeignKey()){
							if(t.isNotValidFKey(a, f)){
								valid=false;
								JOptionPane.showMessageDialog(atf, Sistem.getInstance().getTranslate("Entered_value_of")+a.toString()+Sistem.getInstance().getTranslate("Does_not_Exist_in"), "Error", JOptionPane.ERROR_MESSAGE);	
							}
						}
						data.getTorka().put(a.getName().getCode(), f);											
					}catch(DateTimeException e1){
						valid = false;
						JOptionPane.showMessageDialog(atf, a.toString()+Sistem.getInstance().getTranslate("Not_Valid_Use_Digits"), "Error", JOptionPane.ERROR_MESSAGE);
					}
					
				}
				break;
			case NUMERIC:
				try{
					int value = (Integer) ((JSpinner)podaci.get(a.getName().getCode())).getValue();
					if(a.isUnique()){
						if(t.isEditedValueNotUnique(a, value, data)){
							valid=false;
							JOptionPane.showMessageDialog(atf, Sistem.getInstance().getTranslate("Entered_value_of")+a.toString()+Sistem.getInstance().getTranslate("Is_not_unique"), "Error", JOptionPane.ERROR_MESSAGE);	
						}	
					}								
					if(a.isForeignKey()){
						if(t.isNotValidFKey(a, value)){
							valid=false;
							JOptionPane.showMessageDialog(atf, Sistem.getInstance().getTranslate("Entered_value_of")+a.toString()+Sistem.getInstance().getTranslate("Does_not_Exist_in"), "Error", JOptionPane.ERROR_MESSAGE);	
						}
					}
					data.getTorka().put(a.getName().getCode(), value);	
				}catch(NumberFormatException eee){
					valid = false;
					JOptionPane.showMessageDialog(atf, Sistem.getInstance().getTranslate("Num_Value")+a.toString() +") "+Sistem.getInstance().getTranslate("Is_Not_Valid"), "Error", JOptionPane.ERROR_MESSAGE);					
				} 
				break;
			case VARCHAR:
				if(t.isKey(a))
					break;
				String ss = ((JTextField)podaci.get(a.getName().getCode())).getText().toString();
				if(ss.isEmpty()){
					if(!a.getisNull()){
						valid = false;
						JOptionPane.showMessageDialog(atf, a.toString()+Sistem.getInstance().getTranslate("Must_be_entered"), "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				if(a.isUnique()){
					if(t.isEditedValueNotUnique(a, ss, data)){
						valid=false;
						JOptionPane.showMessageDialog(atf, Sistem.getInstance().getTranslate("Entered_value_of")+a.toString()+Sistem.getInstance().getTranslate("Is_not_unique"), "Error", JOptionPane.ERROR_MESSAGE);	
					}	
				}								
				if(a.isForeignKey()){
					if(t.isNotValidFKey(a, ss)){
						valid=false;
						JOptionPane.showMessageDialog(atf, Sistem.getInstance().getTranslate("Entered_value_of")+a.toString()+Sistem.getInstance().getTranslate("Does_not_Exist_in"), "Error", JOptionPane.ERROR_MESSAGE);	
					}
				}
				data.getTorka().put(a.getName().getCode(), ss);
				break;
			default:
				break;				
			}
			i++;			
		}
		if(valid){
			DataTabela dt = JSONDataSerialize.getDataTable(t);
			dt.getTorke().set(index, data);
			
			JSONDataSerialize.saveData(dt);
			EditTorkaFrame.getInstance(t).destroy();
			Tabs.refreshTable(t);
		}
	}
	/**
	 * Go through all Components that contains data, checks if they are valid
	 * and in the end makes a tuple and edits it in relational dataBase or redirect it to actionPerformedOnRelational-method
	 */
	private void actionPerformedOnRelational() {
		Torka torka = new Torka();
		EditTorkaFrame atf = EditTorkaFrame.getInstance(t);
		HashMap<String, Object> podaci = atf.polja;
		boolean valid = true;
		ArrayList<String> fkey = new ArrayList<String>();
		Key k0 = t.getKeys().get(0);
		for(Id_Id id0 : k0.getIds()){
			fkey.add(id0.getAtributeKey());
		}
		int i=0;
		for(Atribut a : t.getPolja()){	
			Value v = new Value();
			switch(a.getDomain().getTip()){
			case INT:
				if(((JTextField)podaci.get(a.getName().getCode())).getText().toString().isEmpty()){
					if(!a.getisNull()){
						JOptionPane.showMessageDialog(atf, a.toString()+Sistem.getInstance().getTranslate("Must_be_entered"), "Error", JOptionPane.ERROR_MESSAGE);
						valid = false;
					}
				}else{
					String num = ((JTextField)podaci.get(a.getName().getCode())).getText().toString();
					try{
						int n = Integer.parseInt(num);
						if(a.isUnique()){
							if(t.isValueNotUnique(a, n)){
								valid=false;
								JOptionPane.showMessageDialog(atf, Sistem.getInstance().getTranslate("Entered_value_of")+a.toString()+Sistem.getInstance().getTranslate("Is_not_unique"), "Error", JOptionPane.ERROR_MESSAGE);	
							}								
							if(a.isForeignKey()){
								if(t.isNotValidFKey(a, n)){
									valid=false;
									JOptionPane.showMessageDialog(atf, Sistem.getInstance().getTranslate("Entered_value_of")+a.toString()+Sistem.getInstance().getTranslate("Does_not_Exist_in"), "Error", JOptionPane.ERROR_MESSAGE);	
								}
							}
						}
						v.setCode(a.getName().getCode());
						v.setTip(Tip.INT);
						v.setValue(n);
						torka.add(v);
					}catch(NumberFormatException n){
						valid = false;
						JOptionPane.showMessageDialog(atf, Sistem.getInstance().getTranslate("Entered_value_of")+a.toString()+Sistem.getInstance().getTranslate("Is_Not_Valid"), "Error", JOptionPane.ERROR_MESSAGE);
					}						
				}
				break;
			case BIGINT:
				if(((JTextField)podaci.get(a.getName().getCode())).getText().toString().isEmpty()){
					if(!a.getisNull()){
						valid = false;
						JOptionPane.showMessageDialog(atf, a.toString()+Sistem.getInstance().getTranslate("Must_be_entered"), "Error", JOptionPane.ERROR_MESSAGE);
						}
					}else{
					String num = ((JTextField)podaci.get(a.getName().getCode())).getText().toString();
					try{
						BigInteger n = new BigInteger(num);
						if(a.isUnique()){
							if(t.isValueNotUnique(a, n)){
								valid=false;
								JOptionPane.showMessageDialog(atf, Sistem.getInstance().getTranslate("Entered_value_of")+a.toString()+Sistem.getInstance().getTranslate("Is_not_unique"), "Error", JOptionPane.ERROR_MESSAGE);	
							}								
							if(a.isForeignKey()){
								if(t.isNotValidFKey(a, n)){
									valid=false;
									JOptionPane.showMessageDialog(atf, Sistem.getInstance().getTranslate("Entered_value_of")+a.toString()+Sistem.getInstance().getTranslate("Does_not_Exist_in"), "Error", JOptionPane.ERROR_MESSAGE);	
								}
							}
						}
						v.setCode(a.getName().getCode());
						v.setTip(Tip.BIGINT);
						v.setValue(n);
						torka.add(v);
					}catch(NumberFormatException n){
						valid = false;
						JOptionPane.showMessageDialog(atf, Sistem.getInstance().getTranslate("Entered_value_of")+a.toString()+Sistem.getInstance().getTranslate("Is_Not_Valid"), "Error", JOptionPane.ERROR_MESSAGE);
					}						
				}
				break;
			case BOOLEAN:
				if(!a.getisNull()){
					if(((JRadioButton)podaci.get(a.getName().getCode()+"N")).isSelected()){
						JOptionPane.showMessageDialog(atf, a.toString()+" "+Sistem.getInstance().getTranslate("Value_Can_Not_Be_Null"), "Error", JOptionPane.ERROR_MESSAGE);	
						valid = false;
					}
				}else{
					int pomI = i;
					v.setCode(a.getName().getCode());
					v.setTip(Tip.BOOLEAN);
					if(((JRadioButton)podaci.get(a.getName().getCode()+"T")).isSelected())
						v.setValue(true);
					else if(((JRadioButton)podaci.get(a.getName().getCode()+"F")).isSelected())
						v.setValue(false);
					else
						v.setValue(null);	
					torka.add(v);
				}
				i=i+2;
				break;
			case CHAR:
				if(((JTextField)podaci.get(a.getName().getCode())).getText().toString().isEmpty()){
					if(!a.getisNull()){
						JOptionPane.showMessageDialog(atf, a.toString() +  Sistem.getInstance().getTranslate("Char_Value")+Sistem.getInstance().getTranslate("Is_Not_Valid"), "Error", JOptionPane.ERROR_MESSAGE);
						valid = false;
					}
				}
				else{
					if(((JTextField)podaci.get(a.getName().getCode())).getText().toString().length()!=a.getDomain().getLength()){
						JOptionPane.showMessageDialog(atf, a.toString() + ": char value is not valid", "Error", JOptionPane.ERROR_MESSAGE);
						valid = false;
						}
					else{
						String val = ((JTextField)podaci.get(a.getName().getCode())).getText();
						if(a.isUnique()){
							if(t.isValueNotUnique(a, val)){
								valid=false;
								JOptionPane.showMessageDialog(atf, Sistem.getInstance().getTranslate("Entered_value_of")+a.toString()+Sistem.getInstance().getTranslate("Is_not_unique"), "Error", JOptionPane.ERROR_MESSAGE);	
							}								
							if(a.isForeignKey()){
								if(t.isNotValidFKey(a, val)){
									valid=false;
									JOptionPane.showMessageDialog(atf, Sistem.getInstance().getTranslate("Entered_value_of")+a.toString()+Sistem.getInstance().getTranslate("Does_not_Exist_in"), "Error", JOptionPane.ERROR_MESSAGE);	
								}
							}
						}
						v.setCode(a.getName().getCode());
						v.setTip(Tip.CHAR);
						v.setValue(val);
						torka.add(v);					
					}
				}
				break;
			case DATE:
				if(((JTextField)podaci.get(a.getName().getCode())).getText().toString().isEmpty()){
					if(!a.getisNull()){
						valid = false;
						JOptionPane.showMessageDialog(atf, a.toString()+Sistem.getInstance().getTranslate("Must_be_entered"), "Error", JOptionPane.ERROR_MESSAGE);
					}
				}else{
					try{
						Date date = Date.valueOf(((JTextField)podaci.get(a.getName().getCode())).getText().toString());
						if(a.isUnique()){
							if(t.isValueNotUnique(a, date)){
								valid=false;
								JOptionPane.showMessageDialog(atf, Sistem.getInstance().getTranslate("Entered_value_of")+a.toString()+Sistem.getInstance().getTranslate("Is_not_unique"), "Error", JOptionPane.ERROR_MESSAGE);	
							}								
							if(a.isForeignKey()){
								if(t.isNotValidFKey(a, date)){
									valid=false;
									JOptionPane.showMessageDialog(atf, Sistem.getInstance().getTranslate("Entered_value_of")+a.toString()+Sistem.getInstance().getTranslate("Does_not_Exist_in"), "Error", JOptionPane.ERROR_MESSAGE);	
								}
							}
						}
						v.setCode(a.getName().getCode());
						v.setTip(Tip.DATE);
						v.setValue(date);
						torka.add(v);										
					}catch(DateTimeException e1){
						JOptionPane.showMessageDialog(atf, Sistem.getInstance().getTranslate("Date_(")+a.toString() +Sistem.getInstance().getTranslate("Date_valid_Format"), "Error", JOptionPane.ERROR_MESSAGE);
					}
					
				}
				break;
			case DATETIME:
				if(((JTextField)podaci.get(a.getName().getCode())).getText().toString().isEmpty()){
					if(!a.getisNull()){
						valid = false;
						JOptionPane.showMessageDialog(atf, a.toString()+Sistem.getInstance().getTranslate("Must_be_entered"), "Error", JOptionPane.ERROR_MESSAGE);
					}
				}else{
					try{
						Timestamp date = Timestamp.valueOf(((JTextField)podaci.get(a.getName().getCode())).getText().toString());
						if(a.isUnique()){
							if(t.isValueNotUnique(a, date)){
								valid=false;
								JOptionPane.showMessageDialog(atf, Sistem.getInstance().getTranslate("Entered_value_of")+a.toString()+Sistem.getInstance().getTranslate("Is_not_unique"), "Error", JOptionPane.ERROR_MESSAGE);	
							}								
							if(a.isForeignKey()){
								if(t.isNotValidFKey(a, date)){
									valid=false;
									JOptionPane.showMessageDialog(atf, Sistem.getInstance().getTranslate("Entered_value_of")+a.toString()+Sistem.getInstance().getTranslate("Does_not_Exist_in"), "Error", JOptionPane.ERROR_MESSAGE);	
								}
							}
						}
						v.setCode(a.getName().getCode());
						v.setTip(Tip.DATETIME);
						v.setValue(date);
						torka.add(v);										
					}catch(DateTimeException e1){
						JOptionPane.showMessageDialog(atf, Sistem.getInstance().getTranslate("Date_And_Time")+a.toString() +Sistem.getInstance().getTranslate("Date_And_Time_valid_Format"), "Error", JOptionPane.ERROR_MESSAGE);
						valid=false;
					}
				}
				break;
			case TIME:
				if(((JTextField)podaci.get(a.getName().getCode())).getText().toString().isEmpty()){
					if(!a.getisNull()){
						valid = false;
						JOptionPane.showMessageDialog(atf, a.toString()+" must be entered", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}else{
					try{
						Time date = Time.valueOf(((JTextField)podaci.get(a.getName().getCode())).getText().toString());
						if(a.isUnique()){
							if(t.isValueNotUnique(a, date)){
								valid=false;
								JOptionPane.showMessageDialog(atf, Sistem.getInstance().getTranslate("Entered_value_of")+a.toString()+Sistem.getInstance().getTranslate("Is_not_unique"), "Error", JOptionPane.ERROR_MESSAGE);	
							}								
							if(a.isForeignKey()){
								if(t.isNotValidFKey(a, date)){
									valid=false;
									JOptionPane.showMessageDialog(atf, Sistem.getInstance().getTranslate("Entered_value_of")+a.toString()+Sistem.getInstance().getTranslate("Does_not_Exist_in"), "Error", JOptionPane.ERROR_MESSAGE);	
								}
							}
						}
						v.setCode(a.getName().getCode());
						v.setTip(Tip.TIME);
						v.setValue(date);
						torka.add(v);										
					}catch(DateTimeException e1){
						JOptionPane.showMessageDialog(atf, Sistem.getInstance().getTranslate("Time_")+a.toString() +Sistem.getInstance().getTranslate("Time_valid_Format"), "Error", JOptionPane.ERROR_MESSAGE);
						valid=false;
					}
				}
				break;
			case DOUBLE:
				if(((JTextField)podaci.get(a.getName().getCode())).getText().toString().isEmpty()){
					if(!a.getisNull()){
						JOptionPane.showMessageDialog(atf, a.toString()+Sistem.getInstance().getTranslate("Must_be_entered"), "Error", JOptionPane.ERROR_MESSAGE);
						valid = false;
					}
				}else{
					String num = ((JTextField)podaci.get(a.getName().getCode())).getText().toString();
					try{
						Double n = Double.parseDouble(num);
						if(a.isUnique()){
							if(t.isValueNotUnique(a, n)){
								valid=false;
								JOptionPane.showMessageDialog(atf, Sistem.getInstance().getTranslate("Entered_value_of")+a.toString()+Sistem.getInstance().getTranslate("Is_not_unique"), "Error", JOptionPane.ERROR_MESSAGE);	
							}								
							if(a.isForeignKey()){
								if(t.isNotValidFKey(a, n)){
									valid=false;
									JOptionPane.showMessageDialog(atf, Sistem.getInstance().getTranslate("Entered_value_of")+a.toString()+Sistem.getInstance().getTranslate("Does_not_Exist_in"), "Error", JOptionPane.ERROR_MESSAGE);	
								}
							}
						}
						v.setCode(a.getName().getCode());
						v.setTip(Tip.DOUBLE);
						v.setValue(n);
						torka.add(v);
					}catch(NumberFormatException n){
						valid = false;
						JOptionPane.showMessageDialog(atf, Sistem.getInstance().getTranslate("Entered_value_of")+a.toString()+Sistem.getInstance().getTranslate("Is_Not_Valid"), "Error", JOptionPane.ERROR_MESSAGE);
					}						
				}
				break;
			case SMALINT:
				if(((JTextField)podaci.get(a.getName().getCode())).getText().toString().isEmpty()){
					if(!a.getisNull()){
						JOptionPane.showMessageDialog(atf, a.toString()+Sistem.getInstance().getTranslate("Must_be_entered"), "Error", JOptionPane.ERROR_MESSAGE);
						valid = false;
					}
				}else{
					String num = ((JTextField)podaci.get(a.getName().getCode())).getText().toString();
					try{
						Short n = Short.parseShort(num);
						if(a.isUnique()){
							if(t.isValueNotUnique(a, n)){
								valid=false;
								JOptionPane.showMessageDialog(atf, Sistem.getInstance().getTranslate("Entered_value_of")+a.toString()+Sistem.getInstance().getTranslate("Is_not_unique"), "Error", JOptionPane.ERROR_MESSAGE);	
							}								
							if(a.isForeignKey()){
								if(t.isNotValidFKey(a, n)){
									valid=false;
									JOptionPane.showMessageDialog(atf, Sistem.getInstance().getTranslate("Entered_value_of")+a.toString()+Sistem.getInstance().getTranslate("Does_not_Exist_in"), "Error", JOptionPane.ERROR_MESSAGE);	
								}
							}
						}
						v.setCode(a.getName().getCode());
						v.setTip(Tip.SMALINT);
						v.setValue(n);
						torka.add(v);
					}catch(NumberFormatException n){
						valid = false;
						JOptionPane.showMessageDialog(atf, Sistem.getInstance().getTranslate("Entered_value_of")+a.toString()+Sistem.getInstance().getTranslate("Is_Not_Valid"), "Error", JOptionPane.ERROR_MESSAGE);
					}						
				}
				break;
			case FLOAT:
				if(((JTextField)podaci.get(a.getName().getCode())).getText().toString().isEmpty()){
					if(!a.getisNull()){
						valid = false;
						JOptionPane.showMessageDialog(atf, a.toString()+Sistem.getInstance().getTranslate("Must_be_entered"), "Error", JOptionPane.ERROR_MESSAGE);
					}
				}else{
					try{
						Float f = Float.parseFloat(((JTextField)podaci.get(a.getName().getCode())).getText().toString());
						if(a.isUnique()){
							if(t.isValueNotUnique(a, f)){
								valid=false;
								JOptionPane.showMessageDialog(atf, Sistem.getInstance().getTranslate("Entered_value_of")+a.toString()+Sistem.getInstance().getTranslate("Is_not_unique"), "Error", JOptionPane.ERROR_MESSAGE);	
							}								
							if(a.isForeignKey()){
								if(t.isNotValidFKey(a, f)){
									valid=false;
									JOptionPane.showMessageDialog(atf, Sistem.getInstance().getTranslate("Entered_value_of")+a.toString()+Sistem.getInstance().getTranslate("Does_not_Exist_in"), "Error", JOptionPane.ERROR_MESSAGE);	
								}
							}
						}
						v.setCode(a.getName().getCode());
						v.setTip(Tip.FLOAT);
						v.setValue(f);
						torka.add(v);									
					}catch(DateTimeException e1){
						valid = false;
						JOptionPane.showMessageDialog(atf, a.toString()+Sistem.getInstance().getTranslate("Not_Valid_Use_Digits"), "Error", JOptionPane.ERROR_MESSAGE);
					}
					
				}
				break;
			case NUMERIC:
				try{
					int value = (Integer) ((JSpinner)podaci.get(a.getName().getCode())).getValue();
					if(a.isUnique()){
						if(t.isValueNotUnique(a, value)){
							valid=false;
							JOptionPane.showMessageDialog(atf, Sistem.getInstance().getTranslate("Entered_value_of")+a.toString()+Sistem.getInstance().getTranslate("Is_not_unique"), "Error", JOptionPane.ERROR_MESSAGE);	
						}								
						if(a.isForeignKey()){
							if(t.isNotValidFKey(a, value)){
								valid=false;
								JOptionPane.showMessageDialog(atf, Sistem.getInstance().getTranslate("Entered_value_of")+a.toString()+Sistem.getInstance().getTranslate("Does_not_Exist_in"), "Error", JOptionPane.ERROR_MESSAGE);	
							}
						}
					}
					v.setCode(a.getName().getCode());
					v.setTip(Tip.NUMERIC);
					v.setValue(value);
					torka.add(v);
				}catch(NumberFormatException eee){
					valid = false;
					JOptionPane.showMessageDialog(atf, Sistem.getInstance().getTranslate("Num_Value")+a.toString() +") "+ Sistem.getInstance().getTranslate("Is_Not_Valid"), "Error", JOptionPane.ERROR_MESSAGE);					
				} 
				break;
			case VARCHAR:
				String ss = ((JTextField)podaci.get(a.getName().getCode())).getText().toString();
				if(((JTextField)podaci.get(a.getName().getCode())).getText().toString().isEmpty()){
					if(!a.getisNull()){
						valid = false;
						JOptionPane.showMessageDialog(atf,a.toString()+Sistem.getInstance().getTranslate("Must_be_entered"), "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				if(a.isUnique()){
					if(t.isValueNotUnique(a, ss)){
						valid=false;
						JOptionPane.showMessageDialog(atf, Sistem.getInstance().getTranslate("Entered_value_of")+a.toString()+Sistem.getInstance().getTranslate("Is_not_unique"), "Error", JOptionPane.ERROR_MESSAGE);	
					}								
					if(a.isForeignKey()){
						if(t.isNotValidFKey(a, ss)){
							valid=false;
							JOptionPane.showMessageDialog(atf, Sistem.getInstance().getTranslate("Entered_value_of")+a.toString()+Sistem.getInstance().getTranslate("Does_not_Exist_in"), "Error", JOptionPane.ERROR_MESSAGE);	
						}
					}
				}
				v.setCode(a.getName().getCode());
				v.setTip(Tip.VARCHAR);
				v.setValue(ss);
				torka.add(v);
				break;
			default:
				break;				
			}
			i++;			
		}
		if(valid){
			RelationalUpdateTuple rut = new RelationalUpdateTuple(torka, t.getNaziv().getCode(), fkey);
			rut.doCommand();
			EditTorkaFrame.getInstance(t).destroy();
			Tabs.refreshTable(t);
		}
	}

}
