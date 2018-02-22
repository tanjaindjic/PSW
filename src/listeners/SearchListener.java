package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.table.DefaultTableModel;

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
 * Class that filters table content
 *
 */
public class SearchListener implements ActionListener{
	private JTextField tf;
	/**
	 * Constructors that provides reference to textField that contains key words for search
	 * @param tf Text field for listener.
	 */
	public SearchListener(JTextField tf) {
		this.tf = tf;
	}
	/**
	 * Method that, depending on dataBase type adds or deletes rows from the table, according to content of search field
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		int index = Tabs.getInstance().getTabele().getSelectedIndex();
		JPanel jpanel = (JPanel) Tabs.getInstance().getTabele().getComponentAt(index);
		JScrollPane jscrollpane = (JScrollPane) jpanel.getComponent(0);
		JViewport jviewport = (JViewport) jscrollpane.getComponent(0);
		JTable jtable = (JTable) jviewport.getComponent(0);
		
		String search = tf.getText();
		Table t = Sistem.getInstance().getTableByTabName(jpanel.getName());
		DefaultTableModel model = (DefaultTableModel) jtable.getModel();
		if(InfViewModel.getInstance().getDatabaseType().equals(DatabaseType.RELATIONAL)){
			int rowNum = jtable.getRowCount();
			for(int row=0; row<rowNum; row++){
				boolean delete = true;
				for(int col=0; col<jtable.getColumnCount(); col++){
					String value = jtable.getValueAt(row, col).toString();
					if(value.contains(search)){
						delete = false;
					}
				}
				if(delete){
					model.removeRow(row);
					row--;
					rowNum = jtable.getRowCount();
				}
			}
		}
		else{			
			for(int i=jtable.getRowCount()-1; i>=0; i--){
				model.removeRow(0);
			}
			DataTabela dt = JSONDataSerialize.getDataTable(t);
			
			for(Data d0 : dt.getTorke()){
				boolean gotIt=false;
				ArrayList<Atribut> a = t.getPolja();
				for(Atribut a0 : a){
					if(gotIt){
						continue;
					}
					String value = (d0.getTorka().get(a0.getName().getCode())).toString();
					if(value.contains(search)){
						gotIt=true;
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
	

}
