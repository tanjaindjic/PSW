package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;

import db.crud.RelationalCreateTuple;
import db.crud.RelationalUpdateTuple;
import db.model.Torka;
import db.model.Value;
import db.search.RelationalSearch;
import dialogs.EditTableFrame;
import dialogs.TranslationFrame;
import editorSeme.model.additional.JSONSerialize;
import editorSeme.model.enums.Tip;
import editorSeme.model.pojo.Atribut;
import editorSeme.model.pojo.Package;
import editorSeme.model.pojo.Sistem;
import editorSeme.model.pojo.Table;
import editorSeme.model.pojo.Translation;
import editorSeme.view.EditorWorkbench;
import start.DatabaseType;
import start.InfViewModel;
import workingsection.Tabs;
import workingsection.WorkArea;
import workingsection.tree.Tree;

/**
 * Adds Translation for Packages, Tables and Attributes.
 */
public class TranslateSvegaListener implements ActionListener {

	private TranslationFrame translationFrame;
	private EditTableFrame etf;
	private boolean atrEdit;
	
	/**
	 * Propagation of Table or Package information.
	 * @param translationFrame where Translation was entered and language was chosen.
	 * @param etf is Edit Table Frame with information which Table is being edited.
	 * @param atrEdit indicates if there is Attribute being edited.
	 */
	public TranslateSvegaListener(TranslationFrame translationFrame, EditTableFrame etf, boolean atrEdit) {
		this.translationFrame = translationFrame;
		this.etf = etf;
		this.atrEdit = atrEdit;
	}

	/**
	 * Creates Translation with given parameters.
	 */
		@Override
		public void actionPerformed(ActionEvent e) {
	
			Object node = (Object) Tree.getInstance().getSelected();
			if(node instanceof Package){
				if(InfViewModel.getInstance().getDatabaseType().equals(DatabaseType.JSON)){
					if(!translationFrame.getTranslationTextField().getText().isEmpty() && translationFrame.getLangComboBox().getSelectedItem()!=null){
						for(Translation t : ( (Package) Tree.getInstance().getSelected() ).getNaziv().getTranslate()){
							if(t.getLang().equals(translationFrame.getLangComboBox().getSelectedItem().toString())){
								t.setTr(translationFrame.getTranslationTextField().getText());
								Tree.getInstance().reload();
								 EditorWorkbench.reloadJsonTekst();
								 translationFrame.dispose();
								// JSONSerialize.saveObj(Sistem.getInstance());
								 EditorWorkbench.reloadJsonTekst();
								 return;
							}
						}
						( (Package) Tree.getInstance().getSelected() ).getNaziv().addTranslate(translationFrame.getLangComboBox().getSelectedItem().toString(), translationFrame.getTranslationTextField().getText());
						 Tree.getInstance().reload();
						 EditorWorkbench.reloadJsonTekst();
						 translationFrame.dispose();
						 //JSONSerialize.saveObj(Sistem.getInstance());
						 EditorWorkbench.reloadJsonTekst();
					}
				}else{
					if(!translationFrame.getTranslationTextField().getText().isEmpty() && translationFrame.getLangComboBox().getSelectedItem()!=null){
						
						Torka t = new Torka();
						ArrayList<String> pkey = new ArrayList<>();
						pkey.add("JEZ_CODE");
						pkey.add("JEZ_LANGUAGE");
						
						ArrayList<Value> vals = new ArrayList<>();
						Value v1 = new Value(((Package) Tree.getInstance().getSelected()).getNaziv().getCode(), Tip.VARCHAR, "JEZ_CODE");
						vals.add(v1);
						Value v2 = new Value(translationFrame.getLangComboBox().getSelectedItem().toString(), Tip.CHAR, "JEZ_LANGUAGE");
						vals.add(v2);
						Value v3 = new Value(translationFrame.getTranslationTextField().getText(), Tip.VARCHAR, "JEZ_TRANSLATION");
						vals.add(v3);
						t.setTorka(vals);
						
						RelationalSearch rs = new RelationalSearch("JEZICI");
						ResultSet torke = (ResultSet) rs.StringSearch("JEZ_CODE", ((Package) Tree.getInstance().getSelected()).getNaziv().getCode());
						
							if(rs.checkLanguage(torke, translationFrame.getLangComboBox().getSelectedItem().toString())){
								RelationalUpdateTuple rct = new RelationalUpdateTuple(t,"JEZICI", pkey);
								rct.doCommand();
								((Package) Tree.getInstance().getSelected()).getNaziv().editTranslate(translationFrame.getLangComboBox().getSelectedItem().toString(), translationFrame.getTranslationTextField().getText());
								 Tree.getInstance().reload();
								translationFrame.dispose();
							}else{
								RelationalCreateTuple rct = new RelationalCreateTuple(t,"JEZICI");
								rct.doCommand();
								if(translationFrame.getLangComboBox().getSelectedItem().toString().equals("enUS"))
									((Package) Tree.getInstance().getSelected()).getNaziv().addTranslate(translationFrame.getLangComboBox().getSelectedItem().toString(), translationFrame.getTranslationTextField().getText());
								else
									((Package) Tree.getInstance().getSelected()).getNaziv().addTranslateSerbian(translationFrame.getLangComboBox().getSelectedItem().toString(), translationFrame.getTranslationTextField().getText());
								 Tree.getInstance().reload();
								translationFrame.dispose();
							}
						
					}
					
				}
				
				
					
				
			}
			else if(node instanceof Table){
				if(!atrEdit){
					if(!translationFrame.getTranslationTextField().getText().isEmpty() && translationFrame.getLangComboBox().getSelectedItem()!=null){
					if(InfViewModel.getInstance().getDatabaseType().equals(DatabaseType.JSON))	{
					for(Translation t : ( (Table) Tree.getInstance().getSelected() ).getNaziv().getTranslate()){
						if(t.getLang().equals(translationFrame.getLangComboBox().getSelectedItem().toString())){
							int index = Tabs.getTabele().indexOfTab(((Table)Tree.getInstance().getSelected()).toString());
					        if (index >= 0) {

					        	Tabs.getTabele().removeTabAt(index);
					        
					        }
					
							t.setTr(translationFrame.getTranslationTextField().getText());
							 if(Tabs.getTabele().getTabCount()==0){
									WorkArea.getInstance().removeAll();
									WorkArea.getInstance().repaint();
									WorkArea.getInstance().revalidate();
									
									Tabs.getTabele().revalidate();
									Tabs.getTabele().repaint();
									Tabs.getChildren().removeAll();
									WorkArea.getInstance().add(Tabs.getInstance());
								}
							Tabs.getChildren().removeAll();
							 Table newtab = (Table) Tree.getInstance().getSelected();
							 Tabs.addTab(newtab);
							Tree.getInstance().reload();
							 EditorWorkbench.reloadJsonTekst();
							 translationFrame.dispose();
							 //JSONSerialize.saveObj(Sistem.getInstance());
							
							 return;
						}
					}
					int index = Tabs.getTabele().indexOfTab(((Table)Tree.getInstance().getSelected()).toString());
			        if (index >= 0) {

			        	Tabs.getTabele().removeTabAt(index);
			        
			        }
					( (Table) Tree.getInstance().getSelected() ).getNaziv().addTranslate(translationFrame.getLangComboBox().getSelectedItem().toString(), translationFrame.getTranslationTextField().getText());
					 if(Tabs.getTabele().getTabCount()==0){
							WorkArea.getInstance().removeAll();
							WorkArea.getInstance().repaint();
							WorkArea.getInstance().revalidate();
							
							Tabs.getTabele().revalidate();
							Tabs.getTabele().repaint();
							Tabs.getChildren().removeAll();
							WorkArea.getInstance().add(Tabs.getInstance());
						}
					Tabs.getChildren().removeAll();
					Table newtab = (Table) Tree.getInstance().getSelected(); 
					Tabs.addTab(newtab);
					Tree.getInstance().reload();
					 EditorWorkbench.reloadJsonTekst();
					 translationFrame.dispose();
					 //JSONSerialize.saveObj(Sistem.getInstance());
					
				}else{
					
					Torka t = new Torka();
					ArrayList<String> pkey = new ArrayList<>();
					pkey.add("JEZ_CODE");
					pkey.add("JEZ_LANGUAGE");
					
					ArrayList<Value> vals = new ArrayList<>();
					Value v1 = new Value(((Table) Tree.getInstance().getSelected()).getNaziv().getCode(), Tip.VARCHAR, "JEZ_CODE");
					vals.add(v1);
					Value v2 = new Value(translationFrame.getLangComboBox().getSelectedItem().toString(), Tip.CHAR, "JEZ_LANGUAGE");
					vals.add(v2);
					Value v3 = new Value(translationFrame.getTranslationTextField().getText(), Tip.VARCHAR, "JEZ_TRANSLATION");
					vals.add(v3);
					t.setTorka(vals);
					
					RelationalSearch rs = new RelationalSearch("JEZICI");
					ResultSet torke = (ResultSet) rs.StringSearch("JEZ_CODE", ((Table) Tree.getInstance().getSelected()).getNaziv().getCode());
					
						if(rs.checkLanguage(torke, translationFrame.getLangComboBox().getSelectedItem().toString())){
							System.out.println("Treba da odradi update prevoda tabele");
							
							int index = Tabs.getTabele().indexOfTab(((Table)Tree.getInstance().getSelected()).toString());
					        if (index >= 0) {

					        	Tabs.getTabele().removeTabAt(index);
					        
					        }
					        
							RelationalUpdateTuple rct = new RelationalUpdateTuple(t,"JEZICI", pkey);
							rct.doCommand();
							((Table) Tree.getInstance().getSelected()).getNaziv().editTranslate(translationFrame.getLangComboBox().getSelectedItem().toString(), translationFrame.getTranslationTextField().getText());
							Tabs.getInstance().validate();
							Tabs.getChildren().removeAll();
							if(Tabs.getTabele().getTabCount()==0){
								WorkArea.getInstance().removeAll();
								WorkArea.getInstance().repaint();
								WorkArea.getInstance().revalidate();
								
								Tabs.getTabele().revalidate();
								Tabs.getTabele().repaint();
								Tabs.getChildren().removeAll();
								WorkArea.getInstance().add(Tabs.getInstance());
							}
							
							Tabs.getTabele().revalidate();
							Tabs.getTabele().repaint();
							
							Table newtab = (Table) Tree.getInstance().getSelected(); 
							Tabs.addTab(newtab);
							Tree.getInstance().reload();
							
							translationFrame.dispose();
						}else{
							System.out.println("Treba da odradi create prevoda tabele");
							int index = Tabs.getTabele().indexOfTab(((Table)Tree.getInstance().getSelected()).toString());
					        if (index >= 0) {

					        	Tabs.getTabele().removeTabAt(index);
					        
					        }
							RelationalCreateTuple rct = new RelationalCreateTuple(t,"JEZICI");
							rct.doCommand();
							
							if(translationFrame.getLangComboBox().getSelectedItem().toString().equals("enUS"))
								((Table) Tree.getInstance().getSelected()).getNaziv().addTranslate(translationFrame.getLangComboBox().getSelectedItem().toString(), translationFrame.getTranslationTextField().getText());
							else
								((Table) Tree.getInstance().getSelected()).getNaziv().addTranslateSerbian(translationFrame.getLangComboBox().getSelectedItem().toString(), translationFrame.getTranslationTextField().getText());
							if(Tabs.getTabele().getTabCount()==0){
								WorkArea.getInstance().removeAll();
								WorkArea.getInstance().repaint();
								WorkArea.getInstance().revalidate();
								
								Tabs.getTabele().revalidate();
								Tabs.getTabele().repaint();
								Tabs.getChildren().removeAll();
								WorkArea.getInstance().add(Tabs.getInstance());
							}
							
							Tabs.getInstance().validate();
							Tabs.getChildren().removeAll();
							Table newtab = (Table) Tree.getInstance().getSelected(); 
							Tabs.addTab(newtab);
							Tree.getInstance().reload();
							
							
							translationFrame.dispose();
						}
					
					
				}
			}
			
			}else{
				if(!translationFrame.getTranslationTextField().getText().isEmpty() && translationFrame.getLangComboBox().getSelectedItem()!=null){
					if(etf.getList()==null){
						return;
					}
					
					if(InfViewModel.getInstance().getDatabaseType().equals(DatabaseType.JSON))	{
						Atribut a = (Atribut) etf.getList().getSelectedValue();
						for(Translation t : a.getNaziv().getTranslate()){
							if(t.getLang().equals(translationFrame.getLangComboBox().getSelectedItem().toString())){
								t.setTr(translationFrame.getTranslationTextField().getText());
								
								int index = Tabs.getTabele().indexOfTab(((Table)Tree.getInstance().getSelected()).toString());
						        if (index >= 0) {

						        	Tabs.getTabele().removeTabAt(index);
						        
						        }
						        if(Tabs.getTabele().getTabCount()==0){
									WorkArea.getInstance().removeAll();
									WorkArea.getInstance().repaint();
									WorkArea.getInstance().revalidate();
									
									Tabs.getTabele().revalidate();
									Tabs.getTabele().repaint();
									Tabs.getChildren().removeAll();
									WorkArea.getInstance().add(Tabs.getInstance());
								}
						        Tabs.getChildren().removeAll();
								Table newtab = (Table) Tree.getInstance().getSelected(); 
								Tabs.addTab(newtab);
								 EditorWorkbench.reloadJsonTekst();
								 translationFrame.dispose();
								// JSONSerialize.saveObj(Sistem.getInstance());
								 Tabs.getInstance().refreshTab();
								 return;
							}
						}
						a.getNaziv().addTranslate(translationFrame.getLangComboBox().getSelectedItem().toString(), translationFrame.getTranslationTextField().getText());
						int index = Tabs.getTabele().indexOfTab(((Table)Tree.getInstance().getSelected()).toString());
				        if (index >= 0) {

				        	Tabs.getTabele().removeTabAt(index);
				        
				        }
				        if(Tabs.getTabele().getTabCount()==0){
							WorkArea.getInstance().removeAll();
							WorkArea.getInstance().repaint();
							WorkArea.getInstance().revalidate();
							
							Tabs.getTabele().revalidate();
							Tabs.getTabele().repaint();
							Tabs.getChildren().removeAll();
							WorkArea.getInstance().add(Tabs.getInstance());
						}
				        Tabs.getChildren().removeAll();
						Table newtab = (Table) Tree.getInstance().getSelected(); 
						Tabs.addTab(newtab);
						 EditorWorkbench.reloadJsonTekst();
						 translationFrame.dispose();
						 //JSONSerialize.saveObj(Sistem.getInstance());
						 Tabs.getInstance().refreshTab();
					}else{
						 
						Atribut a = (Atribut) etf.getList().getSelectedValue();
						Torka t = new Torka();
						ArrayList<String> pkey = new ArrayList<>();
						pkey.add("JEZ_CODE");
						pkey.add("JEZ_LANGUAGE");
						
						ArrayList<Value> vals = new ArrayList<>();
						Value v1 = new Value(a.getName().getCode(), Tip.VARCHAR, "JEZ_CODE");
						vals.add(v1);
						Value v2 = new Value(translationFrame.getLangComboBox().getSelectedItem().toString(), Tip.CHAR, "JEZ_LANGUAGE");
						vals.add(v2);
						Value v3 = new Value(translationFrame.getTranslationTextField().getText(), Tip.VARCHAR, "JEZ_TRANSLATION");
						vals.add(v3);
						t.setTorka(vals);
						
						RelationalSearch rs = new RelationalSearch("JEZICI");
						ResultSet torke = (ResultSet) rs.StringSearch("JEZ_CODE", a.getName().getCode());
						
							if(rs.checkLanguage(torke, translationFrame.getLangComboBox().getSelectedItem().toString())){
								System.out.println("Treba da odradi update prevoda atributa");
								RelationalUpdateTuple rct = new RelationalUpdateTuple(t,"JEZICI", pkey);
								rct.doCommand();
								a.getNaziv().editTranslate(translationFrame.getLangComboBox().getSelectedItem().toString(), translationFrame.getTranslationTextField().getText());
								
								int index = Tabs.getTabele().indexOfTab(((Table)Tree.getInstance().getSelected()).toString());
						        if (index >= 0) {

						        	Tabs.getTabele().removeTabAt(index);
						        
						        }
						        if(Tabs.getTabele().getTabCount()==0){
									WorkArea.getInstance().removeAll();
									WorkArea.getInstance().repaint();
									WorkArea.getInstance().revalidate();
									
									Tabs.getTabele().revalidate();
									Tabs.getTabele().repaint();
									Tabs.getChildren().removeAll();
									WorkArea.getInstance().add(Tabs.getInstance());
								}
								Tabs.getChildren().removeAll();
								Table newtab = (Table) Tree.getInstance().getSelected(); 
								Tabs.addTab(newtab);
								
								translationFrame.dispose();
							}else{
								System.out.println("Treba da odradi create prevoda atributa");
								RelationalCreateTuple rct = new RelationalCreateTuple(t,"JEZICI");
								rct.doCommand();
								if(translationFrame.getLangComboBox().getSelectedItem().toString().equals("enUS"))
									a.getNaziv().addTranslate(translationFrame.getLangComboBox().getSelectedItem().toString(), translationFrame.getTranslationTextField().getText());
								else
									a.getNaziv().addTranslateSerbian(translationFrame.getLangComboBox().getSelectedItem().toString(), translationFrame.getTranslationTextField().getText());
								int index = Tabs.getTabele().indexOfTab(((Table)Tree.getInstance().getSelected()).toString());
						        if (index >= 0) {

						        	Tabs.getTabele().removeTabAt(index);
						        
						        }
						        if(Tabs.getTabele().getTabCount()==0){
									WorkArea.getInstance().removeAll();
									WorkArea.getInstance().repaint();
									WorkArea.getInstance().revalidate();
									
									Tabs.getTabele().revalidate();
									Tabs.getTabele().repaint();
									Tabs.getChildren().removeAll();
									WorkArea.getInstance().add(Tabs.getInstance());
								}
								Tabs.getChildren().removeAll();
								Table newtab = (Table) Tree.getInstance().getSelected(); 
								Tabs.addTab(newtab);
								
								
								translationFrame.dispose();
							}
						
						
					}
					
				}
					
			}
			}else if(node instanceof Sistem){
				
				if(!translationFrame.getTranslationTextField().getText().isEmpty() && translationFrame.getLangComboBox().getSelectedItem()!=null){
					
					for(Translation t : ( (Sistem) Tree.getInstance().getSelected() ).getNaziv().getTranslate()){
						
						if(t.getLang().equals(translationFrame.getLangComboBox().getSelectedItem().toString())){
							
							t.setTr(translationFrame.getTranslationTextField().getText());
							 
							Tree.getInstance().reload();
							 EditorWorkbench.reloadJsonTekst();
							 translationFrame.dispose();
							 //JSONSerialize.saveObj(Sistem.getInstance());
							
							 return;
						}
						
				
				}
					if(translationFrame.getLangComboBox().getSelectedItem().toString().equals("enUS"))
						((Sistem) Tree.getInstance().getSelected() ).getNaziv().addTranslate(translationFrame.getLangComboBox().getSelectedItem().toString(), translationFrame.getTranslationTextField().getText());
					else
						((Sistem) Tree.getInstance().getSelected() ).getNaziv().addTranslateSerbian(translationFrame.getLangComboBox().getSelectedItem().toString(), translationFrame.getTranslationTextField().getText());
					Tree.getInstance().reload();
					EditorWorkbench.reloadJsonTekst();
					// EditorWorkbench.reloadJsonTekst();
					 translationFrame.dispose();
					// JSONSerialize.saveObj(Sistem.getInstance());
					
			}
		}
		}	

}
