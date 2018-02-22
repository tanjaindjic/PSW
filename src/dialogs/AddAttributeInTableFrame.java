package dialogs;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import editorSeme.controller.concreetBuilders.DefaultAtributBuilder;
import editorSeme.controller.concreetBuilders.DefaultTableBuilder;
import editorSeme.model.additional.JSONSerialize;
import editorSeme.model.pojo.Atribut;
import editorSeme.model.pojo.Sistem;
import editorSeme.model.pojo.Table;
import editorSeme.view.EditorWorkbench;
import listeners.AddDomainListener;
import listeners.AtributeTranslationListener;
import listeners.MoreAttributesListener;
import listeners.SaveAtributeInTableListener;
import workingsection.MainWindow;
import workingsection.Tabs;
import workingsection.tree.Tree;

/**
 * Creates attributes in Table.
 * 
 */
public class AddAttributeInTableFrame extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField codeTextField;
	private JButton btnAddTranslation;
	private JButton btnAddDomain;
	private JCheckBox nullCheckBox;
	private JCheckBox uniqueCheckBox;
	private JButton btnCancel;
	private JButton btnSave;
	private DefaultAtributBuilder dab;
	private DefaultTableBuilder dtb;
	private NewTableFrame ntf;

	/**
	 * Adds new Attributes in default Table until the current one is saved.
	 *
	 * @param  dab  builds temporary Attribute which will later be added and saved in Table.
	 * @param  dtb  builds temporary Table for storing Attributes.
	 * @param ntf Frame contains entered information for new Table.
	 */
	public AddAttributeInTableFrame(DefaultAtributBuilder dab, DefaultTableBuilder dtb, NewTableFrame ntf) {
		this.dab=dab;
		this.dtb=dtb;
		this.ntf= ntf;
		//Tree t = Tree.getInstance();

		
		setResizable(false);
		setModal(true);
		setTitle(Sistem.getInstance().getTranslate("Add_atr"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 308);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{100, 100, 100, 100, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gridBagLayout);
		
		JLabel label = new JLabel("      ");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 1;
		gbc_label.gridy = 0;
		panel.add(label, gbc_label);
		
		JLabel lblCode = new JLabel(Sistem.getInstance().getTranslate("Code"));
		GridBagConstraints gbc_lblCode = new GridBagConstraints();
		gbc_lblCode.anchor = GridBagConstraints.WEST;
		gbc_lblCode.insets = new Insets(0, 0, 5, 5);
		gbc_lblCode.gridx = 1;
		gbc_lblCode.gridy = 1;
		panel.add(lblCode, gbc_lblCode);
		
		codeTextField = new JTextField();
		GridBagConstraints gbc_codeTextField = new GridBagConstraints();
		gbc_codeTextField.insets = new Insets(0, 0, 5, 5);
		gbc_codeTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_codeTextField.gridx = 2;
		gbc_codeTextField.gridy = 1;
		panel.add(codeTextField, gbc_codeTextField);
		codeTextField.setColumns(10);
		
		JLabel lblAddTranslation = new JLabel(Sistem.getInstance().getTranslate("Add_tr"));
		GridBagConstraints gbc_lblAddTranslation = new GridBagConstraints();
		gbc_lblAddTranslation.anchor = GridBagConstraints.WEST;
		gbc_lblAddTranslation.insets = new Insets(0, 0, 5, 5);
		gbc_lblAddTranslation.gridx = 1;
		gbc_lblAddTranslation.gridy = 2;
		panel.add(lblAddTranslation, gbc_lblAddTranslation);
		
		btnAddTranslation = new JButton("+");
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.insets = new Insets(0, 0, 5, 5);
		gbc_button.gridx = 2;
		gbc_button.gridy = 2;
		panel.add(btnAddTranslation, gbc_button);
		btnAddTranslation.addActionListener(new AtributeTranslationListener(dab, codeTextField));
		
		JLabel lblDomain = new JLabel(Sistem.getInstance().getTranslate("Add_dom"));
		GridBagConstraints gbc_lblDomain = new GridBagConstraints();
		gbc_lblDomain.anchor = GridBagConstraints.WEST;
		gbc_lblDomain.insets = new Insets(0, 0, 5, 5);
		gbc_lblDomain.gridx = 1;
		gbc_lblDomain.gridy = 3;
		panel.add(lblDomain, gbc_lblDomain);
		
		btnAddDomain = new JButton("+");
		GridBagConstraints gbc_btnAddDomain = new GridBagConstraints();
		gbc_btnAddDomain.insets = new Insets(0, 0, 5, 5);
		gbc_btnAddDomain.gridx = 2;
		gbc_btnAddDomain.gridy = 3;
		panel.add(btnAddDomain, gbc_btnAddDomain);
		btnAddDomain.addActionListener(new AddDomainListener(dab));
		
		JLabel lblNull = new JLabel(Sistem.getInstance().getTranslate("BoolNull"));
		GridBagConstraints gbc_lblNull = new GridBagConstraints();
		gbc_lblNull.anchor = GridBagConstraints.WEST;
		gbc_lblNull.insets = new Insets(0, 0, 5, 5);
		gbc_lblNull.gridx = 1;
		gbc_lblNull.gridy = 4;
		panel.add(lblNull, gbc_lblNull);
		
		nullCheckBox = new JCheckBox("");
		GridBagConstraints gbc_nullCheckBox = new GridBagConstraints();
		gbc_nullCheckBox.insets = new Insets(0, 0, 5, 5);
		gbc_nullCheckBox.gridx = 2;
		gbc_nullCheckBox.gridy = 4;
		panel.add(nullCheckBox, gbc_nullCheckBox);
		
		JLabel lblAddMoreAttributes = new JLabel(Sistem.getInstance().getTranslate("BoolUnique"));
		GridBagConstraints gbc_lblAddMoreAttributes = new GridBagConstraints();
		gbc_lblAddMoreAttributes.anchor = GridBagConstraints.WEST;
		gbc_lblAddMoreAttributes.insets = new Insets(0, 0, 5, 5);
		gbc_lblAddMoreAttributes.gridx = 1;
		gbc_lblAddMoreAttributes.gridy = 5;
		panel.add(lblAddMoreAttributes, gbc_lblAddMoreAttributes);
		
		uniqueCheckBox = new JCheckBox("");
		GridBagConstraints gbc_uniqueCheckBox = new GridBagConstraints();
		gbc_uniqueCheckBox.insets = new Insets(0, 0, 5, 5);
		gbc_uniqueCheckBox.gridx = 2;
		gbc_uniqueCheckBox.gridy = 5;
		panel.add(uniqueCheckBox, gbc_uniqueCheckBox);
		
		JLabel label_3 = new JLabel("      ");
		GridBagConstraints gbc_label_3 = new GridBagConstraints();
		gbc_label_3.insets = new Insets(0, 0, 5, 5);
		gbc_label_3.gridx = 1;
		gbc_label_3.gridy = 6;
		panel.add(label_3, gbc_label_3);
		
		btnCancel = new JButton(Sistem.getInstance().getTranslate("Cancel"));
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.insets = new Insets(0, 0, 5, 5);
		gbc_btnCancel.gridx = 1;
		gbc_btnCancel.gridy = 7;
		panel.add(btnCancel, gbc_btnCancel);
		btnCancel.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e)
		    {
		       dispose();
		    }
		});
		
		btnSave = new JButton(Sistem.getInstance().getTranslate("Save"));
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.insets = new Insets(0, 0, 5, 5);
		gbc_btnSave.gridx = 2;
		gbc_btnSave.gridy = 7;
		btnSave.addActionListener(new SaveAtributeInTableListener(this, dab, dtb, codeTextField, nullCheckBox, uniqueCheckBox, ntf ));
		panel.	add(btnSave, gbc_btnSave);
		
		pack();
		setLocation((Toolkit.getDefaultToolkit().getScreenSize().width)/2 - getWidth()/2, (Toolkit.getDefaultToolkit().getScreenSize().height)/2 - getHeight()/2);
		

	}

	/**
	 * Adds new Attributes in default Table until the current one is saved.
	 *@param etf contains information about Table that is being edited.
	 */
	public AddAttributeInTableFrame(EditTableFrame etf) {
		// TODO Auto-generated constructor stub
		
		DefaultAtributBuilder dab = new DefaultAtributBuilder((Table) Tree.getInstance().getSelected());
		setResizable(false);
		setModal(true);
		setTitle(Sistem.getInstance().getTranslate("Add_atr"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 308);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{100, 100, 100, 100, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gridBagLayout);
		
		JLabel label = new JLabel("      ");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 1;
		gbc_label.gridy = 0;
		panel.add(label, gbc_label);
		
		JLabel lblCode = new JLabel(Sistem.getInstance().getTranslate("Code"));
		GridBagConstraints gbc_lblCode = new GridBagConstraints();
		gbc_lblCode.anchor = GridBagConstraints.WEST;
		gbc_lblCode.insets = new Insets(0, 0, 5, 5);
		gbc_lblCode.gridx = 1;
		gbc_lblCode.gridy = 1;
		panel.add(lblCode, gbc_lblCode);
		
		codeTextField = new JTextField();
		GridBagConstraints gbc_codeTextField = new GridBagConstraints();
		gbc_codeTextField.insets = new Insets(0, 0, 5, 5);
		gbc_codeTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_codeTextField.gridx = 2;
		gbc_codeTextField.gridy = 1;
		panel.add(codeTextField, gbc_codeTextField);
		codeTextField.setColumns(10);
		
		JLabel lblAddTranslation = new JLabel(Sistem.getInstance().getTranslate("Add_tr"));
		GridBagConstraints gbc_lblAddTranslation = new GridBagConstraints();
		gbc_lblAddTranslation.anchor = GridBagConstraints.WEST;
		gbc_lblAddTranslation.insets = new Insets(0, 0, 5, 5);
		gbc_lblAddTranslation.gridx = 1;
		gbc_lblAddTranslation.gridy = 2;
		panel.add(lblAddTranslation, gbc_lblAddTranslation);
		
		btnAddTranslation = new JButton("+");
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.insets = new Insets(0, 0, 5, 5);
		gbc_button.gridx = 2;
		gbc_button.gridy = 2;
		panel.add(btnAddTranslation, gbc_button);
		btnAddTranslation.addActionListener(new AtributeTranslationListener(dab, codeTextField));
		
		JLabel lblDomain = new JLabel(Sistem.getInstance().getTranslate("Add_dom"));
		GridBagConstraints gbc_lblDomain = new GridBagConstraints();
		gbc_lblDomain.anchor = GridBagConstraints.WEST;
		gbc_lblDomain.insets = new Insets(0, 0, 5, 5);
		gbc_lblDomain.gridx = 1;
		gbc_lblDomain.gridy = 3;
		panel.add(lblDomain, gbc_lblDomain);
		
		btnAddDomain = new JButton("+");
		GridBagConstraints gbc_btnAddDomain = new GridBagConstraints();
		gbc_btnAddDomain.insets = new Insets(0, 0, 5, 5);
		gbc_btnAddDomain.gridx = 2;
		gbc_btnAddDomain.gridy = 3;
		panel.add(btnAddDomain, gbc_btnAddDomain);
		btnAddDomain.addActionListener(new AddDomainListener(dab));
		
		JLabel lblNull = new JLabel(Sistem.getInstance().getTranslate("BoolNull"));
		GridBagConstraints gbc_lblNull = new GridBagConstraints();
		gbc_lblNull.anchor = GridBagConstraints.WEST;
		gbc_lblNull.insets = new Insets(0, 0, 5, 5);
		gbc_lblNull.gridx = 1;
		gbc_lblNull.gridy = 4;
		panel.add(lblNull, gbc_lblNull);
		
		nullCheckBox = new JCheckBox("");
		GridBagConstraints gbc_nullCheckBox = new GridBagConstraints();
		gbc_nullCheckBox.insets = new Insets(0, 0, 5, 5);
		gbc_nullCheckBox.gridx = 2;
		gbc_nullCheckBox.gridy = 4;
		panel.add(nullCheckBox, gbc_nullCheckBox);
		
		JLabel lblAddMoreAttributes = new JLabel(Sistem.getInstance().getTranslate("BoolUnique"));
		GridBagConstraints gbc_lblAddMoreAttributes = new GridBagConstraints();
		gbc_lblAddMoreAttributes.anchor = GridBagConstraints.WEST;
		gbc_lblAddMoreAttributes.insets = new Insets(0, 0, 5, 5);
		gbc_lblAddMoreAttributes.gridx = 1;
		gbc_lblAddMoreAttributes.gridy = 5;
		panel.add(lblAddMoreAttributes, gbc_lblAddMoreAttributes);
		
		uniqueCheckBox = new JCheckBox("");
		GridBagConstraints gbc_uniqueCheckBox = new GridBagConstraints();
		gbc_uniqueCheckBox.insets = new Insets(0, 0, 5, 5);
		gbc_uniqueCheckBox.gridx = 2;
		gbc_uniqueCheckBox.gridy = 5;
		panel.add(uniqueCheckBox, gbc_uniqueCheckBox);
		
		JLabel label_3 = new JLabel("      ");
		GridBagConstraints gbc_label_3 = new GridBagConstraints();
		gbc_label_3.insets = new Insets(0, 0, 5, 5);
		gbc_label_3.gridx = 1;
		gbc_label_3.gridy = 6;
		panel.add(label_3, gbc_label_3);
		
		btnCancel = new JButton(Sistem.getInstance().getTranslate("Cancel"));
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.insets = new Insets(0, 0, 5, 5);
		gbc_btnCancel.gridx = 1;
		gbc_btnCancel.gridy = 7;
		panel.add(btnCancel, gbc_btnCancel);
		btnCancel.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e)
		    {
		       dispose();
		    }
		});
		
		btnSave = new JButton(Sistem.getInstance().getTranslate("Save"));
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.insets = new Insets(0, 0, 5, 5);
		gbc_btnSave.gridx = 2;
		gbc_btnSave.gridy = 7;
		btnSave.addActionListener( new MoreAttributesListener(this, dab, etf));
		panel.add(btnSave, gbc_btnSave);
		
		pack();
		setLocation((Toolkit.getDefaultToolkit().getScreenSize().width)/2 - getWidth()/2, (Toolkit.getDefaultToolkit().getScreenSize().height)/2 - getHeight()/2);
		
	}

	/**
	 * Gets field for Attribute code.
	 * @return	codeTextField	containing Attribute code.
	 */
	public JTextField getCodeTextField() {
		return codeTextField;
	}

	/**
	 * Sets field for Attribute code.
	 * @param	codeTextField	containing Attribute code.
	 */
	public void setCodeTextField(JTextField codeTextField) {
		this.codeTextField = codeTextField;
	}

	/**
	 * Gets CheckBox for Null values of an Attribute.
	 * @return CheckBox containing information about Null values of an Attribute.
	 */
	public JCheckBox getNullCheckBox() {
		return nullCheckBox;
	}

	/**
	 * Sets CheckBox for Null values of an Attribute.
	 * @param nullCheckBox containing information about Null values of an Attribute.
	 */
	public void setNullCheckBox(JCheckBox nullCheckBox) {
		this.nullCheckBox = nullCheckBox;
	}

	/**
	 * Gets CheckBox for Unique values of an Attribute.
	 * @return CheckBox containing information about Unique values of an Attribute.
	 */
	public JCheckBox getUniqueCheckBox() {
		return uniqueCheckBox;
	}

	/**
	 * Sets CheckBox for Unique values of an Attribute.
	 * @param uniqueCheckBox containing information about Unique values of an Attribute.
	 */
	public void setUniqueCheckBox(JCheckBox uniqueCheckBox) {
		this.uniqueCheckBox = uniqueCheckBox;
	}

}
