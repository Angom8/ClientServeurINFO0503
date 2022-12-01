import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class Option_Voiture {

    private String nom;
    private int id;

    public Option_Voiture(String nom, int id){
        this.id = id;
        this.nom = nom;
    }

    public Option_Voiture(JSONObject obj, int id){
        this.id= id;
        this.nom = obj.getString("nom");
    }


    public String getNom(){
        return this.nom;
    }

    public int getId(){
        return this.id;
    }

    public JSONObject toJSON(){
        JSONObject output = new JSONObject();

        output.put("nom", getNom());
        output.put("id", getId());

        return output;
    }


}
