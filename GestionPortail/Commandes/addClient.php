<?php
require_once('../Models/User.php');
require_once('../Models/MiscFunctions.php');
session_start();

if(isset($_SESSION['user']) && $_SESSION['user']->getType() == 1){

    if(isset($_POST['nom']) && isset($_POST['prenom'])){

        // Préparation de la requête
        $options = [
            'http' =>
                [
                    'method'  => 'POST',
                    'header'  => 'Content-type: application/json',
                    'content' => json_encode(['nom' => htmlentities($_POST['nom']), 'prenom' => htmlentities($_POST['prenom'])])
                ]
        ];


        // Envoi de la requête et lecture du JSON reçu
        $URL = COMMANDES_SERVER_URL."addClient.php";
        $contexte  = stream_context_create($options);

        if(($jsonTexte = @file_get_contents($URL, false, $contexte)) !== false) {
            // Analyse du JSON reçu
            $tableau = json_decode($jsonTexte, true);
            if($tableau['code'] == "OK")
                header('Location: '.WEBSITE_URL_COMMANDES.'createClient.php?valid=true');
            else
                header('Location: '.WEBSITE_URL_COMMANDES.'createClient.php?invalid=true');
        }
        else
            header('Location: '.WEBSITE_URL_COMMANDES.'createClient.php?invalid=true');

    }
    else{
        header('Location: '.WEBSITE_URL_COMMANDES.'createClient.php?invalid=true');
    }


}
else{
    header('Location: '.WEBSITE_URL);
    exit();
}
?>