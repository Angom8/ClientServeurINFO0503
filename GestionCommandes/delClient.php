<?php

require_once('Commandes/Client.php');
header("Content-type: application/json");

$jsonData = file_get_contents("php://input");
$data = json_decode($jsonData, true);

if (array_key_exists('id', $data)) {

    //Récupération des clients
    $json = file_get_contents("Datas/Client.json");
    $clients = json_decode($json, true);

    //On recherche le client à supprimer
    $i=0;

    foreach ($clients as $c) {
        if ($c['id'] == $data['id']) {
           break;
        }
        $i++;
    }
    unset($clients[$i]);

    //On replace les clients
    $back = json_encode($clients);

    file_put_contents("Datas/Client.json", $back);

    $tableau = ["code" => "OK", "message" => "Suppression réussie"];

} else
    $tableau = ["code" => "erreur", "message" => "données manquantes."];

echo json_encode($tableau);


