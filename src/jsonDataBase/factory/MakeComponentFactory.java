package jsonDataBase.factory;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import editorSeme.model.pojo.Atribut;
import jsonDataBase.AddTorkaFrame;
/**
 * Class that is part of abstract factory pattern
 *
 */
public abstract class MakeComponentFactory extends Component {
	
	protected abstract ComponentPart makeComponent(Atribut a, int i);
	public ComponentPart build(Atribut a, int i){
		ComponentPart buildPart = makeComponent(a, i);
		return buildPart;
	}
}
