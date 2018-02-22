package jsonDataBase;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.util.HashMap;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.border.EmptyBorder;

import db.model.Torka;
import editorSeme.model.enums.Tip;
import editorSeme.model.pojo.Atribut;
import editorSeme.model.pojo.Sistem;
import editorSeme.model.pojo.Table;
import jsonDataBase.additional.JSONDataSerialize;
import jsonDataBase.factory.ComponentFactory;
import jsonDataBase.factory.ComponentPart;
import jsonDataBase.model.Data;
import start.DatabaseType;
import start.InfViewModel;
import workingsection.Tabs;
/**
 * Class that provides frame for editing tuple
 *
 */
public class EditTorkaFrame extends TorkaFrame {
	private static final long serialVersionUID = 1L;
	private static EditTorkaFrame editTorkaFrame =null;
	public static HashMap<String, Object> polja;
	public static Data data;
	private JScrollPane scrollPane;
	private static JPanel contentPane ;
	private static int temp;
	/**
	 * Empty constructor
	 */
	private EditTorkaFrame(){
		
	}
	/**
	 * Frame that provides to user to edit Data from existing table
	 * @param t - Table to which new data are going to be added
	 * @return JFrame that provides editing
	 */
	public static EditTorkaFrame getInstance(Table t) {
		if(editTorkaFrame==null){
			if(InfViewModel.getInstance().getDatabaseType().equals(DatabaseType.RELATIONAL)){
				return editRelationTorka(t);
			}
			int index = Tabs.getInstance().getTabele().getSelectedIndex();
			//kako dobiti tu konkretnu tabelu?
			JPanel jpanel = (JPanel) Tabs.getInstance().getTabele().getComponentAt(index);
			JScrollPane jscrollpane = (JScrollPane) jpanel.getComponent(0);
			JViewport jviewport = (JViewport) jscrollpane.getComponent(0);
			JTable jtable = (JTable) jviewport.getComponent(0);
			temp = jtable.getSelectedRow();
			if(temp==-1){
				JOptionPane.showMessageDialog(null, Sistem.getInstance().getTranslate("Choose_Row"));
				return null;
			}
				
			data = JSONDataSerialize.getDataTable(t).getTorke().get(temp);
			editTorkaFrame = new EditTorkaFrame();
			editTorkaFrame.addWindowListener(new WindowAdapter() {
				@Override
			    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
			        destroy();
			    }
			});
			polja = new HashMap<String, Object>();
			editTorkaFrame.setResizable(false);
			editTorkaFrame.setTitle(Sistem.getInstance().getTranslate("Edit_Data_from")+t.toString());
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //Odredjivanje velicine ekrana 
			double width =  screenSize.getWidth()*0.4; //da bi dialog izgledao normalno na svim ekranima
			double height =  screenSize.getHeight()*0.6; 
			editTorkaFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			editTorkaFrame.setBounds(100, 100, (int)width, (int) height);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			contentPane.setLayout(new BorderLayout(0, 0));
			editTorkaFrame.setContentPane(contentPane);
			
			JPanel panel = new JPanel();
			contentPane.add(panel, BorderLayout.CENTER);
			
			GridBagLayout gridBagLayout = new GridBagLayout();
			gridBagLayout.columnWidths = new int[]{30, 150, 200, 70};
			gridBagLayout.rowHeights = new int[]{30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30};
			gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
			gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			panel.setLayout(gridBagLayout);
			
			int i=3;
			for(Atribut a : t.getPolja()){
				JLabel jl = new JLabel(a.toString()+" :");
				GridBagConstraints gbc_label = new GridBagConstraints();
				gbc_label.insets = new Insets(0, 0, 5, 5);
				gbc_label.gridx = 1;
				gbc_label.gridy = i;
				panel.add(jl, gbc_label);
				ComponentFactory added = new ComponentFactory();
				ComponentPart comp = added.makeComponent(a, i++);
				int ind=0;
				int three=0;
				for(Component c0 : comp.getComp()){
					boolean editable = true;
					if(c0 instanceof JButton)
						continue;
					if(a.isUsedAsKey(data.getTorka().get(a.getName().getCode().toString())));
						editable = false;
					if(c0 instanceof JTextField){
						((JTextField)c0).setText((String) data.getTorka().get(a.getName().getCode().toString()));
						((JTextField)c0).setEditable(editable);
						polja.put(a.getName().getCode(), c0);
					}
					else if(c0 instanceof JSpinner){
						((JSpinner)c0).setValue( data.getTorka().get(a.getName().getCode().toString()));
						((JTextField)c0).setEditable(editable);
						polja.put(a.getName().getCode(), c0);
					}
					else if(c0 instanceof JRadioButton){						
						if(three==0){
							if(data.getTorka().get(a.getName().getCode().toString()).equals(true))
								((JRadioButton)c0).setSelected(true);
							polja.put(a.getName().getCode()+"T", c0);
							three=1;
						}
						else if(three==1){
							if(data.getTorka().get(a.getName().getCode().toString()).equals(false))
								((JRadioButton)c0).setSelected(true);
							polja.put(a.getName().getCode()+"F", c0);
							three=2;
						}
						else if(three==2){
							if(!data.getTorka().get(a.getName().getCode().toString()).equals(false))
								if(!data.getTorka().get(a.getName().getCode().toString()).equals(true))
									((JRadioButton)c0).setSelected(true);
							polja.put(a.getName().getCode()+"N", c0);
							three=0;
						}
					}
					panel.add(c0, comp.getGbc().get(ind++));
				}
				if(a.getDomain().getTip().equals(Tip.BOOLEAN)){
					i+=2;
				}
				
			}
			i+=2;
			
			JButton ok = new JButton("OK");
			GridBagConstraints gbc1_label = new GridBagConstraints();
			gbc1_label.insets = new Insets(0, 0, 5, 5);
			gbc1_label.gridx = 1;
			gbc1_label.gridy = i;
			ok.addActionListener(new OkEditTorkaListener(t, data, temp));
			panel.add(ok, gbc1_label);
			

			
			JButton cancel = new JButton(Sistem.getInstance().getTranslate("Cancel"));
			GridBagConstraints gbc3_label = new GridBagConstraints();
			gbc3_label.insets = new Insets(0, 0, 5, 5);
			gbc3_label.gridx = 2;
			gbc3_label.gridy = i++;
			cancel.addActionListener(new CancelTorkaListener(false));
			panel.add(cancel, gbc3_label);
		}
		return editTorkaFrame;
	}
	/**
	 * Frame that provides to user to edit Data from existing relational table
	 * @param t - Table to which new data are going to be added
	 * @return JFrame that provides editing
	 */
	private static EditTorkaFrame editRelationTorka(Table t) {
		int index = Tabs.getInstance().getTabele().getSelectedIndex();
		//kako dobiti tu konkretnu tabelu?
		JPanel jpanel = (JPanel) Tabs.getInstance().getTabele().getComponentAt(index);
		JScrollPane jscrollpane = (JScrollPane) jpanel.getComponent(0);
		JViewport jviewport = (JViewport) jscrollpane.getComponent(0);
		JTable jtable = (JTable) jviewport.getComponent(0);
		temp = jtable.getSelectedRow();
		if(temp==-1){
			JOptionPane.showMessageDialog(null, Sistem.getInstance().getTranslate("Choose_Row"));
			return null;
		}
		
		editTorkaFrame = new EditTorkaFrame();
		editTorkaFrame.addWindowListener(new WindowAdapter() {
			@Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        destroy();
		    }
		});
		polja = new HashMap<String, Object>();
		editTorkaFrame.setResizable(false);
		editTorkaFrame.setTitle(Sistem.getInstance().getTranslate("Edit_Data_from")+t.toString());
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //Odredjivanje velicine ekrana 
		double width =  screenSize.getWidth()*0.4; //da bi dialog izgledao normalno na svim ekranima
		double height =  screenSize.getHeight()*0.6; 
		editTorkaFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		editTorkaFrame.setBounds(100, 100, (int)width, (int) height);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		editTorkaFrame.setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{30, 150, 200, 70};
		gridBagLayout.rowHeights = new int[]{30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gridBagLayout);
		
		int i=3;
		int count = 0;
		for(Atribut a : t.getPolja()){
			int coulCount = jtable.getColumnCount();
			if(coulCount<count)
				continue;
			String lab = a.toString()+"["+a.getDomain().getTip().toString()+"] :";
			try{
				lab = a.toString()+"["+a.getDomain().getTip().toString()+"("+ a.getDomain().getLength()+")] :";
			}catch(Exception e){
				lab = a.toString()+"["+a.getDomain().getTip().toString()+"] :";
			}
			JLabel jl = new JLabel(lab);
			GridBagConstraints gbc_label = new GridBagConstraints();
			gbc_label.insets = new Insets(0, 0, 5, 5);
			gbc_label.gridx = 1;
			gbc_label.gridy = i;
			panel.add(jl, gbc_label);
			{
				ComponentFactory added = new ComponentFactory();
				ComponentPart comp = added.makeComponent(a, i++);
				int ind=0;
				for(Component c0 : comp.getComp()){
					boolean editable = true;
					if(a.isUsedAsKey(jtable.getValueAt(temp, count)))
						editable = false;
					if(c0 instanceof JTextField){
						Object ooo=  jtable.getValueAt(temp, count);
						System.out.println(ooo);
						((JTextField)c0).setText(jtable.getValueAt(temp, count).toString());
						((JTextField)c0).setEditable(editable);
						polja.put(a.getName().getCode(), c0);
					}
					else if(c0 instanceof JSpinner){
						((JSpinner)c0).setValue( jtable.getValueAt(temp, count));
						((JTextField)c0).setEditable(editable);
						polja.put(a.getName().getCode(), c0);
					}
					else if(c0 instanceof JRadioButton){
						if(jtable.getValueAt(temp, count).equals(true) && ind==0){
							((JRadioButton)c0).setSelected(true);
							polja.put(a.getName().getCode()+"T", c0);
						}
						else if(jtable.getValueAt(temp, count).equals(false) && ind==1){
							((JRadioButton)c0).setSelected(true);
							polja.put(a.getName().getCode()+"F", c0);
						}
						else{
							((JRadioButton)c0).setSelected(true);
							polja.put(a.getName().getCode()+"N", c0);
						}
					}
					panel.add(c0, comp.getGbc().get(ind++));
				}
				if(a.getDomain().getTip().equals(Tip.BOOLEAN)){
					i+=2;
				}
			}
			count++;
			
		}
		i+=2;
		
		JButton ok = new JButton("OK");
		GridBagConstraints gbc1_label = new GridBagConstraints();
		gbc1_label.insets = new Insets(0, 0, 5, 5);
		gbc1_label.gridx = 1;
		gbc1_label.gridy = i;
		ok.addActionListener(new OkEditTorkaListener(t, data, temp));
		panel.add(ok, gbc1_label);
		

		
		JButton cancel = new JButton(Sistem.getInstance().getTranslate("Cancel"));
		GridBagConstraints gbc3_label = new GridBagConstraints();
		gbc3_label.insets = new Insets(0, 0, 5, 5);
		gbc3_label.gridx = 2;
		gbc3_label.gridy = i++;
		cancel.addActionListener(new CancelTorkaListener(false));
		panel.add(cancel, gbc3_label);
		
		return editTorkaFrame;
	}

	/**
	 * Method that destroy instance of this class
	 */
	public static void destroy() {
		polja=null;
		editTorkaFrame.dispose();
		editTorkaFrame = null;		
	}
	
}
