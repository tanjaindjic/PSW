package editorSeme.model.eventEncapsulation;

import editorSeme.model.pojo.Package;
import editorSeme.model.pojo.Sistem;
/**
 * Class that tells the observer what action has been triggered for a package. 
 *
 */
public class PackageEvent {
	private Package pack;

	private Sistem struc;
	private Package parent=null;

	private EventType akcije;
	   
	
	/**
	 * Returns the package on which the action will be executed. 
	 * @return The used package.
	 */
	   public Package getPack() {
	      return pack;
	   }
	   
	/**
	 * Returns the used system.
	 * @return The used system.
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
	    * This constructor is used when the package is added to a Sistem.
	    * @param struc The system that will be used.
	    * @param pack The package on which the action is being done.
	    * @param akcije The action that is done on the package.
	    */
	   public PackageEvent(Sistem struc, Package pack, EventType akcije) {
		   this.struc = struc; 
		   this.pack = pack;
		   this.akcije = akcije;
	   }
	   
	   //konstruktor u slucaju da je paket dodat u paket a ne u strukturu
	   /**
	    * This constructor is used when the package is added to a package.
	    * @param struc The package that will be used.
	    * @param pack The package on which the action is being done.
	    * @param akcije The action that is being done.
	    */
	   public PackageEvent(Package struc, Package pack, EventType akcije) {
		   this.parent = struc; 
		   this.pack = pack;
		   this.akcije = akcije;
	   }
}
