package db.search.view.factory;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;

import editorSeme.model.pojo.Atribut;
/**
 * Part of abstract factory pattern
 *
 */
public class BooleanFilter extends AbsFilter {
	/**
	 * Constructor that makes a Factory element, in this case boolean type
	 * @param a - atribut for which factory elements are made
	 * @param i - indicator of grid position in the dialog filter frame
	 */
	public BooleanFilter(Atribut a, int i) {
		ArrayList<Component> comp = new ArrayList<Component>();
		ArrayList<GridBagConstraints> grids = new ArrayList<GridBagConstraints>();
		
		JRadioButton tButton = new JRadioButton("true");
		tButton.setSelected(false);
		tButton.setText(a.toString());
		JRadioButton fButton = new JRadioButton("false");
		fButton.setSelected(false);
		fButton.setText(a.toString());
		JRadioButton iButton = new JRadioButton("ignore");
		iButton.setSelected(true);
		iButton.setText(a.toString());
		ButtonGroup group = new ButtonGroup();
		
	    group.add(tButton);
	    group.add(fButton);
	    group.add(iButton);
	    
	    comp.add(tButton);
	    comp.add(fButton);
	    comp.add(iButton);
	    
	    GridBagConstraints gbc_Tbutton = new GridBagConstraints();
	    gbc_Tbutton.insets = new Insets(0, 0, 5, 5);
	    gbc_Tbutton.fill = GridBagConstraints.HORIZONTAL;
	    gbc_Tbutton.gridx = 3;
	    gbc_Tbutton.gridy = i++;
		
		GridBagConstraints gbc_Fbutton = new GridBagConstraints();
		gbc_Fbutton.insets = new Insets(0, 0, 5, 5);
		gbc_Fbutton.fill = GridBagConstraints.HORIZONTAL;
		gbc_Fbutton.gridx = 3;
		gbc_Fbutton.gridy = i++;
		
		GridBagConstraints gbc_Ibutton = new GridBagConstraints();
		gbc_Ibutton.insets = new Insets(0, 0, 5, 5);
		gbc_Ibutton.fill = GridBagConstraints.HORIZONTAL;
		gbc_Ibutton.gridx = 3;
		gbc_Ibutton.gridy = i++;
		
		grids.add(gbc_Tbutton);
		grids.add(gbc_Fbutton);
		grids.add(gbc_Ibutton);

		setComp(comp);
		setGbc(grids);
	}

}
