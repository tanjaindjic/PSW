package db.connection;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import editorSeme.model.pojo.Sistem;
import listeners.CancelJSONChooser;
import listeners.DBchosenListener;
import listeners.LoadListenerDB;
import listeners.NewJSONListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * 
 * Dialog for choosing database type. Opens chosen database or creates a new one.
 *
 */
public class DBChooser extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JComboBox<String> DBcomboBox;
	/**
	 * getter for DBcomboBox
	 * @return - atribut that is set as DBcomboBox
	 */
	public JComboBox<String> getDBcomboBox() {
		return DBcomboBox;
	}
	/**
	 * setter for DBcomboBox
	 * @param dBcomboBox - atribut of DBChooser class
	 */
	public void setDBcomboBox(JComboBox<String> dBcomboBox) {
		DBcomboBox = dBcomboBox;
	}

	private JPanel optPanel;
	private JPanel JSONpanel;
	private static DBChooser ch=null;
	private JTextField codeTextField;
	private JComboBox langComboBox;
	
	/**
	 * getter for codeTextField
	 * @return - parameter of DBChooser codeTextField
	 */
	public JTextField getCodeTextField() {
		return codeTextField;
	}
	/**
	 * setter for codeTextField
	 * @param codeTextField - JTextField with code value
	 */
	public void setCodeTextField(JTextField codeTextField) {
		this.codeTextField = codeTextField;
	}
	/**
	 * getter for getLangComboBox
	 * @return - atribut that is set as langComboBox
	 */
	public JComboBox getLangComboBox() {
		return langComboBox;
	}
	/**
	 * setter for langComboBox
	 * @param langComboBox - JComboBox with language value
	 */
	public void setLangComboBox(JComboBox langComboBox) {
		this.langComboBox = langComboBox;
	}

	/**
	 * Dialog offers a choice between JSON and a Relational database and also offers supported languages for further use.
	 * Creates the panel for choosing between new JSON database or load existing one. 
	 */
	private DBChooser() {
		JSONpanel = new JPanel();
		String myLocLogo = (System.getProperty("user.dir")+"/src/");;
		ImageIcon imgLogo = new ImageIcon(myLocLogo + "logo1.jpg");
		setIconImage(imgLogo.getImage());
		setTitle(Sistem.getInstance().getTranslate("Choose_DB"));
		setPreferredSize(new Dimension(500,300));
		setBounds(100, 100, 500, 401);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new CardLayout(0, 0));
		{
			optPanel = new JPanel();
			contentPanel.add(optPanel, "optPanel");
			GridBagLayout gbl_optPanel = new GridBagLayout();
			gbl_optPanel.columnWidths = new int[]{100, 140, 140, 100, 0};
			gbl_optPanel.rowHeights = new int[]{70, 0, 30, 0, 30, 0, 70, 0};
			gbl_optPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			gbl_optPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			optPanel.setLayout(gbl_optPanel);
			{
				Component verticalStrut_0 = Box.createVerticalStrut(20);
				GridBagConstraints gbc_verticalStrut_0 = new GridBagConstraints();
				gbc_verticalStrut_0.insets = new Insets(0, 0, 5, 5);
				gbc_verticalStrut_0.gridx = 0;
				gbc_verticalStrut_0.gridy = 0;
				optPanel.add(verticalStrut_0, gbc_verticalStrut_0);
				
				JLabel lblChooseDatabase = new JLabel(Sistem.getInstance().getTranslate("Choose_DB2"));
				GridBagConstraints gbc_lblChooseDatabase = new GridBagConstraints();
				gbc_lblChooseDatabase.insets = new Insets(0, 0, 5, 5);
				gbc_lblChooseDatabase.gridx = 1;
				gbc_lblChooseDatabase.gridy = 1;
				optPanel.add(lblChooseDatabase, gbc_lblChooseDatabase);
			}
			{
				DBcomboBox = new JComboBox<String>();
				DBcomboBox.addItem("JSON");
				DBcomboBox.addItem(Sistem.getInstance().getTranslate("Relational"));
				GridBagConstraints gbc_DBcomboBox = new GridBagConstraints();
				gbc_DBcomboBox.insets = new Insets(0, 0, 5, 5);
				gbc_DBcomboBox.fill = GridBagConstraints.HORIZONTAL;
				gbc_DBcomboBox.gridx = 2;
				gbc_DBcomboBox.gridy = 1;
				//DBcomboBox.addActionListener(new DBchosenListener());
				optPanel.add(DBcomboBox, gbc_DBcomboBox);
			}
			{
				JButton btnOk = new JButton("OK");
				GridBagConstraints gbc_btnOk = new GridBagConstraints();
				gbc_btnOk.gridwidth = 2;
				gbc_btnOk.insets = new Insets(0, 0, 5, 5);
				gbc_btnOk.gridx = 1;
				gbc_btnOk.gridy = 5;
				btnOk.addActionListener(new DBchosenListener(this));
				{
					JLabel lblChooseLanguage = new JLabel(Sistem.getInstance().getTranslate("Choose_lang"));
					GridBagConstraints gbc_lblChooseLanguage = new GridBagConstraints();
					gbc_lblChooseLanguage.insets = new Insets(0, 0, 5, 5);
					gbc_lblChooseLanguage.gridx = 1;
					gbc_lblChooseLanguage.gridy = 3;
					optPanel.add(lblChooseLanguage, gbc_lblChooseLanguage);
				}
				{
					langComboBox = new JComboBox();
					langComboBox.addItem("English");
					langComboBox.addItem("Serbian");
					GridBagConstraints gbc_langComboBox = new GridBagConstraints();
					gbc_langComboBox.insets = new Insets(0, 0, 5, 5);
					gbc_langComboBox.fill = GridBagConstraints.HORIZONTAL;
					gbc_langComboBox.gridx = 2;
					gbc_langComboBox.gridy = 3;
					optPanel.add(langComboBox, gbc_langComboBox);
				}
				optPanel.add(btnOk, gbc_btnOk);
			}
		}
		{
			
			contentPanel.add(JSONpanel, "JSONpanel");
		}
		pack();
		setLocation((Toolkit.getDefaultToolkit().getScreenSize().width)/2 - getWidth()/2, (Toolkit.getDefaultToolkit().getScreenSize().height)/2 - getHeight()/2);
		
	}
	/**
	 * Retrieves JSON Panel for choosing between new JSON database or load an existing one.
	 * @return JSON JPanel
	 */
	public JPanel getJSONpanel() {
		return JSONpanel;
	}

	/**
	 * Sets JSON panel
	 * @param jSONpanel - New instance of JSON panel
	 */
	public void setJSONpanel(JPanel jSONpanel) {
		JSONpanel = jSONpanel;
	}

	/**
	 * Retrieves Content Panel from JSON panel parent.
	 *  @return ContentPanel
	 */
	public JPanel getContentPanel() {
		return contentPanel;
	}

	/**
	 * Returns an instance of DBChooser
	 *  @return DBChooser instance
	 */
	public static DBChooser getInstance(){
		if(ch==null)
			ch = new DBChooser();
		return ch;
	}
	
	/**
	 * Destroys DBChooser instance
	 */
	public static void destroy(){
		ch = null;
	}
	
	/**
	 * Checks if DBChooser Frame is null.
	 * @return if DBChooser Frame is null.
	 */
	public static boolean isNull(){
		return DBChooser.isNull();
	}

}
