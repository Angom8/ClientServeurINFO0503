<?php
require_once('../Models/User.php');
require_once('../Models/MiscFunctions.php');
session_start();

if(isset($_SESSION['user']) && $_SESSION['user']->getType() == 1){

    if(isset($_POST['id'])){

        // Préparation de la requête
        $options = [
            'http' =>
                [
                    'method'  => 'POST',
                    'header'  => 'Content-type: application/json',
                    'content' => json_encode(['id' => htmlentities($_POST['id'])])
                ]
        ];

        $URL = COMMANDES_SERVER_URL."delClient.php";
        $contexte  = stream_context_create($options);

        if(($jsonTexte = @file_get_contents($URL, false, $contexte)) !== false) {
            // Analyse du JSON reçu
            $tableau = json_decode($jsonTexte, true);
            if($tableau['code'] == "OK")
                header('Location: '.WEBSITE_URL_COMMANDES.'listClients.php?valid=true');
            else
                header('Location: '.WEBSITE_URL_COMMANDES.'listClients.php?invalid=true');
        }
        else
            header('Location: '.WEBSITE_URL_COMMANDES.'listClients.php?invalid=true');

    }
    else{
        header('Location: '.WEBSITE_URL_COMMANDES.'listClients.php?invalid=true');
    }


}
else{
    header('Location: '.WEBSITE_URL);
    exit();
}
?>