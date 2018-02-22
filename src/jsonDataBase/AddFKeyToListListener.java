package jsonDataBase;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import db.crud.RelationalRead;
import editorSeme.model.pojo.Atribut;
import editorSeme.model.pojo.FKey;
import editorSeme.model.pojo.Sistem;
import editorSeme.model.pojo.Table;
import jsonDataBase.additional.JSONDataSerialize;
import jsonDataBase.model.Data;
import jsonDataBase.model.DataTabela;
import start.DatabaseType;
import start.InfViewModel;
/**
 *  Listener that is connected to OK button in ChooseFKeyDialog
 *	Provides that chosen value in dialog is set as text in compatible textField
 */
public class AddFKeyToListListener implements ActionListener {
	private JTable table;
	private JTextField jtf;
	private Atribut a;
	private Table t;
	/**
	 * Constructor that provides correspondence of ChooseFKeyDialog and AddTorkaFrame/EditTorkaFrame
	 * @param table1 - jTable that is shown in ChooseFKeyDialog
	 * @param jtf - textField whose content needs to be set, gets value from table1 selected row 
	 * @param a - Atribut whose value is used as foreign key
	 * @param t - Parent table that contains atribut used as foreign key
	 */
	public AddFKeyToListListener(JTable table1, JTextField jtf, Atribut a, Table t) {
		this.table=table1;
		this.jtf=jtf;
		this.a=a;
		this.t=t;
	}
	/**
	 * Finds selected row and sets text of jtf to choosen and coresponding value
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		int i = table.getSelectedRow();
		if(i==-1){
			JOptionPane.showMessageDialog(jtf, Sistem.getInstance().getTranslate("No_Row_selected"), "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		if(InfViewModel.getInstance().getDatabaseType().equals(DatabaseType.RELATIONAL)){
			actionPerformedRelationa();
			return;
		}
		DataTabela dt = JSONDataSerialize.getDataTable(t);
		Data d = dt.getTorke().get(i);
		Table t0 = Sistem.getInstance().getTableByCodeName(a.getParentModel().getNaziv().getCode());
		String code="";
		for(FKey fk0 : t0.getfKeys()){
			int ind=0;
			for(String s0 : fk0.getHomeIds()){
				if(s0.equals(a.getName().getCode()))
					code=fk0.getForeignIds().get(ind);
				ind++;
			}
		}
		if(code!="")
			jtf.setText(d.getTorka().get(code).toString());
		
		ChooseFKeyDialog.getInstance(null, null).destroy();

	}
	/**
	 * In case database is relational, Finds selected row and sets text of jtf to choosen and coresponding value
	 */
	private void actionPerformedRelationa() {
		Table t0 = Sistem.getInstance().getTableByCodeName(a.getParentModel().getNaziv().getCode());
		int i = table.getSelectedRow();
		int kolona = -1;
		for(int k=0; k< t0.getPolja().size(); k++){
			if(a.getName().getCode().equals(t0.getPolja().get(k).getName().getCode()))
				kolona=k;
		}
		if(kolona==-1){
			return;
		}
		jtf.setText((String) table.getValueAt(i, kolona+1));

		ChooseFKeyDialog.getInstance(null, null).destroy();

	}

}
