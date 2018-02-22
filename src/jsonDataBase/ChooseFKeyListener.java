package jsonDataBase;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import editorSeme.model.pojo.Atribut;
/**
 * Class that opens a dialog for choosing foreing key
 *
 */
public class ChooseFKeyListener implements ActionListener {
	private Atribut a;
	private JTextField jtf;
	/**
	 * constructor
	 * @param a - atribut thet needs a value
	 * @param jtf - textField that should show the value of choosen foreign key
	 */
	public ChooseFKeyListener(Atribut a, JTextField jtf) {
		this.a = a;
		this.jtf = jtf;
	}
	/**
	 * Method that initialize the dialog
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		ChooseFKeyDialog cfk = ChooseFKeyDialog.getInstance(a, jtf);
		cfk.setVisible(true);
	}

	

}
