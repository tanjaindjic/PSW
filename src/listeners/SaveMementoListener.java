package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import editorSeme.controller.memento.Originator;
import editorSeme.model.additional.JSONSerialize;
import editorSeme.model.pojo.Sistem;

/**
 * 
 * Action listener that saves a version of the current schema.
 *
 */
public class SaveMementoListener implements ActionListener {

	/**
	 * Saves a current valid version of the JSON database schema.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(Sistem.getInstance().validate()) {
			String s = JSONSerialize.getJsonText();
			Originator.getInstance().createMemento(s);
			Originator.getInstance().SetCurrent(s);
		}
		else{
			JOptionPane.showMessageDialog(null, Sistem.getInstance().getTranslate("saveMemento"));
		}
		
	}

}
