package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import jsonDataBase.TorkaFactory;
import workingsection.Tool;

/**
 * Calls appropriate dialog for adding Tuples.
 */
public class DataListener implements ActionListener {
	private String opt;
	
	/**
	 * Propagation of parameters.
	 * @param opt chosen option if data is being added or edited.
	 */
	public DataListener(String opt) {
		// TODO Auto-generated constructor stub
		this.opt = opt;
	}

	
	/**
	 * Calls appropriate frame for manipulating data.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(opt.equals("Add"))
			TorkaFactory.makeTorkaFrame("Add Data");
		else if(opt.equals("Edit"))
			TorkaFactory.makeTorkaFrame("Edit Data");
	}

}
