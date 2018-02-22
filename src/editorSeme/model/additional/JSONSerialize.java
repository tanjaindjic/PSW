package editorSeme.model.additional;

import java.awt.Component;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import editorSeme.model.pojo.Sistem;
import login.User;
import start.DatabaseType;
import start.InfViewModel;

/**
 * Class which handles JSON serialization of Sistem and User objects.
 *
 */
public class JSONSerialize {
	/**
	 * Method for loading Sistem from disk into class model.
	 * @param path Path from which you want to load Sistem.
	 * @return Returns Sistem loaded from the path param.
	 */
	public static Sistem loadStructure(String path) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.WRAP_EXCEPTIONS, true);
        Sistem ws = null;

        try {
            path = path.replace('\\', '/');
            ws = (Sistem)mapper.readValue(new File(path), Sistem.class);
            InfViewModel.getInstance().setDatabaseType(DatabaseType.JSON);
        } catch (IOException var5) {
            JOptionPane.showMessageDialog((Component)null, "Something went wrong. Please restart application");
            ws = null;
        }

        return ws;
    }
	
	
	/**
	 * Method for saving Sistem on disk on predefines path in Sistem object.
	 * @param s Sistem to be save.
	 */
	public static void saveStructure(Sistem s) {
        ObjectMapper mapper = new ObjectMapper();
        String path = s.getPath();

        try {
            File file = new File(path);
            file.delete();
        } catch (Exception e) {
            
        }

        try {
            mapper.writeValue(new File(path), s);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog((Component)null, "Something went wrong. Changes are not saved.");
            return;
        }

    }
	
	/**
	 * Method that saves any object into JSON.
	 * @param s Object to be saved.
	 */
	public static void saveObj(Object s) {
        ObjectMapper mapper = new ObjectMapper();
        String path = "";
      //Nina dodaje, za odabir lokacije ako nema upisanu lokaciju
        if(Sistem.getInstance().getPath()==null || Sistem.getInstance().getPath().isEmpty()){
	        JsonSaveAs jsa = new JsonSaveAs();
	        path = jsa.saveAs();
	        if(path=="")
	        	return;
        }
        else{
        	path = Sistem.getInstance().getPath();
        }
        try {
            File file = new File(path);
            file.delete();
        } catch (Exception e) {
            
        }

        try {
            mapper.writeValue(new File(path), s);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog((Component)null, "Something went wrong. Changes are not saved.");
            return;
        }
        JOptionPane.showMessageDialog((Component)null, "File is saved succesfully",  "",
        	    JOptionPane.PLAIN_MESSAGE);
    }

	/**
	 * Methot that converts Sistem model into Stirng via JSON serialization. 
	 * @return String in JSON format.
	 */
	public static String getJsonText() {
		String tekst = "";
		String loc = System.getProperty("user.dir")+"\\"+"src\\preview.json";
		Sistem s = Sistem.getInstance();
	
		
	//upis u json-a
		ObjectMapper mapper = new ObjectMapper();
		try {
            File file = new File(loc);
            file.delete();
        } catch (Exception e) {            
        }
		try {
            mapper.writeValue(new File(loc), s);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog((Component)null, "Something went wrong. Preview not working.");
        }
		
	
		
	//ucitavanje json-a kao txt fajla
		try (BufferedReader br = new BufferedReader(new FileReader(loc))) {
			String line;
			while ((line = br.readLine()) != null) {
				tekst+=line;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonParser jp = new JsonParser();
		JsonElement je = jp.parse(tekst);
		String prettyJsonString = gson.toJson(je);
		
		return prettyJsonString;
	}
	
	/**
	 * Returns formated JSON in as a String.
	 * @param path Path from which the Sistem should be loaded.
	 * @return JSON in a string format.
	 */
	public static String getJsonText(String loc) {
		String tekst = "";
		
		Sistem s = Sistem.getInstance();
	
		
	//upis u json-a
		ObjectMapper mapper = new ObjectMapper();
		try {
            File file = new File(loc);
            file.delete();
        } catch (Exception e) {            
        }
		try {
            mapper.writeValue(new File(loc), s);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog((Component)null, "Something went wrong. Preview not working.");
        }
		
	
		
	//ucitavanje json-a kao txt fajla
		try (BufferedReader br = new BufferedReader(new FileReader(loc))) {
			String line;
			while ((line = br.readLine()) != null) {
				tekst+=line;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonParser jp = new JsonParser();
		JsonElement je = jp.parse(tekst);
		String prettyJsonString = gson.toJson(je);
		
		return prettyJsonString;
	}
}
