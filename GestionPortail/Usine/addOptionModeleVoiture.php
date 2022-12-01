<?php

require_once('../Models/User.php');
require_once('../Models/MiscFunctions.php');
session_start();

if (isset($_SESSION['user']) && $_SESSION['user']->getType() >= 2) {

    if (isset($_POST['id'])   && isset($_POST['options'])) {

        $data = [
            "action" => 12,
            "datas" => ['id_modele' => intval(htmlentities($_POST['id'])),
                'id_option' => intval(htmlentities($_POST['options'])),
            ]
        ];

        // Préparation de la requête
        $options = [
            'http' =>
                [
                    'method' => 'POST',
                    'header' => 'Content-type: application/json',
                    'content' => json_encode($data)
                ]
        ];

        $URL = USINE_SERVER_URL . $_SESSION["portUsine"]."/";

        $contexte = stream_context_create($options);

        if (($jsonTexte = @file_get_contents($URL, false, $contexte)) !== false) {
            // Analyse du JSON reçu
            $tableau = json_decode($jsonTexte, true);
            if ($tableau['code'] > 0)
                header('Location: ' . WEBSITE_URL_USINE . 'listModelesVoiture.php?valid=true');
            else
                header('Location: '. WEBSITE_URL_USINE . 'listModelesVoiture.php?invalid=true');
        } else
            header('Location: ' . WEBSITE_URL_USINE . 'listModelesVoiture.php?invalid=true');

    } else {
        header('Location: ' . WEBSITE_URL_USINE . 'listModelesVoiture.php?invalid=true');
    }


} else{
    header('Location: '.WEBSITE_URL);
    exit();
}
?>