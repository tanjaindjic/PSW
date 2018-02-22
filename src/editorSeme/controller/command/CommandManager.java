package editorSeme.controller.command;

import java.util.ArrayList;
import java.util.Iterator;

import workingsection.tree.Tree;

/**
 * Manages all the actions in InfViewer. Every action gets added to its list and is executed by it.
 *
 */
public class CommandManager { 

	private static int activePosition; 

	private static ArrayList<AbstractCommand> abstractCommands;


	private static CommandManager instance = null;


	private CommandManager() {} 
	
	/**
	 * If a instance of the CommandManager class didn't exist before this method has been called a new CommandManager with an empty list of commands will be created. 
	 * On the other hand if a CommandManager already exists, the current instance of the CommandManager class whit all of the contained commands will be returned.
	 * @return Returns the instance of the CommandManager.
	 */
	public static CommandManager getInstance(){ 
		if (instance == null){
			instance = new CommandManager();
			activePosition = -1;
			abstractCommands = new ArrayList<AbstractCommand>();
		}
		
		return instance;
	}

	/**
	 * Deletes the current instance of the CommandManager, deleting all of its commands in the process. 
	 */
	public static void deleteInstance() { 
		instance = null;
	}


	/**
	 * Deletes all of the commands and the CommandManager instance.
	 */
	public void deleteAll() {
		activePosition = -1;
		this.removeAllAbstractCommands();
		deleteInstance();
	}

	/**
	 * Gets the active position which shows what is the current command position. 
	 * @return The ordinal number of the command.
	 */
	public int getActivePosition() {
		return activePosition;
	}
	/**
	 * Executes the next command if a command exist that could be executed. 
	 * @return True if a command was successful executed and false otherwise.
	 */
	public boolean doCommand(){
		
		if (activePosition < abstractCommands.size()-1) {
			
			activePosition++;
		
			return doCommandAt(activePosition);
		}
		return false;
		
	}

	/**
	 * Executes the command at the given position if a command exist that could be executed. 
	 * @param position The ordinal number of the command that should be executed.
	 * @return True if a command was successful executed and false otherwise.
	 */
	public boolean doCommandAt(int position) {
		// TODO: implement

		return abstractCommands.get(position).doCommand();
	}

	/**
	 * Reverses the doCommand action if a command exist that could be undone.
	 * @return True if a command was successful executed and false otherwise.
	 */
	public boolean undoCommand() {

		if (activePosition > -1) {

			Boolean returnVal = this.undoCommandAt(activePosition);
			activePosition--;
			return returnVal;
		}
		return false;
	}

	private boolean undoCommandAt(int position) {
		// TODO: implement
		return abstractCommands.get(position).undoCommand();
	}

	/**
	 * Gets all of the exectued commands.
	 * @return A list of all executed commands.
	 */
	public ArrayList<AbstractCommand> getAbstractCommands() {
		if (abstractCommands == null)
			abstractCommands = new ArrayList<AbstractCommand>();
		return abstractCommands;
	}

	private Iterator getIteratorAbstractCommand() {
		if (abstractCommands == null)
			abstractCommands = new ArrayList<AbstractCommand>();
		return abstractCommands.iterator();
	}


	/**
	 * Adds a new command (that represents a new action) to the CommandManager and executes it. 
	 * @param newAbstractCommand The command that should be added and executed. 
	 */
	public void addAbstractCommand(AbstractCommand newAbstractCommand) {
		if (newAbstractCommand == null)
			return;
		if (this.abstractCommands == null)
			this.abstractCommands = new ArrayList<AbstractCommand>();
		if (!this.abstractCommands.contains(newAbstractCommand)){
			this.abstractCommands.add(newAbstractCommand);
			newAbstractCommand.doCommand();
			Tree.getInstance().reload();
			activePosition++;
			
		}
			
	
	}

	/**
	 * Removes the given command from the CommandManager.
	 * @param oldAbstractCommand The command that should be removed.
	 */
	public void removeAbstractCommand(AbstractCommand oldAbstractCommand) {
		if (oldAbstractCommand == null)
			return;
		if (this.abstractCommands != null)
			if (this.abstractCommands.contains(oldAbstractCommand))
				this.abstractCommands.remove(oldAbstractCommand);
	}
/**
 * Deletes all the commands that were done till the point in which this method was called.
 */
	public void removeAllAbstractCommands() {
		if (abstractCommands != null)
			abstractCommands.clear();
	}

}
