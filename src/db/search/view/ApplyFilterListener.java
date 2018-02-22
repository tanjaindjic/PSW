package db.search.view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.chrono.IsoChronology;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.table.DefaultTableModel;

import db.search.RelationalSearch;
import db.search.SearchType;
import editorSeme.model.enums.Tip;
import editorSeme.model.pojo.Atribut;
import editorSeme.model.pojo.Sistem;
import editorSeme.model.pojo.Table;
import jsonDataBase.additional.JSONDataSerialize;
import jsonDataBase.model.Data;
import jsonDataBase.model.DataTabela;
import start.DatabaseType;
import start.InfViewModel;
import workingsection.Tabs;
/**
 * Listener for filtering the table content
 *
 */
public class ApplyFilterListener implements ActionListener {
	private Table t;
	/**
	 * Regular constructor, sets the param of table
	 * @param t - table for which filter is going to be added
	 */
	public ApplyFilterListener(Table t){
		this.t=t;
	}
	/**
	 * Method that changes the content of table, applies filter to the table 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		FilterDialog fd = FilterDialog.getInstance();
		HashMap<String, Component> polja = fd.polja;
		int selTable = Tabs.getInstance().getTabele().getSelectedIndex();
		JPanel jpanel = (JPanel) Tabs.getInstance().getTabele().getComponentAt(selTable);
		JScrollPane jscrollpane = (JScrollPane) jpanel.getComponent(0);
		JViewport jviewport = (JViewport) jscrollpane.getComponent(0);
		JTable jtable = (JTable) jviewport.getComponent(0);
		DefaultTableModel model = (DefaultTableModel) jtable.getModel();
		int rowsCount = model.getRowCount();
		while(rowsCount>0){
			model.removeRow(0);
			rowsCount--;
		}
		if(InfViewModel.getInstance().getDatabaseType().equals(DatabaseType.RELATIONAL)){			
			RelationalSearch relSearch = new RelationalSearch(t.getNaziv().getCode());
			ArrayList<String> atributteName = new ArrayList<String>();
			ArrayList<Object> attributeValue = new ArrayList<Object>();
			ArrayList<SearchType> type = new ArrayList<>();
		
			for(Atribut a: t.getPolja()){
				if(a.getDomain().getTip().equals(Tip.BOOLEAN)){
					atributteName.add(a.getName().getCode());
					type.add(SearchType.BOOLEAN);
					if(((JRadioButton)polja.get(a.getName().getCode()+"T")).isSelected()){
						attributeValue.add(true);
					}
					else if(((JRadioButton)polja.get(a.getName().getCode()+"F")).isSelected()){
						attributeValue.add(false);						
					}
				}
				else if(a.getDomain().getTip().equals(Tip.CHAR) || a.getDomain().getTip().equals(Tip.VARCHAR)){
					if(((JTextField)polja.get(a.getName().getCode())).getText().isEmpty())
						continue;
					atributteName.add(a.getName().getCode());
					type.add(SearchType.STRING);
					attributeValue.add(((JTextField)polja.get(a.getName().getCode())).getText());			
				}
				else{
					if(!((JTextField)polja.get(a.getName().getCode()+"MIN")).getText().isEmpty()){
						try{
							float min = Float.parseFloat(((JTextField)polja.get(a.getName().getCode()+"MIN")).getText());
							attributeValue.add(min);
							atributteName.add(a.getName().getCode());
							type.add(SearchType.MIN);
						}catch(Exception ee){
							JOptionPane.showMessageDialog(null, a.toString()+Sistem.getInstance().getTranslate("Filter_format"), "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
					if(!((JTextField)polja.get(a.getName().getCode()+"MAX")).getText().isEmpty()){						
						try{
							float max = Float.parseFloat(((JTextField)polja.get(a.getName().getCode()+"MAX")).getText());
							attributeValue.add(max);
							atributteName.add(a.getName().getCode());
							type.add(SearchType.MAX);
						}catch(Exception ee){
							JOptionPane.showMessageDialog(null, a.toString()+Sistem.getInstance().getTranslate("Filter_format"), "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}		
			ResultSet rs= (ResultSet) relSearch.MultiObjectSearch(atributteName, attributeValue, type);
			if(rs!=null){
				try {
					while(rs.next()){
						Vector row = new Vector();
						for (int columnIndex = 1; columnIndex <= t.getPolja().size(); columnIndex++) {
					        try {
								row.add(rs.getObject(columnIndex));
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
					    }
					    model.addRow(row);
					}
				} catch (SQLException e2) {
				}
			}
			
		}else{
			DataTabela dt = JSONDataSerialize.getDataTable(t);
			for(Data d0 : dt.getTorke()){
				boolean belongs = true;
				int i=0;
				for(Atribut a0 : t.getPolja()){
					if(a0.getDomain().getTip().equals(Tip.BOOLEAN)){
						if( ((JRadioButton)polja.get(a0.getName().getCode()+"T")).isSelected()){
							if(!d0.getTorka().get(a0.getName().getCode()).equals(true)){
								belongs=false;
							}
						}
						else if( ((JRadioButton)polja.get(a0.getName().getCode()+"F")).isSelected()){
							if(!d0.getTorka().get(a0.getName().getCode()).equals(false)){
								belongs=false;
							}
						}
						else{
							belongs=true;
						}
					}
					else if(a0.getDomain().getTip().equals(Tip.VARCHAR) || a0.getDomain().getTip().equals(Tip.CHAR)){
						String value = ((JTextField)polja.get(a0.getName().getCode())).getText().trim();
						if(value!=""){
							String torkaValue = d0.getTorka().get(a0.getName().getCode()).toString();
							if(!torkaValue.contains(value))
								belongs=false;
						}
					}
					else{
						int i00 = i;
						if(((JTextField)polja.get(i00++)).getText().toString().isEmpty() && ((JTextField)polja.get(i00++)).getText().toString().isEmpty())
							continue;
						String from = "";
						String to ="";
						if(!((JTextField)polja.get(a0.getName().getCode()+"MIN")).getText().toString().isEmpty())
							from = ((JTextField)polja.get(a0.getName().getCode()+"MIN")).getText().toString();
						if(!((JTextField)polja.get(a0.getName().getCode()+"MAX")).getText().toString().isEmpty())
							to = ((JTextField)polja.get(a0.getName().getCode()+"MAX")).getText().toString();
						switch(a0.getDomain().getTip()){
							case BIGINT:
								try{
									BigDecimal f;
									BigDecimal t;
									BigDecimal v = new BigDecimal((char[]) d0.getTorka().get(a0.getName().getCode().toString()));	
									
									if(!from.isEmpty()){
										f = new BigDecimal(from);
										if(v.doubleValue()<f.doubleValue())
											belongs=false;											
									}
									if(!to.isEmpty()){
										t = new BigDecimal(to);	
										if(v.doubleValue()>t.doubleValue())
											belongs=false;											
									}
								}catch (Exception e2) {
									if(!from.isEmpty() || !to.isEmpty())
										JOptionPane.showMessageDialog(null, a0.toString()+Sistem.getInstance().getTranslate("Filter_format") , Sistem.getInstance().getTranslate("Error"),  JOptionPane.ERROR_MESSAGE);
								}
								break;
							case DOUBLE:
								try{	
									if(!from.isEmpty()){
										double f = Double.parseDouble(from);
										if((double)d0.getTorka().get(a0.getName().getCode())<f)
											belongs=false;											
									}
									if(!to.isEmpty()){
										double t = Double.parseDouble(to);
										if((double)d0.getTorka().get(a0.getName().getCode())>t)
											belongs=false;											
									}
								}catch (Exception e2) {
									if(!from.isEmpty() || !to.isEmpty())
										JOptionPane.showMessageDialog(null, a0.toString()+Sistem.getInstance().getTranslate("Filter_format") , Sistem.getInstance().getTranslate("Error"),  JOptionPane.ERROR_MESSAGE);
								}
								break;
							case FLOAT:
								try{
									if(!from.isEmpty()){
										float f = Float.parseFloat(from);
										if((float)d0.getTorka().get(a0.getName().getCode())<f)
											belongs=false;											
									}
									if(!to.isEmpty()){
										float t = Float.parseFloat(to);	
										if((float)d0.getTorka().get(a0.getName().getCode())>t)
											belongs=false;											
									}
								}catch (Exception e2) {
									if(!from.isEmpty() || !to.isEmpty())
										JOptionPane.showMessageDialog(null, a0.toString()+Sistem.getInstance().getTranslate("Filter_format") , Sistem.getInstance().getTranslate("Error"),  JOptionPane.ERROR_MESSAGE);
								}
								break;
							case INT:
								try{	
									if(!from.isEmpty()){
										int f = Integer.parseInt(from);
										if((Integer)d0.getTorka().get(a0.getName().getCode())<f)
											belongs=false;											
									}
									if(!to.isEmpty()){
										int t = Integer.parseInt(to);
										if((Integer)d0.getTorka().get(a0.getName().getCode())>t)
											belongs=false;											
									}
								}catch (Exception e2) {
									if(!from.isEmpty() || !to.isEmpty())
										JOptionPane.showMessageDialog(null, a0.toString()+Sistem.getInstance().getTranslate("Filter_format") , Sistem.getInstance().getTranslate("Error"),  JOptionPane.ERROR_MESSAGE);
								}
								break;
							case NUMERIC:
								try{
									BigDecimal v = new BigDecimal((char[]) d0.getTorka().get(a0.getName().getCode().toString()));	
									
									if(!from.isEmpty()){
										BigDecimal f = new BigDecimal(from);
										if(v.doubleValue()<f.doubleValue())
											belongs=false;											
									}
									if(!to.isEmpty()){
										BigDecimal t = new BigDecimal(to);	
										if(v.doubleValue()>t.doubleValue())
											belongs=false;											
									}
								}catch (Exception e2) {
									if(!from.isEmpty() || !to.isEmpty())
										JOptionPane.showMessageDialog(null, a0.toString()+Sistem.getInstance().getTranslate("Filter_format") , Sistem.getInstance().getTranslate("Error"),  JOptionPane.ERROR_MESSAGE);
								}
								break;
							case SMALINT:
								try{
									Short v = Short.parseShort( (String) d0.getTorka().get(a0.getName().getCode().toString()));	
									
									if(!from.isEmpty()){
										Short f = Short.parseShort(from);
										if(v<f)
											belongs=false;											
									}
									if(!to.isEmpty()){
										Short t = Short.parseShort(to);	
										if(v>t)
											belongs=false;											
									}
								}catch (Exception e2) {
									if(!from.isEmpty() || !to.isEmpty())
										JOptionPane.showMessageDialog(null, a0.toString()+Sistem.getInstance().getTranslate("Filter_format") , Sistem.getInstance().getTranslate("Error"),  JOptionPane.ERROR_MESSAGE);
								}
								break;
							case DATE:
								try{
									Date v = Date.valueOf((String) d0.getTorka().get(a0.getName().getCode().toString()));
									if(!from.isEmpty()){
										Date f = Date.valueOf(from);
										if(v.before(f))
											belongs=false;											
									}
									if(!to.isEmpty()){
										Date t = Date.valueOf(to);
										if(v.after(t))
											belongs=false;											
									}
								}catch (Exception e2) {
									JOptionPane.showMessageDialog(null, a0.toString()+Sistem.getInstance().getTranslate("Filter_format_date") , Sistem.getInstance().getTranslate("Error"),  JOptionPane.ERROR_MESSAGE);
								}
								break;
							case DATETIME:
								try{
									Timestamp v = Timestamp.valueOf((String) d0.getTorka().get(a0.getName().getCode().toString()));
									if(!from.isEmpty()){
										Timestamp f = Timestamp.valueOf(from);
										if(v.before(f))
											belongs=false;											
									}
									if(!to.isEmpty()){
										Timestamp t = Timestamp.valueOf(to);
										if(v.after(t))
											belongs=false;											
									}
								}catch (Exception e2) {
									JOptionPane.showMessageDialog(null, a0.toString()+Sistem.getInstance().getTranslate("Filter_format_datetime") , Sistem.getInstance().getTranslate("Error"),  JOptionPane.ERROR_MESSAGE);
								}
								break;
							case TIME:
								try{
									Time v = Time.valueOf((String) d0.getTorka().get(a0.getName().getCode().toString()));
									if(!from.isEmpty()){
										Time f = Time.valueOf(from);
										if(v.before(f))
											belongs=false;											
									}
									if(!to.isEmpty()){
										Time t = Time.valueOf(to);
										if(v.after(t))
											belongs=false;											
									}
								}catch (Exception e2) {
									JOptionPane.showMessageDialog(null, a0.toString()+Sistem.getInstance().getTranslate("Filter_format_time") , Sistem.getInstance().getTranslate("Error"),  JOptionPane.ERROR_MESSAGE);
								}
								break;
							default:
								break;	
						}
					}
				}
				if(belongs){
					Vector row = new Vector();
					for(int count=0; count<model.getColumnCount(); count++){
						row.add(d0.getTorka().get(t.getPolja().get(count).getName().getCode()).toString());
					}
				    model.addRow(row);
				}
			}
		}
	}

}
