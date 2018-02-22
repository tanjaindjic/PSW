package dialogs;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import editorSeme.model.pojo.Sistem;
import editorSeme.model.pojo.Table;
import listeners.ChooseParentTableListener;

/**
 * Gives an option to select parent Table to demote from current Table.
 * 
 */
public class ChooseParentTableFrame extends JDialog {
	private static ArrayList<Table> parTab;
	
	/**
	 * Lists all parents of current Table for selection.
	 * @param parentTables is a List of parent Tables.
	 */
	public ChooseParentTableFrame(ArrayList<Table> parentTables) {

		this.parTab = parentTables;
		setBounds(100, 100, 450, 210);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{100, 0, 100, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 40, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel lblChooseParentTable = new JLabel(Sistem.getInstance().getTranslate("Choose_parent_table"));
		GridBagConstraints gbc_lblChooseParentTable = new GridBagConstraints();
		gbc_lblChooseParentTable.insets = new Insets(0, 0, 5, 5);
		gbc_lblChooseParentTable.gridx = 1;
		gbc_lblChooseParentTable.gridy = 1;
		getContentPane().add(lblChooseParentTable, gbc_lblChooseParentTable);
		
		JComboBox tablesComboBox = new JComboBox();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 2;
		for(int i = 0; i < parTab.size(); i++){
			tablesComboBox.addItem(parTab.get(i));
		}
		getContentPane().add(tablesComboBox, gbc_comboBox);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 0, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 4;
		getContentPane().add(panel, gbc_panel);
		
		
		JButton btnCancel_1 = new JButton(Sistem.getInstance().getTranslate("Cancel"));
		btnCancel_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		panel.add(btnCancel_1);
		
		JButton btnChoose = new JButton(Sistem.getInstance().getTranslate("Choose"));
		if(parTab.size()==0)
			btnChoose.setEnabled(false);
		btnChoose.addActionListener(new ChooseParentTableListener((Table) tablesComboBox.getSelectedItem(), this));
		panel.add(btnChoose);
		
		pack();
		setLocation((Toolkit.getDefaultToolkit().getScreenSize().width)/2 - getWidth()/2, (Toolkit.getDefaultToolkit().getScreenSize().height)/2 - getHeight()/2);
		
		

	}

	
}
