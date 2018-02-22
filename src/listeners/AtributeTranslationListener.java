package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import dialogs.TranslationFrame;
import editorSeme.controller.concreetBuilders.DefaultAtributBuilder;

/**
 * Calls appropriate Translation dialog for translating an Attribute.
 */
public class AtributeTranslationListener implements ActionListener {

	
	private DefaultAtributBuilder dab;
	private JTextField s;
	

	/**
	 * Creates a dialog for Translation.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		TranslationFrame tf = new TranslationFrame(dab, s);
		tf.setVisible(true);
		
		
	}
	
	/**
	 * Propagation of parameters needed to create Translation of an Attribute.
	 * @param d is default builder for Attributes.
	 * @param s code of Attribute that is being translated.
	 */
	public AtributeTranslationListener(DefaultAtributBuilder d, JTextField s){
		this.dab= d;
		this.s=s;
	}
}
