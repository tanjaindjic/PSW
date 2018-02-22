package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import dialogs.EditPackageFrame;
import dialogs.EditTableFrame;
import dialogs.TranslationFrame;
import editorSeme.model.pojo.Atribut;
import editorSeme.model.pojo.Sistem;
/**
 * Calls appropriate method for adding Translation to an Object.
 * 
 */
public class AddTranslListener implements ActionListener {

	private EditPackageFrame editPackageFrame;
	private EditTableFrame etf;
	private Atribut selectedValue;
	private boolean isAttr = false;
	
	/**
	 * Propagates Edit Package Frame.
	 * @param editPackageFrame with information what Package is being edited.
	 */
	public AddTranslListener(EditPackageFrame editPackageFrame) {
		this.editPackageFrame = editPackageFrame;
		this.isAttr = false;
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Propagates Edit Table Frame.
	 * @param etf with information what Table is being edited.
	 */
	public AddTranslListener(EditTableFrame etf) {
		// TODO Auto-generated constructor stub
		this.etf = etf;
		this.isAttr = false;
	}

	/**
	 * Propagates Edit Package Frame with selected Attribute.
	 * @param etf with information what Table is being edited.
	 * @param selectedValue with information what Attribte is being edited.
	 */
	public AddTranslListener(EditTableFrame etf, Atribut selectedValue) {
		// TODO Auto-generated constructor stub
		this.etf = etf;
		this.selectedValue = selectedValue;
		this.isAttr = true;
		
	}

	/**
	 * Calls Translation Frame according to parameters being sent.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		TranslationFrame tf = null;
		if(isAttr){
			if(selectedValue == null){
				if(etf.getList().getSelectedValue()==null){
					JOptionPane.showMessageDialog(null, Sistem.getInstance().getTranslate("No_atr_selected"), Sistem.getInstance().getTranslate("Error"), JOptionPane.ERROR_MESSAGE);
					
					return;
				}
			}
			tf = new TranslationFrame(etf, true);
		}
			
		else{
			
			tf = new TranslationFrame(null,false);
		}
			
		tf.setVisible(true);
		if(editPackageFrame!=null)
			editPackageFrame.dispose();
		if(etf!=null)
			etf.dispose();
	}

}
