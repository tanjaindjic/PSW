package jsonDataBase.additional;

import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import editorSeme.model.pojo.Package;
import editorSeme.model.pojo.Sistem;
import editorSeme.model.pojo.Table;
import jsonDataBase.model.DataTabela;
/**
 * Class that makes, reads and writes to json files
 *
 */
public class JSONDataSerialize {
	/**
	 * Method that provides saving json dataBase 
	 * @param dt - object that should be saved to json file
	 */
	public static void saveData(DataTabela dt) {
		if(Sistem.getInstance().getPath()==null){
			JOptionPane.showMessageDialog(null, Sistem.getInstance().getTranslate("No_Path_Save"), "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		String path = Sistem.getInstance().getPath();
		File tmpDir = new File(path);
		createFiles(path.substring(0, path.length()-5));
		String tablePath = getTablePath(dt.getKodTabele());
		
		ObjectMapper mapper = new ObjectMapper();
        try {
            File file = new File(tablePath);
            file.delete();
        } catch (Exception e) {            
        }
        try {
            mapper.writeValue(new File(tablePath), dt);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog((Component)null, Sistem.getInstance().getTranslate("Went_wrong"));
            return;
        }
		
	}
	/**
	 * Method that makes valid path for saving json
	 * @param kodTabele - code that identify the table
	 * @return
	 */
	private static String getTablePath(String kodTabele) {
		String retVal= Sistem.getInstance().getPath();
		retVal = retVal.substring(0, retVal.length()-5);
		Table temp = null;
		ArrayList<Table> t = Sistem.getInstance().getAllTables();
		for(Table t0 : t){
			if(t0.getNaziv().getCode().equals(kodTabele))
				temp=t0; 
		}
		retVal+=addPath(temp);
		return retVal;
	}
	/**
	 * Method that process the path to make it valid
	 * @param temp - Object that is going to become part of the path
	 * @return
	 */
	private static String addPath(Object temp) {
		String retVal = "";
		if(temp instanceof Table){
			retVal= addPath(((Table) temp).getParent())+"\\"+((Table) temp).getNaziv().getCode()+".json";
		}
		if(temp instanceof Package){
			retVal=addPath(((Package)temp).getParent())+"\\"+((Package)temp).getNaziv().getCode()+retVal;
		}
		return retVal;
	}
	/**
	 * Method that makes the whole json dataBase sistem valid by making folders (packages)
	 * @param path - path to folder that should be created
	 */
	private static void createFiles(String path) {
		File file = new File(path);
        if (!file.exists()) {
            if (file.mkdir()) {
                System.out.println("Directory is created!");
            } else {
                System.out.println("Failed to create directory!");
            }
        }
        createPackageFiles(path, Sistem.getInstance());
		
	}
	/**
	 * Method that makes the whole json dataBase sistem valid by making folders (packages)
	 * @param path - path to folder that should be created
	 * @param parent - object that is part of creating chain
	 */
	private static void createPackageFiles(String path, Object parent) {
		ArrayList<Package> p;
		if(parent instanceof Sistem)
	        p = ((Sistem)parent).getPackages();
		else 
			p=((Package)parent).getPackages();
		for(Package p0 : p){
        	String pathP = path+"\\"+p0.getNaziv().getCode();
    		File fileP = new File(pathP);
        	 if (!fileP.exists()) {
                 if (fileP.mkdir()) {
                     System.out.println("Directory is created! 0");
                 } else {
                     System.out.println("Failed to create directory!");
                 }
             }
        	 for(Package p1 : p0.getPackages())
        		 createPackageFiles(pathP, p0);
        }
		
	}
	/**
	 * Method that reads table from json file
	 * @param t - table whose tuples needs to be read
	 * @return - returns object that contains tupples
	 */
	public static DataTabela getDataTable(Table t) {
		if(Sistem.getInstance().getPath()==null){
			return null;
		}
		createFiles(Sistem.getInstance().getPath().substring(0, Sistem.getInstance().getPath().length()-5));
		String path = getTablePath(t.getNaziv().getCode());
		ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.WRAP_EXCEPTIONS, true);
        DataTabela dt = null;

        try {
            path = path.replace('\\', '/');
            File file1 = new File(path);
            if(!file1.exists()){
            	createJSONDataTable(path, t.getNaziv().getCode());
            }
            dt = (DataTabela)mapper.readValue(new File(path), DataTabela.class);
        } catch (IOException var5) {
            JOptionPane.showMessageDialog((Component)null, Sistem.getInstance().getTranslate("Went_wrong_Restart"));
            dt = null;
        }

		return dt;
	}
	/**
	 * Method taht cerates json file, based on table existance on model
	 * @param path - location to write json file 
	 * @param code - code of the table to which json file belongs
	 */
	private static void createJSONDataTable(String path, String code) {
		ObjectMapper mapper = new ObjectMapper();
        try {
        	DataTabela dt = new DataTabela();      	
        	dt.setKodTabele(code);
        	dt.setTorke(new ArrayList<>());
            mapper.writeValue(new File(path), dt);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog((Component)null, Sistem.getInstance().getTranslate("Went_wrong"));
            return;
        }
		
	}


	
}
