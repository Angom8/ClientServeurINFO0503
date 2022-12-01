<?php
require_once('Commandes/Client.php');
header("Content-type: application/json");

$jsonData = file_get_contents("php://input");
$data = json_decode($jsonData, true);

if(array_key_exists ('nom', $data) && array_key_exists('prenom', $data)) {

    //Récupération des clients
    $json = file_get_contents("Datas/Client.json");

    $clients = json_decode($json, true);
    $max = 0;
    //On détermine l'ID à utiliser pour stocker le nouveau client
    foreach($clients as $c){
        if($c['id'] >= $max){
            $max = $c['id'] + 1;
        }
    }

    //On crée puis ajoute ce dit client
    $client = new Client(htmlentities($data['nom']), htmlentities($data['prenom']), $max);
    array_push($clients, $client->jsonSerialize());
    $back = json_encode($clients);

    //On replace le json
    file_put_contents("Datas/Client.json", $back);

    $tableau = [ "code" => "OK", "message" => "insertion réussie" ];

}
else
    $tableau = [ "code" => "erreur", "message" => "données manquantes." ];

echo json_encode($tableau);


?>