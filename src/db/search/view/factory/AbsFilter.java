package db.search.view.factory;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.util.ArrayList;
/**
 * Class that is part of abstract factory pattern
 *
 */
public abstract class AbsFilter {
	private ArrayList<GridBagConstraints> gbc;
	private ArrayList<Component> comp;
	public ArrayList<GridBagConstraints> getGbc() {
		return gbc;
	}
	public void setGbc(ArrayList<GridBagConstraints> gbc) {
		this.gbc = gbc;
	}
	public ArrayList<Component> getComp() {
		return comp;
	}
	public void setComp(ArrayList<Component> comp) {
		this.comp = comp;
	}
}
