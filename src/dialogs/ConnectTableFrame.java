package dialogs;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import editorSeme.controller.concreetBuilders.DefaultTableBuilder;
import editorSeme.model.pojo.Atribut;
import editorSeme.model.pojo.Sistem;
import editorSeme.model.pojo.Table;
import listeners.CancelFKeyListener;
import listeners.ChosenTableListener;
import listeners.addFKListener;
import listeners.saveFKListener;

/**
 * Connects two Tables using selected Attributes which must be Domain compatible.
 * 
 */
public class ConnectTableFrame extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private DefaultTableBuilder dtb; 
	private NewTableFrame newTableFrame; 
	private DefaultListModel<Atribut> dlm;
	private JComboBox tablesComboBox;
	private JComboBox FKAttrComboBox;
	private JComboBox HomeAtrrComboBox;
	private JList FKlist;
	private ArrayList<String> home;
	private ArrayList<String> forei;
	
	/**
	 * Adds Foreign Key to Table.
	 * @param s is the code of Attribute used for making Foreign Key.
	 */
	public void addFor(String s){
		forei.add(s);
	}
	
	/**
	 * Adds Key for connecting with another Table. The Key is Domain compatible with the Foreign Key.
	 * @param s is the code of Attribute from current Table used to connect with another Table.
	 */
	public void addHome(String s){
		home.add(s);
	}
	
	/**
	 * Gets the Key for connecting with another Table. The Key is Domain compatible with the Foreign Key.
	 * @return Code of Attribute from current Table used to connect with another Table.
	 */
	public String getSelectedHome(){
		return (String)HomeAtrrComboBox.getSelectedItem();
	}
	
	/**
	 * Gets the Foreign Key from Table.
	 * @return Code of Attribute used for making Foreign Key.
	 */
	public String getSelectedFor(){
		return (String)FKAttrComboBox.getSelectedItem();
	}
	
	/**
	 * Connects two Tables using chosen Keys.
	 * @param dtb makes temporary Table and allows creation of Attributes and Keys.
	 * @param newTableFrame shows all Tables available for connection.
	 * @param dlm makes Model for List of Attributes.
	 */
	public ConnectTableFrame(DefaultTableBuilder dtb, NewTableFrame newTableFrame, DefaultListModel<Atribut> dlm) {
		this.dtb = dtb;
		home = new ArrayList<String>();
		forei = new  ArrayList<String>();
		this.newTableFrame = newTableFrame;
		this.dlm = dlm;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{100, 100, 100, 0};
		gbl_contentPanel.rowHeights = new int[]{20, 0, 0, 0, 0, 0, 0, 40, 20, 50, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblChooseTable = new JLabel(Sistem.getInstance().getTranslate("Choose_table"));
			GridBagConstraints gbc_lblChooseTable = new GridBagConstraints();
			gbc_lblChooseTable.insets = new Insets(0, 0, 5, 5);
			gbc_lblChooseTable.gridx = 1;
			gbc_lblChooseTable.gridy = 1;
			contentPanel.add(lblChooseTable, gbc_lblChooseTable);
		}
		{
			tablesComboBox = new JComboBox();
			GridBagConstraints gbc_comboBox = new GridBagConstraints();
			gbc_comboBox.insets = new Insets(0, 0, 5, 5);
			gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBox.gridx = 1;
			gbc_comboBox.gridy = 2;
			contentPanel.add(tablesComboBox, gbc_comboBox);
			for(Table t : Sistem.getInstance().getAllTables())//PREPRAVITI U TOSTRING ZA NAZIV
				tablesComboBox.addItem(t);
		}
		tablesComboBox.addActionListener(new ChosenTableListener(this));
		{
			JLabel lblChooseAttributeFrom = new JLabel(Sistem.getInstance().getTranslate("Atr_from_choosen_table"));
			GridBagConstraints gbc_lblChooseAttributeFrom = new GridBagConstraints();
			gbc_lblChooseAttributeFrom.insets = new Insets(0, 0, 5, 5);
			gbc_lblChooseAttributeFrom.gridx = 1;
			gbc_lblChooseAttributeFrom.gridy = 3;
			contentPanel.add(lblChooseAttributeFrom, gbc_lblChooseAttributeFrom);
		}
		{
			FKAttrComboBox = new JComboBox();
			GridBagConstraints gbc_comboBox2 = new GridBagConstraints();
			gbc_comboBox2.insets = new Insets(0, 0, 5, 5);
			gbc_comboBox2.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBox2.gridx = 1;
			gbc_comboBox2.gridy = 4;
			contentPanel.add(FKAttrComboBox, gbc_comboBox2);
			
			if(tablesComboBox.getSelectedItem()!=null){
				if(((Table) tablesComboBox.getSelectedItem()).getPolja()!=null)
					for(Atribut a : ((Table) tablesComboBox.getSelectedItem()).getPolja() ){
						FKAttrComboBox.addItem(a.getName().getCode());
					}   
			}
		}
		{
			JLabel lblAttributeFromChosen = new JLabel(Sistem.getInstance().getTranslate("Atr_from_curr_table"));
			GridBagConstraints gbc_lblAttributeFromChosen = new GridBagConstraints();
			gbc_lblAttributeFromChosen.insets = new Insets(0, 0, 5, 5);
			gbc_lblAttributeFromChosen.gridx = 1;
			gbc_lblAttributeFromChosen.gridy = 5;
			contentPanel.add(lblAttributeFromChosen, gbc_lblAttributeFromChosen);
		}
		{
			HomeAtrrComboBox = new JComboBox();
			GridBagConstraints gbc_comboBox2 = new GridBagConstraints();
			gbc_comboBox2.insets = new Insets(0, 0, 5, 5);
			gbc_comboBox2.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBox2.gridx = 1;
			gbc_comboBox2.gridy = 6;
			contentPanel.add(HomeAtrrComboBox, gbc_comboBox2);
			{
				JButton btnConnect = new JButton(Sistem.getInstance().getTranslate("Connect"));
				GridBagConstraints gbc_btnConnect = new GridBagConstraints();
				gbc_btnConnect.anchor = GridBagConstraints.ABOVE_BASELINE;
				gbc_btnConnect.insets = new Insets(0, 0, 5, 5);
				gbc_btnConnect.gridx = 1;
				gbc_btnConnect.gridy = 7;
				contentPanel.add(btnConnect, gbc_btnConnect);
				btnConnect.addActionListener( new addFKListener(this));
			}
			{
				JPanel panel = new JPanel();
				GridBagConstraints gbc_panel = new GridBagConstraints();
				gbc_panel.insets = new Insets(0, 0, 0, 5);
				gbc_panel.fill = GridBagConstraints.BOTH;
				gbc_panel.gridx = 1;
				gbc_panel.gridy = 9;
				contentPanel.add(panel, gbc_panel);
				{
					JButton cancelButton = new JButton(Sistem.getInstance().getTranslate("Cancel"));
					panel.add(cancelButton);
					cancelButton.addActionListener(new CancelFKeyListener(this));
				}
				{
					JButton okButton = new JButton(Sistem.getInstance().getTranslate("Save"));
					panel.add(okButton);
					okButton.addActionListener(new saveFKListener(this, dtb, tablesComboBox, forei, home));
					okButton.setActionCommand("OK");
					getRootPane().setDefaultButton(okButton);
					
				/*	
					Table chosenTable = (Table) tablesComboBox.getSelectedItem();
					Atribut homeKey = (Atribut) HomeAtrrComboBox.getSelectedItem();
					Atribut FK = (Atribut) FKAttrComboBox.getSelectedItem();
					//OVO POSLATI U LISTENER
					
					*/
				}
			}
			for(Atribut a : dtb.getAtributes() ){
				HomeAtrrComboBox.addItem(a.getName().getCode());
			}  
		}
		pack();
		setLocation((Toolkit.getDefaultToolkit().getScreenSize().width)/2 - getWidth()/2, (Toolkit.getDefaultToolkit().getScreenSize().height)/2 - getHeight()/2);
		
	}
	/**
	 * Gets List of all Tables in a ComboBox.
	 * @return JComboBox with all Tables.
	 * 
	 */
	public JComboBox getTablesComboBox() {
		return tablesComboBox;
	}
	
	/**
	 * Enables and disables ComboBox with all Tables.
	 * @param b is boolean for enabling/disabling ComboBox.
	 * 
	 */
	public void setTablesComboBox(Boolean b) {
		this.tablesComboBox.setEnabled(b);
	}
	
	/**
	 * Sets List of all Tables in a ComboBox.
	 * @param dlm2 is a Model for JComboBox with all Tables.
	 * 
	 */
	public void setFKlist(DefaultListModel dlm2) {
	
		FKAttrComboBox.removeAllItems();
		for(int i = 0; i < dlm2.size(); i++){
			FKAttrComboBox.addItem(((Atribut) dlm2.get(i)).getName().getCode());
		} 
		FKAttrComboBox.repaint();
		
	}
}
