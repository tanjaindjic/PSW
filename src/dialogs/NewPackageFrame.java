package dialogs;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import editorSeme.controller.concreetBuilders.DefaultPackageBuilder;
import editorSeme.model.pojo.Package;
import editorSeme.model.pojo.Sistem;
import listeners.AddTranslationListener;
import listeners.BackToFormOptListener;
import listeners.NewPackageSaveListener;
import workingsection.tree.Tree;

/**
 * Creates new Package.
 */
public class NewPackageFrame extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JTextField codeTextField;
	private JButton btnAddTranslation;
	private JButton btnCancel;
	private JButton btnSave;
	private Tree t; 
	private DefaultPackageBuilder dpb;
	/**
	 * Creates new Package, available Types are Package and SubSystem.
	 */
	public NewPackageFrame() {
		t= Tree.getInstance();
		dpb = new DefaultPackageBuilder(t.getSelected()); 
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{100, 100, 100, 100, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
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
		GridBagConstraints gbc_codeTextField = new GridBagConstraints();
		gbc_codeTextField.anchor = GridBagConstraints.WEST;
		gbc_codeTextField.insets = new Insets(0, 0, 5, 5);
		gbc_codeTextField.gridx = 2;
		gbc_codeTextField.gridy = 1;
		add(codeTextField, gbc_codeTextField);
		codeTextField.setColumns(9);
		
		JLabel lblTranslation = new JLabel(Sistem.getInstance().getTranslate("Add_tr"));
		GridBagConstraints gbc_lblTranslation = new GridBagConstraints();
		gbc_lblTranslation.gridheight = 2;
		gbc_lblTranslation.anchor = GridBagConstraints.WEST;
		gbc_lblTranslation.insets = new Insets(0, 0, 5, 5);
		gbc_lblTranslation.gridx = 1;
		gbc_lblTranslation.gridy = 2;
		add(lblTranslation, gbc_lblTranslation);
		
		btnAddTranslation = new JButton("+");
		GridBagConstraints gbc_btnAddTranslation = new GridBagConstraints();
		gbc_btnAddTranslation.gridheight = 2;
		gbc_btnAddTranslation.insets = new Insets(0, 0, 5, 5);
		gbc_btnAddTranslation.gridx = 2;
		gbc_btnAddTranslation.gridy = 2;
		add(btnAddTranslation, gbc_btnAddTranslation);
		btnAddTranslation.addActionListener(new AddTranslationListener(dpb, codeTextField));
		
		JLabel label_1 = new JLabel("      ");
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1.gridx = 1;
		gbc_label_1.gridy = 4;
		add(label_1, gbc_label_1);
		
		btnCancel = new JButton(Sistem.getInstance().getTranslate("Cancel"));
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.insets = new Insets(0, 0, 5, 5);
		gbc_btnCancel.gridx = 1;
		gbc_btnCancel.gridy = 5;
		add(btnCancel, gbc_btnCancel);
		
		btnSave = new JButton(Sistem.getInstance().getTranslate("Save"));
		btnSave.addActionListener(new NewPackageSaveListener(dpb, codeTextField));
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.insets = new Insets(0, 0, 5, 5);
		gbc_btnSave.gridx = 2;
		gbc_btnSave.gridy = 5;
		add(btnSave, gbc_btnSave);
		btnCancel.addActionListener(new BackToFormOptListener());
		
		
	}
	/**
	 * Gets field with code for new Package.
	 * @return TextField containing code for new Package.
	 */
	public JTextField getCodeTextField() {
		return codeTextField;
	}
	
	/**
	 * Sets field with code for new Package.
	 * @param codeTextField TextField containing code for new Package.
	 */
	public void setCodeTextField(JTextField codeTextField) {
		this.codeTextField = codeTextField;
	}

}
