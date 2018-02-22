package workingsection;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import editorSeme.model.additional.JSONSerialize;
import editorSeme.model.pojo.Sistem;
import start.DatabaseType;
import start.InfViewModel;
import workingsection.tree.Tree;

/**
 * Class that represents the main frame of aplication
 *
 */
public class MainWindow extends JFrame{
	
	private static MainWindow mainWindow = null;
	
	private static MenuBar menuBar;
	private static ToolBar toolBar;
	//private static StatusBar statusBar;
	public static JScrollPane scrollPane; 
	private static Tree tree;
	private static WorkArea workarea;
	
	/**
	 * Empty constructor
	 */
	private MainWindow(){
		
	}
	/**
	 * Singleton pattern, makes the frame and adds all necessary elements to it
	 * @return Instance of Main Window.
	 */
	public static MainWindow getInstance(){
		if(mainWindow == null){
			mainWindow = new MainWindow();
			mainWindow.setTitle(Sistem.getInstance().getTranslate("InfView"));
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //Odredjivanje velicine ekrana 
			double width =  screenSize.getWidth(); //da bi dialog izgledao normalno na svim ekranima
			double height =  screenSize.getHeight(); 
			mainWindow.setBounds(0, 0, (int)(width), (int)(height));
			//mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			mainWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			mainWindow.addWindowListener(new WindowAdapter() {
			    @Override
			    public void windowClosing(WindowEvent we)
			    { 
			    	if(InfViewModel.getInstance().getDatabaseType().equals(DatabaseType.JSON)){
			    		 String ObjButtons[] = {Sistem.getInstance().getTranslate("Yes"),Sistem.getInstance().getTranslate("No")};
					        int PromptResult = JOptionPane.showOptionDialog(null,Sistem.getInstance().getTranslate("Save_changes"),Sistem.getInstance().getTranslate("Warning"),JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,ObjButtons,ObjButtons[1]);
					        if(PromptResult==JOptionPane.NO_OPTION)
					        {
					            System.exit(0);
					        }else if(PromptResult==JOptionPane.YES_OPTION){
					        	 JSONSerialize.saveObj(Sistem.getInstance());
					        	 System.exit(0);
					        }
			    	}else{
			    		System.exit(0);
			    	}
			       
			    }
			});
			
			String myLoc = (System.getProperty("user.dir")+"/src/");;
			ImageIcon img = new ImageIcon(myLoc + "logo1.jpg");
			mainWindow.setIconImage(img.getImage());
			
			scrollPane = new JScrollPane();
			mainWindow.getContentPane().add(scrollPane, BorderLayout.WEST);
		//menubar	
			menuBar = MenuBar.getInstance();
			mainWindow.setJMenuBar(menuBar);
		
		//toolbar
			toolBar = ToolBar.getInstance();
			toolBar.setPreferredSize(new Dimension((int)width, 50));
			
			mainWindow.getContentPane().add(toolBar, BorderLayout.PAGE_START);
			
		//tree
			tree = Tree.getInstance();
			tree.setPreferredSize(new Dimension(200, 64));
			if(tree.isSelectionEmpty())
				tree.show(false);
			else
				tree.show(true);

			scrollPane = new JScrollPane();
			scrollPane.setViewportView(tree);
			mainWindow.getContentPane().add(scrollPane, BorderLayout.WEST);
			
		//workarea
			workarea = WorkArea.getInstance();
			workarea.setPreferredSize(new Dimension(1000, (int)height));
		//	JScrollPane scrollPaneWA = new JScrollPane();
		//	scrollPaneWA.setViewportView(workarea);
			mainWindow.getContentPane().add(workarea, BorderLayout.CENTER);
		
		//ako postoji model, treba ga ucitati i u tabove u vidu tabela
			if(tree.isVisible()){
				workarea.loadTabs();
			}
			
			
			mainWindow.setVisible(true);
		}
		return mainWindow;
	}
	/**
	 * Method that provides the panel to which working elements are going to be added
	 * @return Work Area panel.
	 */
	public static WorkArea getWorkArea(){
		return workarea;
	}
	/**
	 * Destructor, provides for main window to be destroyed and gone
	 */
	public static void destroy(){
		mainWindow.setVisible(false);
		mainWindow = null;
	}
}
