package workingsection;

import javax.swing.JMenuItem;

import editorSeme.model.additional.JSONSerialize;
import editorSeme.model.pojo.Sistem;
import listeners.AccountSettingsListener;
import listeners.LoadListener;
import listeners.NewSystemListener;
import listeners.SaveBtnListener;
import listeners.SignOutListener;
import start.DatabaseType;
import start.InfViewModel;

/**
 * Class that makes JMenuItem and adds listener to it
 *
 */

@SuppressWarnings("serial")
public class MenuItem extends JMenuItem{
	private String name;
	
	/**
	 * Constructor that sets up name and adds action listener to the instance of this class
	 * @param name is the name of Menu Item.
	 */
	public MenuItem(String name){
		this.name=name;
		
		setText(name);
		if(name.equals(Sistem.getInstance().getTranslate("New_System")))
			addActionListener(new NewSystemListener());
		else if(name.equals(Sistem.getInstance().getTranslate("Load")))
			addActionListener(new LoadListener());
		else if(name.equals(Sistem.getInstance().getTranslate("Settings")))
			addActionListener(new AccountSettingsListener());
		else if(name.equals(Sistem.getInstance().getTranslate("Sign_out")))
			addActionListener(new SignOutListener());
		else if(name.equals(Sistem.getInstance().getTranslate("Exit")))
			addActionListener(new ExitListener());
		else if(name.equals(Sistem.getInstance().getTranslate("Save")) || name.equals(Sistem.getInstance().getTranslate("Save_As"))){
			if(InfViewModel.getInstance().getDatabaseType().equals(DatabaseType.RELATIONAL)){
				setEnabled(false);
			}
			addActionListener(new SaveBtnListener());
		//	System.out.println("dodao save btn");
		}
	}
	/**
	 * getter for name
	 */
	public String getName() {
		return name;
	}
	/**
	 * setter for name
	 */
	public void setName(String name) {
		this.name = name;
	}
	

	
}
