package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import editorSeme.model.additional.JSONSerialize;
import editorSeme.model.additional.JsonSaveAs;
import editorSeme.model.pojo.Sistem;
import login.LoginFrame;
import start.DatabaseType;
import start.InfViewModel;
import workingsection.MainWindow;
/**
 * Class that  provides singing out
 *
 */
public class SignOutListener implements ActionListener{
	/**
	 * Method that depending on the type of the base offers saving of database
	 * or just quiting the main window. Application is redirected to logInFrame
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(InfViewModel.getInstance().getDatabaseType().equals(DatabaseType.JSON)){
			int n = JOptionPane.showOptionDialog(null, Sistem.getInstance().getTranslate("Save_changes"), "",
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, 2);
	
			if(n==0){
				if(Sistem.getInstance().getPath()==null)
					Sistem.getInstance().setPath(JsonSaveAs.saveAs());
				else if(Sistem.getInstance().getPath().isEmpty())
					Sistem.getInstance().setPath(JsonSaveAs.saveAs());
				JSONSerialize.saveObj(Sistem.getInstance());
			}
			else if(n==2)
				return;
		}
		MainWindow.getInstance().dispose();
		LoginFrame.getInstance().textName.setText("");
		LoginFrame.getInstance().passField.setText("");
		LoginFrame.getInstance().setVisible(true);
		
	}
	
	
}
