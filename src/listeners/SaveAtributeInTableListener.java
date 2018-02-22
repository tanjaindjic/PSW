package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JTextField;

import dialogs.AddAttributeInTableFrame;
import dialogs.NewTableFrame;
import editorSeme.controller.concreetBuilders.DefaultAtributBuilder;
import editorSeme.controller.concreetBuilders.DefaultTableBuilder;
import editorSeme.model.pojo.Atribut;

/**
 * Action listener class that enables the addition of an attribute to a table.
 * It tries to save an attribute to a table.
 *
 */
public class SaveAtributeInTableListener implements ActionListener {

	private DefaultAtributBuilder dab;
	private DefaultTableBuilder dtb;
	private JTextField code;
	private JCheckBox isNull;
	private JCheckBox unique;
	private AddAttributeInTableFrame a;
	private NewTableFrame ntf;
	/**
	 * 	
	 * Saves the attribute and closes the frame.
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		dab.BuildName(code.getText().toString());
		dab.buildisNull(isNull.isSelected());
		dab.buildUnique(unique.isSelected());
		Atribut atr = dab.getAtribute();
		
		if(atr != null){
			dtb.buildNewAtribute(atr);
			ntf.update();
			ntf.repaint();
			a.dispose();
		}
		
		
		
		
	}
	/**
	 * Constructor for the SaveAtributeToTableListener.
	 * @param a The frame that should be closed.
	 * @param d The builder that is used to create the attribute in the table.
	 * @param t The builder of the table to which the attribute should be added.
	 * @param codeTextField The code of the attribute.
	 * @param nullCheckBox Shows if the attribute can be null.
	 * @param uniqueCheckBox Shows if the attribute should be unique.
	 * @param ntf The table frame that should changed after the addition.
	 */
	public SaveAtributeInTableListener(AddAttributeInTableFrame a, DefaultAtributBuilder d, DefaultTableBuilder t, JTextField codeTextField, JCheckBox nullCheckBox, JCheckBox uniqueCheckBox, NewTableFrame ntf){
		this.dab = d;
		this.a = a;
		this.dtb=t;
		this.ntf=ntf;
		this.code = codeTextField;
		this.isNull = nullCheckBox;
		this.unique = uniqueCheckBox;
	}

}