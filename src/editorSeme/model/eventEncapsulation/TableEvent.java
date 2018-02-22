package editorSeme.model.eventEncapsulation;

import editorSeme.model.pojo.Table;
import editorSeme.model.pojo.Package;

public class TableEvent {
	private Table table;
	private Package pack;


	   private EventType akcije;
	   
	
	   /**
	    * Returns the table on which the action is done. 
	    * @return The used table.
	    */
	   public Table getTable() {
	      return table;
	   }
	   
	   /**
	    * Returns the package that was used.
	    * @return The used package.
	    */
	   public Package getPack() {
	      return pack;
	   }
	   
	   /**
	    * Returns the type of action that is being done.
	    * @return The type of action.
	    */
	   public EventType getEventType() {
		return akcije;
		   
	   }

	   /**
	    * Constructor for the TableEvent.
	    * @param table The table on which the action is being done.
	    * @param pack The attribute on which the action is being done.
	    * @param akcije The action that is being done.
	    */
	   public TableEvent(Table table, Package pack, EventType akcije) {
		   this.table = table; 
		   this.pack = pack;
		   this.akcije = akcije;
	   }
}
