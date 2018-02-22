	package dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import editorSeme.model.pojo.Sistem;
import jsonDataBase.additional.ChoosePathListener;
import listeners.AddTranslListener;
import listeners.SaveSystemEditsListner;
import listeners.TranslationSystemListener;
import listeners.TranslationTableListener;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * 
*Allows adding Translate to System and location change.
 */
public class EditSystemFrame extends JDialog {

	private final JPanel contentPanel = new JPanel();
	public static JTextField tf;

	/**
	 *Changes location for System in JSON Database and adds Translate.
	 */
	public EditSystemFrame() {
		setTitle(Sistem.getInstance().getTranslate("Edit_sys"));
		setModal(true);
		setBounds(100, 100, 419, 321);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{30, 120, 120, 30, 0};
		gbl_contentPanel.rowHeights = new int[]{30, 0, 30, 0, 30, 0, 30};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE, 0.0};
		contentPanel.setLayout(gbl_contentPanel);
		
		{
			JLabel lblAddTranslation = new JLabel(Sistem.getInstance().getTranslate("Add_tr"));
			GridBagConstraints gbc_lblAddTranslation = new GridBagConstraints();
			gbc_lblAddTranslation.anchor = GridBagConstraints.EAST;
			gbc_lblAddTranslation.insets = new Insets(0, 0, 5, 5);
			gbc_lblAddTranslation.gridx = 1;
			gbc_lblAddTranslation.gridy = 1;
			contentPanel.add(lblAddTranslation, gbc_lblAddTranslation);
		}
		{
			JButton AddTranslationBtn = new JButton("+");
			GridBagConstraints gbc_AddTranslationBtn = new GridBagConstraints();
			gbc_AddTranslationBtn.anchor = GridBagConstraints.WEST;
			gbc_AddTranslationBtn.insets = new Insets(0, 0, 5, 5);
			gbc_AddTranslationBtn.gridx = 2;
			gbc_AddTranslationBtn.gridy = 1;
			
			AddTranslationBtn.addActionListener(new TranslationSystemListener());
			contentPanel.add(AddTranslationBtn, gbc_AddTranslationBtn);
		}
		
		
		{
			JLabel lblEditLocation = new JLabel(Sistem.getInstance().getTranslate("Edit_loc"));
			GridBagConstraints gbc_lblEditLocation = new GridBagConstraints();
			gbc_lblEditLocation.insets = new Insets(0, 0, 5, 5);
			gbc_lblEditLocation.gridx = 1;
			gbc_lblEditLocation.gridy = 3;
			contentPanel.add(lblEditLocation, gbc_lblEditLocation);
		}
		{
			tf = new JTextField();
			GridBagConstraints gbc_tf = new GridBagConstraints();
			gbc_tf.insets = new Insets(0, 0, 5, 0);
			gbc_tf.fill = GridBagConstraints.HORIZONTAL;
			gbc_tf.gridx = 2;
			gbc_tf.gridy = 3;
			contentPanel.add(tf, gbc_tf);
			tf.setColumns(10);
		}
		{
			JButton btnChoose = new JButton(Sistem.getInstance().getTranslate("Choose"));
			btnChoose.addActionListener(new ChoosePathListener(tf));
			GridBagConstraints gbc_btnChoose = new GridBagConstraints();
			gbc_btnChoose.insets = new Insets(0, 0, 5, 0);
			gbc_btnChoose.gridwidth = 3;
			gbc_btnChoose.gridx = 1;
			gbc_btnChoose.gridy = 5;
			contentPanel.add(btnChoose, gbc_btnChoose);
		}
		
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			GridBagLayout gbl_buttonPane = new GridBagLayout();
			gbl_buttonPane.columnWidths = new int[]{0, 71, 0};
			gbl_buttonPane.rowHeights = new int[]{25, 15, 0};
			gbl_buttonPane.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
			gbl_buttonPane.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
			buttonPane.setLayout(gbl_buttonPane);
			{
				JButton cancelButton = new JButton(Sistem.getInstance().getTranslate("Cancel"));
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
				JButton okButton = new JButton(Sistem.getInstance().getTranslate("Save"));
				GridBagConstraints gbc_okButton = new GridBagConstraints();
				gbc_okButton.insets = new Insets(0, 0, 5, 0);
				gbc_okButton.anchor = GridBagConstraints.WEST;
				gbc_okButton.gridx = 1;
				gbc_okButton.gridy = 0;
				buttonPane.add(okButton, gbc_okButton);
				okButton.addActionListener(new SaveSystemEditsListner(this));
				getRootPane().setDefaultButton(okButton);
			}
		}
		pack();
		setLocation((Toolkit.getDefaultToolkit().getScreenSize().width)/2 - getWidth()/2, (Toolkit.getDefaultToolkit().getScreenSize().height)/2 - getHeight()/2);
		
	}

	/**
	 * Gets field for new location.
	 * @return TextField containing new location of System.
	 * 
	 */
	public JTextField gettf() {
		return tf;
	}

	/**
	 * Sets field for new location.
	 * @param tf TextField containing new location of System.
	 * 
	 */
	public void settf(JTextField tf) {
		this.tf = tf;
	}

}
