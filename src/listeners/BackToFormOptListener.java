package listeners;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import editorSeme.view.EditorWorkbench;

/**
 * Enables option to go back from Form for creating a new Object to Options panel.
 */
public class BackToFormOptListener implements ActionListener {

	/**
	 * Closes active Form for creating an Object and opens Options panel.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		//EditorWorkbench.setActiveFormPanel(new NewSystemFrame());
		EditorWorkbench.getInstance().validate();
		EditorWorkbench.getInstance().repaint();
		CardLayout cardLayout = (CardLayout) EditorWorkbench.getOptPanel().getLayout();
		cardLayout.show(EditorWorkbench.getOptPanel(), "opcije");
		EditorWorkbench.reloadSplitPane();
	}

}
