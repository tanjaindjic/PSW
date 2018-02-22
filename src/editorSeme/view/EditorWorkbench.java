package editorSeme.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.text.Highlighter.HighlightPainter;

import editorSeme.controller.concreetBuilders.DefaultPackageBuilder;
import editorSeme.model.additional.JSONSerialize;
import editorSeme.model.enums.PackageType;
import editorSeme.model.pojo.Atribut;
import editorSeme.model.pojo.Package;
import editorSeme.model.pojo.Sistem;
import editorSeme.model.pojo.Table;
import listeners.CancelNewSystemListener;
import listeners.ForwardListener;
import listeners.NewPackageFormListener;
import listeners.NewSubSystemFormListener;
import listeners.NewTableListener;
import listeners.RollbacListener;
import listeners.SaveBtnListener;
import listeners.SaveMementoListener;
import listeners.ValidVersionListener;
import listeners.ValidateListener;
import start.DatabaseType;
import start.InfViewModel;
import workingsection.tree.Tree;

/**
 * Creates workbench for JSON Database. 
 */
public class EditorWorkbench extends JPanel {

	
	private static final long serialVersionUID = 1L;
	
	private static EditorWorkbench editor=null;
	private static JPanel formPanel=null;
	private static JPanel optPanel=null ;
	private static JPanel activeFormPanel=null;
	private static JScrollPane scrollPane_1=null;
	private static String jsonTekst;
	public static JTextArea codeArea;
	private static int dividerLoc;
	private static JButton btnNewSubSys;
	private static JButton btnNewPackage;
	private static JButton btnNewTable;
	private static ArrayList<String> keywords;
	

	/**
	 * Gets JSON code.
	 * @return JSON code as String.
	 */
	public static String getJsonTekst() {
		return jsonTekst;
	}

	/**
	 * Sets JSON code.
	 * @param jsonTekst is JSON code as String.
	 */
	public static void setJsonTekst(String jsonTekst) {
		EditorWorkbench.jsonTekst = jsonTekst;
	}
	
	/**
	 * Highlights JSON code.
	 * @param word is JSON keywords for highlighting.
	 */
	public static void higlightWords(String word){
		
		
		 Highlighter highlighter = codeArea.getHighlighter();
	      HighlightPainter painter = 
	             new DefaultHighlighter.DefaultHighlightPainter(Color.pink);
	      
		  int index = jsonTekst.indexOf(word);
	      while(index >= 0) {
	       
	          int p1 = index + word.length();
	          try {
				highlighter.addHighlight(index, p1, painter );
			} catch (BadLocationException e) {
				
			}
	          index = jsonTekst.indexOf(word, index+1);
	         
	      }
		
	}
	
	/**
	 * Refreshes JSON code.
	 */
	public static void reloadJsonTekst() {
		jsonTekst = JSONSerialize.getJsonText();
		if(codeArea!=null){
			codeArea.setText(jsonTekst);
			for (String word : keywords) {
				higlightWords(word);
			}

		}
		
		
	}
	
	private static Tree t;
	private static JPanel codeOptPanel;
	private static JPanel codePanel;
	private static JSplitPane splitPane;
	private static JPanel optionsPanel;

	private DefaultPackageBuilder dpb;
	
	/**
	 * Returns panel with active form for creating Objects.
	 * @return panel with currently open form for creating Objects.
	 */
	public static JPanel getActiveFormPanelInstance() {
		return activeFormPanel;
	}
	
	/**
	 * Returns builder for temporary Packages.
	 * @return builder for creating temporary Packages.
	 */
	public DefaultPackageBuilder getBuilder(){
		return dpb;
	}
	
	/**
	 * Sets builder for temporary Packages.
	 * @param d is default builder for creating temporary Packages.
	 */
	public void setBuilder( DefaultPackageBuilder d ){
		dpb=d;
	}
	
	/**
	 * Sets panel with active form for creating Objects.
	 * @param activeFormP is panel with currently open form for creating Objects.
	 */
	public static void setActiveFormPanel(JPanel activeFormP) {
		activeFormPanel = activeFormP;
		reloadJsonTekst();
		codeArea.setText(jsonTekst);
		scrollPane_1.setViewportView(activeFormPanel);
		formPanel.repaint();
		formPanel.validate();
		optPanel.repaint();
		optPanel.validate();
		reloadSplitPane();
		editor.repaint();
		editor.validate();

	}
	
	/**
	 * Refreshes SplitPane in workbench.
	 */
	public static void reloadSplitPane(){
		splitPane.setLeftComponent(optPanel);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //Odredjivanje velicine ekrana 
		double width =  screenSize.getWidth(); //da bi dialog izgledao normalno na svim ekranima
		double height =  screenSize.getHeight(); 
		splitPane.setDividerLocation(dividerLoc);
		splitPane.repaint();
		splitPane.validate();
	}

	/**
	 * Returns panel with options for creating Objects.
	 * @return panel with options for creating Objects.
	 */
	public static JPanel getOptPanel() {
		return optPanel;
	}

	/**
	 * Sets panel with options for creating Objects.
	 * @param optP with options for creating Objects.
	 */
	public static void setOptPanel(JPanel optP) {
		optPanel = optP;
	}
	
	/**
	 * Empty constructor for Editor Workbench.
	 */
	public EditorWorkbench() {
	

	}
	
	/**
	 * Returns Instance of EditorWorkbench or creates one if there is no Instance.
	 * @return Instance of EditorWorkbench.
	 */
	public static EditorWorkbench getInstance(){
		keywords = new ArrayList<String>();
		String s = "\"packages\":";
		String s1 = "\"packageType\":";
		String s2 = "\"tables\":";
		String s3 = "\"naziv\":";
		String s4 = "\"code\":";
		String s5 = "\"translate\":";
		String s6 = "\"lang\":";
		String s7 = "\"tr\":";
		String s8 = "\"polja\":";
		String s9 = "\"domain\":";
		String s10 = "\"length\":";
		String s11 = "\"tip\":";
		String s12 = "\"isNull\":";
		String s13 = "\"name\":";
		String s14 = "\"keys\":";
		String s15 = "\"ids\":";
		String s16 = "\"tableKey\":";
		String s17 = "\"atributeKey\":";
		String s18 = "\"fKeys\":";
		String s19 = "\"constraint\":";
		String s20 = "\"pKey\":";
		String s21 = "\"connectedTable\":";
		String s22 = "\"homeIds\":";
		String s23 = "\"foreignIds\":";
		keywords.add(s);
		keywords.add(s1);
		keywords.add(s2);
		keywords.add(s3);
		keywords.add(s4);
		keywords.add(s5);
		keywords.add(s6);
		keywords.add(s7);
		keywords.add(s8);
		keywords.add(s9);
		keywords.add(s10);
		keywords.add(s11);
		keywords.add(s12);
		keywords.add(s13);
		keywords.add(s14);
		keywords.add(s15);
		keywords.add(s16);
		keywords.add(s17);
		keywords.add(s18);
		keywords.add(s19);
		keywords.add(s20);
		keywords.add(s21);
		keywords.add(s22);
		keywords.add(s23);
		
		if(InfViewModel.getInstance().getDatabaseType().equals(DatabaseType.JSON))
			reloadJsonTekst();
		if(editor==null){
			editor = new EditorWorkbench();
			if(InfViewModel.getInstance().getDatabaseType().equals(DatabaseType.RELATIONAL))
				return editor;
			editor.setLayout(new BorderLayout());
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //Odredjivanje velicine ekrana 
			double width =  screenSize.getWidth(); //da bi dialog izgledao normalno na svim ekranima
			double height =  screenSize.getHeight(); 
			editor.	setPreferredSize(new Dimension((int)(0.8*width),(int)(0.8* height)));
			dividerLoc = (int)(0.3*width);
			splitPane = new JSplitPane();
			editor.	add(splitPane, BorderLayout.CENTER);
			splitPane.setDividerLocation(dividerLoc);
			splitPane.setContinuousLayout(true);
		    splitPane.setOneTouchExpandable(true);
		    splitPane.addPropertyChangeListener(new PropertyChangeListener() {
				
				@Override
				public void propertyChange(PropertyChangeEvent evt) {
					// TODO Auto-generated method stub
					JSplitPane sourceSplitPane = (JSplitPane) evt.getSource();
	                String propertyName = evt.getPropertyName();
	                if (propertyName.equals(JSplitPane.DIVIDER_LOCATION_PROPERTY)) {
	                   	dividerLoc = sourceSplitPane.getDividerLocation();

	                }
				}
			});
			optPanel = new JPanel();
			optPanel.setLayout(new CardLayout(0, 0));
			optPanel.setPreferredSize(new Dimension((int)(width*0.4), (int)(height*0.7)));
			
			splitPane.setLeftComponent(optPanel);
		
			optionsPanel = new JPanel();
			optPanel.add(optionsPanel, "opcije");
			TitledBorder title = BorderFactory.createTitledBorder( BorderFactory.createLineBorder(Color.gray), Sistem.getInstance().getTranslate("FORMS"));
			optionsPanel.setBorder(title);
			optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));
			
			Component verticalStrut_1 = Box.createVerticalStrut(10);
			optionsPanel.add(verticalStrut_1);
			
			btnNewSubSys = new JButton(Sistem.getInstance().getTranslate("New_SubSystem"));
			btnNewSubSys.setMinimumSize(new Dimension(150, 30));
			btnNewSubSys.setMaximumSize(new Dimension(150, 30));
			btnNewSubSys.setAlignmentX(0.5f);
			optionsPanel.add(btnNewSubSys);
			btnNewSubSys.addActionListener(new NewSubSystemFormListener());
			
			Component verticalStrut_2 = Box.createVerticalStrut(10);
			optionsPanel.add(verticalStrut_2);
			
			btnNewPackage = new JButton(Sistem.getInstance().getTranslate("New_Package"));
			btnNewPackage.setMinimumSize(new Dimension(150, 30));
			btnNewPackage.setMaximumSize(new Dimension(150, 30));
			btnNewPackage.setAlignmentX(0.5f);
			optionsPanel.add(btnNewPackage);
			btnNewPackage.addActionListener(new NewPackageFormListener());
			
			Component verticalStrut_3 = Box.createVerticalStrut(10);
			optionsPanel.add(verticalStrut_3);
			
			btnNewTable = new JButton(Sistem.getInstance().getTranslate("New_Table"));
			btnNewTable.setMinimumSize(new Dimension(150, 30));
			btnNewTable.setMaximumSize(new Dimension(150, 30));
			btnNewTable.setAlignmentX(0.5f);
			optionsPanel.add(btnNewTable);
			btnNewTable.addActionListener(new NewTableListener());
			
			Component verticalStrut_4 = Box.createVerticalStrut(10);
			optionsPanel.add(verticalStrut_4);

			JButton btnCancel = new JButton(Sistem.getInstance().getTranslate("Cancel"));
			btnCancel.setMinimumSize(new Dimension(150, 30));
			btnCancel.setMaximumSize(new Dimension(150, 30));
			btnCancel.setAlignmentX(0.5f);
			btnCancel.addActionListener(new CancelNewSystemListener());
			optionsPanel.add(btnCancel);
			
			formPanel = new JPanel();
			formPanel.setBorder(title);
			optPanel.add(formPanel, "forme");
			formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
			scrollPane_1 = new JScrollPane();
			activeFormPanel = new JPanel();
			activeFormPanel.setBackground(new Color(1, 255, 0));
			scrollPane_1.setViewportView(activeFormPanel);
			formPanel.add(scrollPane_1);

			
			codePanel = new JPanel();
			splitPane.setRightComponent(codePanel);
			codePanel.setLayout(new BorderLayout(0, 0));
			TitledBorder title2 = BorderFactory.createTitledBorder( BorderFactory.createLineBorder(Color.gray), Sistem.getInstance().getTranslate("CODE"));
			codePanel.setBorder(title2);
			codePanel.setPreferredSize(new Dimension((int)(width*0.4), (int)(height*0.7)));
			GridBagConstraints gbc_codePanel = new GridBagConstraints();
			gbc_codePanel.fill = GridBagConstraints.BOTH;
			gbc_codePanel.gridx = 1;
			gbc_codePanel.gridy = 0;
			
			JPanel panel = new JPanel();
			codePanel.add(panel, BorderLayout.SOUTH);
			panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
			panel.setAlignmentX(RIGHT_ALIGNMENT);
			
			JScrollPane scrollPane = new JScrollPane();
			codePanel.add(scrollPane, BorderLayout.CENTER);
			
			codeArea = new JTextArea();
			codeArea.setLineWrap(true);
			codeArea.setText(jsonTekst);
			codeArea.setMinimumSize(new Dimension(100, 100));
			codeArea.setSize(new Dimension(500, (int)(height*0.7)));
			scrollPane.setViewportView(codeArea);
			

			codeOptPanel = new JPanel();
			editor.	add(codeOptPanel, BorderLayout.EAST);
			codeOptPanel.setPreferredSize(new Dimension((int)(width*0.2), (int)(height*0.7)));			
			TitledBorder title3 = BorderFactory.createTitledBorder( BorderFactory.createLineBorder(Color.gray), Sistem.getInstance().getTranslate("OPTIONS"));
			codeOptPanel.setBorder(title3);
			codeOptPanel.setLayout(new BoxLayout(codeOptPanel, BoxLayout.Y_AXIS));
		
			JButton btnRestart = new JButton(Sistem.getInstance().getTranslate("Restart"));
			btnRestart.setMinimumSize(new Dimension(150, 30));
			btnRestart.setMaximumSize(new Dimension(150, 30));
			btnRestart.setAlignmentX(0.5f);
			codeOptPanel.add(btnRestart);
			
			Component verticalStrut_7 = Box.createVerticalStrut(10);
			codeOptPanel.add(verticalStrut_7);
			
			JButton btnBack = new JButton(Sistem.getInstance().getTranslate("Prev_version"));
			btnBack.setMinimumSize(new Dimension(150, 30));
			btnBack.setMaximumSize(new Dimension(150, 30));
			btnBack.setAlignmentX(0.5f);
			btnBack.addActionListener(new RollbacListener());
			codeOptPanel.add(btnBack);
			
			Component verticalStrut_8 = Box.createVerticalStrut(10); 
			codeOptPanel.add(verticalStrut_8);
			
			JButton btnForward = new JButton(Sistem.getInstance().getTranslate("Curr_version"));
			btnForward.setMinimumSize(new Dimension(150, 30));
			btnForward.setMaximumSize(new Dimension(150, 30));
			btnForward.setAlignmentX(0.5f);
			btnForward.addActionListener(new ForwardListener());
			codeOptPanel.add(btnForward);

			Component verticalStrut_9 = Box.createVerticalStrut(10); 
			codeOptPanel.add(verticalStrut_9);
			
			JButton getLastValid = new JButton(Sistem.getInstance().getTranslate("Get_last_valid"));
			getLastValid.setMinimumSize(new Dimension(150, 30));
			getLastValid.setMaximumSize(new Dimension(150, 30));
			getLastValid.setAlignmentX(0.5f);
			getLastValid.addActionListener(new ValidVersionListener());
			codeOptPanel.add(getLastValid);

			Component verticalStrut_99 = Box.createVerticalStrut(10); 
			codeOptPanel.add(verticalStrut_99);
			
			JButton btnValidate = new JButton(Sistem.getInstance().getTranslate("Validate"));
			btnValidate.setMinimumSize(new Dimension(150, 30));
			btnValidate.setMaximumSize(new Dimension(150, 30));
			btnValidate.setAlignmentX(0.5f);
			codeOptPanel.add(btnValidate);
			btnValidate.addActionListener(new ValidateListener());

			Component verticalStrut_10 = Box.createVerticalStrut(10); 
			codeOptPanel.add(verticalStrut_10);
			
			JButton btnSaveCurrVersion = new JButton(Sistem.getInstance().getTranslate("Save_this_version"));
			btnSaveCurrVersion.setMinimumSize(new Dimension(150, 30));
			btnSaveCurrVersion.setMaximumSize(new Dimension(150, 30));
			btnSaveCurrVersion.setAlignmentX(0.5f);
			btnSaveCurrVersion.addActionListener(new SaveMementoListener());
			codeOptPanel.add(btnSaveCurrVersion);
			

			Component verticalStrut_18 = Box.createVerticalStrut(10); 
			codeOptPanel.add(verticalStrut_18);
			
			JButton btnSave = new JButton(Sistem.getInstance().getTranslate("Save"));
			btnSave.setMinimumSize(new Dimension(150, 30));
			btnSave.setMaximumSize(new Dimension(150, 30));
			btnSave.setAlignmentX(0.5f);
			//GRBA DODO, MOZETE BRISATI
			btnSave.addActionListener(
					new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							JSONSerialize.saveObj(Sistem.getInstance());
						}
					}

					);
			//DOVDE DODO
			codeOptPanel.add(btnSave);
			
			
			Component verticalStrut_11 = Box.createVerticalStrut(10); 
			codeOptPanel.add(verticalStrut_11);
			
			JButton btnCancel2 = new JButton(Sistem.getInstance().getTranslate("Cancel"));
			btnCancel2.setMinimumSize(new Dimension(150, 30));
			btnCancel2.setMaximumSize(new Dimension(150, 30));
			btnCancel2.setAlignmentX(0.5f);
			codeOptPanel.add(btnCancel2);
			btnCancel2.addActionListener(new CancelNewSystemListener());
			
			Component verticalStrut_12 = Box.createVerticalStrut(10); 
			codeOptPanel.add(verticalStrut_12);
			
			t=Tree.getInstance();
			 Object node = (Object) t.getLastSelectedPathComponent();
			 if(node instanceof Sistem){
					btnNewPackage.setEnabled(false);
					btnNewSubSys.setEnabled(true);
				//	btnNewSys.setEnabled(false);
					btnNewTable.setEnabled(false);
			 }
				else if(node instanceof Package){
				Package p = (Package) node;
				if(p.getPackageType().equals(PackageType.SUBSYSTEM)){
					btnNewPackage.setEnabled(true);
					btnNewSubSys.setEnabled(true);
				//	btnNewSys.setEnabled(false);
					btnNewTable.setEnabled(true);
				}else if(p.getPackageType().equals(PackageType.PACKAGE)){
					btnNewPackage.setEnabled(true);
					btnNewSubSys.setEnabled(false);
				//	btnNewSys.setEnabled(false);
					btnNewTable.setEnabled(true);
				}
			}else if(node instanceof Table){
				btnNewPackage.setEnabled(false);
				btnNewSubSys.setEnabled(false);
			//	btnNewSys.setEnabled(false);
				btnNewTable.setEnabled(false);
			}else if(node instanceof Atribut){
				btnNewPackage.setEnabled(false);
				btnNewSubSys.setEnabled(false);
			//	btnNewSys.setEnabled(false);
				btnNewTable.setEnabled(false);
			}else{
				System.out.println("nije nijedan tip selektovan");
			}
			
			
			editor.repaint();
			editor.validate();
		}
		
		return editor;
	}
	
	/**
	 * Returns panel for forms for creating Objects.
	 * @return panel for forms for creating Objects.
	 */
	public static JPanel getFormPanel() {
		return formPanel;
	}

	
	/**
	 * Sets panel for forms for creating Objects.
	 * @param formPanel for forms for creating Objects.
	 */
	public static void setFormPanel(JPanel formPanel) {
		EditorWorkbench.formPanel = formPanel;
	}

	/**
	 * Destroys an Instance of Editor Workbench.
	 */
	public static void destroy(){
		editor = null;
	}
	
	/**
	 *Checks if Instance of Editor Workbench is Null.
	 *@return if Instance is Null
	 */
	public static boolean isNull(){
		if(editor == null)
			return true;
		return false;
	}

	/**
	 * Returns button for creating New Package.
	 * @return button for creating New Package.
	 */
	public static JButton getBtnNewPackage() {
		return btnNewPackage;
	}

	/**
	 * Sets button for creating New Package.
	 * @param btnNewPackage button for creating New Package.
	 */
	public static void setBtnNewPackage(JButton btnNewPackage) {
		EditorWorkbench.btnNewPackage = btnNewPackage;
	}

	/**
	 * Returns button for creating New Table.
	 * @return button for creating New Table.
	 */
	public static JButton getBtnNewTable() {
		return btnNewTable;
	}

	/**
	 * Sets button for creating New Table.
	 * @param btnNewTable button for creating New Table.
	 */
	public static void setBtnNewTable(JButton btnNewTable) {
		EditorWorkbench.btnNewTable = btnNewTable;
	}

	/**
	 * Returns button for creating New System.
	 * @return button for creating New System.
	 */
	public static JButton getBtnNewSubSys() {
		return btnNewSubSys;
	}

	/**
	 * Sets button for creating New System.
	 * @param btnNewSubSys button for creating New System.
	 */
	public static void setBtnNewSubSys(JButton btnNewSubSys) {
		EditorWorkbench.btnNewSubSys = btnNewSubSys;
	}

	/**
	 * Returns ScrollPane for Code Area in Editor Workbench.
	 * @return ScrollPane for Code Area.
	 */
	public static JScrollPane getScrollPane_1() {
		return scrollPane_1;
	}

	/**
	 * Sets ScrollPane for Code Area in Editor Workbench.
	 * @param scrollPane_1 for Code Area.
	 */
	public static void setScrollPane_1(JScrollPane scrollPane_1) {
		EditorWorkbench.scrollPane_1 = scrollPane_1;
	}

	/**
	 * Returns Code Area in Editor Workbench.
	 * @return Code Area for JSON Database.
	 */
	public static JTextArea getCodeArea() {
		return codeArea;
	}

	/**
	 * Sets Code Area in Editor Workbench.
	 * @param codeArea for JSON Database.
	 */
	public static void setCodeArea(JTextArea codeArea) {
		EditorWorkbench.codeArea = codeArea;
	}

	/**
	 * Returns Instance of Editor Workbench.
	 * @return Instance of Editor Workbench.
	 */
	public static EditorWorkbench getEditor() {
		return editor;
	}

	
	/**
	 * Sets Instance of Editor Workbench.
	 * @param editor is an Instance of Editor Workbench.
	 */
	public static void setEditor(EditorWorkbench editor) {
		EditorWorkbench.editor = editor;
	}

	/**
	 * Returns Instance of Tree.
	 * @return Instance of Tree.
	 */
	public static Tree getT() {
		return t;
	}


	/**
	 * Sets Instance of Tree.
	 * @param t is Instance of Tree.
	 */
	public static void setT(Tree t) {
		EditorWorkbench.t = t;
	}

	/**
	 * Returns panel with options for Code Area.
	 * @return panel with options for Code Area.
	 */
	public static JPanel getCodeOptPanel() {
		return codeOptPanel;
	}

	/**
	 * Sets panel with options for Code Area.
	 * @param codeOptPanel panel with options for Code Area.
	 */
	public static void setCodeOptPanel(JPanel codeOptPanel) {
		EditorWorkbench.codeOptPanel = codeOptPanel;
	}

	/**
	 * Returns code panel of Code Area.
	 * @return code panel of Code Area.
	 */
	public static JPanel getCodePanel() {
		return codePanel;
	}

	/**
	 * Sets code panel of Code Area.
	 * @param codePanel of Code Area.
	 */
	public static void setCodePanel(JPanel codePanel) {
		EditorWorkbench.codePanel = codePanel;
	}

	/**
	 * Returns split pane between Forms Area and Code Area.
	 * @return split pane between Forms Area and Code Area.
	 */
	public static JSplitPane getSplitPane() {
		return splitPane;
	}

	
	/**
	 * Sets split pane between Forms Area and Code Area.
	 * @param splitPane between Forms Area and Code Area.
	 */
	public static void setSplitPane(JSplitPane splitPane) {
		EditorWorkbench.splitPane = splitPane;
	}

	
	/**
	 * Returns options panel from workbench.
	 * @return options panel from workbench.
	 */
	public static JPanel getOptionsPanel() {
		return optionsPanel;
	}

	
	/**
	 * Sets options panel from workbench.
	 * @param optionsPanel from workbench.
	 */
	public static void setOptionsPanel(JPanel optionsPanel) {
		EditorWorkbench.optionsPanel = optionsPanel;
	}

	/**
	 * Returns default builder for temporary Packages.
	 * @return default builder for temporary Packages.
	 */
	public DefaultPackageBuilder getDpb() {
		return dpb;
	}

	/**
	 * Sets default builder for temporary Packages.
	 * @param dpb is default builder for temporary Packages.
	 */
	public void setDpb(DefaultPackageBuilder dpb) {
		this.dpb = dpb;
	}

	/**
	 * Returns active form panel from workbench.
	 * @return active form panel from workbench.
	 */
	public static JPanel getActiveFormPanel() {
		return activeFormPanel;
	}


}
