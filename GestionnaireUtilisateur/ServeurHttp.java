import java.io.IOException;
import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;

/**
 * Classe correspondant à un serveur Http simple.
 * Le serveur écoute sur le port 8082 sur le contexte 'login'.
 * @author Cyril Rabat
 * @author Antoine THEBAULT
 */

public class ServeurHttp {
    public static void main(String[] args) {

        HttpServer serveur = null;
        try {
            //Modifier ici en cas de changement de port
            serveur = HttpServer.create(new InetSocketAddress(8082), 0);
        } catch(IOException e) {
            System.err.println("Erreur lors de la création du serveur " + e);
            System.exit(0);
        }

        serveur.createContext("/", new HomeHandler());
        serveur.createContext("/login", new LoginHandler());
        serveur.setExecutor(null);
        serveur.start();

        System.out.println("Serveur démarré. Pressez CRTL+C pour arrêter.");

    }
}
