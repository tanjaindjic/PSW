package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import db.crud.RelationalUpdateTuple;
import db.model.Torka;
import db.model.Value;
import editorSeme.model.enums.Tip;
import login.User;
import login.UserType;

/**
 * Saves changes after editing an User.
 *
 */
public class EditUserSaveListener implements ActionListener {
	private User u;
	
	/**
	 * Propagation of parameters.
	 * @param u user that is being edited.
	 */
	public EditUserSaveListener(User u) {
		// TODO Auto-generated constructor stub
		this.u = u;
	}

	/**
	 * Saves changes of edited User.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Torka t = new Torka();
		ArrayList<Value> values = new ArrayList<>();
		Value v1 = new Value(u.getUsername(), Tip.VARCHAR, "USERNAME");
		
		values.add(v1);
		Value v2 = new Value(u.getPassword(), Tip.VARCHAR, "PASSWORD");
		
		values.add(v2);
		
		if(u.getTypeOfUser().equals(UserType.ADMIN)){
			Value v3 = new Value("a", Tip.CHAR, "USER_TYPE");
			values.add(v3);
		}
			
		else if(u.getTypeOfUser().equals(UserType.PROJEKTANT)){
			Value v3 =new Value("p", Tip.CHAR, "USER_TYPE");
			values.add(v3);
		}
			
		else {
			Value v3 =new Value("u", Tip.CHAR, "USER_TYPE");
		
			values.add(v3);
		}

		
		t.setTorka(values);
		ArrayList<String> pkey = new ArrayList<>();
		pkey.add("USERNAME");
		RelationalUpdateTuple rut = new RelationalUpdateTuple(t, "KORISNICI", pkey);
		rut.doCommand();
	}

}
