import org.json.JSONObject;

public class Modele_Moteur {

    private int id;
    private double puissance;
    private Type_Moteur_Enum carburation;

    public Modele_Moteur(double puissance, Type_Moteur_Enum carburation, int id){
        this.id = id;
        this.puissance = puissance;
        this.carburation = carburation;
    }

    public Modele_Moteur(JSONObject obj, int id){
        this.id= id;
        this.puissance = obj.getDouble("puissance");
        this.carburation = Type_Moteur_Enum.valueOf(obj.getString("carburation"));
    }

    public double getPuissance(){
        return this.puissance;
    }

    public int getId(){
        return this.id;
    }

    public Type_Moteur_Enum getCarburation(){
        return this.carburation;
    }

    public JSONObject toJSON(){
        JSONObject output = new JSONObject();

        output.put("puissance", getPuissance());
        output.put("id", getId());
        output.put("carburation", getCarburation().toString());

        return output;
    }


}
