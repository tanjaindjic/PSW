package editorSeme.model.eventEncapsulation;

import editorSeme.model.pojo.Table;
/**
 * Class that tells the observer what action has been triggered for a Atribut. 
 *
 */
public class AtributEvent {

	private Object atribut;

	private Table table;
	private EventType akcije;
	   
	/**
	 * Returns the attribute on which the action is done. 
	 * @return The used attribute.
	 */
	   public Object getAtribut() {
	      return atribut;
	   }
	   
	/**
	 * Returns the table that was used.
	 * @return The used table.
	 */
	   public Table getTable() {
	      return table;
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
	    * @param tabela The table that will be used.
	    * @param atribut The attribute on which the action is being done.
	    * @param akcije The action that is being done.
	    */
	   public AtributEvent(Table tabela, Object atribut, EventType akcije) {
		   this.table = tabela; 
		   this.atribut = atribut;
		   this.akcije = akcije;
	   }

}
