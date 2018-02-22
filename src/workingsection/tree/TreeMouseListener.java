package workingsection.tree;

import java.awt.CardLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

import editorSeme.model.pojo.NameTranslate;
import editorSeme.model.pojo.Sistem;
import editorSeme.model.pojo.Table;
import editorSeme.model.pojo.Translation;
import editorSeme.view.EditorWorkbench;
import jsonDataBase.additional.NoPathFrame;
import start.DatabaseType;
import start.InfViewModel;
import workingsection.MainWindow;
import workingsection.Tabs;
import workingsection.WorkArea;

/**
 * Opens dialog for editing Nodes in Tree with right click, expandes, collapses or opens Tables on double click.
 */
public class TreeMouseListener implements MouseListener{

	/**
	 * Enables option for editing Nodes in Tree with right click.
	 * @param e is Object that invoked the edit popup.
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		if (SwingUtilities.isRightMouseButton(e)) {

	        int row = Tree.getInstance().getClosestRowForLocation(e.getX(), e.getY());
	        Tree.getInstance().setSelectionRow(row);
	        PopupMenu  pm = new PopupMenu(Tree.getInstance());
	        pm.show(e.getComponent(), e.getX(), e.getY());
	    }
	}
	/**
	 * usused method
	 */
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * usused method
	 */
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * usused method
	 */
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Opens Tables on double click, expands and collapses Packages on double click.
	 * @param arg0 is Object that invoked opening.
	 */
	@Override
	public void mouseReleased(MouseEvent arg0) {
	//	if(EditorWorkbench.isNull())
	//	if(!(Tree.getInstance().getSelected() instanceof Table))
		//	return;
		if(arg0.getClickCount()==1){
			
		}
		if(arg0.getClickCount()==2){
			if(EditorWorkbench.isNull())
				return;
			if(Tree.getInstance().getSelected() instanceof Table){
				if(InfViewModel.getInstance().getDatabaseType().equals(DatabaseType.JSON)){
					if(Sistem.getInstance().getPath()==null || Sistem.getInstance().getPath().isEmpty()){
						NoPathFrame.getInstance(null).setVisible(true);
					}
					
					if(!(Sistem.getInstance().getPath()==null || Sistem.getInstance().getPath().isEmpty()) && InfViewModel.getInstance().getDatabaseType().equals(DatabaseType.JSON)){
						WorkArea wa = MainWindow.getWorkArea();
						wa.remove(EditorWorkbench.getInstance());
						wa.remove(Tabs.getInstance());
						wa.repaint();
						wa.validate();
						Tabs.getChildren().removeAll();
						
						Tabs.addTab((Table) Tree.getInstance().getSelected());
						wa.add(Tabs.getInstance());
						wa.repaint();
						wa.validate();
						MainWindow.getInstance().validate();
						MainWindow.getInstance().repaint();
						
					}
					
				}else{
					WorkArea wa = MainWindow.getWorkArea();
					wa.remove(EditorWorkbench.getInstance());
					wa.remove(Tabs.getInstance());
					wa.repaint();
					wa.validate();
					Tabs.getChildren().removeAll();
					
					Tabs.addTab((Table) Tree.getInstance().getSelected());
					wa.add(Tabs.getInstance());
					wa.repaint();
					wa.validate();
					MainWindow.getInstance().validate();
					MainWindow.getInstance().repaint();
				}
				
				
			}
		}
		
	}

}
