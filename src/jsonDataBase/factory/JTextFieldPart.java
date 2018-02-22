package jsonDataBase.factory;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JTextField;

import editorSeme.model.pojo.Atribut;
/**
 * Class that makes Components neccesery for adding attribute
 *
 */
public class JTextFieldPart extends ComponentPart{
	/**
	 * Constructor for components that are going to provide adding value
	 * @param a - atribut that is going to get value
	 * @param i - integer that indicates position in grid 
	 */
	public JTextFieldPart(Atribut a, int i){
		JTextField jtf = new JTextField();
		jtf.setColumns(a.getDomain().getLength());
		ArrayList<Component> list1 = new ArrayList<Component>();
		list1.add(jtf);
		setComp(list1);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 2;
		gbc_textField.gridy = i++;
		ArrayList<GridBagConstraints> list = new ArrayList<GridBagConstraints>();
		list.add(gbc_textField);
		setGbc(list);
	}
}
