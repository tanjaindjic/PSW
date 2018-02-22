package listeners;

import java.awt.Component;
import java.awt.event.MouseEvent;
/**
 * Represents a modified <code>MouseEvent</code> class in a such way that it stores 
 * an <code>int</code> value that represents a tab index of a tab that was the pointer
 * positioned when the event occured
 */
public class CustomMouseEvent extends MouseEvent {

	private int index;
	
	/**
	 * Sets arguments and index for Mouse Listener.
	 * @param arg0 for custom Mouse Listener.
	 * @param arg1 for custom Mouse Listener.
	 * @param arg2 for custom Mouse Listener.
	 * @param arg3 for custom Mouse Listener.
	 * @param arg4 for custom Mouse Listener.
	 * @param arg5 for custom Mouse Listener.
	 * @param arg6 for custom Mouse Listener.
	 * @param arg7 for custom Mouse Listener.
	 * @param index for custom Mouse Listener.
	 */
	public CustomMouseEvent(Component arg0, int arg1, long arg2, int arg3, int arg4, int arg5, int arg6, boolean arg7, int index) {
		super(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7);
		// TODO Auto-generated constructor stub
		this.index = index;
	}
	
	/**
	 * Sets arguments for Mouse Listener.
	 * @param arg0 for custom Mouse Listener.
	 * @param arg1 for custom Mouse Listener.
	 * @param arg2 for custom Mouse Listener.
	 * @param arg3 for custom Mouse Listener.
	 * @param arg4 for custom Mouse Listener.
	 * @param arg5 for custom Mouse Listener.
	 * @param arg6 for custom Mouse Listener.
	 * @param arg7 for custom Mouse Listener.
	 */
	public CustomMouseEvent(Component arg0, int arg1, long arg2, int arg3, int arg4, int arg5, int arg6, boolean arg7) {
		super(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7);
		// TODO Auto-generated constructor stub
		
	}
	

	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Returns index for Mouse Listener.
	 * @return index.
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * Sets index for Mouse Listener.
	 * @param index.
	 */
	public void setIndex(int index) {
		this.index = index;
	}
	
}
