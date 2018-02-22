package listeners;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import db.connection.DBChooser;
import editorSeme.view.EditorWorkbench;

/**
 * Enables option to change from JSON Database to Relational and to change language.
 */
public class CancelJSONChooser implements ActionListener {

	/**
	 * Goes back to panel for choosing Database type and language.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		CardLayout cardLayout = (CardLayout) DBChooser.getInstance().getContentPanel().getLayout();
		cardLayout.show(DBChooser.getInstance().getContentPanel(), "optPanel");
		DBChooser.getInstance().getJSONpanel().removeAll();
	//	EditorWorkbench.reloadSplitPane();
	}

}
