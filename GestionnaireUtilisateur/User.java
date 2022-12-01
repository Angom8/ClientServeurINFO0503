import org.json.JSONObject;

/**
 * Classe correspondant à la classe Utilisateur
 * @author Antoine THEBAULT
 */

public class User {

    private String username;
    private String password;
    private int userType;

    public User(String username, String password){
        this.password = password;
        this.username = username;
        this.userType = 0;
    }

    public User(JSONObject obj){
        this.password = obj.getString("password");
        this.username = obj.getString("username");
        this.userType = obj.getInt("userType");
    }


    public String getUsername(){
        return this.username;
    }
    public String getPassword(){
        return this.password;
    }
    public int getType(){return this.userType;}

    public void setType(int t){this.userType = t;}

    public JSONObject toJSON(){

        JSONObject output = new JSONObject();

        output.put("username", getUsername());
        output.put("password", getPassword());
        output.put("userType", getType());
        return output;
    }

}
