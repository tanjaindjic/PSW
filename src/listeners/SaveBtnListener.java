package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dialogs.AddAttributeInTableFrame;
import dialogs.NewTableFrame;
import editorSeme.controller.concreetBuilders.DefaultAtributBuilder;
import editorSeme.controller.concreetBuilders.DefaultTableBuilder;
import editorSeme.model.additional.JSONSerialize;
import editorSeme.model.pojo.Sistem;
/**
 * Class that saves structure
 *
 */
public class SaveBtnListener implements ActionListener {

	private DefaultAtributBuilder dab;
	private DefaultTableBuilder dtb;
	private NewTableFrame ntf;
	public SaveBtnListener(){
	}
	/**
	 * Provides json file in sistem, made by the model
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
	//	Sistem.getInstance().setPath(System.getProperty("user.dir")+"/"+Sistem.getInstance().getNaziv().getCode()+".json");
		JSONSerialize.saveStructure(Sistem.getInstance());
	}

}