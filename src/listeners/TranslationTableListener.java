package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import dialogs.TranslationFrame;
import editorSeme.controller.concreetBuilders.DefaultTableBuilder;

/**
 * Calls dialog for adding Translation for Table.
 */
public class TranslationTableListener implements ActionListener {

	
	private DefaultTableBuilder dpb;
	private JTextField s;
	
	/**
	 * Returns default Table builder.
	 * @return default Table builder.
	 */
	public DefaultTableBuilder getPackageBulder(){
		return dpb;
	}
	
	/**
	 * Opens a dialog for adding Table Translation.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		TranslationFrame tf = new TranslationFrame(dpb,s);
		tf.setVisible(true);
		
		
	}
	
	/**
	 * Propagation of parameters.
	 * @param d is default Table builder for temporary created Tables.
	 * @param s code of Table being translated.
	 */
	public TranslationTableListener(DefaultTableBuilder d, JTextField s){
		this.dpb= d;
		this.s=s;
	}

}
