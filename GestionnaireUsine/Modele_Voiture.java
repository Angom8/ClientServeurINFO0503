import org.json.JSONObject;

import java.util.Iterator;
import java.util.Vector;

public class Modele_Voiture {

    private Color_Enum couleur;
    private String nom;
    private Vector<Option_Voiture> options;
    private Modele_Moteur modele;
    private int id;

    public Modele_Voiture(Color_Enum couleur, String nom, Modele_Moteur modele, int id){
        this.couleur = couleur;
        this.options = new Vector<Option_Voiture>();
        this.nom = nom;
        this.modele = modele;
        this.id = id;
    }

    public Modele_Voiture(JSONObject obj, int id){
        this.id = id;
        this.nom = obj.getString("nom");
        this.couleur = Color_Enum.valueOf(obj.getString("couleur"));
        this.modele = null;
        this.options = new Vector<Option_Voiture>();
    }

    public Color_Enum getCouleur() {
        return couleur;
    }

    public String getNom() {
        return nom;
    }

    public Modele_Moteur getModele() {
        return modele;
    }

    public void setModele(Modele_Moteur modele){
        this.modele = modele;
    }

    public int getId() {
        return id;
    }

    public boolean addOption(Option_Voiture op){
        boolean r = true;

        if(this.options.contains(op)){
            r = false;
        }
        else{
            this.options.addElement(op);
        }

        return r;
    }

    public boolean removeOption(int id){
        boolean r = false;
        Option_Voiture o;
        Iterator<Option_Voiture> it = this.options.iterator();

        while(it.hasNext()){
            if((o = it.next()).getId() == id){
                this.options.remove(o);
                r = true;
                break;
            }
        }
        return r;
    }

    public JSONObject toJSON(){
        JSONObject output = new JSONObject();
        int[] tab_options = new int[this.options.size()];
        int i = 0;


        Iterator<Option_Voiture> it = this.options.iterator();

        while(it.hasNext()){
           tab_options[i] = it.next().getId();
            i++;
        }

        output.put("couleur", getCouleur().toString());
        output.put("id", getId());
        output.put("nom", getNom());
        output.put("modele", modele.getId());
        output.put("options", tab_options);


        return output;
    }

    public JSONObject toSpecialJSON(){
        JSONObject output = new JSONObject();
        String[] tab_options = new String[this.options.size()];
        int i = 0;


        Iterator<Option_Voiture> it = this.options.iterator();

        while(it.hasNext()){
            tab_options[i] = it.next().getNom();
            i++;
        }

        output.put("couleur", getCouleur().toString());
        output.put("id", getId());
        output.put("nom", getNom());
        output.put("modele", modele.getCarburation().toString());
        output.put("options", tab_options);


        return output;
    }


    public String toString(){
        return this.nom + " " + this.couleur + " " + this.modele.toJSON().toString() + " " + this.options.size();

    }
    //faire un equals

}
