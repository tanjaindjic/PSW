package login;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import db.connection.DBConnection;
import editorSeme.model.pojo.Sistem;
import listeners.AddUserListener;
import listeners.DeleteUserListener;
import listeners.EditUserListener;

/**
 * 
 * Frame for Administrators. Gives options for adding, editing and deleting Users.
 *
 */
public class AdminFrame extends JDialog {

	private JPanel contentPane;
	private JList<String> list;
	private static AdminFrame af;
	
	/**
	 * Frame with options for adding, editing and deleting Users.
	 */
	private AdminFrame() {}
	
	public static AdminFrame getInstance(){
		if(af == null)
			af = new AdminFrame();

		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //Odredjivanje velicine ekrana 
		double width =  screenSize.getWidth(); //da bi dialog izgledao normalno na svim ekranima
		double height =  screenSize.getHeight(); 
		af.setTitle(Sistem.getInstance().getTranslate("Admin_panel"));
		af.setPreferredSize(new Dimension(500,300));
		af.setBounds(100, 100, 500, 401);
		String myLoc = (System.getProperty("user.dir")+"/src/");;
		ImageIcon img = new ImageIcon(myLoc + "logo1.jpg");
		af.setIconImage(img.getImage());
		

		af.contentPane = new JPanel();
		af.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		af.setContentPane(af.contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{250, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		af.contentPane.setLayout(gbl_contentPane);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 0, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		af.contentPane.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{30, 0, 30, 0};
		gbl_panel.rowHeights = new int[]{30, 0, 0, 0, 30, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblNewLabel = new JLabel(Sistem.getInstance().getTranslate("Users"));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		panel.add(lblNewLabel, gbc_lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 3;
		panel.add(scrollPane, gbc_scrollPane);
		
		ArrayList<User> users = DBConnection.getInstance().getUsers();
		DefaultListModel<String> dlm = new DefaultListModel<String>();
		for(User u : users)
			dlm.addElement(u.getUsername());
		af.list = new JList<String>();
		af.list.setModel(dlm);
		af.list.setFont(new Font( "Arial", Font.CENTER_BASELINE,  12) );
			
		scrollPane.setViewportView(af.list);
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 0;
		af.	contentPane.add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{30, 0, 30, 0, 30, 0, 30, 0, 0, 0, 30, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblNewLabel_1 = new JLabel(Sistem.getInstance().getTranslate("Options"));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_1.gridwidth = 3;
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		panel_1.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JButton btnNewButton = new JButton(Sistem.getInstance().getTranslate("Add_user"));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridwidth = 3;
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 3;
		panel_1.add(btnNewButton, gbc_btnNewButton);
		btnNewButton.addActionListener(new AddUserListener());
		
		JButton btnNewButton_1 = new JButton(Sistem.getInstance().getTranslate("Edit_user"));
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 1;
		gbc_btnNewButton_1.gridy = 5;
		panel_1.add(btnNewButton_1, gbc_btnNewButton_1);
		btnNewButton_1.addActionListener(new EditUserListener(af));
		
		JButton btnDeleteUser = new JButton(Sistem.getInstance().getTranslate("Del_user"));
		GridBagConstraints gbc_btnDeleteUser = new GridBagConstraints();
		gbc_btnDeleteUser.insets = new Insets(0, 0, 5, 5);
		gbc_btnDeleteUser.gridx = 1;
		gbc_btnDeleteUser.gridy = 7;
		panel_1.add(btnDeleteUser, gbc_btnDeleteUser);
		btnDeleteUser.addActionListener(new DeleteUserListener(af));
		
		af.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width)/2 - af.getWidth()/2, (Toolkit.getDefaultToolkit().getScreenSize().height)/2 - af.getHeight()/2);
		return af;
		
	
	}

  /**
   * Returns list of Users.
   * @return list of Users in System.
   */
	public JList<String> getList() {
		return list;
	}

	/**
	   * Sets list of Users.
	   * @param list of Users in System.
	   */
	public void setList(JList<String> list) {
		this.list = list;
	}

}
