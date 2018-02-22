package jsonDataBase.factory;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;

import editorSeme.model.pojo.Atribut;
/**
 * Class that makes Components neccesery for adding attribute that has boolean as its type
 *
 */
public class JRadioButtonPart extends ComponentPart{
	/**
	 * Constructor for components that are going to provide boolean value
	 * @param a - atribut that is going to get value
	 * @param i - integer that indicates position in grid 
	 */
	public JRadioButtonPart(Atribut a, int i){
		JRadioButton yesButton = new JRadioButton("Yes");
		yesButton.setSelected(false);
		yesButton.setText("Yes");
		JRadioButton noButton = new JRadioButton("No");
		noButton.setSelected(false);
		noButton.setText("No");
		JRadioButton dnButton = new JRadioButton("(null)");
		dnButton.setSelected(true);
		dnButton.setText("(null)");
		ButtonGroup group = new ButtonGroup();
	    group.add(yesButton);
	    group.add(noButton);
	    group.add(dnButton);
	    
	    GridBagConstraints gbc_YbuttonField = new GridBagConstraints();
	    gbc_YbuttonField.insets = new Insets(0, 0, 5, 5);
	    gbc_YbuttonField.fill = GridBagConstraints.HORIZONTAL;
	    gbc_YbuttonField.gridx = 2;
	    gbc_YbuttonField.gridy = i++;
		
		GridBagConstraints gbc_NbuttonField = new GridBagConstraints();
		gbc_NbuttonField.insets = new Insets(0, 0, 5, 5);
		gbc_NbuttonField.fill = GridBagConstraints.HORIZONTAL;
		gbc_NbuttonField.gridx = 2;
		gbc_NbuttonField.gridy = i++;
		
		GridBagConstraints gbc_dnbuttonField = new GridBagConstraints();
		gbc_dnbuttonField.insets = new Insets(0, 0, 5, 5);
		gbc_dnbuttonField.fill = GridBagConstraints.HORIZONTAL;
		gbc_dnbuttonField.gridx = 2;
		gbc_dnbuttonField.gridy = i++;
		
		ArrayList<Component> list1 = new ArrayList<Component>();
		list1.add(yesButton);
		list1.add(noButton);
		list1.add(dnButton);
		setComp(list1);
		
		ArrayList<GridBagConstraints> list = new ArrayList<GridBagConstraints>();
		list.add(gbc_YbuttonField);
		list.add(gbc_NbuttonField);
		list.add(gbc_dnbuttonField);
		setGbc(list);
	}
}
