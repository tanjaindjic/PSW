package dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import editorSeme.controller.concreetBuilders.DefaultTableBuilder;
import editorSeme.model.pojo.Atribut;
import editorSeme.model.pojo.Sistem;
import listeners.CancelKeyListener;
import listeners.SaveKeyListener;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Creates Primary Key for current Table.
 * 
 */
public class CreateKeyFrame extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private DefaultTableBuilder dtb; 
	private NewTableFrame newTableFrame; 
	private DefaultListModel<Atribut> dlm;
	private JList listaAtributa;
	
	/**
	 * Creates Primary Key from chosen Attributes.
	 * 
	 * @param dtb makes temporary Table and allows creation of Attributes and Keys.
	 * @param newTableFrame shows all Attributes from current Table with Unique values.
	 * @param dlm makes Model for List of Attributes.
	 */
	public CreateKeyFrame(DefaultTableBuilder dtb, NewTableFrame newTableFrame, DefaultListModel<Atribut> dlm) {
		this.dtb = dtb;
		this.newTableFrame = newTableFrame;
		this.dlm = dlm;
		setTitle(Sistem.getInstance().getTranslate("Create_key"));
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{100, 0, 100, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 20, 50, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblChooseKeys = new JLabel(Sistem.getInstance().getTranslate("Choose_key_s"));
			GridBagConstraints gbc_lblChooseKeys = new GridBagConstraints();
			gbc_lblChooseKeys.insets = new Insets(0, 0, 5, 0);
			gbc_lblChooseKeys.gridwidth = 3;
			gbc_lblChooseKeys.gridx = 0;
			gbc_lblChooseKeys.gridy = 1;
			contentPanel.add(lblChooseKeys, gbc_lblChooseKeys);
		}
		{
			 dlm = new DefaultListModel<Atribut>();
				if(dtb!=null)
					for(Atribut a : dtb.getAtributes() ){
						if(!a.getisNull()) // Ako je is null stiklirano onda moze biti null
							if(a.isUnique()) // Ako je unique stiklirano onda mora biti unique
					     dlm.addElement(a);
					}    
				
				
			JScrollPane scrollPane = new JScrollPane();
			GridBagConstraints gbc_scrollPane = new GridBagConstraints();
			gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
			gbc_scrollPane.fill = GridBagConstraints.BOTH;
			gbc_scrollPane.gridx = 1;
			gbc_scrollPane.gridy = 2;
			contentPanel.add(scrollPane, gbc_scrollPane);
			{
				JList list = new JList();
				scrollPane.setViewportView(list);
			}
			

			listaAtributa = new JList(dlm);
			scrollPane.setViewportView(listaAtributa);
		}
		{
			JPanel panel = new JPanel();
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.insets = new Insets(0, 0, 0, 5);
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 1;
			gbc_panel.gridy = 4;
			contentPanel.add(panel, gbc_panel);
			{
				JButton cancelButton = new JButton(Sistem.getInstance().getTranslate("Cancel"));
				cancelButton.addActionListener(new CancelKeyListener(this));
				panel.add(cancelButton);
				
				
			}
			JButton okButton = new JButton(Sistem.getInstance().getTranslate("Save"));
			panel.add(okButton);
			okButton.addActionListener(new SaveKeyListener(this, listaAtributa, dtb, newTableFrame));
			
		
			getRootPane().setDefaultButton(okButton);
		}
		{
			{
				DefaultListModel<Atribut> dlmm = new DefaultListModel<>();
				
				for(int i = 0; i <  listaAtributa.getSelectedValuesList().size(); i++){
					dlmm.addElement( (Atribut) listaAtributa.getSelectedValuesList().get(i));
				}
				JList selektovaniKljucevi = new JList<>(dlmm);///OVO JE LISTA ATRIBUTA OD KOJIH SE STVARA KLJUC
				//DODATI ACTION LISTENER NA DUGME SAVE
			}
		}
		pack();
		setLocation((Toolkit.getDefaultToolkit().getScreenSize().width)/2 - getWidth()/2, (Toolkit.getDefaultToolkit().getScreenSize().height)/2 - getHeight()/2);
		
	}

}
