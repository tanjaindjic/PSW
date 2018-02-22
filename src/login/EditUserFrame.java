package login;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import editorSeme.model.pojo.Sistem;
import listeners.EditUserSaveListener;

/**
 * Edits User in Database.
 */
public class EditUserFrame extends JDialog {

	private JPanel contentPane;
	private JTextField textField;


/**
 * Edits User in Database, only available to Administrators.
 * @param u is User being edited.
 */
	public EditUserFrame(User u) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 300, 300);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{30, 0, 0, 30, 0};
		gbl_contentPane.rowHeights = new int[]{30, 0, 30, 0, 30, 0, 30, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblIzmenaLozinke = new JLabel(Sistem.getInstance().getTranslate("Change_pass"));
		GridBagConstraints gbc_lblIzmenaLozinke = new GridBagConstraints();
		gbc_lblIzmenaLozinke.insets = new Insets(0, 0, 5, 0);
		gbc_lblIzmenaLozinke.gridwidth = 4;
		gbc_lblIzmenaLozinke.gridx = 0;
		gbc_lblIzmenaLozinke.gridy = 1;
		contentPane.add(lblIzmenaLozinke, gbc_lblIzmenaLozinke);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 2;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 3;
		contentPane.add(textField, gbc_textField);
		textField.setColumns(10);
		
		JButton btnSave = new JButton(Sistem.getInstance().getTranslate("Save"));
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.anchor = GridBagConstraints.EAST;
		gbc_btnSave.insets = new Insets(0, 0, 5, 5);
		gbc_btnSave.gridx = 1;
		gbc_btnSave.gridy = 5;
		contentPane.add(btnSave, gbc_btnSave);
		btnSave.addActionListener(new EditUserSaveListener(u));
		
		JButton btnCancel = new JButton(Sistem.getInstance().getTranslate("Cancel"));
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.anchor = GridBagConstraints.WEST;
		gbc_btnCancel.insets = new Insets(0, 0, 5, 5);
		gbc_btnCancel.gridx = 2;
		gbc_btnCancel.gridy = 5;
		contentPane.add(btnCancel, gbc_btnCancel);
		btnCancel.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
			
		});
		setModal(true);
		setLocation((Toolkit.getDefaultToolkit().getScreenSize().width)/2 - getWidth()/2, (Toolkit.getDefaultToolkit().getScreenSize().height)/2 - getHeight()/2);
		
	}

}
