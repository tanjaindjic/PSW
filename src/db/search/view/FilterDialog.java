package db.search.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.border.EmptyBorder;

import db.search.view.factory.AbsFilter;
import db.search.view.factory.FilterComponent;
import db.search.view.factory.FilterFactory;
import editorSeme.model.enums.Tip;
import editorSeme.model.pojo.Atribut;
import editorSeme.model.pojo.Sistem;
import editorSeme.model.pojo.Table;
import jsonDataBase.ApplyAddTorkaListener;
import jsonDataBase.CancelTorkaListener;
import jsonDataBase.ClearAddTorkaListener;
import jsonDataBase.OkAddTorkaListener;
import jsonDataBase.factory.ComponentFactory;
import jsonDataBase.factory.ComponentPart;
import workingsection.Tabs;
/**
 * Dialog that provieds choosing of filters to be implemented on selected table
 *
 */
public class FilterDialog extends JDialog{
	private static FilterDialog fd = null;
	private static JPanel contentPane;
	private static JPanel panel;
	public static HashMap<String, Component> polja;
	/**
	 * Empty constructor
	 */
	private FilterDialog(){
		
	}
	/**
	 * Singleton pattern, makes the diaolog for choosing the filters
	 * @return Instance of Filter Dialog
	 */
	public static FilterDialog getInstance(){
		if(fd==null){
			fd = new FilterDialog();
			
			int selTable = Tabs.getInstance().getTabele().getSelectedIndex();
			JPanel jpanel = (JPanel) Tabs.getInstance().getTabele().getComponentAt(selTable);
			JScrollPane jscrollpane = (JScrollPane) jpanel.getComponent(0);
			JViewport jviewport = (JViewport) jscrollpane.getComponent(0);
			JTable jtable = (JTable) jviewport.getComponent(0);
			String tableName = Tabs.getInstance().getTabele().getSelectedComponent().getName();
			Table t = Sistem.getInstance().getTableByTabName(tableName);
			
			fd.addWindowListener(new WindowAdapter() {
				@Override
			    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
			        destroy();
			    }
			});
			
			fd.setResizable(false);
			fd.setTitle(Sistem.getInstance().getTranslate("Edit_filters_to") + t.toString());
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //Odredjivanje velicine ekrana 
			double width =  screenSize.getWidth()*0.45; //da bi dialog izgledao normalno na svim ekranima
			double height =  screenSize.getHeight()*0.6; 
			fd.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			fd.setBounds(100, 100, (int)width, (int) height);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			contentPane.setLayout(new BorderLayout(0, 0));
			fd.setContentPane(contentPane);
			
			panel = new JPanel();
			contentPane.add(panel, BorderLayout.CENTER);
			
			GridBagLayout gridBagLayout = new GridBagLayout();
			gridBagLayout.columnWidths = new int[]{30, 200, 50, 100, 70};
			gridBagLayout.rowHeights = new int[]{30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30};
			gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
			gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			panel.setLayout(gridBagLayout);
			polja = new HashMap<>();
			
			int i=3;
			for(Atribut a : t.getPolja()){
				String lab = a.toString()+" ["+a.getDomain().getTipString()+"] :";
				try{
					lab = a.toString()+" ["+a.getDomain().getTipString()+"("+ a.getDomain().getLength()+")] :";
				}catch(Exception e){
					lab = a.toString()+" ["+a.getDomain().getTipString()+"] :";
				}
				JLabel jl = new JLabel(lab);
				GridBagConstraints gbc_label = new GridBagConstraints();
				gbc_label.insets = new Insets(0, 0, 5, 5);
				gbc_label.gridx = 1;
				gbc_label.gridy = i;
				panel.add(jl, gbc_label);
				{
					FilterFactory added = new FilterFactory();
					AbsFilter comp = added.makeComponent(a, i++);
					int ind=0;
					int three=0;
					int minMax=0;
					for(Component c0 : comp.getComp()){
						panel.add(c0, comp.getGbc().get(ind++));
						if(c0 instanceof JLabel)
							continue;
						if(a.getDomain().getTip().equals(Tip.BOOLEAN)){
							String cc = a.getName().getCode();
							if(three==0){
								cc+="T";
								three++;
							}
							else if(three==1){
								cc+="F";
								three++;
							}else{
								cc+="N";
								three=0;
							}
							 polja.put(cc, c0);
						}
						else if(a.getDomain().getTip().equals(Tip.CHAR) || a.getDomain().getTip().equals(Tip.VARCHAR)){
							polja.put(a.getName().getCode(), c0);
						}
						else{
							if(minMax==0){
								polja.put(a.getName().getCode()+"MIN", c0);
								minMax++;
							}
							else{
								polja.put(a.getName().getCode()+"MAX", c0);
								minMax=0;
							}
						}
					}
					if(a.getDomain().getTip().equals(Tip.BOOLEAN)){
						i+=2;
					}
					else if(a.getDomain().getTip().equals(Tip.CHAR) || a.getDomain().getTip().equals(Tip.VARCHAR)){
						
					}
					else{
						i+=1;
					}
				}
			}
			JButton clear = new JButton(Sistem.getInstance().getTranslate("Clear"));
			GridBagConstraints gbc4_label = new GridBagConstraints();
			gbc4_label.insets = new Insets(0, 0, 5, 5);
			gbc4_label.gridx = 1;
			gbc4_label.gridy = ++i;
			clear.addActionListener(new ClearFilterListener());
			panel.add(clear, gbc4_label);
			
			JButton ok = new JButton(Sistem.getInstance().getTranslate("Filter"));
			GridBagConstraints gbc1_label = new GridBagConstraints();
			gbc1_label.insets = new Insets(0, 0, 5, 5);
			gbc1_label.gridx = 3;
			gbc1_label.gridy = i++;
			ok.addActionListener(new ApplyFilterListener(t));
			panel.add(ok, gbc1_label);
			
			JButton cancel = new JButton(Sistem.getInstance().getTranslate("Cancel"));
			GridBagConstraints gbc3_label = new GridBagConstraints();
			gbc3_label.insets = new Insets(0, 0, 5, 5);
			gbc3_label.gridx = 3;
			gbc3_label.gridy = i;
			cancel.addActionListener(new CancelFilterListener());
			panel.add(cancel, gbc3_label);
			
		}
		return fd;
	}

	/**
	 * Destructor
	 */
	public static void destroy() {
		fd.dispose();
		fd = null;		
	}
}
