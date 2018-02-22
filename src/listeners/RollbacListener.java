package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import editorSeme.controller.memento.Originator;
import editorSeme.view.EditorWorkbench;

/**
 * 
 * Action listener that returns a previous version of the current schema.
 *
 */
public class RollbacListener implements ActionListener {

	/**
	 * Returns the previous valid version of the JSON database schema.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String s = Originator.getInstance().restoreMemento();
		if(	!s.equals(""))
			EditorWorkbench.codeArea.setText(s);
		
	
	}

}
