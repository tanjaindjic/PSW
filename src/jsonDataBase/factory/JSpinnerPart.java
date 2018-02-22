package jsonDataBase.factory;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JSpinner;

import editorSeme.model.pojo.Atribut;
/**
 * Class that makes Components neccesery for adding attribute that has numeric as its type
 *
 */
public class JSpinnerPart extends ComponentPart{
	/**
	 * Constructor for components that are going to provide adding numeric value
	 * @param a - atribut that is going to get value
	 * @param i - integer that indicates position in grid 
	 */
	public JSpinnerPart(Atribut a, int i){
		JSpinner jsp = new JSpinner();
		
		ArrayList<Component> list1 = new ArrayList<Component>();
		list1.add(jsp);
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
