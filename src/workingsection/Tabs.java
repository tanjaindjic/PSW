package workingsection;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import db.crud.RelationalRead;
import editorSeme.model.pojo.Key;
import editorSeme.model.pojo.Sistem;
import editorSeme.model.pojo.Table;
import jsonDataBase.additional.JSONDataSerialize;
import jsonDataBase.additional.NoPathFrame;
import jsonDataBase.model.Data;
import jsonDataBase.model.DataTabela;
import listeners.CloseTabListener;
import listeners.SelectedTabListener;
import start.DatabaseType;
import start.InfViewModel;
import workingsection.tree.Tree;

/**
 * Creates Tabs for Tables in System.
 *
 */
public class Tabs extends JPanel{
	private static Tabs tab=null;
	private static JPanel upPanel;
	private static JPanel downPanel;
	private static CustomTabbedPane glavnaTabelaPane;
	private static JTabbedPane childrenPane;
	private static ArrayList<Table> tablesList;
	private static ArrayList<Table> childList;
	private static CustomTabbedPaneUI tabUI;
	private static Tree t = Tree.getInstance();
	private static HashMap<String, JTable> tabeleMap; 
	
	/**
	 * Creates a private Instance of Tabs.
	 */
	private Tabs(){
		glavnaTabelaPane = new CustomTabbedPane();
		childrenPane = new JTabbedPane();
		tabUI = new CustomTabbedPaneUI();
		glavnaTabelaPane.setUI(tabUI);
		//childrenPane.setUI(tabUI);
	}
	
	/**
	 * Returns an Instance of Tabs or creates one if it's Null.
	 * @return Instance of Tabs
	 */
	public static Tabs getInstance(){
		if(tab==null){
			tabeleMap = new HashMap<>();
			tab=new Tabs();
			tabUI = new CustomTabbedPaneUI();
			tab.setLayout(new BorderLayout());
			tablesList = new ArrayList<Table>();
			
		/*	if(t!= null && t.getSelected()!=null){
				Object current = t.getSelected();
				while(!(current instanceof Sistem)){
					current = NewTableFrame.getParent(current);
				}
				for(Table a : ((Sistem) current).getAllTables()){
					tablesList.add(a); 
				}
				
			}
			
			*/
			for(Table a : Sistem.getInstance().getAllTables()){
				tablesList.add(a); 
			}
			
			int width =  WorkArea.getInstance().getWidth();
			int height =  WorkArea.getInstance().getHeight(); 
			tab.setPreferredSize(new Dimension(width, height));
			
			upPanel = new JPanel();
			upPanel.setPreferredSize(new Dimension(width, height/2));
			upPanel.setLayout(new CardLayout(0, 0));
			glavnaTabelaPane = new CustomTabbedPane();
			//tabele.setUI(tabUI);
			
			
			
			for(int i=0; i<tablesList.size(); i++){		
				addTab(tablesList.get(i));
				/*
				JPanel pan = new JPanel();
				String naziv;
				//IZMENITI OVO U ZAVISNOSTI OD IZABRANOG JEZIKA
				naziv = tablesList.get(i).toString();			

				pan.setName(naziv);
				glavnaTabelaPane.addTab(naziv, pan);
				
				int index = glavnaTabelaPane.indexOfTab(naziv);
				JPanel pnlTab = new JPanel(new GridBagLayout());
				pnlTab.setOpaque(false);
				JLabel lblTitle = new JLabel(naziv);
				JButton btnClose = new JButton("x");

				GridBagConstraints gbc = new GridBagConstraints();
				gbc.gridx = 0;
				gbc.gridy = 0;
				gbc.weightx = 1;

				pnlTab.add(lblTitle, gbc);

				gbc.gridx++;
				gbc.weightx = 0;
				pnlTab.add(btnClose, gbc);

				glavnaTabelaPane.setTabComponentAt(index, pnlTab);

				btnClose.addActionListener(new CloseTabListener(naziv));
				*/
				
			}
			
			/*upPanel.add(glavnaTabelaPane);
			
			downPanel = new JPanel();
			downPanel.setPreferredSize(new Dimension(width, height/2));
			downPanel.setLayout(new CardLayout(0, 0));
			childrenPane = new CustomTabbedPane();
			childrenPane.setUI(tabUI);
			for(int i=0; i<tablesList.size(); i++){
				ArrayList<FKey> fkeys = (ArrayList<FKey>) tablesList.get(i).getfKeys();
				for(int j=0; j<fkeys.size(); j++){
					JPanel pan2 = new JPanel();
					pan2.setName(fkeys.get(j).getConnectedTable());
					childrenPane.addTab(fkeys.get(j).getConnectedTable(), pan2);
				}
			}
			downPanel.add(childrenPane);
			
			tab.add(upPanel, BorderLayout.NORTH);
			tab.add(downPanel, BorderLayout.SOUTH);*/
		}
		return tab;
	}
	
	/**
	 * Adds a Tab containing a Table from System.
	 * @param tabl is Table for adding in Tab.
	 */
	public static void addTab(Table tabl){
		if(InfViewModel.getInstance().getDatabaseType().equals(DatabaseType.RELATIONAL)){
			addRelTab(tabl);
			return;
		}
		String naziv = tabl.toString();//getNaziv().getCode() + " (" +tabl.getParentPackage().getNaziv().getCode() + ")";
		int index = -1;
		
        	index = Tabs.getTabele().indexOfTab(naziv);
        	if(index != -1){
        		Tabs.getTabele().setSelectedIndex(index);
        		createChildrenTabs(tabl);
        		//Tabs.getChildren().setSelectedIndex(-1);
            	return;
        }
		if(tablesList == null)
			tablesList = new ArrayList<>();
		
		if(upPanel==null)
			upPanel = new JPanel();
		if(glavnaTabelaPane==null)
			glavnaTabelaPane = new CustomTabbedPane();
		glavnaTabelaPane.addChangeListener(new SelectedTabListener());
		if(tab==null)
			tab = new Tabs();
		
		JPanel pan = new JPanel();
		
		pan.setName(naziv);
		//OVDE PRAVIM JTABLE
	/*	String[] columns = new String[tabl.getPolja().size()];
		Object[][] data = {};
		for(int i = 0; i < tabl.getPolja().size(); i++){
			//OVO IZMENITI GETCODE!
			columns[i]=tabl.getPolja().get(i).toString();
		}
		
		JTable table1 = new JTable(data, columns);
		
		TableColumn column = null;
		for (int i = 0; i < tabl.getPolja().size(); i++) {
		    column = table1.getColumnModel().getColumn(i);
		    
		        column.setPreferredWidth(100);
		        column.setMinWidth(100);
		        column.setMaxWidth(300);
		    
		}
		*/
	//Nina je menjala i zakomentarisala	
		DefaultTableModel model = new DefaultTableModel(); 
		JTable table1 = new JTable(model); 
	//	table1.setEnabled(false);
		
		for (int i = 0; i < tabl.getPolja().size(); i++) {
			model.addColumn(tabl.getPolja().get(i).toString()); 
		}

		DataTabela dt = new DataTabela();
		dt = JSONDataSerialize.getDataTable(tabl);
		if(dt==null){
			NoPathFrame.getInstance(tabl).setVisible(true);
			return;
		}
		for(Data d : dt.getTorke()){
			Vector row = new Vector();
			for(int count=0; count<model.getColumnCount(); count++){
				row.add(d.getTorka().get(tabl.getPolja().get(count).getName().getCode()).toString());
			}
		    model.addRow(row);
		}
		
		JScrollPane scrollPane = new JScrollPane(table1);
		table1.setFillsViewportHeight(true);
		tabeleMap.put(tabl.getNaziv().getCode(), table1);
		pan.setLayout(new BoxLayout(pan, BoxLayout.Y_AXIS));
		pan.add(scrollPane);
		
		glavnaTabelaPane.addTab(naziv, pan);
		index = glavnaTabelaPane.indexOfTab(naziv);
		JPanel pnlTab = new JPanel(new GridBagLayout());

		pnlTab.setOpaque(false);
		JLabel lblTitle = new JLabel(naziv);
		lblTitle.setFont( new Font( "Dialog", Font.CENTER_BASELINE,  20) );
		lblTitle.setMinimumSize(new Dimension(200, 40));
		JButton btnClose = new JButton("x");

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;

		pnlTab.add(lblTitle, gbc);

		gbc.gridx++;
		gbc.weightx = 0;
		
		JLabel label = new JLabel(" ");
		pnlTab.add(label);
		gbc.gridx++;
		gbc.weightx = 0;
        //add more space between the label and the button
        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
		pnlTab.add(btnClose, gbc);

		glavnaTabelaPane.setTabComponentAt(index, pnlTab);
		glavnaTabelaPane.setSelectedIndex(index);
		
		btnClose.addActionListener(new CloseTabListener(naziv));
		
		upPanel.add(glavnaTabelaPane);
		tab.add(upPanel, BorderLayout.NORTH);
		
		////
		
		if(downPanel==null)
			downPanel = new JPanel();
		if(childrenPane==null)
			childrenPane = new CustomTabbedPane();
		/*
		if(tab==null)
			tab = new Tabs();
		*/
			createChildrenTabs(tabl);
			//Tabs.getChildren().setSelectedIndex(-1);
			downPanel.add(childrenPane);
			tab.add(downPanel, BorderLayout.SOUTH);
		
		//
		Tabs.getTabele().setSelectedIndex(index);
		tab.repaint();
		tab.revalidate();
		WorkArea wa = MainWindow.getWorkArea();
		wa.repaint();
		wa.validate();
		MainWindow.getInstance().validate();
		MainWindow.getInstance().repaint();
		
	}


	/**
	 * Adds a Tab containing a Table from System.
	 * @param tabl is Table for adding in Tab.
	 */
	private static void addRelTab(Table tabl) {
		int index = -1;	
		if(tabl==null){
			return;
		}
		//System.out.println("odradio tabelu");
		String naziv = tabl.toString();
    	index = Tabs.getTabele().indexOfTab(naziv);
    	if(index != -1){
    		Tabs.getTabele().setSelectedIndex(index);
    		createChildrenTabs(tabl);
    		//Tabs.getChildren().setSelectedIndex(-1);
        	return;
    	}
		RelationalRead rr = new RelationalRead();
		Object result = rr.readTable(tabl.getNaziv().getCode());
		ResultSet rs = null;
		if(result instanceof ResultSet)
			rs = (ResultSet) result;
		JPanel pan = new JPanel();
		
		pan.setName(naziv);
		DefaultTableModel model = new DefaultTableModel(); 
		JTable table1 = new JTable(model); 
	//	table1.setEnabled(false);
		
		for (int i = 0; i < tabl.getPolja().size(); i++) {
			model.addColumn(tabl.getPolja().get(i).toString()); 
		}
		if(rs!=null){
			try {
				while(rs.next()){
					Vector row = new Vector();
					for (int columnIndex = 1; columnIndex <= tabl.getPolja().size(); columnIndex++) {
				        try {
							row.add(rs.getObject(columnIndex));
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				    }
				    model.addRow(row);
				}
			} catch (SQLException e) {
				System.out.println("nema podataka u tabeli "+tabl.toString());
			}
		}
		JScrollPane scrollPane = new JScrollPane(table1);
		table1.setFillsViewportHeight(true);
		tabeleMap.put(tabl.getNaziv().getCode(), table1);
		pan.setLayout(new BoxLayout(pan, BoxLayout.Y_AXIS));
		pan.add(scrollPane);
		
		if(glavnaTabelaPane==null)
			glavnaTabelaPane = new CustomTabbedPane();
		glavnaTabelaPane.addChangeListener(new SelectedTabListener());
		
		glavnaTabelaPane.addTab(naziv, pan);
		index = glavnaTabelaPane.indexOfTab(naziv);
		JPanel pnlTab = new JPanel(new GridBagLayout());

		pnlTab.setOpaque(false);
		JLabel lblTitle = new JLabel(naziv);
		lblTitle.setFont( new Font( "Dialog", Font.CENTER_BASELINE,  20) );
		lblTitle.setMinimumSize(new Dimension(200, 40));
		JButton btnClose = new JButton("x");

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;

		pnlTab.add(lblTitle, gbc);

		gbc.gridx++;
		gbc.weightx = 0;
		
		JLabel label = new JLabel(" ");
		pnlTab.add(label);
		gbc.gridx++;
		gbc.weightx = 0;
        //add more space between the label and the button
        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
		pnlTab.add(btnClose, gbc);

		glavnaTabelaPane.setTabComponentAt(index, pnlTab);
		glavnaTabelaPane.setSelectedIndex(index);
		
		btnClose.addActionListener(new CloseTabListener(naziv));
		
		upPanel.add(glavnaTabelaPane);
		tab.add(upPanel, BorderLayout.NORTH);
		
		////
		
		if(downPanel==null)
			downPanel = new JPanel();
		if(childrenPane==null)
			childrenPane = new CustomTabbedPane();
		/*
		if(tab==null)
			tab = new Tabs();
		*/
			createChildrenTabs(tabl);
			//Tabs.getChildren().setSelectedIndex(-1);
			downPanel.add(childrenPane);
			tab.add(downPanel, BorderLayout.SOUTH);
		
		//
		Tabs.getTabele().setSelectedIndex(index);
		tab.repaint();
		tab.revalidate();
		WorkArea wa = MainWindow.getWorkArea();
		wa.repaint();
		wa.validate();
		MainWindow.getInstance().validate();
		MainWindow.getInstance().repaint();		
	}

	/**
	 * Returns all open Tables as Tabs.
	 * @return pane with all parent Tables opened as Tabs.
	 */
	public static CustomTabbedPane getTabele() {
		return glavnaTabelaPane;
	}

	/**
	 * Sets all open Tables as Tabs.
	 * @param tabele is pane with all parent Tables opened as Tabs.
	 */
	public static void setTabele(CustomTabbedPane tabele) {
		Tabs.glavnaTabelaPane = tabele;
	}

	/**
	 * Returns all open child Tables as Tabs.
	 * @return pane with all child Tables opened as Tabs.
	 */
	public static JTabbedPane getChildren() {
		return childrenPane;
	}

	
	/**
	 * Sets all open child Tables as Tabs.
	 * @param children is pane with all child Tables opened as Tabs.
	 */
	public static void setChildren(JTabbedPane children) {
		Tabs.childrenPane = children;
	}	
	
	/**
	 * Creates Tabs with child Tables of selected parent Table.
	 * @param tabl is selected parent Table.
	 */
	public static void createChildrenTabs(Table tabl){
		
		for(Table t : Sistem.getInstance().getAllTables()){
			  
			if(childList == null)
				childList = new ArrayList<>();
			childList.clear();
			if(!t.getNaziv().getCode().equals(tabl.getNaziv().getCode()))
				for(int j=0; j<t.getfKeys().size(); j++){
					if( (t.getfKeys().get(j).getConnectedTable().equals( tabl.getNaziv().getCode() ) ) && fkIsKeyInTable(t.getfKeys().get(j).getHomeIds(), t.getKeys())){
						
						
						
						int idx = -1;
						idx = Tabs.getChildren().indexOfTab(t.toString());
				        if (idx >= 0) {

				        	Tabs.getChildren().setSelectedIndex(idx);
				        	return;
				        }
				        
				      
						childList.add(t);
						
						JPanel pan2 = new JPanel();
						pan2.setName(t.toString());
						/*	
						String[] columns2 = new String[t.getPolja().size()];
						for(int i = 0; i < t.getPolja().size(); i++){
							columns2[i]=t.getPolja().get(i).toString();
						}
						
						Object[][] data2 = {};
						
						JTable table2 = new JTable(data2, columns2);
						table2.setMinimumSize(new Dimension(500, 400));
						table2.setPreferredSize(new Dimension(500, 400));
						TableColumn column2 = null;
						for (int i = 0; i < t.getPolja().size(); i++) {
						    column2 = table2.getColumnModel().getColumn(i);
						    
						    column2.setPreferredWidth(100);
						    column2.setMinWidth(100);
						    column2.setMaxWidth(300);
						    
						}
						*/
						
						DefaultTableModel model = new DefaultTableModel(); 
						JTable table2 = new JTable(model); 
						
						for (int i = 0; i < t.getPolja().size(); i++) {
							model.addColumn(t.getPolja().get(i).toString()); 
						}
						if(InfViewModel.getInstance().getDatabaseType().equals(DatabaseType.JSON))	{
							DataTabela dt = new DataTabela();
							dt = JSONDataSerialize.getDataTable(t);
							for(Data d : dt.getTorke()){
								Vector row = new Vector();
								for(int count=0; count<model.getColumnCount(); count++){
									row.add(d.getTorka().get(t.getPolja().get(count).getName().getCode()).toString());
								}
							    model.addRow(row);
							}
						}else{
							RelationalRead rr = new RelationalRead();
							Object result = rr.readTable(t.getNaziv().getCode());
							ResultSet rs = null;
							if(result instanceof ResultSet)
								rs = (ResultSet) result;
							if(rs!=null){
								try {
									while(rs.next()){
										Vector row = new Vector();
										for (int columnIndex = 1; columnIndex <= tabl.getPolja().size(); columnIndex++) {
									        try {
												row.add(rs.getObject(columnIndex));
											} catch (SQLException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
									    }
									    model.addRow(row);
									}
								} catch (SQLException e) {
									System.out.println("nema podataka u tabeli "+tabl.toString());
								}
							}
						}
						
						JScrollPane scrollPane2 = new JScrollPane(table2);
						table2.setFillsViewportHeight(true);
						pan2.setLayout(new BoxLayout(pan2, BoxLayout.Y_AXIS));
						pan2.add(scrollPane2);
						
						Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //Odredjivanje velicine ekrana 
						double width =  screenSize.getWidth(); //da bi dialog izgledao normalno na svim ekranima
						double height =  screenSize.getHeight(); 
						
						pan2.setPreferredSize(new Dimension((int) (width - 100 - Tree.getInstance().getWidth()), ((int)height/3)));
						childrenPane.addTab(t.toString(), pan2);
					
						int index2 = childrenPane.indexOfTab(t.toString());
						JPanel pnlTab2 = new JPanel(new GridBagLayout());

						pnlTab2.setOpaque(false);
						pnlTab2.setPreferredSize(new Dimension(200, 25));
						pnlTab2.setMinimumSize(new Dimension(300, 25));
						pnlTab2.setBackground(Color.LIGHT_GRAY);
						JLabel lblTitle2 = new JLabel(t.toString());
						lblTitle2.setFont( new Font( "Dialog", Font.CENTER_BASELINE,  15) );
						lblTitle2.setBackground(Color.LIGHT_GRAY);
						

						GridBagConstraints gbc2 = new GridBagConstraints();
						gbc2.gridx = 0;
						gbc2.gridy = 0;
						gbc2.weightx = 1;

						pnlTab2.add(lblTitle2, gbc2);

						gbc2.gridx++;
						gbc2.weightx = 0;
						
						JLabel label2 = new JLabel("        ");
						pnlTab2.add(label2);
						gbc2.gridx++;
						gbc2.weightx = 0;
				        //add more space between the label and the button
				        label2.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
						//pnlTab2.add(btnClose2, gbc2);

						childrenPane.setTabComponentAt(index2, pnlTab2);
						childrenPane.setSelectedIndex(index2);
				//		childrenPane.getComponentAt(index2).setBackground(Color.WHITE);

						
					}
		
				
			}
		}
	
	}
	
	/**
	 * Creates String from other data types.
	 * @return String result of conversion.
	 */
	private static String createString(Object obj) {
		if(obj instanceof String)
			return (String) obj;
		if(obj instanceof Integer)
			return ((Integer)obj).toString();
		if(obj instanceof Character)
			return ((Character)obj).toString();
		if(obj instanceof Boolean)
			return ((Boolean)obj).toString();
		
		
		return "(?)";
	}

	/**
	 * Checks if Primary Key of a Table is a Foreign Key in other Table.
	 * @param homeids contains Primary Keys of current Table.
	 * @param keys contains Primary Keys of other Table.
	 * @return if current Table has Foreign Keys from other Table.
	 */
	public static boolean fkIsKeyInTable(ArrayList<String> homeids, ArrayList<Key> keys) {
		for(Key k:keys) {
			if(subStringList(homeids, k.getAtributs())) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Checks if one Array of String elements are contained in other Array of Strings.
	 * @param smaler Array of Strings being checked.
	 * @param bigger Array of Strings for comparison.
	 * @return if one Array of String elements are contained in other Array of Strings.
	 */
	public static boolean subStringList(ArrayList<String> smaler, ArrayList<String> bigger) {
		for(String s:smaler) {
			if(!subString(s, bigger)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Checks if a String is contained in  Array of Strings.
	 * @param small String being checked.
	 * @param big Array of Strings for comparison.
	 * @return if String is contained in Array of Strings.
	 */
	public static boolean subString(String small, ArrayList<String> big) {
		for(String s:big) {
			if(s.equals(small)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Refreshes a Tab containing a Table.
	 * @param t2 is Table contained in Tab.
	 */
	public static void refreshTable(Table t2) {		
		if(InfViewModel.getInstance().getDatabaseType().equals(DatabaseType.RELATIONAL)){
			refreshRelationalTable(t2);
			return;
		}
        int index = Tabs.getTabele().indexOfTab(t2.toString());
        Tabs.getTabele().setSelectedIndex(index);
        JTable jt = tabeleMap.get(t2.getNaziv().getCode());
        DefaultTableModel model = (DefaultTableModel) jt.getModel();
        int size = model.getRowCount();
        for(int i=0; i<size; i++){
        	model.removeRow(0);
        }
        DataTabela dt = new DataTabela();
		dt = JSONDataSerialize.getDataTable(t2);
		for(Data d : dt.getTorke()){
			Vector row = new Vector();
			for(int count=0; count<model.getColumnCount(); count++)
				row.add(d.getTorka().get(t2.getPolja().get(count).getName().getCode()).toString());
		    model.addRow(row);
		}
	}

	/**
	 * Refreshes a Tab containing a Table.
	 * @param t2 is Table contained in Tab.
	 */
	private static void refreshRelationalTable(Table t2) {
		int index = Tabs.getTabele().indexOfTab(t2.toString());
        Tabs.getTabele().setSelectedIndex(index);
        JTable jt = tabeleMap.get(t2.getNaziv().getCode());
        DefaultTableModel model = (DefaultTableModel) jt.getModel();
        int size = model.getRowCount();
        for(int i=0; i<size; i++){
        	model.removeRow(0);
        }

        RelationalRead rr = new RelationalRead();
		Object result = rr.readTable(t2.getNaziv().getCode());
		ResultSet rs = null;
		if(result instanceof ResultSet)
			rs = (ResultSet) result;
		if(rs!=null){
			try {
				while(rs.next()){
					Vector row = new Vector();
					for (int columnIndex = 1; columnIndex <= t2.getPolja().size(); columnIndex++) {
				        try {
							row.add(rs.getObject(columnIndex));
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				    }
				    model.addRow(row);
				}
			} catch (SQLException e) {
				System.out.println("nema podataka u tabeli "+t2.toString());
			}
		}
		
		
	}

	/**
	 * Refreshes a Tab containing a Table.
	 */
	public void refreshTab() {
		int index = Tabs.getInstance().getTabele().getSelectedIndex();
		JPanel jpanel = (JPanel) Tabs.getInstance().getTabele().getComponentAt(index);
		
		Table t = Sistem.getInstance().getTableByTabName(jpanel.getName());
		
		refreshTable(t);
		
	}

	public void destroy() {
		tab.removeAll();
		tabeleMap = null;
		tab=null;
		tabUI = null;
		tablesList = null;
		glavnaTabelaPane = null;
		childrenPane = null;
		tabeleMap = null;
		upPanel = null;
		downPanel = null;
	}



	

	

}
