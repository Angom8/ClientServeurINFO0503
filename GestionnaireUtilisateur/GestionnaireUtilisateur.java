import org.json.JSONArray;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;;
import java.util.HashMap;

/**
 * Classe correspondant au Gestionnaire des utilisateurs / users
 * @author Antoine THEBAULT
 */

public class GestionnaireUtilisateur {

    private HashMap<Integer, User> users;

    /*
    Constructeur prenant en paramètre l'emplacement du User.json
     */
    public GestionnaireUtilisateur(String filepath) {

        String json = "";
        this.users = new HashMap<Integer, User>();
        try {
            byte[] contenu = Files.readAllBytes(Paths.get(filepath));
            json = new String(contenu);
            JSONArray object = new JSONArray(json);
            User u;int i;
            for (i = 0; i < object.length(); i++) {
                u = new User(object.getJSONObject(i));
                users.put(i, u);
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture du fichier '" + filepath + "'");
            System.exit(0);
        }

    }

    /*
    Non utilisé
     */
    public void addUser(User u) {
        users.put(users.size(), u);
    }

    /*
    Détermine si un User est valide
     */
    public boolean isValid(User u) {
        boolean r = false;
        int i = 0;
        while (users.containsKey(i) & !r) {
            if (users.get(i).getUsername().equals(u.getUsername())) {
                if (users.get(i).getPassword().equals(u.getPassword())) {
                    r = true;
                }
            }
            i++;
        }

        return r;
    }

    /*
    Retourne le type de l'utilisateur
     */
    public int getType(User u) {
        int r = 0;
        int i = 0;
        while (users.containsKey(i)) {
            if (users.get(i).getUsername().equals(u.getUsername())) {
                if (users.get(i).getPassword().equals(u.getPassword())) {
                    r = users.get(i).getType();
                }
            }
            i++;
        }

        return r;
    }

    /*
    Retourune JSONArray de l'ensemble des utilisateurs
     */
    public JSONArray toJSON() {

        JSONArray output = new JSONArray();
        int i = 0;
        while(users.containsKey(i)) {
            output.put(i , users.get(i).toJSON());
            i++;
        }
        return output;
    }

    /*
    Non utilisé
     */
    public void save(){
        JSONArray output = this.toJSON();
        FileWriter fs = null;
        try {
            fs = new FileWriter("Users.json");
            output.write(fs, 3, 0);
            fs.flush();
        } catch(IOException e) {
            System.err.println("Erreur lors de l'ouverture ou l'écriture du fichier '" + users + "'.");
            System.err.println(e);System.exit(0);
        }
    }
}
