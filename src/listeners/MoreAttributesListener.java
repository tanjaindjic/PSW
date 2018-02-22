package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import dialogs.AddAttributeInTableFrame;
import dialogs.EditTableFrame;
import editorSeme.controller.concreetBuilders.DefaultAtributBuilder;
import editorSeme.model.additional.JSONSerialize;
import editorSeme.model.pojo.Atribut;
import editorSeme.model.pojo.Sistem;
import editorSeme.model.pojo.Table;
import editorSeme.view.EditorWorkbench;
import workingsection.MainWindow;
import workingsection.Tabs;
import workingsection.tree.Tree;

/**
 * Adds more Attributes to a Table.
 */
public class MoreAttributesListener implements ActionListener {

	private AddAttributeInTableFrame addAttributeInTableFrame;
	private DefaultAtributBuilder dab;
	private EditTableFrame etf;
	
	/**
	 * Sets parameters with information which Table is being edited and with created temporary Attribute to be added.
	 * @param addAttributeInTableFrame contains code and Domain for Attribute.
	 * @param dab temporary Attribute for adding to Table.
	 * @param etf contains information which Table is being edited.
	 */
	public MoreAttributesListener(AddAttributeInTableFrame addAttributeInTableFrame, DefaultAtributBuilder dab, EditTableFrame etf) {
		// TODO Auto-generated constructor stub
		this.addAttributeInTableFrame = addAttributeInTableFrame;
		this.dab = dab;
		this.etf = etf;
	}

	/**
	 * Creates new Attribute and adds it to edited Table.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(addAttributeInTableFrame.getCodeTextField().getText().isEmpty()){
			JOptionPane.showMessageDialog(MainWindow.getInstance(),
					Sistem.getInstance().getTranslate("Code_must_be_entered") );
			return;
		}
			
		for(Atribut a : ((Table) Tree.getInstance().getSelected()).getPolja()){
			if(a.getNaziv().getCode().equals(addAttributeInTableFrame.getCodeTextField().getText())){
				JOptionPane.showMessageDialog(null,
						Sistem.getInstance().getTranslate("Code_taken"));
				return;
			}
		}
			
		dab.BuildName(addAttributeInTableFrame.getCodeTextField().getText().toString());
		dab.buildisNull(true);
		dab.buildUnique(addAttributeInTableFrame.getUniqueCheckBox().isSelected());
		if(dab.getAtribute()==null){
			
			return;
		}
		Atribut atr = dab.getAtribute();
		
		((Table) Tree.getInstance().getSelected()).addAtribute(atr);
		int index = Tabs.getTabele().indexOfTab(((Table)Tree.getInstance().getSelected()).toString());
        if (index >= 0) {

        	Tabs.getTabele().removeTabAt(index);
        
        }
		Table newtab = (Table) Tree.getInstance().getSelected(); 
		if(newtab==null){
			JOptionPane.showMessageDialog(null,
					Sistem.getInstance().getTranslate("Error"));
			return;
		}
		Tabs.getInstance().addTab(newtab);
		EditTableFrame.updateList(((Table) Tree.getInstance().getSelected()));
		
		 EditorWorkbench.getCodeArea().validate();
		 addAttributeInTableFrame.dispose();
		 JSONSerialize.saveObj(Sistem.getInstance());
		
	}
}


