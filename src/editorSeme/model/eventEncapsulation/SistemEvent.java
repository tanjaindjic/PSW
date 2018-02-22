package editorSeme.model.eventEncapsulation;

import editorSeme.model.pojo.Sistem;

/**
 * Class that tells the observer what action has been triggered for a Sistem. 
 *
 */
public class SistemEvent {
	private Sistem struc;


	   private EventType akcije;
	/**
	 * Returns the Sistem on which the action is done.
	 * @return The used Sistem.
	 */
	   public Sistem getStruc() {
	      return struc;
	   }
	   
	   
	   /**
	    * Returns the type of action that is being done.
	    * @return The type of action.
	    */
	   public EventType getEventType() {
		return akcije;
		   
	   }

	   /**
	    * Constructor for the AtributEvent.
	    * @param struc The Sistem on which the action is being done.
	    * @param akcije The action that is being done.
	    */
	   public SistemEvent(Sistem struc, EventType akcije) {
		   this.struc = struc; 
		   this.akcije = akcije;
	   }
}
