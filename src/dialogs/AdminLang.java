package dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import editorSeme.model.pojo.Sistem;
import listeners.ChosenLangListener;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JComboBox;

/**
 * Allows Administrators to choose language.
 */
public class AdminLang extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JComboBox langComboBox;

	
/**
 * Creates dialog for choosing language.
 */
	public AdminLang() {
		setBounds(100, 100, 450, 292);
		setTitle(Sistem.getInstance().getTranslate("Admin_panel"));
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 40, 0, 40, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblChooseLanguage = new JLabel("Choose language:");
			GridBagConstraints gbc_lblChooseLanguage = new GridBagConstraints();
			gbc_lblChooseLanguage.insets = new Insets(0, 0, 5, 5);
			gbc_lblChooseLanguage.gridx = 1;
			gbc_lblChooseLanguage.gridy = 1;
			contentPanel.add(lblChooseLanguage, gbc_lblChooseLanguage);
		}
		{
			langComboBox = new JComboBox();
			GridBagConstraints gbc_comboBox = new GridBagConstraints();
			gbc_comboBox.insets = new Insets(0, 0, 5, 5);
			gbc_comboBox.gridx = 1;
			gbc_comboBox.gridy = 2;
			contentPanel.add(langComboBox, gbc_comboBox);
			
			langComboBox.addItem("English");
			langComboBox.addItem("Serbian");
		}
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			GridBagLayout gbl_buttonPane = new GridBagLayout();
			gbl_buttonPane.columnWidths = new int[]{0, 0, 0};
			gbl_buttonPane.rowHeights = new int[]{25, 15, 0};
			gbl_buttonPane.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
			gbl_buttonPane.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
			buttonPane.setLayout(gbl_buttonPane);
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				GridBagConstraints gbc_cancelButton = new GridBagConstraints();
				gbc_cancelButton.insets = new Insets(0, 0, 5, 5);
				gbc_cancelButton.anchor = GridBagConstraints.NORTHEAST;
				gbc_cancelButton.gridx = 0;
				gbc_cancelButton.gridy = 0;
				buttonPane.add(cancelButton, gbc_cancelButton);
			}
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				GridBagConstraints gbc_okButton = new GridBagConstraints();
				gbc_okButton.insets = new Insets(0, 0, 5, 0);
				gbc_okButton.anchor = GridBagConstraints.NORTHWEST;
				gbc_okButton.gridx = 1;
				gbc_okButton.gridy = 0;
				buttonPane.add(okButton, gbc_okButton);
				okButton.addActionListener(new ChosenLangListener(this, langComboBox));
				getRootPane().setDefaultButton(okButton);
			}
			pack();
			setLocation((Toolkit.getDefaultToolkit().getScreenSize().width)/2 - getWidth()/2, (Toolkit.getDefaultToolkit().getScreenSize().height)/2 - getHeight()/2);
			
		}
	}

}
