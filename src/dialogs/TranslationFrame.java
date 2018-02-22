 package dialogs;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import db.crud.RelationalCreateTuple;
import db.crud.RelationalRead;
import db.crud.RelationalUpdateTuple;
import db.model.Torka;
import db.model.Value;
import db.search.RelationalSearch;
import editorSeme.controller.concreetBuilders.DefaultBuilder;
import editorSeme.model.additional.JSONSerialize;
import editorSeme.model.enums.Tip;
import editorSeme.model.pojo.Atribut;
import editorSeme.model.pojo.Package;
import editorSeme.model.pojo.Sistem;
import editorSeme.model.pojo.Table;
import editorSeme.model.pojo.Translation;
import editorSeme.view.EditorWorkbench;
import listeners.TranslateSaveListener;
import listeners.TranslateSvegaListener;
import start.DatabaseType;
import start.InfViewModel;
import workingsection.Tabs;
import workingsection.WorkArea;
import workingsection.tree.Tree;


/**
 * Creates Translation for selected Object.
 */
public class TranslationFrame extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField translationTextField;
	private JComboBox<String> langComboBox;
	private JButton btnCancel;
	private JButton btnSave;
	private DefaultBuilder db;
	private JTextField s;
	private EditTableFrame etf;
	private boolean atrEdit;
	private EditSystemFrame esf;
	
	


	/**
	 * Creates Translate for selected Object.
	 * @param db is default builder for Objects.
	 * @param s is code of selected Object.
	 */
	public TranslationFrame(DefaultBuilder db, JTextField s) {
		this.db = db;
		this.s = s;
		setTitle(Sistem.getInstance().getTranslate("Add_tr_title"));
		setResizable(false);
		setModal(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 349, 238);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{45, 100, 118, 45, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gridBagLayout);
		
		JLabel label = new JLabel("      ");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 1;
		gbc_label.gridy = 0;
		panel.add(label, gbc_label);
		
		JLabel lblCode = new JLabel(Sistem.getInstance().getTranslate("Lang"));
		GridBagConstraints gbc_lblCode = new GridBagConstraints();
		gbc_lblCode.anchor = GridBagConstraints.WEST;
		gbc_lblCode.insets = new Insets(0, 0, 5, 5);
		gbc_lblCode.gridx = 1;
		gbc_lblCode.gridy = 1;
		panel.add(lblCode, gbc_lblCode);
		
		langComboBox = new JComboBox<String>();
		GridBagConstraints gbc_langComboBox = new GridBagConstraints();
		gbc_langComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_langComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_langComboBox.gridx = 2;
		gbc_langComboBox.gridy = 1;
		panel.add(langComboBox, gbc_langComboBox);
		
		langComboBox.addItem("enUS");
		langComboBox.addItem("srRS");
		
		
		JLabel lblTranslation = new JLabel(Sistem.getInstance().getTranslate("Add_tr"));
		GridBagConstraints gbc_lblTranslation = new GridBagConstraints();
		gbc_lblTranslation.anchor = GridBagConstraints.WEST;
		gbc_lblTranslation.insets = new Insets(0, 0, 5, 5);
		gbc_lblTranslation.gridx = 1;
		gbc_lblTranslation.gridy = 2;
		panel.add(lblTranslation, gbc_lblTranslation);
		
		translationTextField = new JTextField();
		GridBagConstraints gbc_translationTextField = new GridBagConstraints();
		gbc_translationTextField.insets = new Insets(0, 0, 5, 5);
		gbc_translationTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_translationTextField.gridx = 2;
		gbc_translationTextField.gridy = 2;
		panel.add(translationTextField, gbc_translationTextField);
		translationTextField.setColumns(10);
		
		JLabel label_1 = new JLabel("      ");
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1.gridx = 1;
		gbc_label_1.gridy = 3;
		panel.add(label_1, gbc_label_1);
		
		btnCancel = new JButton(Sistem.getInstance().getTranslate("Cancel"));
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.insets = new Insets(0, 0, 5, 5);
		gbc_btnCancel.gridx = 1;
		gbc_btnCancel.gridy = 4;
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
		gbc_btnSave.gridy = 4;
		btnSave.addActionListener(new TranslateSaveListener(this, translationTextField, langComboBox, db, s ));
		panel.add(btnSave, gbc_btnSave);
		
		pack();
		setLocation((Toolkit.getDefaultToolkit().getScreenSize().width)/2 - getWidth()/2, (Toolkit.getDefaultToolkit().getScreenSize().height)/2 - getHeight()/2);
		
		
		
	}


	/**
	 * Creates Translate for selected Object.
	 * @param etf is parent Frame.
	 * @param atrEdit indicates if Translation should be added to Table or Attribute.
	 */
	public TranslationFrame(EditTableFrame etf, boolean atrEdit) {
		// TODO Auto-generated constructor stub
		this.etf = etf;
		this.atrEdit = atrEdit;
		setTitle(Sistem.getInstance().getTranslate("Add_tr_title"));
		setResizable(false);
		setModal(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 349, 238);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{45, 100, 118, 45, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gridBagLayout);
		
		JLabel label = new JLabel("      ");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 1;
		gbc_label.gridy = 0;
		panel.add(label, gbc_label);
		
		JLabel lblCode = new JLabel(Sistem.getInstance().getTranslate("Lang"));
		GridBagConstraints gbc_lblCode = new GridBagConstraints();
		gbc_lblCode.anchor = GridBagConstraints.WEST;
		gbc_lblCode.insets = new Insets(0, 0, 5, 5);
		gbc_lblCode.gridx = 1;
		gbc_lblCode.gridy = 1;
		panel.add(lblCode, gbc_lblCode);
		
		langComboBox = new JComboBox<String>();
		GridBagConstraints gbc_langComboBox = new GridBagConstraints();
		gbc_langComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_langComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_langComboBox.gridx = 2;
		gbc_langComboBox.gridy = 1;
		panel.add(langComboBox, gbc_langComboBox);
		langComboBox.addItem("enUS");
		langComboBox.addItem("srRS");
		
		
		JLabel lblTranslation = new JLabel(Sistem.getInstance().getTranslate("Add_tr"));
		GridBagConstraints gbc_lblTranslation = new GridBagConstraints();
		gbc_lblTranslation.anchor = GridBagConstraints.WEST;
		gbc_lblTranslation.insets = new Insets(0, 0, 5, 5);
		gbc_lblTranslation.gridx = 1;
		gbc_lblTranslation.gridy = 2;
		panel.add(lblTranslation, gbc_lblTranslation);
		
		translationTextField = new JTextField();
		GridBagConstraints gbc_translationTextField = new GridBagConstraints();
		gbc_translationTextField.insets = new Insets(0, 0, 5, 5);
		gbc_translationTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_translationTextField.gridx = 2;
		gbc_translationTextField.gridy = 2;
		panel.add(translationTextField, gbc_translationTextField);
		translationTextField.setColumns(10);
		
		JLabel label_1 = new JLabel("      ");
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1.gridx = 1;
		gbc_label_1.gridy = 3;
		panel.add(label_1, gbc_label_1);
		
		btnCancel = new JButton(Sistem.getInstance().getTranslate("Cancel"));
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.insets = new Insets(0, 0, 5, 5);
		gbc_btnCancel.gridx = 1;
		gbc_btnCancel.gridy = 4;
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
		gbc_btnSave.gridy = 4;
		btnSave.addActionListener(new TranslateSvegaListener(this, etf, atrEdit));
		panel.add(btnSave, gbc_btnSave);
		
		pack();
		setLocation((Toolkit.getDefaultToolkit().getScreenSize().width)/2 - getWidth()/2, (Toolkit.getDefaultToolkit().getScreenSize().height)/2 - getHeight()/2);
		
		
		
	}


	public TranslationFrame() {
		// TODO Auto-generated constructor stub
		
		
		setTitle(Sistem.getInstance().getTranslate("Add_tr_title"));
		setResizable(false);
		setModal(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 349, 238);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{45, 100, 118, 45, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gridBagLayout);
		
		JLabel label = new JLabel("      ");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 1;
		gbc_label.gridy = 0;
		panel.add(label, gbc_label);
		
		JLabel lblCode = new JLabel(Sistem.getInstance().getTranslate("Lang"));
		GridBagConstraints gbc_lblCode = new GridBagConstraints();
		gbc_lblCode.anchor = GridBagConstraints.WEST;
		gbc_lblCode.insets = new Insets(0, 0, 5, 5);
		gbc_lblCode.gridx = 1;
		gbc_lblCode.gridy = 1;
		panel.add(lblCode, gbc_lblCode);
		
		langComboBox = new JComboBox<String>();
		GridBagConstraints gbc_langComboBox = new GridBagConstraints();
		gbc_langComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_langComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_langComboBox.gridx = 2;
		gbc_langComboBox.gridy = 1;
		panel.add(langComboBox, gbc_langComboBox);
		langComboBox.addItem("enUS");
		langComboBox.addItem("srRS");
		
		
		JLabel lblTranslation = new JLabel(Sistem.getInstance().getTranslate("Add_tr"));
		GridBagConstraints gbc_lblTranslation = new GridBagConstraints();
		gbc_lblTranslation.anchor = GridBagConstraints.WEST;
		gbc_lblTranslation.insets = new Insets(0, 0, 5, 5);
		gbc_lblTranslation.gridx = 1;
		gbc_lblTranslation.gridy = 2;
		panel.add(lblTranslation, gbc_lblTranslation);
		
		translationTextField = new JTextField();
		GridBagConstraints gbc_translationTextField = new GridBagConstraints();
		gbc_translationTextField.insets = new Insets(0, 0, 5, 5);
		gbc_translationTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_translationTextField.gridx = 2;
		gbc_translationTextField.gridy = 2;
		panel.add(translationTextField, gbc_translationTextField);
		translationTextField.setColumns(10);
		
		JLabel label_1 = new JLabel("      ");
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1.gridx = 1;
		gbc_label_1.gridy = 3;
		panel.add(label_1, gbc_label_1);
		
		btnCancel = new JButton(Sistem.getInstance().getTranslate("Cancel"));
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.insets = new Insets(0, 0, 5, 5);
		gbc_btnCancel.gridx = 1;
		gbc_btnCancel.gridy = 4;
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
		gbc_btnSave.gridy = 4;
		btnSave.addActionListener(new TranslateSvegaListener(this, null, false));
		panel.add(btnSave, gbc_btnSave);
		
		pack();
		setLocation((Toolkit.getDefaultToolkit().getScreenSize().width)/2 - getWidth()/2, (Toolkit.getDefaultToolkit().getScreenSize().height)/2 - getHeight()/2);
		
		
		
	}
	
	/**
	 * Gets Field for Translation of an Object.
	 * @return Text Field containing Translation.
	 */
	public JTextField getTranslationTextField() {
		return translationTextField;
	}


	/**
	 * Sets Field for Translation of an Object.
	 * @param translationTextField containing Translation.
	 */
	public void setTranslationTextField(JTextField translationTextField) {
		this.translationTextField = translationTextField;
	}


	/**
	 * Gets Language ComboBox with available languages.
	 * @return Language ComboBox with selected language.
	 */
	public JComboBox<String> getLangComboBox() {
		return langComboBox;
	}

	/**
	 * Sets Language ComboBox with available languages.
	 * @param langComboBox with selected language.
	 */
	public void setLangComboBox(JComboBox<String> langComboBox) {
		this.langComboBox = langComboBox;
	}

}
