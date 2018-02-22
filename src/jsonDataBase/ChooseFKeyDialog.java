package jsonDataBase;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import db.crud.RelationalRead;
import editorSeme.model.pojo.Atribut;
import editorSeme.model.pojo.Sistem;
import editorSeme.model.pojo.Table;
import jsonDataBase.additional.JSONDataSerialize;
import jsonDataBase.model.Data;
import jsonDataBase.model.DataTabela;
import start.DatabaseType;
import start.InfViewModel;
/**
 * Class that makes a dialog for choosing value of foreign key
 *
 */
public class ChooseFKeyDialog extends JDialog{
	private Atribut a;
	private static JTextField jtf;
	private static ChooseFKeyDialog chooseFKeyDialog =null;
	public static JTable table1;
	/**
	 * Empty constructor, initialize JDialog
	 */
	private ChooseFKeyDialog() {
	}
	/**
	 * Method that makes a dialog which contains a table that contains values for foreig key
	 * @param a - atribut that gets a value
	 * @param jtf0 - textField that shows the value of choosen foreign key
	 * @return - singleton instance of class ChooseFKeyDialog
	 */
	public static ChooseFKeyDialog getInstance(Atribut a, JTextField jtf0) {
		if(chooseFKeyDialog==null){
			jtf = jtf0;
			chooseFKeyDialog= new ChooseFKeyDialog();
			chooseFKeyDialog.addWindowListener(new WindowAdapter() {
				@Override
			    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
			        destroy();
			    }
			});
			chooseFKeyDialog.setResizable(false);
			chooseFKeyDialog.setTitle(Sistem.getInstance().getTranslate("Choose_FKEY")+a.toString());
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //Odredjivanje velicine ekrana 
			double width =  screenSize.getWidth()*0.8; //da bi dialog izgledao normalno na svim ekranima
			double height =  screenSize.getHeight()*0.8; 
			chooseFKeyDialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			chooseFKeyDialog.setBounds(100, 100, (int)width, (int) height);
			JPanel contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			contentPane.setLayout(new BorderLayout(0, 0));
			chooseFKeyDialog.setContentPane(contentPane);
			
			JPanel panel = new JPanel();
			contentPane.add(panel, BorderLayout.CENTER);
			
			Table tabl = a.getTableForFKey();
			if(tabl==null){
				tabl = new Table();
			}
			DefaultTableModel model = new DefaultTableModel(); 
			table1 = new JTable(model); 
			
			GridBagLayout gridBagLayout = new GridBagLayout();
			gridBagLayout.columnWidths = new int[]{0,(int) (width*0.52) , 0};
			gridBagLayout.rowHeights = new int[]{0, 0, 0, (int) (height*0.72), 0,0,0};
			gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
			gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			panel.setLayout(gridBagLayout);
			
			JTextField seatchField = new JTextField();
			seatchField.setToolTipText("search field");
			seatchField.addKeyListener(new FKeySearchListener(seatchField, table1, tabl));
			GridBagConstraints gbc_codeTextField = new GridBagConstraints();
			gbc_codeTextField.insets = new Insets(0, 0, 5, 5);
			gbc_codeTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_codeTextField.gridx = 1;
			gbc_codeTextField.gridy = 1;
			seatchField.setColumns(a.getDomain().getLength());
			panel.add(seatchField, gbc_codeTextField);

			
			for (int i = 0; i < tabl.getPolja().size(); i++) {
				model.addColumn(tabl.getPolja().get(i).toString()); 
			}
			if(InfViewModel.getInstance().getDatabaseType().equals(DatabaseType.JSON)){
				DataTabela dt = new DataTabela();
				dt = JSONDataSerialize.getDataTable(tabl);
				for(Data d : dt.getTorke()){
					Vector row = new Vector();
					for(int count=0; count<model.getColumnCount(); count++)
						row.add(d.getTorka().get(tabl.getPolja().get(count).getName().getCode()).toString());
				    model.addRow(row);
				}
			}
			else{
				RelationalRead rr = new RelationalRead();
				ResultSet rs = (ResultSet) rr.readTable(tabl.getNaziv().getCode());
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
					System.out.println("bacio bi exception (ChooseFKeyDialog->124)");
				}
			}
		//	table1.addMouseListener(new ChooseFKeyLabel(seatchField));
			JScrollPane scrollPane = new JScrollPane(table1);
			table1.setFillsViewportHeight(true);
			GridBagConstraints gbc_table = new GridBagConstraints();
			gbc_table.insets = new Insets(0, 0, 5, 5);
			gbc_table.fill = GridBagConstraints.HORIZONTAL;
			gbc_table.gridx = 1;
			gbc_table.gridy = 3;
			panel.add(scrollPane, gbc_table);
			
			JButton okBtn = new JButton("OK");
			GridBagConstraints gbc_okBtn = new GridBagConstraints();
			gbc_okBtn.insets = new Insets(0, 0, 5, 5);
			gbc_okBtn.fill = GridBagConstraints.HORIZONTAL;
			gbc_okBtn.gridx = 2;
			gbc_okBtn.gridy = 4;
			okBtn.addActionListener(new AddFKeyToListListener(table1,jtf, a, tabl));
			panel.add(okBtn, gbc_okBtn);
			
		}
		return chooseFKeyDialog;
	}
	/**
	 * Method that destroy instance of this class
	 */
	protected static void destroy() {
		chooseFKeyDialog.dispose();
		jtf=null;
		table1=null;
		chooseFKeyDialog=null;
		
	}
	
	

}
