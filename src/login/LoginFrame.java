package login;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import com.sun.jndi.toolkit.url.Uri;
import com.sun.org.apache.xml.internal.utils.URI;
import com.sun.org.apache.xml.internal.utils.URI.MalformedURIException;

import editorSeme.model.pojo.Sistem;
import listeners.HelpLaunchListener;
import listeners.HelpMouseListener;
import listeners.LoginEnterListener;
import listeners.LoginSubmitListener;

/**
 * Login frame for all Users of InfView.
 * User types are Regular, Projectant and Administrator.
 */

public class LoginFrame extends JFrame{
	
	/**
	 * Gets Username for an User.
	 * @return textName contains value for Username.
	 */
	public static JTextField getTextName() {
		return textName;
	}

	/**
	 * Sets Username for an User.
	 * @param textName contains value for Username.
	 */
	public static void setTextName(JTextField textName) {
		LoginFrame.textName = textName;
	}

	
	/**
	 * Gets Password of an User.
	 * @return Field containing User password.
	 */
	public static JPasswordField getPassField() {
		return passField;
	}

	/**
	 * Sets Password of an User.
	 * @param passField Field containing User password.
	 */
	public static void setPassField(JPasswordField passField) {
		LoginFrame.passField = passField;
	}

	/**
	 * Gets button for Sign In option.
	 * @return Sign In button for Login Frame.
	 */
	public static JButton getSignIn() {
		return signIn;
	}

	/**
	 * Sets button for Sign In option.
	 * @param signIn button for Login Frame.
	 */
	public static void setSignIn(JButton signIn) {
		LoginFrame.signIn = signIn;
	}

	private static JPanel contentPane;	
	private static JMenuBar mBar;
	private static JLabel labName;
	private static JLabel labPass;
	public static JTextField textName;
	public static JPasswordField passField;
	private static BufferedImage logo;
	private static JPanel leftPan;
	private static JPanel rightPan;
	private static JButton signIn;
	private static LoginFrame logInFrame = null;
	private static JLabel lblProjektant;
	private static JLabel lblAdmir;
	private LoginFrame(){
		
	}
	
	/**
	 * Returns Password entered by User.
	 * @return field containing entered Password.
	 */
	public String getPassword(){
		return  passField.getText().toString();
	}
	
	/**
	 * Returns Username entered by User.
	 * @return field containing entered Username.
	 */
	public String getUsername(){	
		return  textName.getText().toString();
	}
	
	/**
	 * Returns an Instance of Login Frame or creates one if it doesn't exists.
	 * @return Instance of Login Frame.
	 */
	public static LoginFrame getInstance(){
		if(logInFrame==null){
			
			logInFrame = new LoginFrame();
			logInFrame.setTitle("InfView");
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			double width =  screenSize.getWidth(); //da bi dialog izgledao normalno na svim ekranima
			double height =  screenSize.getHeight();
			width=width*0.5; //Procenat ekrana
			height=height*0.5;
			logInFrame.mBar = new JMenuBar();
			JMenu mi0 = new JMenu(Sistem.getInstance().getTranslate("Help"));
		//	mi0.addActionListener(new HelpLaunchListener());
			mBar.add(Box.createHorizontalGlue());
			mBar.add(mi0);
			mi0.getInputMap().put(KeyStroke.getKeyStroke("F1"),"login");
			mi0.addMouseListener(new HelpMouseListener());
			mi0.getActionMap().put("login",new HelpLaunchListener());
			
			
			
				
			logInFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			logInFrame.setBounds(100, 100, (int)width, (int)height);
		//	String myLocLogo = (System.getProperty("user.dir")+"/src/");;
			//ImageIcon icon = new ImageIcon(Sistem.getInstance().getClass().getResource("/logo1.jpg"));
			String ss = Sistem.getInstance().getClass().getResource("/logo.jpg").toString();
			String lll = ss+"../src/logo.jpg";
			System.out.println(Sistem.getInstance().getClass().getResource("/logo1.jpg"));
		//	logInFrame.setIconImage(icon.getImage());
		//	
			String myLoc= (System.getProperty("user.dir")+"/src/logo.jpg");
			ImageIcon icon = new ImageIcon(lll);
			
			try {                
		         logo = ImageIO.read(new File(myLoc));
		    } catch (IOException ex) {
		            
		    }		
			JLabel picLabel = new JLabel(new ImageIcon(logo));
			contentPane = new JPanel();
			
			
			
			
			
			
			leftPan = new JPanel();
			GridBagLayout gbl_leftPan = new GridBagLayout();
			gbl_leftPan.columnWidths = new int[]{20, 50, 200, 20};
			gbl_leftPan.rowHeights = new int[]{2, 30, 30, 30, 30, 20};
			gbl_leftPan.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0};
			gbl_leftPan.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
			leftPan.setLayout(gbl_leftPan);
			
			labName = new JLabel(Sistem.getInstance().getTranslate("Username"));
			GridBagConstraints gbc_labName = new GridBagConstraints();
			gbc_labName.insets = new Insets(0, 0, 5, 5);
			gbc_labName.gridx = 1;
			gbc_labName.gridy = 1;
			leftPan.add(labName, gbc_labName);
			
			labPass = new JLabel(Sistem.getInstance().getTranslate("Password"));
			GridBagConstraints gbc_labPass = new GridBagConstraints();
			gbc_labPass.insets = new Insets(0, 0, 5, 5);
			gbc_labPass.gridx = 1;
			gbc_labPass.gridy = 2;
			leftPan.add(labPass, gbc_labPass);
			
			textName = new JTextField();
			textName.setName("username");
			textName.setPreferredSize(new Dimension(150,25));
			GridBagConstraints gbc_textName = new GridBagConstraints();
			gbc_textName.insets = new Insets(0, 0, 5, 5);
			gbc_textName.gridx = 2;
			gbc_textName.gridy = 1;
			leftPan.add(textName, gbc_textName);
			
			
			passField = new JPasswordField();
			passField.setName("pass");
			passField.setPreferredSize(new Dimension(150,25));
			GridBagConstraints gbc_passField = new GridBagConstraints();
			gbc_passField.insets = new Insets(0, 0, 5, 5);
			gbc_passField.gridx = 2;
			gbc_passField.gridy = 2;
			leftPan.add(passField, gbc_passField);
			passField.addKeyListener(new LoginEnterListener()); // IMAMO ENTER ! 
			
			
			
					
			signIn = new JButton(Sistem.getInstance().getTranslate("Sign_in"));
			signIn.setPreferredSize(new Dimension(90,25));
			
			signIn.addActionListener( new LoginSubmitListener());
			/*signIn.addActionListener(new ActionListener()
			{
				@SuppressWarnings("deprecation")
				public void actionPerformed(ActionEvent e)
				{		
					boolean gotIt = false;
					for(int i=0; i<users.getUsers().size(); i++){
						if(textName.getText().toString().equals(users.getUsers().get(i).getUsername())){
							if(passField.getText().equals(users.getUsers().get(i).getPassword())){
								if(users.getUsers().get(i).getTypeOfUser()==UserType.DESIGNER){
									MainWindow main = MainWindow.getInstance();
									if(users.getUsers().get(i).isWantsDialog()){
										ChooseDefaultPath cdp = ChooseDefaultPath.getInstance();
										cdp.show();
										gotIt=true;
									}
								}
								else if(users.getUsers().get(i).getTypeOfUser()==UserType.ADMIN){
									AdminFrame admFrame = AdminFrame.getInstance();
									logInFrame.dispose();
									gotIt=true;
								}
							}
						}
					}
					if(!gotIt)
						JOptionPane.showMessageDialog(null,"Your user name or password is incorrect.");					
				}
			});	*/
			GridBagConstraints gbc_signIn= new GridBagConstraints();
			gbc_signIn.insets = new Insets(0, 0, 5, 5);
			gbc_signIn.gridx = 2;
			gbc_signIn.gridy = 4;
			leftPan.add(signIn, gbc_signIn);
			
		
			
			
		//	leftPan.add(signIn);
			rightPan = new JPanel();
			rightPan.add(picLabel);
			logInFrame.setContentPane(contentPane);
			
			logInFrame.setJMenuBar(mBar);
			contentPane.add(rightPan, BorderLayout.WEST);
			contentPane.add(leftPan, BorderLayout.EAST);
			
			lblProjektant = new JLabel("Projektant (username: proj pass: proj)");
			GridBagConstraints gbc_lblProjektant = new GridBagConstraints();
			gbc_lblProjektant.gridwidth = 2;
			gbc_lblProjektant.insets = new Insets(0, 0, 0, 5);
			gbc_lblProjektant.gridx = 1;
			gbc_lblProjektant.gridy = 5;
			leftPan.add(lblProjektant, gbc_lblProjektant);
			
			lblAdmir = new JLabel("Admin (username: admin pass: admin)");
			GridBagConstraints gbc_lblAdmir = new GridBagConstraints();
			gbc_lblAdmir.gridwidth = 2;
			gbc_lblAdmir.insets = new Insets(0, 0, 0, 5);
			gbc_lblAdmir.gridx = 1;
			gbc_lblAdmir.gridy = 6;
			leftPan.add(lblAdmir, gbc_lblAdmir);
			
			logInFrame.pack();
			logInFrame.setVisible(true);
			
			logInFrame.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width)/2 - logInFrame.getWidth()/2, (Toolkit.getDefaultToolkit().getScreenSize().height)/2 - logInFrame.getHeight()/2);
			
			
		}
	      return logInFrame;
	   }

}
