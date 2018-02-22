package workingsection;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
/**
 * Class that makes toolButtons in toolBar
 *
 */
public class Tool extends JButton{
	private String name;
	private String pathToIcon;
	/**
	 * Constructor for making toolButtons
	 * @param name - text for tooltip of the button
	 * @param pathToIcon - location to the icon for the button
	 */
	public Tool(String name, String pathToIcon){
		this.name = name;
		this.pathToIcon = pathToIcon;
		
		setToolTipText(name);
		ImageIcon ii = new ImageIcon(pathToIcon);		
		setIcon(ii);		
		setToolTipText(name);
		setPreferredSize(new Dimension(40,super.getPreferredSize().height));
	}
}
