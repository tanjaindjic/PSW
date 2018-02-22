package listeners;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.tree.TreeNode;

import editorSeme.controller.command.AddTable;
import editorSeme.controller.command.CommandManager;
import editorSeme.controller.concreetBuilders.DefaultTableBuilder;
import editorSeme.model.pojo.Atribut;
import editorSeme.model.pojo.Package;
import editorSeme.model.pojo.Sistem;
import editorSeme.model.pojo.Table;
import editorSeme.view.EditorWorkbench;
import workingsection.MainWindow;
import workingsection.Tabs;
import workingsection.WorkArea;
import workingsection.tree.Tree;
/**
 * Saves created Table.
 *
 */
public class NewTableSaveListener implements ActionListener {

	private DefaultTableBuilder dtb;
	private JTextField code;
	private JList pkey;
	@Override
	

	/**
	 * Creates and saves new Table in System.
	 */
	public void actionPerformed(ActionEvent arg0) {
		dtb.BuildName(code.getText().toString());
		int index = pkey.getSelectedIndex();
		if(index==-1){
			JOptionPane.showMessageDialog(MainWindow.getInstance(), Sistem.getInstance().getTranslate("No_prim_key"), Sistem.getInstance().getTranslate("Warning"),
			        JOptionPane.WARNING_MESSAGE);
			return;
		}
		dtb.buildPKey(index);
		
		
		Tree t = Tree.getInstance();
		
		TreeNode node = (TreeNode) t.getSelected();
		
		if(!(node instanceof Package )) {
			while(!(node.getParent() instanceof Package) ) {
				node= node.getParent();
			}
			node= node.getParent();
		}
		
		dtb.setP((Package) node);
		
	
		Table test = dtb.getTable();
		
		if(test != null){
			CommandManager.getInstance().addAbstractCommand(new AddTable( (Package) node , test ));
			
			Tabs.addTab(dtb.getTable());
			EditorWorkbench.getBtnNewPackage().setEnabled(true);
			EditorWorkbench.getBtnNewSubSys().setEnabled(true);

			EditorWorkbench.getBtnNewTable().setEnabled(true);
	/*		WorkArea wa = MainWindow.getWorkArea();
			wa.remove(EditorWorkbench.getInstance());
			wa.remove(Tabs.getInstance());
			wa.repaint();
			wa.validate();
			wa.add(Tabs.getInstance());
			wa.repaint();
			wa.validate();*/
			
			MainWindow.getInstance().validate();
			MainWindow.getInstance().repaint();
			CardLayout cardLayout = (CardLayout) EditorWorkbench.getOptPanel().getLayout();
			cardLayout.show(EditorWorkbench.getOptPanel(), "opcije");
			EditorWorkbench.reloadSplitPane();
			if(test.getPolja().get(0).getParentModel()==null){
				for(Atribut a0 : test.getPolja())
					a0.setParentModel(test);
			}
		}
	}
	
	public NewTableSaveListener(DefaultTableBuilder d, JTextField t, JList l){
		this.dtb = d;
		this.code=t;
		this.pkey=l;
	}
	
}