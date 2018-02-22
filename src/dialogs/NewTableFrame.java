package dialogs;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.tree.TreeNode;

import editorSeme.controller.concreetBuilders.DefaultTableBuilder;
import editorSeme.model.pojo.Atribut;
import editorSeme.model.pojo.Key;
import editorSeme.model.pojo.Package;
import editorSeme.model.pojo.Sistem;
import editorSeme.model.pojo.Table;
import listeners.AddAttributeToTableListener;
import listeners.AddKeyListener;
import listeners.BackToFormOptListener;
import listeners.ConnectTableListener;
import listeners.NewTableSaveListener;
import listeners.TranslationTableListener;
import workingsection.tree.Tree;

/**
 * Creates new Table.
 */
public class NewTableFrame extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JTextField codeTextField;
	@SuppressWarnings("rawtypes")
	private JList listaAtributa;
	private JButton btnAddMoreAttributes;
	private JButton btnAddAttribute1;
	private JButton btnAddTranslation;
	private Tree t; 
	private DefaultTableBuilder dtb;
	private JList FKlist;
	private JComboBox<Table> tablesComboBox;
	private DefaultListModel<Atribut> dlm;
	private DefaultListModel<Table> tablesList;
	private DefaultListModel<Key> keys; // Ovde su kljucevi
	private DefaultListModel dlmFK;
	private JList selektovaniKljucevi;
	private DefaultListModel keysModel;

	/**
	 * Updates list of Attributes for current Table.
	 */
	public void update(){
		
		if(dlm.size() !=0)
		for(int i=dlm.size(); i>0; i--)
			dlm.remove(i-1);
			
		for(Atribut a : dtb.getAtributes() ){
			
					dlm.addElement(a);
		}   
		
	}
	
	/**
	 * Updates list of Keys for current Table.
	 */
	public void updateKeys(){
		
		if(keys.size() !=0)
		for(int i=keys.size(); i>0; i--)
			keys.remove(i-1);
			
		for(Key k : dtb.getKeys() ){
			keys.addElement(k);
		}   
		
		
		for(int i = 0; i < keys.getSize(); i++){
			String kljuc = "";
			for(int j = 0; j < keys.get(i).getIds().size(); j++){
				kljuc += keys.get(i).getIds().get(j).getAtributeKey();
			}
			keysModel.addElement(kljuc);
		}
		
		
		
	}
	
	/*	if(tablesList.size() !=0 )
			for(int i=tablesList.size(); i>0; i--)
				tablesList.remove(i-1);
				
			for(Table t : Sistem.getInstance().getAllTables() ){
				tablesList.addElement(t);
			}  
			
		if(tablesComboBox.getSelectedItem()!=null){
			if(dlmFK.size() !=0){
				for(int i=dlmFK.size(); i>0; i--)
					dlmFK.remove(i-1);
					
				for(Atribut a : ((Table) tablesComboBox.getSelectedItem()).getPolja() ){
					dlmFK.addElement(a);
				} 
				FKlist = new JList<>(dlmFK);
			}
				
				
		}
	}
	/**
	 * Create the panel.
	 */
	/**
	 * Creates new Table, demands that System is already saved.	
	 */
	@SuppressWarnings("rawtypes")
	public NewTableFrame() {
		t = Tree.getInstance();
		keys = new DefaultListModel<Key>();
		
	// node = t.getSelected();
		TreeNode node = (TreeNode) t.getSelected();
		
		if(!(node instanceof Package )) {
			while(!(node.getParent() instanceof Package) ) {
				node= node.getParent();
			}
			node= node.getParent();
		}
			
		dtb = new DefaultTableBuilder( (Package) node );
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{100, 100, 100, 100, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 20, 0, 100, 20, 0, 0, 0, 100, 20, 0, 50, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel label = new JLabel("      ");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 1;
		gbc_label.gridy = 0;
		add(label, gbc_label);
		
		JLabel lblCode = new JLabel(Sistem.getInstance().getTranslate("Code_obavezno"));
		GridBagConstraints gbc_lblCode = new GridBagConstraints();
		gbc_lblCode.anchor = GridBagConstraints.WEST;
		gbc_lblCode.insets = new Insets(0, 0, 5, 5);
		gbc_lblCode.gridx = 1;
		gbc_lblCode.gridy = 1;
		add(lblCode, gbc_lblCode);
		
		codeTextField = new JTextField();
		GridBagConstraints gbc_codeTextField = new GridBagConstraints();
		gbc_codeTextField.insets = new Insets(0, 0, 5, 5);
		gbc_codeTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_codeTextField.gridx = 2;
		gbc_codeTextField.gridy = 1;
		add(codeTextField, gbc_codeTextField);
		codeTextField.setColumns(10);
		
		JLabel lblAddTranslation = new JLabel(Sistem.getInstance().getTranslate("Add_tr"));
		GridBagConstraints gbc_lblAddTranslation = new GridBagConstraints();
		gbc_lblAddTranslation.anchor = GridBagConstraints.WEST;
		gbc_lblAddTranslation.insets = new Insets(0, 0, 5, 5);
		gbc_lblAddTranslation.gridx = 1;
		gbc_lblAddTranslation.gridy = 2;
		add(lblAddTranslation, gbc_lblAddTranslation);
		
		btnAddTranslation = new JButton("+");
		GridBagConstraints gbc_btnCreate = new GridBagConstraints();
		gbc_btnCreate.insets = new Insets(0, 0, 5, 5);
		gbc_btnCreate.gridx = 2;
		gbc_btnCreate.gridy = 2;
		add(btnAddTranslation, gbc_btnCreate);
		btnAddTranslation.addActionListener(new TranslationTableListener(dtb, codeTextField));
		
		JLabel lblAttribute1 = new JLabel(Sistem.getInstance().getTranslate("Atr_obavezno"));
		GridBagConstraints gbc_lblAttribute1 = new GridBagConstraints();
		gbc_lblAttribute1.anchor = GridBagConstraints.WEST;
		gbc_lblAttribute1.insets = new Insets(0, 0, 5, 5);
		gbc_lblAttribute1.gridx = 1;
		gbc_lblAttribute1.gridy = 3;
		add(lblAttribute1, gbc_lblAttribute1);
		
		btnAddAttribute1 = new JButton("+");
		GridBagConstraints gbc_btnAddAttribute1 = new GridBagConstraints();
		gbc_btnAddAttribute1.insets = new Insets(0, 0, 5, 5);
		gbc_btnAddAttribute1.gridx = 2;
		gbc_btnAddAttribute1.gridy = 3;
		add(btnAddAttribute1, gbc_btnAddAttribute1);
		btnAddAttribute1.addActionListener(new AddAttributeToTableListener(dtb, this));
		
		JLabel lblChoosePrimaryKey = new JLabel(Sistem.getInstance().getTranslate("Added_atr"));
		GridBagConstraints gbc_lblChoosePrimaryKey = new GridBagConstraints();
		gbc_lblChoosePrimaryKey.anchor = GridBagConstraints.WEST;
		gbc_lblChoosePrimaryKey.insets = new Insets(0, 0, 5, 5);
		gbc_lblChoosePrimaryKey.gridx = 1;
		gbc_lblChoosePrimaryKey.gridy = 5;
		add(lblChoosePrimaryKey, gbc_lblChoosePrimaryKey);
		
		 dlm = new DefaultListModel<Atribut>();
		if(dtb!=null)
			for(Atribut a : dtb.getAtributes() ){
			     dlm.addElement(a);
			}    
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 6;
		add(scrollPane, gbc_scrollPane);
	
		listaAtributa = new JList(dlm);
		scrollPane.setViewportView(listaAtributa);
		/*
		JLabel lblAddForeignKey = new JLabel("Add Foreign Key:");
		GridBagConstraints gbc_lblAddForeignKey = new GridBagConstraints();
		gbc_lblAddForeignKey.anchor = GridBagConstraints.WEST;
		gbc_lblAddForeignKey.insets = new Insets(0, 0, 5, 5);
		gbc_lblAddForeignKey.gridx = 1;
		gbc_lblAddForeignKey.gridy = 9;
		add(lblAddForeignKey, gbc_lblAddForeignKey);
		
		JLabel label_6 = new JLabel("      ");
		GridBagConstraints gbc_label_6 = new GridBagConstraints();
		gbc_label_6.insets = new Insets(0, 0, 5, 5);
		gbc_label_6.gridx = 1;
		gbc_label_6.gridy = 10;
		add(label_6, gbc_label_6);
		
		JLabel lblChooseTable = new JLabel("Choose Table:");
		GridBagConstraints gbc_lblChooseTable = new GridBagConstraints();
		gbc_lblChooseTable.anchor = GridBagConstraints.WEST;
		gbc_lblChooseTable.insets = new Insets(0, 0, 5, 5);
		gbc_lblChooseTable.gridx = 1;
		gbc_lblChooseTable.gridy = 11;
		add(lblChooseTable, gbc_lblChooseTable);
		
		
		
		tablesList = new DefaultListModel<Table>();
		
		for(Table t : Sistem.getInstance().getAllTables())
			tablesList.addElement(t);
	
		
		tablesComboBox = new JComboBox<Table>();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.gridwidth = 2;
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 12;
		add(tablesComboBox, gbc_comboBox);
		if(tablesList.size()>0){
			for(int i = 0; i < tablesList.size(); i++){
				tablesComboBox.addItem((Table) tablesList.get(i)); 
			}
		}
		tablesComboBox.addActionListener(new ChosenTableListener(this));
		
		JLabel lblChooseKeys = new JLabel("Choose Foreign Key(s):");
		GridBagConstraints gbc_lblChooseKeys = new GridBagConstraints();
		gbc_lblChooseKeys.anchor = GridBagConstraints.WEST;
		gbc_lblChooseKeys.insets = new Insets(0, 0, 5, 5);
		gbc_lblChooseKeys.gridx = 1;
		gbc_lblChooseKeys.gridy = 14;
		add(lblChooseKeys, gbc_lblChooseKeys);
		
		dlmFK = new DefaultListModel();
		if(tablesComboBox.getSelectedItem()!=null){
			for(Atribut a : ((Table) tablesComboBox.getSelectedItem()).getPolja() ){
				dlmFK.addElement(a);
			}   
		}
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.gridwidth = 2;
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 1;
		gbc_scrollPane_1.gridy = 16;
		add(scrollPane_1, gbc_scrollPane_1);
		
		
		FKlist = new JList(dlmFK);
		scrollPane_1.setViewportView(FKlist);
		
		JLabel lblChooseKeysTo = new JLabel("Choose Key(s) for connection:");
		GridBagConstraints gbc_lblChooseKeysTo = new GridBagConstraints();
		gbc_lblChooseKeysTo.anchor = GridBagConstraints.WEST;
		gbc_lblChooseKeysTo.gridwidth = 2;
		gbc_lblChooseKeysTo.insets = new Insets(0, 0, 5, 5);
		gbc_lblChooseKeysTo.gridx = 1;
		gbc_lblChooseKeysTo.gridy = 18;
		add(lblChooseKeysTo, gbc_lblChooseKeysTo);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_2 = new GridBagConstraints();
		gbc_scrollPane_2.gridwidth = 2;
		gbc_scrollPane_2.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_2.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_2.gridx = 1;
		gbc_scrollPane_2.gridy = 20;
		add(scrollPane_2, gbc_scrollPane_2);
		
		JList listConnect = new JList(dlm);
		scrollPane_2.setViewportView(listConnect);
		
		
		JLabel label_3 = new JLabel("      ");
		GridBagConstraints gbc_label_3 = new GridBagConstraints();
		gbc_label_3.gridwidth = 2;
		gbc_label_3.insets = new Insets(0, 0, 5, 5);
		gbc_label_3.gridx = 1;
		gbc_label_3.gridy = 21;
		add(label_3, gbc_label_3);
		
		*/
		
		JLabel lblCreateKeys = new JLabel(Sistem.getInstance().getTranslate("Create_keys"));
		GridBagConstraints gbc_lblCreateKeys = new GridBagConstraints();
		gbc_lblCreateKeys.anchor = GridBagConstraints.WEST;
		gbc_lblCreateKeys.insets = new Insets(0, 0, 5, 5);
		gbc_lblCreateKeys.gridx = 1;
		gbc_lblCreateKeys.gridy = 8;
		add(lblCreateKeys, gbc_lblCreateKeys);
		
		JButton btnCreate = new JButton(Sistem.getInstance().getTranslate("Create"));
		GridBagConstraints gbc_btnCreate1 = new GridBagConstraints();
		gbc_btnCreate1.insets = new Insets(0, 0, 5, 5);
		gbc_btnCreate1.gridx = 2;
		gbc_btnCreate1.gridy = 8;
		add(btnCreate, gbc_btnCreate1);
		btnCreate.addActionListener(new AddKeyListener(dtb, this, dlm));
		
		JLabel lblChoosePrimaryKey_1 = new JLabel(Sistem.getInstance().getTranslate("Choose_prim_key"));
		GridBagConstraints gbc_lblChoosePrimaryKey_1 = new GridBagConstraints();
		gbc_lblChoosePrimaryKey_1.anchor = GridBagConstraints.WEST;
		gbc_lblChoosePrimaryKey_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblChoosePrimaryKey_1.gridx = 1;
		gbc_lblChoosePrimaryKey_1.gridy = 9;
		add(lblChoosePrimaryKey_1, gbc_lblChoosePrimaryKey_1);
		
		//DOBIJENE KLJUCEVE DODATI OVDE U LISTU
		JScrollPane scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.gridheight = 2;
		gbc_scrollPane_1.gridwidth = 2;
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 1;
		gbc_scrollPane_1.gridy = 10;
		add(scrollPane_1, gbc_scrollPane_1);
		
		keysModel = new DefaultListModel(); 
		
		JList odabirPrimarnogKljuca = new JList(keysModel);
		
		
		scrollPane_1.setViewportView(odabirPrimarnogKljuca);
		
		JLabel lblConnectWithAnother = new JLabel(Sistem.getInstance().getTranslate("Connect_with_table"));
		GridBagConstraints gbc_lblConnectWithAnother = new GridBagConstraints();
		gbc_lblConnectWithAnother.insets = new Insets(0, 0, 5, 5);
		gbc_lblConnectWithAnother.gridx = 1;
		gbc_lblConnectWithAnother.gridy = 13;
		add(lblConnectWithAnother, gbc_lblConnectWithAnother);
		
		JButton btnConnect = new JButton(Sistem.getInstance().getTranslate("Connect"));
		GridBagConstraints gbc_btnConnect = new GridBagConstraints();
		gbc_btnConnect.insets = new Insets(0, 0, 5, 5);
		gbc_btnConnect.gridx = 2;
		gbc_btnConnect.gridy = 13;
		add(btnConnect, gbc_btnConnect);
		btnConnect.addActionListener(new ConnectTableListener(dtb, this, dlm));
		
		JButton btnCancel = new JButton(Sistem.getInstance().getTranslate("Cancel"));
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.insets = new Insets(0, 0, 5, 5);
		gbc_button.gridx = 1;
		gbc_button.gridy = 15;
		add(btnCancel, gbc_button);

		btnCancel.addActionListener(new BackToFormOptListener());
		
		
		JButton btnSave = new JButton(Sistem.getInstance().getTranslate("Save"));
		GridBagConstraints gbc_button_1 = new GridBagConstraints();
		gbc_button_1.insets = new Insets(0, 0, 5, 5);
		gbc_button_1.gridx = 2;
		gbc_button_1.gridy = 15;
		add(btnSave, gbc_button_1);
	//	int primarniKljucIdx =  odabirPrimarnogKljuca.getSelectedIndex(); ne mogu ovo koristiti jer ce se samo stvoriti u konsturktoru, nece se update-ovati :D Ali koristim ovaj odabir :D
		btnSave.addActionListener(new NewTableSaveListener(dtb, codeTextField, odabirPrimarnogKljuca));
		

	}

	/**
	 * Gets button for adding more Attributes.
	 * @return button for adding more Attributes.
	 */
	public JButton getBtnAddMoreAttributes() {
		return btnAddMoreAttributes;
	}
	
	/**
	 * Sets button for adding more Attributes.
	 * @param  btnAddMoreAttributes button for adding more Attributes.
	 */
	public void setBtnAddMoreAttributes(JButton btnAddMoreAttributes) {
		this.btnAddMoreAttributes = btnAddMoreAttributes;
	}
	
	/**
	 * Gets button for adding Attribute.
	 * @return button for adding Attribute.
	 */
	public JButton getBtnAddAttribute1() {
		return btnAddAttribute1;
	}
	
	/**
	 * Sets button for adding Attribute.
	 * @param btnAddAttribute1 button for adding Attribute.
	 */
	public void setBtnAddAttribute1(JButton btnAddAttribute1) {
		this.btnAddAttribute1 = btnAddAttribute1;
	}
	
	/**
	 * Returns parent of selected Object in Tree.
	 * @param o is parent of an Object selected in Tree.
	 * @return Parent object.
	 */
	public static Object getParent(Object o) {
		
		if(o instanceof Table)
			return ((Table) o).getParent();
		else if(o instanceof Package)
			return ((Package) o).getParent();
		else if(o instanceof Sistem)
			return o;
		else return null;
		
	}

	/**
	 * Sets the List of Foreign Keys.
	 * @param dlm2 is Model for the List of Foreign Keys.
	 */
	public void setFKlist(DefaultListModel dlm2) {
		FKlist = new JList(dlm2);
		FKlist.repaint();
	}

}
