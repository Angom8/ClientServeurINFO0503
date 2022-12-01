import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.Headers;
import org.json.JSONObject;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLDecoder;

/**
 * Classe correspondant au code d'execution du serveur HTTP
 * @author Cyril Rabat
 * @author Antoine THEBAULT
 */

public class LoginHandler implements HttpHandler {
    public void handle(HttpExchange t) {

        System.out.println("Traitement d'une demande de connexion ... ");
        JSONObject objet = new JSONObject(" { \"userType\" : -1 }");
        String query = null;

        // Utilisation d'un flux pour lire les donnees du message Http
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(t.getRequestBody(),"utf-8"));
        }
        catch(UnsupportedEncodingException e) {
            System.err.println("Erreur lors de la recuperation du flux " + e);
        }

        // Recuperation des donnees en POST
        try {
            query = br.readLine();
        }
        catch(IOException e) {
            System.err.println("Erreur lors de la lecture d'une ligne " + e);
        }
        System.out.println("Query : " + query);


        // Traitement des donnees
        if(query != null) {
            try {
                query = URLDecoder.decode(query, "UTF-8");
            }
            catch(UnsupportedEncodingException e) {
                query = null;
            }

            JSONObject reponse = new JSONObject(query);

            System.out.println("Analysed query : " + reponse.toString(3));

            User u = new User(reponse.getString("username"), reponse.getString("password"));

            GestionnaireUtilisateur gu = new GestionnaireUtilisateur(System.getProperty("user.dir") + "/src/Users.json");

            if(gu.isValid(u)){
                u.setType(gu.getType(u));
                objet = u.toJSON();
            }
        }

        System.out.println("Objet envoyé : " + objet.toString(3));

        // Envoi de l'en-tete Http
        try {
            Headers h = t.getResponseHeaders();
            h.set("Content-Type", "application/json; charset=utf-8");
            t.sendResponseHeaders(200, objet.toString().length());
        }
        catch(IOException e) {
            System.err.println("Erreur lors de l'envoi de l'en-tete : " + e);
        }

        // Envoi du corps (donnees HTML)
        try {
            OutputStream os = t.getResponseBody();
            os.write(objet.toString().getBytes());
            os.close();
        }
        catch(IOException e) {
            System.err.println("Erreur lors de l'envoi du corps : " + e);
        }
    }


}
