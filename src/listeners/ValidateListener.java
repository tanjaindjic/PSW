package listeners;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JOptionPane;

import com.fasterxml.jackson.databind.ObjectMapper;

import editorSeme.controller.memento.Memento;
import editorSeme.controller.memento.Originator;
import editorSeme.model.additional.JSONSerialize;
import editorSeme.model.pojo.Sistem;
import editorSeme.view.EditorWorkbench;
/**
 * Class that is called to check if json text from editor is valid
 *
 */
public class ValidateListener implements ActionListener {
	/**
	 * Method that checks if text is valid by trying to save it in json file 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		writeDown();
		//s.validate();
		//String json = EditorWorkbench.getInstance().getJsonTekst();
		String loc = System.getProperty("user.dir")+"\\"+"src\\preview.json";
		ObjectMapper mapper = new ObjectMapper();
		try {
			String path = Sistem.getInstance().getPath();
			if(path == null) {
				path = System.getProperty("user.dir")+"\\"+"src\\valid.json";
			}
				
			String tempL = System.getProperty("user.dir")+"\\"+"src\\valid.json";
			Sistem.getInstance().setPath(tempL);
			JSONSerialize.saveStructure(Sistem.getInstance());
			Sistem.getInstance().setPath(path);
			Memento m = new Memento( JSONSerialize.getJsonText(path));
			Originator.getInstance().setValid(m);
			
			
			Sistem.getInstance().addSistem((Sistem)mapper.readValue(new File(loc), Sistem.class));
			if(Sistem.getInstance().validate()) {
				
				JOptionPane.showMessageDialog((Component)null, Sistem.getInstance().getTranslate("Good_JSON"));
				return;
			}else {
				
			
				JOptionPane.showMessageDialog((Component)null, Sistem.getInstance().getTranslate("Bad_JSON"));
	            return;
			}
        } catch (IOException ex1) {
            JOptionPane.showMessageDialog((Component)null, Sistem.getInstance().getTranslate("Bad_JSON"));
            return;
        }
		//JOptionPane.showMessageDialog((Component)null, Sistem.getInstance().getTranslate("Good_JSON"));
	}
	/**
	 * Method that is called to formattext from editor
	 */
	private void writeDown() {
		BufferedWriter writer = null;
        try {
        	FileWriter fw = new FileWriter(System.getProperty("user.dir")+"\\"+"src\\preview.json");
        	BufferedWriter bw = new BufferedWriter(fw);
        	String n = EditorWorkbench.getCodeArea().getText();
        	n = n.replace("\n", "");
        	bw.write(n);
            bw.close();
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
            } catch (Exception e) {
            }
        }
	}

}
