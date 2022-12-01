<?php
require_once('Commandes/Client.php');
header("Content-type: application/json");

$json = file_get_contents("Datas/Client.json");
echo $json;
?>