package listeners;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import editorSeme.model.pojo.Package;
import editorSeme.controller.command.AddPackage;
import editorSeme.controller.command.CommandManager;
import editorSeme.controller.concreetBuilders.DefaultPackageBuilder;
import editorSeme.model.enums.PackageType;
import editorSeme.view.EditorWorkbench;
import editorSeme.view.WrongParentException;
import workingsection.tree.Tree;

/**
 * Action listener class that enables the addition of a subsystem.
 * It tries to set the subsystem and closes the Domain frame. 
 *
 */
public class NewSubSystemSaveListener implements ActionListener {

	private DefaultPackageBuilder dpb;
	private JTextField code;
	/**
	 * If the inputed values are correct the package is added. 
	 * Otherwise it shows a warning message and informs the user that the inputed values aren't valid via the WrongParentException.
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		dpb.BuildName(code.getText().toString());
		dpb.buildType(PackageType.SUBSYSTEM);
		Tree t = Tree.getInstance();
		EditorWorkbench.getInstance().reloadJsonTekst();
		
		Package p = dpb.getPackage();
			if(p != null){
			
			try {
				Object o = t.getSelected();
			
				CommandManager.getInstance().addAbstractCommand(new AddPackage(t.getSelected(), p));
			} catch (WrongParentException e) {
	
				e.printStackTrace();
			}
			EditorWorkbench.getBtnNewPackage().setEnabled(true);
			EditorWorkbench.getBtnNewSubSys().setEnabled(true);

			EditorWorkbench.getBtnNewTable().setEnabled(true);
			EditorWorkbench.getInstance().validate();
			EditorWorkbench.getInstance().repaint();
			CardLayout cardLayout = (CardLayout) EditorWorkbench.getOptPanel().getLayout();
			cardLayout.show(EditorWorkbench.getOptPanel(), "opcije");
			EditorWorkbench.reloadSplitPane();
		}
	}
	/**
	 * Constructor for the NewSubSystemSaveListener.
	 * @param d Package builder that is used for making a model of the package.
	 * @param t The name of the subsystem.
	 */
	public NewSubSystemSaveListener(DefaultPackageBuilder d, JTextField t){
		this.dpb = d;
		this.code=t;
	}

}