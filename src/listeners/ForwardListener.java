package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import editorSeme.controller.memento.Originator;
import editorSeme.view.EditorWorkbench;

/**
 * 
 * Action listener that returns one version of the current schema.
 *
 */
public class ForwardListener implements ActionListener {

	/**
	 * Returns the next valid version of the JSON database schema.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String s = Originator.getInstance().getCurrentVersion();
		if(	!s.equals(""))
			EditorWorkbench.codeArea.setText(s);
		
	}

}
