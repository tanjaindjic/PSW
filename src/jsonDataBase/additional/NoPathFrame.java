package jsonDataBase.additional;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import editorSeme.model.pojo.Package;
import editorSeme.model.pojo.Sistem;
import editorSeme.model.pojo.Table;
import editorSeme.view.EditorWorkbench;
import jsonDataBase.model.DataTabela;
/**
 * Class that represents diaolog for adding a path for saving json file
 *
 */
public class NoPathFrame extends JDialog{
	private static NoPathFrame npf = null;
	private static JPanel contentPane;
	private static JPanel panel;
	public static JTextField tf;
	private static boolean done = false;
	private static Table t;
	/**
	 * Empty constructor
	 */
	public NoPathFrame(){
		
	}
	/**
	 * Singleton method that creates dialog
	 * @param t0 - table
	 * @return Object that is only instance of this class
	 */
	public static NoPathFrame getInstance(Table t0){
		if(npf == null){
			t=t0;
			npf = new NoPathFrame();
			npf.setResizable(false);
			npf.setModal(true);
			npf.setTitle(Sistem.getInstance().getTranslate("System_Not_saved"));
			npf.addWindowListener(new WindowAdapter() {
				@Override
			    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
					EditorWorkbench.getInstance().validate();
					EditorWorkbench.getInstance().repaint();
					CardLayout cardLayout = (CardLayout) EditorWorkbench.getOptPanel().getLayout();
					cardLayout.show(EditorWorkbench.getOptPanel(), "opcije");
					EditorWorkbench.reloadSplitPane();
			        destroy();
			    }
			});
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //Odredjivanje velicine ekrana 
			double width =  screenSize.getWidth()*0.4; //da bi dialog izgledao normalno na svim ekranima
			double height =  screenSize.getHeight()*0.3; 
			npf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			npf.setBounds(100, 100, 471, 324);
			
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			contentPane.setLayout(new BorderLayout(0, 0));
			npf.setContentPane(contentPane);
			
			panel = new JPanel();
			contentPane.add(panel, BorderLayout.CENTER);
			
			GridBagLayout gridBagLayout = new GridBagLayout();
			gridBagLayout.columnWidths = new int[]{30, 239, 80, 30};
			gridBagLayout.rowHeights = new int[]{30, 30, 30, 30, 50, 30, 30, 30};
			gridBagLayout.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0};
			gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
			panel.setLayout(gridBagLayout);
			
			JLabel tex = new JLabel(Sistem.getInstance().getTranslate("System_Not_saved_Choose"));
			GridBagConstraints gbc_label = new GridBagConstraints();
			gbc_label.gridwidth = 2;
			gbc_label.insets = new Insets(0, 0, 5, 5);
			gbc_label.gridx = 1;
			gbc_label.gridy = 1;
			panel.add(tex, gbc_label);
			
			tf = new JTextField();
			tf.setEnabled(false);
			GridBagConstraints gbc_tf = new GridBagConstraints();
			gbc_tf.fill = GridBagConstraints.HORIZONTAL;
			gbc_tf.insets = new Insets(0, 0, 5, 5);
			gbc_tf.gridx = 1;
			gbc_tf.gridy = 3;
			panel.add(tf, gbc_tf);
			
			JButton btn = new JButton(Sistem.getInstance().getTranslate("Choose"));
			GridBagConstraints gbc_btn = new GridBagConstraints();
			gbc_btn.insets = new Insets(0, 0, 5, 5);
			gbc_btn.gridx = 2;
			gbc_btn.gridy = 3;
			btn.addActionListener(new ChoosePathListener(tf));
			panel.add(btn, gbc_btn);
			
			
			JButton btn1 = new JButton("OK");
			GridBagConstraints gbc_btn1 = new GridBagConstraints();
			gbc_btn1.gridwidth = 2;
			gbc_btn1.insets = new Insets(0, 0, 5, 5);
			gbc_btn1.gridx = 1;
			gbc_btn1.gridy = 5;
			btn1.addActionListener(new OkNoPathListener());
			panel.add(btn1, gbc_btn1);
			npf.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width)/2 - npf.getWidth()/2, (Toolkit.getDefaultToolkit().getScreenSize().height)/2 - npf.getHeight()/2);
			
			
		}
		return npf;
	}
	/**
	 * Method that destroys this dialogFrame and sets it to null
	 */
	public static void destroy(){
		npf.setVisible(false);
		npf=null;
	}
}
