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

public class Gestionnaire_Parking {
/*
    private HashMap<Integer, Parking> parkings;
    private HashMap<Integer, Place_Parking> places;

    public Gestionnaire_Parking(){
        parkings = new HashMap<Integer, Parking>();
        places = new HashMap<Integer, Place_Parking>();
        int j;

        String json = "";
        String filepath1 = System.getProperty("user.dir") + "/src/Parking.json";
        String filepath2 = System.getProperty("user.dir") + "/src/Place_Parking.json";

        try {
            byte[] contenu = Files.readAllBytes(Paths.get(filepath2));
            json = new String(contenu);
            JSONArray object = new JSONArray(json);
            Place_Parking pp;int i;
            for (i = 0; i < object.length(); i++) {
                pp = new Place_Parking(object.getJSONObject(i), object.getJSONObject(i).getInt("id"));
                places.put(pp.getId(), pp);
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture du fichier '" + filepath2 + "'");
            System.exit(0);
        }

        try {
            byte[] contenu = Files.readAllBytes(Paths.get(filepath1));
            json = new String(contenu);
            JSONArray object = new JSONArray(json);
            Parking p;int i;
            JSONArray tmp;
            for (i = 0; i < object.length(); i++) {
                p = new Parking(object.getJSONObject(i).getInt("numero"));
                tmp = object.getJSONObject(i).getJSONArray("places");
                for(j = 0; j < tmp.length(); j++){
                    p.addPlace(getPlace(tmp.getInt(j)));
                    getPlace(tmp.getInt(j)).setParking(p);
                }

                parkings.put(p.getNumero(), p);

            }
        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture du fichier '" + filepath1 + "'");
            System.exit(0);
        }
    }

    public Place_Parking getPlace(int id){
        return this.places.get(id);
    }

    public void addPlace(JSONObject data){
        int i = 0;
        while(options.containsKey(i)) {
            i++;
        }
        System.out.println("Ajout de l'option d'id : " + i);
        Option_Voiture o = new Option_Voiture(data, i);
        options.put(o.getId(),o);
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
*/
}
