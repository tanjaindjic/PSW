package db.search.view.factory;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JTextField;

import editorSeme.model.pojo.Atribut;
/**
 * Part of abstract factory pattern
 *
 */
public class AlfanumFilter extends AbsFilter {
	/**
	 * Constructor that makes a Factory element, in this case alfanumeric
	 * @param a - atribut for which factory elements are made
	 * @param i - indicator og grid position in the dialog filter frame
	 */
	public AlfanumFilter(Atribut a, int i) {
		ArrayList<Component> comp = new ArrayList<Component>();
		ArrayList<GridBagConstraints> grids = new ArrayList<GridBagConstraints>();
		
		JTextField jtf = new JTextField();
		
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 3;
		gbc_textField.gridy = i++;
		
		comp.add(jtf);
		grids.add(gbc_textField);
		
		setComp(comp);
		setGbc(grids);
	}

}
