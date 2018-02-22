package jsonDataBase.additional;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;

import javax.swing.JFileChooser;
import javax.swing.JTextField;
/**
 * Class that calls fileChooser for choosing location
 *
 */
public class ChoosePathListener implements ActionListener {
	private JTextField tf;
	/**
	 * Constructor that makes connection to text field which will contain location for saving
	 * @param tf - text field with location for saving
	 */
	public ChoosePathListener(JTextField tf) {
		this.tf=tf;
	}
	/**
	 * Method that opens fileChooser and sets location for saving
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		JFileChooser chooser = new JFileChooser();
	    int retrival = chooser.showSaveDialog(null);
	    if (retrival == JFileChooser.APPROVE_OPTION) {
        	try{
        	    String path = chooser.getSelectedFile()+".json";
        	    tf.setText(path);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	    }
	}

}
