import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Gestionnaire_Option_Voiture {

    private HashMap<Integer, Option_Voiture> options;

    public Gestionnaire_Option_Voiture(){
        options = new HashMap<Integer, Option_Voiture>();
        String json = "";
        String filepath = System.getProperty("user.dir") + "/src/Option_Voiture.json";
        try {
            byte[] contenu = Files.readAllBytes(Paths.get(filepath));
            json = new String(contenu);
            JSONArray object = new JSONArray(json);
            Option_Voiture o;int i;
            for (i = 0; i < object.length(); i++) {
                o = new Option_Voiture(object.getJSONObject(i), object.getJSONObject(i).getInt("id"));
                options.put(o.getId(), o);
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture du fichier '" + filepath + "'");
            System.exit(0);
        }
    }

    public Option_Voiture getOption(int id){
        return this.options.get(id);
    }

    public void addOption(JSONObject data){
        int i = 0;
        while(options.containsKey(i)) {
            i++;
        }
        System.out.println("Ajout de l'option d'id : " + i);
        Option_Voiture o = new Option_Voiture(data, i);
        options.put(o.getId(),o);
        save();
    }

    public void delOption(JSONObject data){
        options.remove(data.getInt("id"));
        System.out.println("Retrait de l'option d'id : " + data.getInt("id"));
        save();
    }

   private void save(){
        JSONArray output = new JSONArray();
        JSONObject obj;

       System.out.println("Sauvegarde des options...");

       for (Map.Entry<Integer, Option_Voiture> pair : options.entrySet()) {
           obj = pair.getValue().toJSON();
           output.put(obj);
       }
       String filepath = System.getProperty("user.dir") + "/src/Option_Voiture.json";

       File file = new File(filepath);

       try {
           if (!file.exists())
               file.createNewFile();
           FileWriter writer = new FileWriter(file);
           writer.write(output.toString());
           writer.flush();
           writer.close();
       } catch (IOException e) {
           System.out.println("Erreur: impossible de créer le fichier '"
                   + filepath + "'");
       }

       System.out.println("Sauvegarde terminée !");

   }

    //lecture du fichier contenant tous les objets de la classe
    public JSONArray getJSON(){
        JSONArray output = new JSONArray();
        JSONObject obj;

        for (Map.Entry<Integer, Option_Voiture> pair : options.entrySet()) {
            obj = pair.getValue().toJSON();
            output.put(obj);
        }

        return output;
    }

}
