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

public class Gestionnaire_Modele_Moteur{

    private HashMap<Integer, Modele_Moteur> modeles;

    public Gestionnaire_Modele_Moteur(){
        modeles = new HashMap<Integer, Modele_Moteur>();
        String json = "";
        String filepath = System.getProperty("user.dir") + "/src/Modele_Moteur.json";
        try {
            byte[] contenu = Files.readAllBytes(Paths.get(filepath));
            json = new String(contenu);
            JSONArray object = new JSONArray(json);
            Modele_Moteur m;int i;
            for (i = 0; i < object.length(); i++) {
                m = new Modele_Moteur(object.getJSONObject(i), object.getJSONObject(i).getInt("id"));
                modeles.put(m.getId(), m);
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture du fichier '" + filepath + "'");
            System.exit(0);
        }
    }

    public Modele_Moteur getModele(int id){
        return this.modeles.get(id);
    }

    public void addModele(JSONObject data){
        int i = 0;
        while(modeles.containsKey(i)) {
            i++;
        }
        System.out.println("Ajout du modele d'id : " + i);
        Modele_Moteur m = new Modele_Moteur(data, i);
        modeles.put(m.getId(),m);
        save();
    }

    public void delModele(JSONObject data){
        modeles.remove(data.getInt("id"));
        System.out.println("Retrait de du modele d'id : " + data.getInt("id"));
        save();
    }

    private void save(){
        JSONArray output = new JSONArray();
        JSONObject obj;

        System.out.println("Sauvegarde des modeles...");

        for (Map.Entry<Integer, Modele_Moteur> pair : modeles.entrySet()) {
            obj = pair.getValue().toJSON();
            output.put(obj);
        }
        String filepath = System.getProperty("user.dir") + "/src/Modele_Moteur.json";

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

        for (Map.Entry<Integer, Modele_Moteur> pair : modeles.entrySet()) {
            obj = pair.getValue().toJSON();
            output.put(obj);
        }

        return output;
    }

}
