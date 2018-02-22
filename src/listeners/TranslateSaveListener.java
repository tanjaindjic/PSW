package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import dialogs.TranslationFrame;
import editorSeme.controller.concreetBuilders.DefaultAtributBuilder;
import editorSeme.controller.concreetBuilders.DefaultBuilder;
import editorSeme.controller.concreetBuilders.DefaultPackageBuilder;
import editorSeme.controller.concreetBuilders.DefaultTableBuilder;
import workingsection.Tabs;

/**
 * Saves new Translation.
 *
 */
public class TranslateSaveListener implements ActionListener {

	private JComboBox lang;
	private JTextField tr;
	private DefaultBuilder dpb;
	private JTextField code;
	private TranslationFrame trf;
	
	/**
	 * Adds Translation for selected Object.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		if (dpb instanceof DefaultPackageBuilder) {
			DefaultPackageBuilder new_name = (DefaultPackageBuilder) dpb;
			//System.out.println(code.getText());
			new_name.BuildName(code.getText());
			
			//System.out.println(tr.getText().toString());
			
		//	System.out.println( lang.getSelectedItem().toString());

			new_name.BuildNewTranslate(tr.getText().toString(), lang.getSelectedItem().toString());
			
		}else if(dpb instanceof DefaultTableBuilder){
			DefaultTableBuilder new_name = (DefaultTableBuilder) dpb;
			new_name.BuildName(code.getText());
			
			new_name.BuildNewTranslate(tr.getText().toString(), lang.getSelectedItem().toString());
			if(Tabs.getTabele().getTabCount()>0)
				Tabs.getInstance().refreshTab();
			
		}else if(dpb instanceof DefaultAtributBuilder){
			DefaultAtributBuilder new_name = (DefaultAtributBuilder) dpb;
			new_name.BuildName(code.getText());

			new_name.BuildNewTranslate(tr.getText().toString(), lang.getSelectedItem().toString());
			if(Tabs.getTabele().getTabCount()>0)
				Tabs.getInstance().refreshTab();
		}else{
			
			System.out.println("Neki error kod translate save");
		}
		trf.dispose();

	}
	
	/**
	 * Propagation of parameters.
	 * @param trf frame containing Translation and selected language.
	 * @param t entered translation.
	 * @param jcb Combo Box containing selected language.
	 * @param dpb default builder for Objects.
	 * @param code of Object that is being translated.
	 */
	public TranslateSaveListener(TranslationFrame trf, JTextField t, JComboBox jcb, DefaultBuilder dpb, JTextField code){
		lang=jcb;
		tr=t;
		this.dpb=dpb;
		this.code = code;
		this.trf=trf;
		
	}

}
