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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import editorSeme.model.enums.Tip;
import editorSeme.model.pojo.Atribut;
import editorSeme.model.pojo.Sistem;
import editorSeme.model.pojo.Table;
import jsonDataBase.factory.ComponentFactory;
import jsonDataBase.factory.ComponentPart;
/**
 * Class that provides frame for adding tuple
 *
 */
public class AddTorkaFrame extends TorkaFrame {
	private static AddTorkaFrame addTorkaFrame =null;
	public static HashMap<String, Object> polja;
	private JScrollPane scrollPane;
	public static JPanel panel;
	private static JPanel contentPane ;
	/**
	 * Empty constructor
	 */
	private AddTorkaFrame(){
		
	}
	/**
	 * Frame that provides to user to add Data to table
	 * @param t - Table to which new data are going to be added
	 * @return JFrame that provides input
	 */
	public static AddTorkaFrame getInstance(Table t) {
		if(addTorkaFrame==null){
			addTorkaFrame = new AddTorkaFrame();
			addTorkaFrame.addWindowListener(new WindowAdapter() {
				@Override
			    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
			        destroy();
			    }
			});
			polja = new HashMap<String, Object>();
			addTorkaFrame.setResizable(false);
			addTorkaFrame.setTitle(Sistem.getInstance().getTranslate("Add_Data_to") + t.toString());
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //Odredjivanje velicine ekrana 
			double width =  screenSize.getWidth()*0.6; //da bi dialog izgledao normalno na svim ekranima
			double height =  screenSize.getHeight()*0.6; 
			addTorkaFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			addTorkaFrame.setBounds(100, 100, (int)width, (int) height);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			contentPane.setLayout(new BorderLayout(0, 0));
			addTorkaFrame.setContentPane(contentPane);
			
			panel = new JPanel();
			contentPane.add(panel, BorderLayout.CENTER);
			
			GridBagLayout gridBagLayout = new GridBagLayout();
			gridBagLayout.columnWidths = new int[]{30, 300, 200, 70};
			gridBagLayout.rowHeights = new int[]{30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30};
			gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
			gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			panel.setLayout(gridBagLayout);
			
			int i=3;
			for(Atribut a : t.getPolja()){
				String lab = a.toString()+"["+a.getDomain().getTipString()+"] :";
				try{
					lab = a.toString()+"["+a.getDomain().getTipString()+"("+ a.getDomain().getLength()+")] :";
				}catch(Exception e){
					lab = a.toString()+"["+a.getDomain().getTipString()+"] :";
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
					int three=0;
					for(Component c0 : comp.getComp()){
						panel.add(c0, comp.getGbc().get(ind++));
						if(c0 instanceof JButton)
							continue;
						if(!(c0 instanceof JRadioButton))
							polja.put(a.getName().getCode(), c0);
						else{
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
					}
					if(a.getDomain().getTip().equals(Tip.BOOLEAN)){
						i+=2;
					}
				}
				
			}
			
			JButton clear = new JButton(Sistem.getInstance().getTranslate("Clear"));
			GridBagConstraints gbc4_label = new GridBagConstraints();
			gbc4_label.insets = new Insets(0, 0, 5, 5);
			gbc4_label.gridx = 1;
			gbc4_label.gridy = ++i;
			clear.addActionListener(new ClearAddTorkaListener(t));
			panel.add(clear, gbc4_label);
			
			JButton ok = new JButton("OK");
			GridBagConstraints gbc1_label = new GridBagConstraints();
			gbc1_label.insets = new Insets(0, 0, 5, 5);
			gbc1_label.gridx = 2;
			gbc1_label.gridy = i;
			ok.addActionListener(new OkAddTorkaListener(t));
			panel.add(ok, gbc1_label);
			
	
			JButton apply = new JButton(Sistem.getInstance().getTranslate("Apply"));
			GridBagConstraints gbc2_label = new GridBagConstraints();
			gbc2_label.insets = new Insets(0, 0, 5, 5);
			gbc2_label.gridx = 1;
			gbc2_label.gridy = ++i;
			apply.addActionListener(new ApplyAddTorkaListener(t));
			panel.add(apply, gbc2_label);
			
			JButton cancel = new JButton(Sistem.getInstance().getTranslate("Cancel"));
			GridBagConstraints gbc3_label = new GridBagConstraints();
			gbc3_label.insets = new Insets(0, 0, 5, 5);
			gbc3_label.gridx = 2;
			gbc3_label.gridy = i;
			cancel.addActionListener(new CancelTorkaListener(true));
			panel.add(cancel, gbc3_label);
		}
		return addTorkaFrame;
	}
	/**
	 * Method that destroy instance of this class
	 */
	public static void destroy() {
		polja=null;
		addTorkaFrame.dispose();
		addTorkaFrame = null;		
	}
	
}
