package editorSeme.model.pojo;

import java.io.Serializable;

import javax.swing.tree.DefaultTreeModel;

/**
 * Class for tree model.
 *
 */
public class SistemModel extends DefaultTreeModel implements Serializable{

	/**
	 * Default constructor using singlton Sistem as model.
	 */
	public SistemModel() {
		super(Sistem.getInstance());
	}
	/**
	 * Constructor using singlont Sistem as model and initializing its path.
	 * @param s New system path.
	 */
	public SistemModel(String s) {
		super(Sistem.getInstance());
		Sistem.getInstance().getNaziv().setCode(s);
	}
	
}