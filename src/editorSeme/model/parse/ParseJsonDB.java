package editorSeme.model.parse;

import javax.swing.JOptionPane;

import editorSeme.model.additional.JSONSerialize;
import editorSeme.model.pojo.Sistem;

/**
 * Concrete implementation of ParseMetaData interface for JSON structured metashcemes.
 *
 */
public class ParseJsonDB implements ParsemMetadata{

	private String targetPath;
	
	/**
	 * Method that starts procces of parseing data from JSON file.
	 */
	@Override
	public void parseModel() {
		Sistem s=null;
		try{
			s = JSONSerialize.loadStructure(targetPath);
			s.setPath(targetPath);

			Sistem.getInstance().addSistem(s);
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,
				    "Something went wrong. File can not be loaded.",
				    "Loading Error",
				    JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if(!Sistem.getInstance().validate()) {
			JOptionPane.showMessageDialog(null, "Loaded system didnt pass validation.");
		}

		Sistem.getInstance().reloadChildren();
		
	}

	/**
	 * Constructor method for parser.
	 * @param targetPath Accepts path to the file which the parser is going to parse.
	 */
	public ParseJsonDB(String targetPath) {
		super();
		this.targetPath = targetPath;
	}
	
	

}
