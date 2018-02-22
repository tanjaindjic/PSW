package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import dialogs.TranslationFrame;
import editorSeme.controller.concreetBuilders.DefaultBuilder;

/**
 * Creates dialog for adding a Translation.
 */
public class AddTranslationListener implements ActionListener {

	
	private DefaultBuilder db;
	private JTextField s;
	

	/**
	 * Calls dialog for creating new Translation.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		TranslationFrame tf = new TranslationFrame(db, s);
		tf.setVisible(true);
	
		
	}
	
	/**
	 * Calls dialog for creating new Translation.
	 * @param d default builder for Tables, Attributes and Packages.
	 * @param s code of an Object that is being translated.
	 */
	public AddTranslationListener(DefaultBuilder d, JTextField s){
		
		this.db= d;
		this.s= s;
	}

}
