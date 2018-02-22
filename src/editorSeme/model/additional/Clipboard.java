package editorSeme.model.additional;

import editorSeme.model.enums.ClipboardContentType;
/**
 * Class that is used for copying content. 
 *
 */
public class Clipboard {

	
		private Object content;
		private ClipboardContentType type;
		
		private static Clipboard instance = null;
		
		/**
		 * If an instance of the Clipboard class didn't exist prior to this method being called a new instance will be created.
		 * If an instance has existed earlier that that instance is returned. 
		 * @return A instance of the Clipboard class that is unique to the whole program.
		 */
		public static Clipboard getInstance(){
			if (instance == null){
				instance = new Clipboard();
				
			}
			return instance;
		}
		
		private Clipboard()
		{
			type=ClipboardContentType.EMPTY;
			content=null;
		}
		/**
		 * Deletes the instance of the Clipboard class.
		 */
		public void delete(){
			
			instance=null;
			this.content=ClipboardContentType.EMPTY;
			this.type= null;
			
		}
		/**
		 * Returns the type of content that is contained in the Clipboard.
		 * @return The type of content.
		 */
		public ClipboardContentType getType() {
			return type;
		}
		/**
		 * Sets the type of content that has been added to the Clipboard.
		 * @param type The type of content.
		 */
		public void setType(ClipboardContentType type) {
			this.type = type;
		}

		/**
		 * Sets the object that is being copied.
		 * @param content The Object that is being copied. 
		 */
		public void setContent(Object content) {
			this.content = content;
		}

		/**
		 * Returns the Object that is currently being copied.
		 * @return Returns the content of the clipboard.
		 */
		public Object getContent() {
			return content;
		}

	
}
