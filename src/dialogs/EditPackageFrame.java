package dialogs;

import java.awt.BorderLayout;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import editorSeme.controller.concreetBuilders.DefaultPackageBuilder;
import listeners.AddTranslListener;
import listeners.AddTranslationListener;
import listeners.SavePackageEditsListener;
import workingsection.tree.Tree;
import editorSeme.model.pojo.Package;
import editorSeme.model.pojo.Sistem;

/**
 * Allows adding Translaion to Packages.
 */
public class EditPackageFrame extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private DefaultPackageBuilder dpb;
	/**
	 *Adds Translation for Packages.
	 */
	public EditPackageFrame() {
		setTitle(Sistem.getInstance().getTranslate("Add_tr_title"));
		dpb = new DefaultPackageBuilder(Tree.getInstance().getSelected()); 
		setModal(true);
		setBounds(100, 100, 371, 254);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{30, 0, 0, 30, 0};
		gbl_contentPanel.rowHeights = new int[]{30, 0, 0, 30, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
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
			
			AddTranslationBtn.addActionListener(new AddTranslListener(this));
			contentPanel.add(AddTranslationBtn, gbc_AddTranslationBtn);
		}
		{
			pack();
			setLocation((Toolkit.getDefaultToolkit().getScreenSize().width)/2 - getWidth()/2, (Toolkit.getDefaultToolkit().getScreenSize().height)/2 - getHeight()/2);
			
	}

	}

}
