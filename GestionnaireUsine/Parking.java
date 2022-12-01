import org.json.JSONObject;

import java.util.Vector;

public class Parking {

    private Vector<Place_Parking> places;
    private int numero;

    public Parking(int numero){
        this.numero = numero;
        this.places = new Vector<>();
    }

    public int getNumero(){return this.numero;}

    public void addPlace(Place_Parking pp){
        this.places.addElement(pp);
    }

    public JSONObject toJSON(){
        return new JSONObject();
    }
}
