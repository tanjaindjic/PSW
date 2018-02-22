package listeners;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import db.connection.DBChooser;
import editorSeme.model.additional.JSONSerialize;
import editorSeme.model.parse.ParseRelationalDB;
import editorSeme.model.pojo.Sistem;
import editorSeme.model.pojo.SistemModel;
import editorSeme.model.pojo.Table;
import editorSeme.view.EditorWorkbench;
import login.UserType;
import start.DatabaseType;
import start.InfViewModel;
import workingsection.MainWindow;
import workingsection.Tabs;
import workingsection.WorkArea;
import workingsection.tree.Tree;


/**
 * Sets chosen Database type in System.
 *
 */
public class DBchosenListener implements ActionListener {
	private JPanel JSONpanel;
	private DBChooser ch;
	private JTextField codeTextField;
	public DBchosenListener(DBChooser ch){
		this.ch = ch;
	}
	
	/**
	 * Sets chosen Database type in System, types are JSON and Relational.
	 *
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		
		
		// TODO Auto-generated method stub
		JComboBox<String> cb = DBChooser.getInstance().getDBcomboBox();
		if(cb.getSelectedItem().equals("JSON")){
			LangSetup();
			DBChooser.getInstance().setTitle(Sistem.getInstance().getTranslate("Choose_DB"));
			JSONpanel = DBChooser.getInstance().getJSONpanel();
			
			GridBagLayout gbl_JSONpanel = new GridBagLayout();
			gbl_JSONpanel.columnWidths = new int[]{100, 0, 0, 100, 0};
			gbl_JSONpanel.rowHeights = new int[]{20, 0, 0, 0, 20, 20, 0, 30, 0, 50, 0, 0};
			gbl_JSONpanel.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
			gbl_JSONpanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
			JSONpanel.setLayout(gbl_JSONpanel);
			{
			}
			JButton btnNew = new JButton(Sistem.getInstance().getTranslate("Create"));
		if(!InfViewModel.getInstance().getCurrentUser().getTypeOfUser().equals(UserType.REG_KOR))	{
			
			{
				JLabel lblNewJsonDatabase = new JLabel(Sistem.getInstance().getTranslate("New_JSON_DB"));
				GridBagConstraints gbc_lblNewJsonDatabase = new GridBagConstraints();
				gbc_lblNewJsonDatabase.anchor = GridBagConstraints.WEST;
				gbc_lblNewJsonDatabase.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewJsonDatabase.gridx = 1;
				gbc_lblNewJsonDatabase.gridy = 1;
				JSONpanel.add(lblNewJsonDatabase, gbc_lblNewJsonDatabase);
			}
			{
				JLabel lblCode = new JLabel(Sistem.getInstance().getTranslate("Code"));
				GridBagConstraints gbc_lblCode = new GridBagConstraints();
				gbc_lblCode.anchor = GridBagConstraints.WEST;
				gbc_lblCode.insets = new Insets(0, 0, 5, 5);
				gbc_lblCode.gridx = 1;
				gbc_lblCode.gridy = 3;
				JSONpanel.add(lblCode, gbc_lblCode);
			}
			{
				codeTextField = new JTextField();
				GridBagConstraints gbc_codeTextField = new GridBagConstraints();
				gbc_codeTextField.insets = new Insets(0, 0, 5, 5);
				gbc_codeTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_codeTextField.gridx = 1;
				gbc_codeTextField.gridy = 4;
				JSONpanel.add(codeTextField, gbc_codeTextField);
				codeTextField.setColumns(10);
			}
			
			GridBagConstraints gbc_btnNew = new GridBagConstraints();
			gbc_btnNew.anchor = GridBagConstraints.EAST;
			gbc_btnNew.insets = new Insets(0, 0, 5, 5);
			gbc_btnNew.gridx = 2;
			gbc_btnNew.gridy = 4;
			btnNew.addActionListener(new NewJSONListener());
			btnNew.setMinimumSize(new Dimension(70, 26));
			btnNew.setMaximumSize(new Dimension(70, 26));
			JSONpanel.add(btnNew, gbc_btnNew);
		}	
			{
				JLabel lblLoadJsonDatabase = new JLabel(Sistem.getInstance().getTranslate("Load_JSON_DB"));
				GridBagConstraints gbc_lblLoadJsonDatabase = new GridBagConstraints();
				gbc_lblLoadJsonDatabase.anchor = GridBagConstraints.WEST;
				gbc_lblLoadJsonDatabase.insets = new Insets(0, 0, 5, 5);
				gbc_lblLoadJsonDatabase.gridx = 1;
				gbc_lblLoadJsonDatabase.gridy = 6;
				JSONpanel.add(lblLoadJsonDatabase, gbc_lblLoadJsonDatabase);
			}
			{
				JButton btnLoad = new JButton(Sistem.getInstance().getTranslate("Load"));
				btnLoad.setPreferredSize(btnNew.getPreferredSize());
				GridBagConstraints gbc_btnLoad = new GridBagConstraints();
				gbc_btnLoad.anchor = GridBagConstraints.EAST;
				gbc_btnLoad.insets = new Insets(0, 0, 5, 5);
				gbc_btnLoad.gridx = 2;
				gbc_btnLoad.gridy = 6;
				btnLoad.addActionListener(new LoadListenerDB(ch));
				btnLoad.setMinimumSize(new Dimension(70, 26));
				btnLoad.setMaximumSize(new Dimension(70, 26));
				JSONpanel.add(btnLoad, gbc_btnLoad);
				
			}
			{
				JButton btnBack = new JButton(Sistem.getInstance().getTranslate("Back"));
				btnBack.addActionListener(new CancelJSONChooser());
				GridBagConstraints gbc_btnBack = new GridBagConstraints();
				gbc_btnBack.gridwidth = 2;
				gbc_btnBack.insets = new Insets(0, 0, 5, 5);
				gbc_btnBack.gridx = 1;
				gbc_btnBack.gridy = 8;
				JSONpanel.add(btnBack, gbc_btnBack);
			}
			
		DBChooser.getInstance().setJSONpanel(JSONpanel);
		DBChooser.getInstance().setCodeTextField(codeTextField);
			CardLayout cardLayout = (CardLayout) DBChooser.getInstance().getContentPanel().getLayout();
			cardLayout.show(DBChooser.getInstance().getContentPanel(), "JSONpanel");
			InfViewModel.getInstance().setDatabaseType(DatabaseType.JSON);
		}else {
			/*
			 OVO SAM SAMO KOPIRAO DA BI SE POKRENULO, NE ZNAM STA TREBA URADITI KAD SE IZABERE DB MOD
			 */
			LangSetup();
			ParseRelationalDB parser = new ParseRelationalDB();
			parser.parseModel();
			InfViewModel.getInstance().setDatabaseType(DatabaseType.RELATIONAL);
			EditorWorkbench.getInstance();
			//JSONSerialize.saveStructure(Sistem.getInstance());
		/*	CardLayout cardLayout = (CardLayout) EditorWorkbench.getOptPanel().getLayout();
			cardLayout.show(EditorWorkbench.getOptPanel(), "opcije");*/
			MainWindow main = MainWindow.getInstance();
			WorkArea wa = MainWindow.getWorkArea();
			Sistem s = Sistem.getInstance();
			Tree.getInstance().setModel(new SistemModel(/*DBChooser.getInstance().getCodeTextField().getText()*/));
		//	Sistem.getInstance().getNaziv().setCode(DBChooser.getInstance().getCodeTextField().getText());
		//	Sistem s = Sistem.getInstance();
			/*
			wa.remove(EditorWorkbench.getInstance());
			wa.remove(Tabs.getInstance());
			wa.repaint();
			wa.validate();
			Tabs.getChildren().removeAll();
			
			Tabs.addTab((Table) Tree.getInstance().getSelected());
			wa.add(Tabs.getInstance());
			wa.repaint();
			wa.validate();
			//s.getNaziv().setCode(DBChooser.getInstance().getCodeTextField().getText());
			*/
			wa.remove(EditorWorkbench.getInstance());
			wa.remove(Tabs.getInstance());
			wa.repaint();
			wa.validate();
			Tabs.getChildren().removeAll();
			Tabs.getTabele().removeAll();
			wa.add(EditorWorkbench.getInstance());
			Tree.getInstance().setVisible(true);
			DBChooser.getInstance().dispose();
		}
		

			
	}
	
	
	/**
	 * Sets up language of System.
	 */
	public void LangSetup() {
		JComboBox<String> lb = DBChooser.getInstance().getLangComboBox();
		String chosenLang = "";
		if(lb.getSelectedItem().equals("English")){
			//Sistem.getInstance().addLang(new Language("ENG", "ENG"));
			//chosenLang = "ENG";
			Locale.setDefault(new Locale("en", "US"));
			//System.out.println(Sistem.getInstance().getTranslate("test"));
		}
			
		else if(lb.getSelectedItem().equals("Serbian")){
			//Sistem.getInstance().addLang(new Language("SRB", "SRB"));
			//chosenLang = "SRB";
			Locale.setDefault(new Locale("sr", "RS"));
			//System.out.println(Sistem.getInstance().getTranslate("test"));
		}
			
		else{
			//Sistem.getInstance().addLang(new Language("ENG", "ENG"));
			//chosenLang = "ENG";
			Locale.setDefault(new Locale("en", "USs"));
			//System.out.println(Sistem.getInstance().getTranslate("test"));
		}
	}

}
