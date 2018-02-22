package dialogs;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import editorSeme.model.pojo.Atribut;
import editorSeme.model.pojo.Table;
import editorSeme.view.EditorWorkbench;
import workingsection.tree.Tree;

/**
 * Sets new code for selected Table or Attribute.
 */
public class EditCodeTable extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private Table selected;
	private Atribut selectedValue;
	private EditTableFrame editTableFrame;
	
	
	/**
	 * Launch the application.
	 * @param editTableFrame is a parent Frame.
	 * @param selectedValue is chosen Attribute for code change from selected Table.
	 * @param selected is selected Table.
	 */
	public EditCodeTable(EditTableFrame editTableFrame, Table selected, Atribut selectedValue) {
		this.editTableFrame = editTableFrame;
		this.selected = selected;
		this.selectedValue = selectedValue;
		setTitle("Edit Attribute code");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{30, 0, 0, 30, 0};
		gbl_contentPanel.rowHeights = new int[]{50, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblEditCode = new JLabel("Edit code:  ");
			GridBagConstraints gbc_lblEditCode = new GridBagConstraints();
			gbc_lblEditCode.anchor = GridBagConstraints.EAST;
			gbc_lblEditCode.insets = new Insets(0, 0, 0, 5);
			gbc_lblEditCode.gridx = 1;
			gbc_lblEditCode.gridy = 1;
			contentPanel.add(lblEditCode, gbc_lblEditCode);
		}
		{
			textField = new JTextField();
			GridBagConstraints gbc_textField = new GridBagConstraints();
			gbc_textField.anchor = GridBagConstraints.WEST;
			gbc_textField.insets = new Insets(0, 0, 0, 5);
			gbc_textField.gridx = 2;
			gbc_textField.gridy = 1;
			contentPanel.add(textField, gbc_textField);
			textField.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			GridBagLayout gbl_buttonPane = new GridBagLayout();
			gbl_buttonPane.columnWidths = new int[]{0, 49, 0};
			gbl_buttonPane.rowHeights = new int[]{25, 15, 0};
			gbl_buttonPane.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
			gbl_buttonPane.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
			buttonPane.setLayout(gbl_buttonPane);
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				GridBagConstraints gbc_cancelButton = new GridBagConstraints();
				gbc_cancelButton.insets = new Insets(0, 0, 5, 5);
				gbc_cancelButton.anchor = GridBagConstraints.NORTHEAST;
				gbc_cancelButton.gridx = 0;
				gbc_cancelButton.gridy = 0;
				buttonPane.add(cancelButton, gbc_cancelButton);
			}
			{
				JButton okButton = new JButton("Save");
				okButton.setActionCommand("OK");
				GridBagConstraints gbc_okButton = new GridBagConstraints();
				gbc_okButton.insets = new Insets(0, 0, 5, 0);
				gbc_okButton.anchor = GridBagConstraints.NORTHWEST;
				gbc_okButton.gridx = 1;
				gbc_okButton.gridy = 0;
				buttonPane.add(okButton, gbc_okButton);
				okButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						if(!textField.getText().isEmpty())
							selected.getNaziv().setCode(textField.getText());
						
						DefaultListModel dlm = new DefaultListModel<>();
						for(Atribut a : selected.getPolja())
							dlm.addElement(a);	
						editTableFrame.getList().setModel(dlm);
						Tree.getInstance().reload();
						EditorWorkbench.getCodeArea().validate();
						dispose();
						
					}
				});
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

}
