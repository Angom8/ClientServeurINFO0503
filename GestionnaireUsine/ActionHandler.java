import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.Headers;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.GenericArrayType;
import java.net.URI;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Classe correspondant au handler sur le contexte 'index.html'.
 * @author Cyril Rabat
 */

public class ActionHandler implements HttpHandler {
    public void handle(HttpExchange t) {

        System.out.println("Traitement d'une demande de connexion ... ");

        JSONObject objet = new JSONObject("{\"code\" : -1}");
        String query = null;

        // Utilisation d'un flux pour lire les donnees du message Http
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(t.getRequestBody(), "utf-8"));
        } catch (UnsupportedEncodingException e) {
            System.err.println("Erreur lors de la recuperation du flux " + e);
        }

        // Recuperation des donnees en POST
        try {
            query = br.readLine();
        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture d'une ligne " + e);
        }
        System.out.println("Query : " + query);

        // Affichage des donnees
        if (query != null) {
            try {
                query = URLDecoder.decode(query, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                query = null;
            }

            JSONObject reponse = new JSONObject(query);
            System.out.println("Analysed query : " + reponse.toString(3));
            Boolean err;
            Gestionnaire_Option_Voiture go;
            Gestionnaire_Modele_Moteur gm;
            Gestionnaire_Modele_Voiture gmv;

            switch (reponse.getInt("action")) {

                case 0: //Ajout véhicule

                    break;


                case 2: //Ajout d'un modèle de moteur

                    System.out.println("Ajout d'un modèle de moteur");
                    gm = new Gestionnaire_Modele_Moteur();
                    System.out.println("Génération du gestionnaire terminée");

                    gm.addModele(reponse.getJSONObject("datas"));
                    objet = new JSONObject("{\"code\" : 1}");
                    break;

                case 3: //Suppression d'un modèle de moteur

                    System.out.println("Suppression d'un modèle de moteur");
                    gm = new Gestionnaire_Modele_Moteur();
                    System.out.println("Génération du gestionnaire terminée");

                    gm.delModele(reponse.getJSONObject("datas"));
                    objet = new JSONObject("[{\"code\" : 1}");
                    break;


                case 4: //Ajout d'un modèle de voiture

                    System.out.println("Ajout d'un modèle de voiture");

                    go = new Gestionnaire_Option_Voiture();
                    gm = new Gestionnaire_Modele_Moteur();

                    gmv = new Gestionnaire_Modele_Voiture(go, gm);

                    System.out.println("Génération du gestionnaire terminée");

                    gmv.addModele(reponse.getJSONObject("datas"));

                    objet = new JSONObject("{\"code\" : 1}");

                    break;
                case 5: //Suppression Modèle Voiture

                    System.out.println("Suppression d'un modèle de voiture");

                    go = new Gestionnaire_Option_Voiture();
                    gm = new Gestionnaire_Modele_Moteur();

                    gmv = new Gestionnaire_Modele_Voiture(go, gm);

                    System.out.println("Génération du gestionnaire terminée");

                    gmv.delModele(reponse.getJSONObject("datas"));
                    objet = new JSONObject("{\"code\" : 1}");

                    break;


                case 6://Ajout Option

                    System.out.println("Ajout d'une option");
                    go = new Gestionnaire_Option_Voiture();
                    System.out.println("Génération du gestionnaire terminée");

                    go.addOption(reponse.getJSONObject("datas"));
                    objet = new JSONObject("{\"code\" : 1}");
                    break;

                case 7: //Suppression Option

                    System.out.println("Suppression d'une option");
                    go = new Gestionnaire_Option_Voiture();
                    System.out.println("Génération du gestionnaire terminée");

                    go.delOption(reponse.getJSONObject("datas"));
                    objet = new JSONObject("[{\"code\" : 1}");
                    break;

                case 8: //Retrieve Modèles

                    System.out.println("Liste des modeles");
                    gm = new Gestionnaire_Modele_Moteur();
                    System.out.println("Génération du gestionnaire terminée");
                    objet = new JSONObject("{\"code\" : 1, \"datas\" :" + gm.getJSON().toString() + "}");

                    break;

                case 9:
                    System.out.println("Liste des options");
                    go = new Gestionnaire_Option_Voiture();
                    System.out.println("Génération du gestionnaire terminée");
                    objet = new JSONObject("{\"code\" : 1, \"datas\" :" + go.getJSON().toString() + "}");
                    //Retrieve Options JSON
                    break;

                case 10:
                    System.out.println("Liste des modeles");
                    go = new Gestionnaire_Option_Voiture();
                    gm = new Gestionnaire_Modele_Moteur();

                    gmv = new Gestionnaire_Modele_Voiture(go, gm);

                    System.out.println("Génération du gestionnaire terminée");
                    objet = new JSONObject("{\"code\" : 1, \"datas\" :" + gmv.getSpecialJSON().toString() + "}");

                    break;
                case 11:
                    //Construction des JSON avec parking to JSON
                    //Obtention des parkings
                    break;
                case 12:
                    //Ajout option à un modèle
                    System.out.println("Ajout d'une option à un modèle");
                    go = new Gestionnaire_Option_Voiture();
                    gm = new Gestionnaire_Modele_Moteur();

                    gmv = new Gestionnaire_Modele_Voiture(go, gm);

                    gmv.addOptionModele(reponse.getJSONObject("datas"));
                    objet = new JSONObject("[{\"code\" : 1}");

                default:
                    objet = new JSONObject("{\"code\" : -1, \"message\" : \"Erreur lors du traitement de l'action\" }");
                    break;
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