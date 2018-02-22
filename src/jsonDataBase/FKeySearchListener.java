package jsonDataBase;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import db.crud.RelationalRead;
import editorSeme.model.pojo.Atribut;
import editorSeme.model.pojo.Table;
import jsonDataBase.additional.JSONDataSerialize;
import jsonDataBase.model.Data;
import jsonDataBase.model.DataTabela;
import start.DatabaseType;
import start.InfViewModel;
/**
 * Class that filters content of table in ChooseFKeyDialog
 *
 */
public class FKeySearchListener implements KeyListener {
	private JTextField tfield;
	private JTable table;
	private Table t;
	/**
	 * Constructor which sets neccessery params
	 * @param seatchField - textField that determines content od jTable
	 * @param table1 - table from ChooseFKeyDialog which content is  changable
	 * @param t - Table that is reflected on jtable table1
	 */
	public FKeySearchListener(JTextField seatchField, JTable table1, Table t) {
		this.tfield = seatchField;
		this.table=table1;
		this.t=t;
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
	/**
	 * Method that filters content/rows of the table1, depending on the content of tfield
	 */
	@Override
	public void keyReleased(KeyEvent arg0) {
		if(InfViewModel.getInstance().getDatabaseType().equals(DatabaseType.JSON)){
			DataTabela dt = JSONDataSerialize.getDataTable(t);
			String input = tfield.getText();
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			for(int i=table.getRowCount()-1; i>=0; i--){
				model.removeRow(0);
			}
			for(Data d0 : dt.getTorke()){
				boolean gotIt=false;
				ArrayList<Atribut> a = t.getPolja();
				for(Atribut a0 : a){
					if(gotIt){
						continue;
					}
					String value = (d0.getTorka().get(a0.getName().getCode())).toString();
					if(value.contains(input)){
						gotIt=true;
						Vector row = new Vector();
						for(int count=0; count<model.getColumnCount(); count++){
							row.add(d0.getTorka().get(t.getPolja().get(count).getName().getCode()).toString());
						}
					    model.addRow(row);
					}
				}
			}
			return;
		}
		String input = tfield.getText();
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		for(int i=table.getRowCount()-1; i>=0; i--){
			model.removeRow(0);
		}
		RelationalRead rr = new RelationalRead();
		ResultSet rs = (ResultSet) rr.readTable(t.getNaziv().getCode());
		ArrayList<Dimension> indeksi = new ArrayList<Dimension>();
		try {
			while(rs.next()){
				Vector row = new Vector();
				boolean gotIt = false;
				for(int columnIndex = 1; columnIndex <= t.getPolja().size(); columnIndex++){
					String value = (String) rs.getObject(columnIndex);
					row.add(value);
					if(value.contains(input)){
						gotIt=true;
					}
				}
				if(gotIt)
					model.addRow(row);
			}
		} catch (SQLException e) {
			System.out.println("greska sa bazom (FKeySearchListener -> 92)");
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
