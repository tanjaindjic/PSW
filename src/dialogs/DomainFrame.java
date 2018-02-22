package dialogs;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import editorSeme.controller.concreetBuilders.DefaultAtributBuilder;
import editorSeme.model.pojo.Sistem;
import listeners.NewDomainSaveListener;

/**
 * Adds Domain for Attribute that is being created.
 * 
 */
public class DomainFrame extends JDialog {

	private static final long serialVersionUID = 1L;
	private JComboBox<String> domainComboBox;
	private JTextField lengthTextField;
	private JButton btnCancel;
	private JButton btnSave;
	private DefaultAtributBuilder dab;

	/**
	 * Sets List of all Domains in a ComboBox.
	 * @param d makes temporary Attribute.
	 * 
	 */
	public DomainFrame(DefaultAtributBuilder d) {
		this.dab=d;
		setTitle(Sistem.getInstance().getTranslate("Add_dom"));
		setResizable(false);
		setModal(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 349, 238);
		JPanel contentPane = new JPanel();
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
		
		JLabel lblCode = new JLabel(Sistem.getInstance().getTranslate("Choose_dom"));
		GridBagConstraints gbc_lblCode = new GridBagConstraints();
		gbc_lblCode.anchor = GridBagConstraints.WEST;
		gbc_lblCode.insets = new Insets(0, 0, 5, 5);
		gbc_lblCode.gridx = 1;
		gbc_lblCode.gridy = 1;
		panel.add(lblCode, gbc_lblCode);
		
		domainComboBox = new JComboBox<String>();
		GridBagConstraints gbc_domainComboBox = new GridBagConstraints();
		gbc_domainComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_domainComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_domainComboBox.gridx = 2;
		gbc_domainComboBox.gridy = 1;
		panel.add(domainComboBox, gbc_domainComboBox);
		domainComboBox.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               
                	if(lengthTextField!=null){
                		if(domainComboBox.getSelectedItem().equals("Boolean"))
                			lengthTextField.setEnabled(false);
                		else lengthTextField.setEnabled(true);
                	
                		
                    
                }
                

                validate();
                repaint();
            }
        });
		
		domainComboBox.addItem("Varchar"); 
		domainComboBox.addItem("Boolean"); 	
		domainComboBox.addItem("Integer"); 
		domainComboBox.addItem("Float"); 
		domainComboBox.addItem("Char"); 
		domainComboBox.addItem("Bigint");
		domainComboBox.addItem("Numeric");
		domainComboBox.addItem("Datetime");
		domainComboBox.addItem("Date");
		domainComboBox.addItem("Time");
		domainComboBox.addItem("Double");
		domainComboBox.addItem("Smalint");
		domainComboBox.addItem("Decimal");
		
		JLabel lblTranslation = new JLabel(Sistem.getInstance().getTranslate("Length"));
		GridBagConstraints gbc_lblTranslation = new GridBagConstraints();
		gbc_lblTranslation.anchor = GridBagConstraints.WEST;
		gbc_lblTranslation.insets = new Insets(0, 0, 5, 5);
		gbc_lblTranslation.gridx = 1;
		gbc_lblTranslation.gridy = 2;
		panel.add(lblTranslation, gbc_lblTranslation);
		
		lengthTextField = new JTextField();
		GridBagConstraints gbc_lengthTextField = new GridBagConstraints();
		gbc_lengthTextField.insets = new Insets(0, 0, 5, 5);
		gbc_lengthTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_lengthTextField.gridx = 2;
		gbc_lengthTextField.gridy = 2;
		panel.add(lengthTextField, gbc_lengthTextField);
		lengthTextField.setColumns(10);
		
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
		btnSave.addActionListener(new NewDomainSaveListener(this, dab, domainComboBox, lengthTextField) );
		panel.add(btnSave, gbc_btnSave);
		
		
		pack();
		setLocation((Toolkit.getDefaultToolkit().getScreenSize().width)/2 - getWidth()/2, (Toolkit.getDefaultToolkit().getScreenSize().height)/2 - getHeight()/2);
		
		
		
		
	}

	/**
	 * Gets List of all Domains.
	 * @return ComboBox with all Domains.
	 * 
	 */
	public JComboBox<String> getDomainComboBox() {
		return domainComboBox;
	}

	/**
	 * Sets List of all Domains in a ComboBox.
	 * @param domainComboBox is JComboBox with all Domains.
	 * 
	 */
	public void setDomainComboBox(JComboBox<String> domainComboBox) {
		this.domainComboBox = domainComboBox;
	}

	/**
	 * Gets field for Length constraint of a Domain.
	 * @return TextField containing Length constraint.
	 * 
	 */
	public JTextField getLengthTextField() {
		return lengthTextField;
	}
	
	/**
	 * Sets field for Length constraint of a Domain.
	 * @param lengthTextField TextField containing Length constraint.
	 * 
	 */
	public void setLengthTextField(JTextField lengthTextField) {
		this.lengthTextField = lengthTextField;
	}

}
