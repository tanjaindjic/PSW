package login;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import editorSeme.model.pojo.Sistem;
import listeners.AddUserListener;
import listeners.SaveNewUserListener;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

/**
 * Adds new User in Database.
 */
public class AddUserFrame extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField usernameTextField;
	private JTextField passTextField;
	private JComboBox typeComboBox;

/**
 * Adds new User in Database, only available to Administrators.
 * User types are Regular, Projectant and Administrator.
 */
	public AddUserFrame() {
		setTitle(Sistem.getInstance().getTranslate("Add_new_user"));
		setBounds(100, 100, 450, 367);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{100, 0, 0, 100, 0};
		gbl_contentPanel.rowHeights = new int[]{30, 0, 30, 0, 30, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblUsername = new JLabel(Sistem.getInstance().getTranslate("Username"));
			GridBagConstraints gbc_lblUsername = new GridBagConstraints();
			gbc_lblUsername.anchor = GridBagConstraints.EAST;
			gbc_lblUsername.insets = new Insets(0, 0, 5, 5);
			gbc_lblUsername.gridx = 1;
			gbc_lblUsername.gridy = 1;
			contentPanel.add(lblUsername, gbc_lblUsername);
		}
		{
			usernameTextField = new JTextField();
			GridBagConstraints gbc_usernameTextField = new GridBagConstraints();
			gbc_usernameTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_usernameTextField.insets = new Insets(0, 0, 5, 5);
			gbc_usernameTextField.gridx = 2;
			gbc_usernameTextField.gridy = 1;
			contentPanel.add(usernameTextField, gbc_usernameTextField);
			usernameTextField.setColumns(10);
		}
		{
			JLabel lblPassword = new JLabel(Sistem.getInstance().getTranslate("Password"));
			GridBagConstraints gbc_lblPassword = new GridBagConstraints();
			gbc_lblPassword.anchor = GridBagConstraints.EAST;
			gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
			gbc_lblPassword.gridx = 1;
			gbc_lblPassword.gridy = 3;
			contentPanel.add(lblPassword, gbc_lblPassword);
		}
		{
			passTextField = new JTextField();
			GridBagConstraints gbc_passTextField = new GridBagConstraints();
			gbc_passTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_passTextField.insets = new Insets(0, 0, 5, 5);
			gbc_passTextField.gridx = 2;
			gbc_passTextField.gridy = 3;
			contentPanel.add(passTextField, gbc_passTextField);
			passTextField.setColumns(10);
		}
		{
			JLabel lblTypeOfUser = new JLabel(Sistem.getInstance().getTranslate("User_type"));
			GridBagConstraints gbc_lblTypeOfUser = new GridBagConstraints();
			gbc_lblTypeOfUser.anchor = GridBagConstraints.EAST;
			gbc_lblTypeOfUser.insets = new Insets(0, 0, 5, 5);
			gbc_lblTypeOfUser.gridx = 1;
			gbc_lblTypeOfUser.gridy = 5;
			contentPanel.add(lblTypeOfUser, gbc_lblTypeOfUser);
		}
		{
			typeComboBox = new JComboBox();
			GridBagConstraints gbc_typeComboBox = new GridBagConstraints();
			gbc_typeComboBox.insets = new Insets(0, 0, 5, 5);
			gbc_typeComboBox.fill = GridBagConstraints.HORIZONTAL;
			gbc_typeComboBox.gridx = 2;
			gbc_typeComboBox.gridy = 5;
			contentPanel.add(typeComboBox, gbc_typeComboBox);
			typeComboBox.addItem("Administrator");
			typeComboBox.addItem("Projectant");
			typeComboBox.addItem("Regular");
		}
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			GridBagLayout gbl_buttonPane = new GridBagLayout();
			gbl_buttonPane.columnWidths = new int[]{100, 0, 0, 100, 0};
			gbl_buttonPane.rowHeights = new int[]{0, 25, 0};
			gbl_buttonPane.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
			gbl_buttonPane.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
			buttonPane.setLayout(gbl_buttonPane);
			{
				JButton okButton = new JButton(Sistem.getInstance().getTranslate("Save"));
				okButton.addActionListener(new SaveNewUserListener(this));
				okButton.setActionCommand("OK");
				GridBagConstraints gbc_okButton = new GridBagConstraints();
				gbc_okButton.fill = GridBagConstraints.VERTICAL;
				gbc_okButton.insets = new Insets(0, 0, 5, 5);
				gbc_okButton.gridx = 1;
				gbc_okButton.gridy = 0;
				buttonPane.add(okButton, gbc_okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton(Sistem.getInstance().getTranslate("Cancel"));
				cancelButton.setActionCommand("Cancel");
				GridBagConstraints gbc_cancelButton = new GridBagConstraints();
				gbc_cancelButton.insets = new Insets(0, 0, 5, 5);
				gbc_cancelButton.fill = GridBagConstraints.VERTICAL;
				gbc_cancelButton.gridx = 2;
				gbc_cancelButton.gridy = 0;
				buttonPane.add(cancelButton, gbc_cancelButton);
				cancelButton.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						dispose();
					}
					
				});
				
			}
		}
		setModal(true);
		setLocation((Toolkit.getDefaultToolkit().getScreenSize().width)/2 - getWidth()/2, (Toolkit.getDefaultToolkit().getScreenSize().height)/2 - getHeight()/2);
		
	}

	/**
	 * Gets field for Username information.
	 * @return field containing Username information.
	 */
	public JTextField getUsernameTextField() {
		return usernameTextField;
	}

	/**
	 * Sets field for Username information.
	 * @param usernameTextField containing Username information.
	 */
	public void setUsernameTextField(JTextField usernameTextField) {
		this.usernameTextField = usernameTextField;
	}


	/**
	 * Gets field for Password information.
	 * @return field containing Password information.
	 */
	public JTextField getPassTextField() {
		return passTextField;
	}


	/**
	 * Sets field for Password information.
	 * @param passTextField containing Password information.
	 */
	public void setPassTextField(JTextField passTextField) {
		this.passTextField = passTextField;
	}

	/**
	 * Gets ComboBox with User types information.
	 * @return ComboBox with User types information.
	 */
	public JComboBox getTypeComboBox() {
		return typeComboBox;
	}

	/**
	 * Sets ComboBox with User types information.
	 * @param typeComboBox with User types information.
	 */
	public void setTypeComboBox(JComboBox typeComboBox) {
		this.typeComboBox = typeComboBox;
	}

}
