package jsonDataBase;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;

import db.crud.RelationalDeleteTuple;
import db.model.Torka;
import db.model.Value;
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
 * Class that provides deleting of tuple
 *
 */
public class DataDeleteListener implements ActionListener {
	/**
	 * Method that checks if there is selected row to be deleted, checks if row can be deleted
	 * (depending on foreign keys) and finally deletes it
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(InfViewModel.getInstance().getDatabaseType().equals(DatabaseType.RELATIONAL)){
			actionPerformedOnRelational();
			return;
		}
		int index = Tabs.getInstance().getTabele().getSelectedIndex();
		//kako dobiti tu konkretnu tabelu?
		JPanel jpanel = (JPanel) Tabs.getInstance().getTabele().getComponentAt(index);
		JScrollPane jscrollpane = (JScrollPane) jpanel.getComponent(0);
		JViewport jviewport = (JViewport) jscrollpane.getComponent(0);
		JTable jtable = (JTable) jviewport.getComponent(0);
		int temp = jtable.getSelectedRow();
		Table t = Sistem.getInstance().getTableByTabName(Tabs.getInstance().getTabele().getSelectedComponent().getName());
		if(temp==-1){
			JOptionPane.showMessageDialog(null, Sistem.getInstance().getTranslate("Row_To_Delete"));
			return;
		}
		
		DataTabela dt = JSONDataSerialize.getDataTable(t);
		if(isUsedTorka(dt, temp, t)){
			JOptionPane.showMessageDialog(null, Sistem.getInstance().getTranslate("Data_Not_Deleted"));
			return;
		}
		dt.getTorke().remove(temp);
		
		JSONDataSerialize.saveData(dt);
		Tabs.refreshTable(t);
	}

	private void actionPerformedOnRelational() {
		int index = Tabs.getInstance().getTabele().getSelectedIndex();
		JPanel jpanel = (JPanel) Tabs.getInstance().getTabele().getComponentAt(index);
		JScrollPane jscrollpane = (JScrollPane) jpanel.getComponent(0);
		JViewport jviewport = (JViewport) jscrollpane.getComponent(0);
		JTable jtable = (JTable) jviewport.getComponent(0);
		int temp = jtable.getSelectedRow();
		Table t = Sistem.getInstance().getTableByTabName(Tabs.getInstance().getTabele().getSelectedComponent().getName());
		if(temp==-1){
			JOptionPane.showMessageDialog(null, Sistem.getInstance().getTranslate("Row_To_Delete"));
			return;
		}
		Torka torka = new Torka();
		int column=0;
		for(Atribut a0 : t.getPolja()){
			Value v = new Value();
			v.setCode(a0.getName().getCode());
			v.setTip(a0.getDomain().getTip());
			v.setValue(jtable.getValueAt(temp, column));
			column++;
			torka.add(v);
		}
		ArrayList<String> fkey = new ArrayList<String>();
		Key k0 = t.getKeys().get(0);
		for(Id_Id id0 : k0.getIds()){
			fkey.add(id0.getAtributeKey());
		}
		RelationalDeleteTuple rdt = new RelationalDeleteTuple(torka, t.getNaziv().getCode(), fkey);
		if(rdt.doCommand()){
			JOptionPane.showMessageDialog(null, Sistem.getInstance().getTranslate("Data_Not_Deleted"));
			return;
		}
		Tabs.refreshTable(t);
	}

	private boolean isUsedTorka(DataTabela dt, int temp, Table t) {
		Data d = dt.getTorke().get(temp);
		for(Atribut a0 : t.getPolja()){
			if(a0.isConnectedAsKey())
				if(a0.isUsedAsKey(d.getTorka().get(a0.getName().getCode()))){
					return true;
				}
					
		}
		return false;
	}

}
