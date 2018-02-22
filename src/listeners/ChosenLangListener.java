package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

import javax.swing.JComboBox;

import dialogs.AdminLang;
import login.AdminFrame;

/**
 * Sets chosen language for User. 
 */
public class ChosenLangListener implements ActionListener {

	private JComboBox selectedItem;
	private AdminLang adminLang;
	
	/**
	 * Propagation of parameters for language setup.
	 * @param adminLang dialog containing information about language selection.
	 * @param selectedItem contains list of supported languages and carries information what language is chosen.
	 */
	public ChosenLangListener(AdminLang adminLang, JComboBox selectedItem) {
		// TODO Auto-generated constructor stub
		this.selectedItem = selectedItem;
		this.adminLang = adminLang;
		
	}

	/**
	 * Sets up language for Administrator Users..
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(selectedItem.getSelectedItem().toString().equals("English")){
			Locale.setDefault(new Locale("en", "US"));
		}
			
		else if(selectedItem.getSelectedItem().toString().equals("Serbian")){
			Locale.setDefault(new Locale("sr", "RS"));
			
		}
			
		else{
			Locale.setDefault(new Locale("en", "USs"));
			
		}
		
		AdminFrame af = AdminFrame.getInstance();
		af.show();
		adminLang.dispose();
	}

}
