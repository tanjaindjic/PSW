package db.search.view.factory;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JTextField;

import editorSeme.model.pojo.Atribut;
import editorSeme.model.pojo.Sistem;
import jsonDataBase.factory.ComponentPart;
/**
 * Part of abstract factory pattern
 *
 */
public class NumDateFilter extends AbsFilter {
	/**
	 * Constructor that makes a Factory element, in this case numeric of date type
	 * @param a - atribut for which factory elements are made
	 * @param i - indicator of grid position in the dialog filter frame
	 */
	public NumDateFilter(Atribut a, int i) {
		ArrayList<Component> comp = new ArrayList<Component>();
		ArrayList<GridBagConstraints> grids = new ArrayList<GridBagConstraints>();
		
		JLabel from = new JLabel(Sistem.getInstance().getTranslate("From"));
		JTextField jtfFrom = new JTextField();
		JLabel to = new JLabel(Sistem.getInstance().getTranslate("To"));
		JTextField jtfTo = new JTextField();
		
		GridBagConstraints gbc_from = new GridBagConstraints();
		gbc_from.insets = new Insets(0, 0, 5, 5);
		gbc_from.fill = GridBagConstraints.HORIZONTAL;
		gbc_from.gridx = 2;
		gbc_from.gridy = i;
		GridBagConstraints gbc_jtfFrom = new GridBagConstraints();
		gbc_jtfFrom.insets = new Insets(0, 0, 5, 5);
		gbc_jtfFrom.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfFrom.gridx = 3;
		gbc_jtfFrom.gridy = i++;
		GridBagConstraints gbc_to = new GridBagConstraints();
		gbc_to.insets = new Insets(0, 0, 5, 5);
		gbc_to.fill = GridBagConstraints.HORIZONTAL;
		gbc_to.gridx = 2;
		gbc_to.gridy = i;
		GridBagConstraints gbc_jtfTo = new GridBagConstraints();
		gbc_jtfTo.insets = new Insets(0, 0, 5, 5);
		gbc_jtfTo.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfTo.gridx = 3;
		gbc_jtfTo.gridy = i++;
		
		comp.add(from);
		comp.add(jtfFrom);
		comp.add(to);
		comp.add(jtfTo);
		
		grids.add(gbc_from);
		grids.add(gbc_jtfFrom);
		grids.add(gbc_to);
		grids.add(gbc_jtfTo);
		
		setComp(comp);
		setGbc(grids);
	}

}
