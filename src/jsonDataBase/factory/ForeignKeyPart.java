package jsonDataBase.factory;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JTextField;

import editorSeme.model.pojo.Atribut;
import jsonDataBase.ChooseFKeyListener;
/**
 * Class that makes Components neccesery for adding attribute that is a foreign key
 *
 */
public class ForeignKeyPart extends ComponentPart{
	/**
	 * Constructor for components that are going to provide adding a foreign key
	 * @param a - atribut that is going to get value
	 * @param i - integer that indicates position in grid 
	 */
	public ForeignKeyPart(Atribut a, int i){
		JTextField fktf = new JTextField();
		GridBagConstraints gbc_fktfTextField = new GridBagConstraints();
		gbc_fktfTextField.insets = new Insets(0, 0, 5, 5);
		gbc_fktfTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_fktfTextField.gridx = 2;
		gbc_fktfTextField.gridy = i;
		fktf.setColumns(a.getDomain().getLength());
		
		JButton chooseFKey = new JButton("search");
		GridBagConstraints gbc_ffkTextField = new GridBagConstraints();
		gbc_ffkTextField.insets = new Insets(0, 0, 5, 5);
		gbc_ffkTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_ffkTextField.gridx = 3;
		gbc_ffkTextField.gridy = i++;
		chooseFKey.addActionListener(new ChooseFKeyListener(a, fktf));

		ArrayList<Component> list1 = new ArrayList<Component>();
		list1.add(fktf);
		list1.add(chooseFKey);
		setComp(list1);

		
		ArrayList<GridBagConstraints> list = new ArrayList<GridBagConstraints>();
		list.add(gbc_fktfTextField);
		list.add(gbc_ffkTextField);
		setGbc(list);
	}
}
