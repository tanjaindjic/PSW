package jsonDataBase;

import javax.swing.JOptionPane;

import editorSeme.model.pojo.Sistem;
import editorSeme.model.pojo.Table;
import workingsection.Tabs;
/**
 * Class that is part of abstract factory patern
 */
public class TorkaFactory {
	/**
	 * Method that calls appropriate method so that right Object is made
	 * @param option - string that provides information on the type of the frame that has to be made
	 */
	public static void makeTorkaFrame(String option){
		int index = Tabs.getInstance().getTabele().getSelectedIndex();
		if(index==-1){	
			JOptionPane.showMessageDialog(null,
					Sistem.getInstance().getTranslate("No_Tab_selected"));
			return;
		}
		String tableName = Tabs.getInstance().getTabele().getTitleAt(index);
		Table t = Sistem.getInstance().getTableByTabName(tableName);
		TorkaFrame tf = null;
		if(option.equals("Add Data"))
			tf = AddTorkaFrame.getInstance(t);
		else if(option.equals("Edit Data"))
			tf = EditTorkaFrame.getInstance(t);
		
		if(tf!=null)
			tf.setVisible(true);
	}
}
