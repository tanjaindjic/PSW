package editorSeme.controller.memento;

import java.util.ArrayList;

import editorSeme.controller.command.AbstractCommand;
import editorSeme.controller.command.CommandManager;
import workingsection.tree.Tree;
/**
 * 
 * Originator class for the Memento pattern. It is used to enable a version control for the text editor part of JSON database creator.
 *
 */
public class Originator {
	
	  private static ArrayList<Memento> mementos;
	  private static Originator instance=null;
	  private static int activePosition;  
	  private static Memento current=null;
	  private static Memento valid = null;
	  
	  /**
	   * Used for auto setting a valid version of schema. 
	   * @param m Memento that should be saved.
	   */
	  public void setValid(Memento m ) {
		  valid = m;
	  }
	  
	  /**
	   * Returns the auto saved version.
	   * @return The valid version of the schema.
	   */
	  public String getValid() {
		  return valid.getCode();
	  }
	  
	   private Originator() {}
	   
	   /**
	    * If a instance of the Originator class didn't exist before this method has been called a new Originator with an empty list of Mementos will be created. 
	    * On the other hand if a Originator already exists, the current instance of the Originator class whit all of the contained Mementos will be returned.
	    * @return An instance of the Originator class which should be used.
	    */
	   public static Originator getInstance(){
		   if (instance == null){
				instance = new Originator();
				activePosition = -1;
				Memento m = new Memento("");
				valid = m;
				mementos = new ArrayList<Memento>();
			}
		   return instance;
	   }
	   
	/**
	 * Creates a new Memento that is added to the Originator. It adds a new version of the JSON schema to the Originator.
	 * @param s The value of the JSON schema in a string format.
	 */
	   public void createMemento(String s) {
		   if (s == null)
				return;
		   this.mementos.add(new Memento(s));
		   current = new Memento(s);
		  
			activePosition++;
	   }
	   /**
	    * Does a roll back of the JSON schema version by setting the current version of the JSON schema to the one before it.
	    * @return The value of the rolled back JSON schema in a string format.
	    */
	   public String restoreMemento(){
		   if(activePosition -1 >= 0){
			   activePosition--;
			   Memento m= mementos.get(activePosition);
			   return m.getCode();
		   }
		   if(mementos.size() == 0)
			   return "";
		   
		   Memento m= mementos.get(0);
		   return m.getCode();
	   }
	   /**
	    * Sets the current version of the JSON schema to the give string value.
	    * @param s The value of the JSON schema in a string format.
	    */
	   public void SetCurrent(String s){
		   Memento m = new Memento(s);
		   current = m;
	   }
	   
	   /**
	    * Gives the current version of the JSON schema.
	    * @return The value of the current JSON schema in a string format.
	    */
	   public String getCurrentVersion(){
		   activePosition=mementos.size();
		   return current.getCode();
	   }


}
