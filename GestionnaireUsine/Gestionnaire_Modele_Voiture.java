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

public class Gestionnaire_Modele_Voiture{

    private HashMap<Integer, Modele_Voiture> modeles;
    private Gestionnaire_Option_Voiture go;
    private Gestionnaire_Modele_Moteur gm;

    public Gestionnaire_Modele_Voiture(Gestionnaire_Option_Voiture go, Gestionnaire_Modele_Moteur gm){
        this.go = go;
        this.gm = gm;
        modeles = new HashMap<Integer, Modele_Voiture>();
        String json = "";
        String filepath = System.getProperty("user.dir") + "/src/Modele_Voiture.json";
        try {
            byte[] contenu = Files.readAllBytes(Paths.get(filepath));
            json = new String(contenu);
            JSONArray object = new JSONArray(json);
            Modele_Voiture mv;int i;int j;
            JSONArray tmp;
            for (i = 0; i < object.length(); i++) {
                mv = new Modele_Voiture(object.getJSONObject(i), object.getJSONObject(i).getInt("id"));

                mv.setModele(gm.getModele(object.getJSONObject(i).getInt("modele")));

                tmp = object.getJSONObject(i).getJSONArray("options");
                for(j = 0; j < tmp.length(); j++){
                    mv.addOption(go.getOption(tmp.getInt(j)));
                }

                modeles.put(mv.getId(), mv);
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture du fichier '" + filepath + "'");
            System.exit(0);
        }
    }

    public void addModele(JSONObject data){
        int i = 0;
        JSONArray tmp;
        while(modeles.containsKey(i)) {
            i++;
        }
        System.out.println("Ajout du modele d'id : " + i);
        Modele_Voiture mv = new Modele_Voiture(data, i);

        mv.setModele(gm.getModele(Integer.parseInt(data.getString("modele"))));

        System.out.println("Modele détecté : " + mv.getModele().getId());


        mv.addOption(go.getOption(Integer.parseInt(data.getString("options"))));

        System.out.println("Option détectée : " + go.getOption(Integer.parseInt(data.getString("options"))).getNom());


        modeles.put(mv.getId(),mv);
        save();
    }

    public void delModele(JSONObject data){

        modeles.remove(data.getInt("id"));
        System.out.println("Retrait du modele d'id : " + data.getInt("id"));
        save();

    }

    private void save(){
        JSONArray output = new JSONArray();
        JSONObject obj;

        System.out.println("Sauvegarde des modeles...");

        for (Map.Entry<Integer, Modele_Voiture> pair : modeles.entrySet()) {
            obj = pair.getValue().toJSON();
            output.put(obj);
        }
        String filepath = System.getProperty("user.dir") + "/src/Modele_Voiture.json";

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

    public void addOptionModele(JSONObject data){
        this.modeles.get(data.getInt("id_modele")).addOption(go.getOption(data.getInt("id_option")));
        save();
    }

    //lecture du fichier contenant tous les objets de la classe
    public JSONArray getJSON(){
        JSONArray output = new JSONArray();
        JSONObject obj;

        for (Map.Entry<Integer, Modele_Voiture> pair : modeles.entrySet()) {
            obj = pair.getValue().toJSON();
            output.put(obj);
        }

        return output;
    }

    //lecture du fichier contenant tous les objets de la classe
    public JSONArray getSpecialJSON(){
        JSONArray output = new JSONArray();
        JSONObject obj;

        for (Map.Entry<Integer, Modele_Voiture> pair : modeles.entrySet()) {
            obj = pair.getValue().toSpecialJSON();
            output.put(obj);
        }

        return output;
    }

}
