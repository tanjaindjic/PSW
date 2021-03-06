package login;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import editorSeme.model.pojo.Sistem;
import listeners.SaveAccountChangesListener;
import start.InfViewModel;
/**
 * Creates dialog for User Account Settings. Enables Password change.
 */
public class AccountSettings extends JFrame{
	private static AccountSettings frame = null;
	private static JTextField username;
	public static JPasswordField passwordField;
	public static JPasswordField passwordField_1;
	public static JTextField workspacePath;
	
	/**
	 * Empty contructor for Account Settings dialog.
	 */
	private AccountSettings(){
		
	}
	
	/**
	 * Returns Instance of User Account Settings where User can change its' password.
	 * @return Instance of User Account Settings Frame.
	 */
	public static AccountSettings getInstance(){
		if(frame == null){
			frame = new AccountSettings();
			frame.setTitle(Sistem.getInstance().getTranslate("Account_set"));
				Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //Odredjivanje velicine ekrana 
				double width =  screenSize.getWidth(); //da bi dialog izgledao normalno na svim ekranima
				double height =  screenSize.getHeight();
				width=width*0.35; //Procenat ekrana
				height=height*0.35; 
				frame.setBounds(100, 100, (int)width, (int)height);
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				String myLoc = (System.getProperty("user.dir")+"/src/");;
				ImageIcon img = new ImageIcon(myLoc + "gerugrok.jpg");
				frame.setIconImage(img.getImage());
				GridBagLayout gridBagLayout = new GridBagLayout();
				gridBagLayout.columnWidths = new int[]{35, 169, 260, 35, 0};
				gridBagLayout.rowHeights = new int[]{50, 16, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
				gridBagLayout.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
				gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
				frame.getContentPane().setLayout(gridBagLayout);
				
				JLabel lblUsername = new JLabel(Sistem.getInstance().getTranslate("Username"));
				GridBagConstraints gbc_lblUsername = new GridBagConstraints();
				gbc_lblUsername.anchor = GridBagConstraints.WEST;
				gbc_lblUsername.insets = new Insets(10, 10, 5, 5);
				gbc_lblUsername.gridx = 1;
				gbc_lblUsername.gridy = 1;
				
				frame.getContentPane().add(lblUsername, gbc_lblUsername);
				
				username = new JTextField(InfViewModel.getInstance().getCurrentUser().getUsername());
				username.setEditable(false);
				GridBagConstraints gbc_textField = new GridBagConstraints();
				gbc_textField.fill = GridBagConstraints.HORIZONTAL;
				gbc_textField.insets = new Insets(10, 10, 5, 5);
				gbc_textField.gridx = 2;
				gbc_textField.gridy = 1;
				frame.getContentPane().add(username, gbc_textField);
				username.setColumns(20);
				
				JLabel lblChangePassword = new JLabel(Sistem.getInstance().getTranslate("Change_pass"));
				GridBagConstraints gbc_lblChangePassword = new GridBagConstraints();
				gbc_lblChangePassword.anchor = GridBagConstraints.WEST;
				gbc_lblChangePassword.insets = new Insets(10, 10, 5, 5);
				gbc_lblChangePassword.gridx = 1;
				gbc_lblChangePassword.gridy = 3;
				frame.getContentPane().add(lblChangePassword, gbc_lblChangePassword);
				
				passwordField = new JPasswordField();
				passwordField.setColumns(30);
				GridBagConstraints gbc_passwordField = new GridBagConstraints();
				gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
				gbc_passwordField.insets = new Insets(10, 10, 5, 5);
				gbc_passwordField.gridx = 2;
				gbc_passwordField.gridy = 3;
				frame.getContentPane().add(passwordField, gbc_passwordField);
				
				JLabel lblConfirmPassword = new JLabel(Sistem.getInstance().getTranslate("Confirm_pass"));
				GridBagConstraints gbc_lblConfirmPassword = new GridBagConstraints();
				gbc_lblConfirmPassword.anchor = GridBagConstraints.WEST;
				gbc_lblConfirmPassword.insets = new Insets(10, 10, 5, 5);
				gbc_lblConfirmPassword.gridx = 1;
				gbc_lblConfirmPassword.gridy = 5;
				frame.getContentPane().add(lblConfirmPassword, gbc_lblConfirmPassword);
				
				passwordField_1 = new JPasswordField();
				GridBagConstraints gbc_passwordField_1 = new GridBagConstraints();
				gbc_passwordField_1.insets = new Insets(10, 10, 5, 5);
				gbc_passwordField_1.fill = GridBagConstraints.HORIZONTAL;
				gbc_passwordField_1.gridx = 2;
				gbc_passwordField_1.gridy = 5;
				frame.getContentPane().add(passwordField_1, gbc_passwordField_1);
				
				
				JButton btnSaveChanges = new JButton(Sistem.getInstance().getTranslate("Save"));
				GridBagConstraints gbc_btnSaveChanges = new GridBagConstraints();
				gbc_btnSaveChanges.anchor = GridBagConstraints.EAST;
				gbc_btnSaveChanges.insets = new Insets(10, 10, 5, 5);
				gbc_btnSaveChanges.gridx = 1;
				gbc_btnSaveChanges.gridy = 10;
				btnSaveChanges.addActionListener(new SaveAccountChangesListener(frame));
				frame.getContentPane().add(btnSaveChanges, gbc_btnSaveChanges);
				 
				 JButton btnCancel = new JButton(Sistem.getInstance().getTranslate("Cancel"));
				 
				 GridBagConstraints gbc_btnCancel = new GridBagConstraints();
				 gbc_btnCancel.insets = new Insets(10, 10, 5, 5);
				 gbc_btnCancel.gridx = 2;
				 gbc_btnCancel.gridy = 10;
				 btnCancel.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						frame.dispose();
					}
					 
				 });
				 frame.getContentPane().add(btnCancel, gbc_btnCancel);
				
			
			
		}
		return frame;
	}
}