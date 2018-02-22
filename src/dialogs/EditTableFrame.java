package dialogs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import editorSeme.model.pojo.Atribut;
import editorSeme.model.pojo.Sistem;
import editorSeme.model.pojo.Table;
import jsonDataBase.additional.JSONDataSerialize;
import jsonDataBase.model.DataTabela;
import listeners.AddTranslListener;
import start.DatabaseType;
import start.InfViewModel;
import workingsection.tree.Tree;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


/**
 * Edits selected Table. Can add Translations and new Attributes.
 * 
 */
public class EditTableFrame extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static JList list;

	/**
	 * Adds new Translations for Table and its' Attributes. Gives an option to add more Attributes for JSON Database.
	 * 
	 */
	public EditTableFrame() {
		
		Table selected = (Table) Tree.getInstance().getSelected();
		setBounds(100, 100, 450, 583);
		setTitle(Sistem.getInstance().getTranslate("Edit_table"));
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane);
			{
				JPanel panel = new JPanel();
				scrollPane.setViewportView(panel);
				GridBagLayout gbl_panel = new GridBagLayout();
				gbl_panel.columnWidths = new int[]{30, 0, 0, 30, 0};
				gbl_panel.rowHeights = new int[]{30, 0, 30, 0, 30, 50, 150, 50, 30, 0};
				gbl_panel.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
				gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
				panel.setLayout(gbl_panel);
				{
					JLabel lblAddTranslation = new JLabel(Sistem.getInstance().getTranslate("Add_tab_tr"));
					GridBagConstraints gbc_lblAddTranslation = new GridBagConstraints();
					gbc_lblAddTranslation.insets = new Insets(0, 0, 5, 5);
					gbc_lblAddTranslation.gridx = 1;
					gbc_lblAddTranslation.gridy = 1;
					panel.add(lblAddTranslation, gbc_lblAddTranslation);
				}
				{
					JButton button = new JButton("+");
					GridBagConstraints gbc_button = new GridBagConstraints();
					gbc_button.insets = new Insets(0, 0, 5, 5);
					gbc_button.gridx = 2;
					gbc_button.gridy = 1;
					button.addActionListener(new AddTranslListener(this));
					panel.add(button, gbc_button);
				}
				{
					JLabel lblAddAttribute = new JLabel(Sistem.getInstance().getTranslate("Add_atr"));
					GridBagConstraints gbc_lblAddAttribute = new GridBagConstraints();
					gbc_lblAddAttribute.insets = new Insets(0, 0, 5, 5);
					gbc_lblAddAttribute.gridx = 1;
					gbc_lblAddAttribute.gridy = 3;
					if(InfViewModel.getInstance().getDatabaseType().equals(DatabaseType.JSON))
						panel.add(lblAddAttribute, gbc_lblAddAttribute);
				}
				{
					JButton button = new JButton("+");
					EditTableFrame etf= this;
					button.addActionListener(new ActionListener() {
						
						public void actionPerformed(ActionEvent e) {
							DataTabela dt = new DataTabela();
							dt = JSONDataSerialize.getDataTable(((Table) Tree.getInstance().getSelected()));
							
							if(dt.getTorke().isEmpty()){
								AddAttributeInTableFrame atf = new AddAttributeInTableFrame(etf);
								atf.setVisible(true);
							}else{
								JOptionPane.showMessageDialog(null,Sistem.getInstance().getTranslate("Table_has_data"));		
								return;
							}
							
						}
					});
					GridBagConstraints gbc_button = new GridBagConstraints();
					gbc_button.insets = new Insets(0, 0, 5, 5);
					gbc_button.gridx = 2;
					gbc_button.gridy = 3;
					if(InfViewModel.getInstance().getDatabaseType().equals(DatabaseType.JSON))
						panel.add(button, gbc_button);
				}
				{
					JLabel lblNewLabel = new JLabel(Sistem.getInstance().getTranslate("Edit_atr"));
					GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
					gbc_lblNewLabel.gridwidth = 2;
					gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
					gbc_lblNewLabel.gridx = 1;
					gbc_lblNewLabel.gridy = 5;
					panel.add(lblNewLabel, gbc_lblNewLabel);
				}
				{
					JPanel panel_1 = new JPanel();
					
					GridBagConstraints gbc_panel_1 = new GridBagConstraints();
					gbc_panel_1.insets = new Insets(0, 0, 5, 0);
					gbc_panel_1.gridwidth = 4;
					gbc_panel_1.fill = GridBagConstraints.BOTH;
					gbc_panel_1.gridx = 0;
					gbc_panel_1.gridy = 6;
					panel.add(panel_1, gbc_panel_1);
					panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
					{
						JScrollPane scrollPane_1 = new JScrollPane();
						scrollPane_1.setMaximumSize(new Dimension(this.getWidth(), 150));
						panel_1.add(scrollPane_1);
						{
							JPanel attrPanel = new JPanel();
							attrPanel.setMaximumSize(new Dimension(this.getWidth(), 150));
							scrollPane_1.setViewportView(attrPanel);
							attrPanel.setLayout(new BoxLayout(attrPanel, BoxLayout.Y_AXIS));
							attrPanel.setAlignmentX(CENTER_ALIGNMENT);
							{
								
								 DefaultListModel<Atribut> dlm = new DefaultListModel<Atribut>();
									if(selected.getPolja()!=null)
										for(Atribut a : selected.getPolja() ){
											ArrayList<String> pkeys = selected.getPkeyAtrs();
											
										     dlm.addElement(a);
										}    
									
									
								list = new JList(dlm);
								
								attrPanel.add(list);
							}
							
						}
					}
				}
				{
					JButton btnAddTranslation = new JButton(Sistem.getInstance().getTranslate("Add_tr_title"));
					GridBagConstraints gbc_btnAddTranslation = new GridBagConstraints();
					gbc_btnAddTranslation.gridwidth = 2;
					gbc_btnAddTranslation.insets = new Insets(0, 0, 5, 5);
					gbc_btnAddTranslation.gridx = 1;
					gbc_btnAddTranslation.gridy = 7;
					btnAddTranslation.addActionListener(new AddTranslListener(this, (Atribut) list.getSelectedValue()));
					panel.add(btnAddTranslation, gbc_btnAddTranslation);
				}
			}
		}
		pack();
		setLocation((Toolkit.getDefaultToolkit().getScreenSize().width)/2 - getWidth()/2, (Toolkit.getDefaultToolkit().getScreenSize().height)/2 - getHeight()/2);
		
	}

	/**
	 * Gets the list of Attributes for edit.
	 * @return List of Attributes of selected Table.
	 */
	public JList getList() {
		return list;
	}
	/**
	 * Sets the list of Attributes for edit.
	 * @param list of Attributes of selected Table.
	 */
	public void setList(JList list) {
		this.list = list;
	}
	
	/**
	 * Sets the list of Attributes for edit.
	 * @param dlm is Model for list of Attributes of selected Table.
	 */
	public static void setList2(DefaultListModel<Atribut> dlm) {
		list.setModel(dlm);
	}

	/**
	 * Updates the list of Attributes for edit.
	 * @param selected Table with the refreshed List of Attributes to replace the old List.
	 */
	
	public static void updateList(Table selected){
		DefaultListModel<Atribut> dlm = new DefaultListModel<Atribut>();
		if(selected.getPolja()!=null)
			for(Atribut a : selected.getPolja() ){
				ArrayList<String> pkeys = selected.getPkeyAtrs();
				
			     dlm.addElement(a);
			}    
		setList2(dlm);
		
		
	
	}
}
