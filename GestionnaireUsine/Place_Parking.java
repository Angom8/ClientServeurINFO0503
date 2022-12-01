import org.json.JSONObject;

public class Place_Parking {

    private Voiture voiture;
    private int numero;
    private String rang;
    private Parking parking;
    private int id;


    public Place_Parking(int id, int numero, String rang){
        this.id = id;
        this.numero = numero;
        this.rang = rang;
        this.voiture = null;
        this.parking = null;
    }

    public Place_Parking(JSONObject obj, int id){
        this.id= id;
        this.numero = obj.getInt("numero");
        this.rang = obj.getString("rang");
        this.voiture = null;
        this.parking = null;
    }

    public boolean empty(){
        boolean r = false;
        if(! isEmpty()){
            this.voiture = null;
            r = true;
        }
        return r;
    }
    public int getId(){
        return this.id;
    }

    public void setParking(Parking p){
        this.parking = p;
    }

    public boolean isEmpty(){
        return this.voiture ==  null;
    }

    public void addVoiture(Voiture v){
        this.voiture = v;
    }
    public Voiture getVoiture(){return this.voiture;}

    public int getNumero(){return numero;}
    public String getRang(){return rang;}
}
