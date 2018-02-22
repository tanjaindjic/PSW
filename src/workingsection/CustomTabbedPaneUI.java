package workingsection;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.plaf.metal.MetalTabbedPaneUI;

import listeners.CustomMouseEvent;
import listeners.TabPaneMouseListener;

/**
 * Creates custom GUI for Tabs.
 *
 */
public class CustomTabbedPaneUI extends MetalTabbedPaneUI {
	  Rectangle xRect;
	  private TabPaneMouseListener mouseListener;

	  /**
	   * Creates custom Mouse Listeners for Tabs.
	   *
	   */
	   protected void installListeners() {
	      super.installListeners();
	      tabPane.addMouseListener(new MyMouseHandler());
	   }
	  
	   /**
	    * Creates Tabs with custom GUI and Mouse Listener.
	    *@param g is custom Graphics settings.
	    *@param tabPlacement is index of a Tab.
	    *@param rects creates custom Tab card for placing title of Tab.
	    *@param textRect creates custom Tab rectangle with Tab title.
	    */
	   protected void paintTab(Graphics g, int tabPlacement,
	                           Rectangle[] rects, int tabIndex,
	                           Rectangle iconRect, Rectangle textRect) {g.setColor(Color.LIGHT_GRAY);
		   super.paintTab(g, tabPlacement, rects, tabIndex, iconRect, textRect);
		   g.setColor(Color.BLACK);
	      Font f = g.getFont();
	      g.setFont(new Font("Courier", Font.BOLD, 10));
	      FontMetrics fm = g.getFontMetrics(g.getFont());
	      int charWidth = fm.charWidth('x');
	      int maxAscent = fm.getMaxAscent();
	      g.drawString("x", textRect.x - 10 + textRect.width + 2 , textRect.y + textRect.height - 3);
	      g.drawRect(textRect.x - 10 +textRect.width,
	                 textRect.y+textRect.height-maxAscent, charWidth+2, maxAscent-1);

	      xRect = new Rectangle(textRect.x-10+textRect.width,
	                 textRect.y+textRect.height-maxAscent, charWidth+2, maxAscent-1);
	      g.setFont(f);
	      
	    }
	   
	   /**
	    * Calculates width of Tab.
	    * @param tabPlacement is index of Tab.
	    * @param tabIndex is index of Tab in Editor Workbench.
	    * @param metrics is metrics for chosen Font.
	    * @return current Tab width.
	    */
	   @Override
     protected int calculateTabWidth(
             int tabPlacement, int tabIndex, FontMetrics metrics) {
         return 120; // the width of the tab
     }
	   	
	   /**
	    * Handles custom Mouse behaviour.
	    * 
	    */
	    public class MyMouseHandler extends MouseAdapter {
	    	
	    	/**
	    	 * Creates custom actions when mouse pressed.
	    	 * @param e is Object that was selected.
	    	 */
	    	public void mousePressed(MouseEvent e) {
	            System.out.println(e);
	            try{
	            if (xRect.contains(e.getPoint())) {
	            	CustomTabbedPane tabPane = (CustomTabbedPane)e.getSource();
	               int tabIndex = tabForCoordinate(tabPane, e.getX(), e.getY());
	               tabPane.remove(tabIndex);
	               CustomMouseEvent ev = new CustomMouseEvent(e.getComponent(), e.getID(),e.getWhen(), e.getModifiers(),e.getX(), e.getY(), e.getClickCount(), e.isPopupTrigger(), tabIndex);
	              // e.setIndex(tabIndex);
	              // mouseListener.getTabMouseAction(ev);
	            }
	            }catch(Exception ex)
	            {}
	        }
	    	
	    }

	    /**
	     * Returns custom Mouse Listener.
	     * @return custom Mouse Listener.
	     */
		public TabPaneMouseListener getMouseListener() {
			return mouseListener;
		}

		 /**
	     * Sets custom Mouse Listener.
	     * @param mouseListener is custom Mouse Listener.
	     */
		public void setMouseHandler(TabPaneMouseListener mouseListener) {
			this.mouseListener = mouseListener;
		}
	    
	    
}
