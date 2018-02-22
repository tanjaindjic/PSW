package editorSeme.model.additional;

import java.awt.Component;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import editorSeme.model.pojo.Sistem;

/**
 * Class that enables saving JSON files.
 *
 */
public class JsonSaveAs {
	/**
	 * Static method that saves JSON
	 * @return Returns chosen location for saveing.
	 */
	public static String saveAs(){
		JFileChooser chooser = new JFileChooser();
		String path = "";
		chooser.setAcceptAllFileFilterUsed(false);
		//chooser.setFileFilter(filter);
		
		chooser.setAcceptAllFileFilterUsed(false);
		int response=chooser.showSaveDialog(null);
		
		if( response ==JFileChooser.APPROVE_OPTION){
			String filename = chooser.getSelectedFile().getName();
			String dir = chooser.getCurrentDirectory().toString();
			if(filename.isEmpty()){
				JOptionPane.showMessageDialog((Component)null, "Filename is not valid.");
			}
			else if(dir.endsWith("\\"))
				path = dir + filename + ".json";
			else
				path = dir + "\\" + filename + ".json";			
		}
		if( path !=""){
			Sistem.getInstance().setPath(path);
		}
		return path;
	}

}
