import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Classe correspondant au handler sur le contexte 'index.html'.
 * @author Cyril Rabat
 * @author Antoine THEBAULT
 */

public class HomeHandler implements HttpHandler {
    public void handle(HttpExchange t) {

        String reponse = "Ceci est un serveur de connexion...";
        // Envoi de l'en-tete Http
        try {
            Headers h = t.getResponseHeaders();
            h.set("Content-Type", "text/html; charset=utf-8");
            t.sendResponseHeaders(200, reponse.length());
        }
        catch(IOException e) {
            System.err.println("Erreur lors de l'envoi de l'en-tete : " + e);
        }

        // Envoi du corps (donnees HTML)
        try {
            OutputStream os = t.getResponseBody();
            os.write(reponse.getBytes());
            os.close();
        }
        catch(IOException e) {
            System.err.println("Erreur lors de l'envoi du corps : " + e);
        }
    }
}
