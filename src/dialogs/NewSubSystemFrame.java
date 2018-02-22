package dialogs;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import editorSeme.controller.concreetBuilders.DefaultPackageBuilder;
import editorSeme.model.pojo.Package;
import editorSeme.model.pojo.Sistem;
import listeners.AddTranslationListener;
import listeners.BackToFormOptListener;
import listeners.NewSubSystemSaveListener;
import workingsection.tree.Tree;

/**
 * Creates new SubSystem.
 */
public class NewSubSystemFrame extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField codeTextField;
	private JButton btnSave;
	private JButton btnCancel;
	private JButton btnAddTranslation;
	private Tree t; 
	private DefaultPackageBuilder dpb;

	/**
	 * Creates a type of Package named SubSystem.
	 */
	public NewSubSystemFrame() {
		t= Tree.getInstance();
		dpb = new DefaultPackageBuilder(t.getSelected()); 
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{100, 100, 100, 100, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel label = new JLabel("      ");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 1;
		gbc_label.gridy = 0;
		add(label, gbc_label);
		
		JLabel lblCode = new JLabel(Sistem.getInstance().getTranslate("Code"));
		GridBagConstraints gbc_lblCode = new GridBagConstraints();
		gbc_lblCode.anchor = GridBagConstraints.WEST;
		gbc_lblCode.insets = new Insets(0, 0, 5, 5);
		gbc_lblCode.gridx = 1;
		gbc_lblCode.gridy = 1;
		add(lblCode, gbc_lblCode);
		
		codeTextField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.anchor = GridBagConstraints.WEST;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 1;
		add(codeTextField, gbc_textField);
		codeTextField.setColumns(9);
		
		JLabel lblTranslation = new JLabel(Sistem.getInstance().getTranslate("Add_tr"));
		GridBagConstraints gbc_lblTranslation = new GridBagConstraints();
		gbc_lblTranslation.anchor = GridBagConstraints.WEST;
		gbc_lblTranslation.insets = new Insets(0, 0, 5, 5);
		gbc_lblTranslation.gridx = 1;
		gbc_lblTranslation.gridy = 2;
		add(lblTranslation, gbc_lblTranslation);
		
		
		btnAddTranslation = new JButton("+");
		GridBagConstraints gbc_btnAddTranslation = new GridBagConstraints();
		gbc_btnAddTranslation.insets = new Insets(0, 0, 5, 5);
		gbc_btnAddTranslation.gridx = 2;
		gbc_btnAddTranslation.gridy = 2;
		add(btnAddTranslation, gbc_btnAddTranslation);
		btnAddTranslation.addActionListener(new AddTranslationListener(dpb, codeTextField));
		
		JLabel label_1 = new JLabel("      ");
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1.gridx = 1;
		gbc_label_1.gridy = 3;
		add(label_1, gbc_label_1);
		
		btnCancel = new JButton(Sistem.getInstance().getTranslate("Cancel"));
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.insets = new Insets(0, 0, 5, 5);
		gbc_btnCancel.gridx = 1;
		gbc_btnCancel.gridy = 4;
		add(btnCancel, gbc_btnCancel);
		btnCancel.addActionListener(new BackToFormOptListener());
		
		btnSave = new JButton(Sistem.getInstance().getTranslate("Save"));
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.insets = new Insets(0, 0, 5, 5);
		gbc_btnSave.gridx = 2;
		gbc_btnSave.gridy = 4;
		add(btnSave, gbc_btnSave);
		btnSave.addActionListener(new NewSubSystemSaveListener(dpb, codeTextField));
		
		

	}
	/**
	 * Gets field with code for new Package.
	 * @return TextField containing code for new SubSystem.
	 */
	public JTextField getCodeTextField() {
		return codeTextField;
	}
	/**
	 * Sets field with code for new Package.
	 * @param codeTextField TextField containing code for new SubSystem.
	 */
	public void setCodeTextField(JTextField codeTextField) {
		this.codeTextField = codeTextField;
	}

}
