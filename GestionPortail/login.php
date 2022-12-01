<?php
    require_once('Models/User.php');
    require_once('Models/ServerConfig.php');
    session_start();

    if(isset($_POST['username']) && isset($_POST['password'])) {
        // Préparation de la requête
        $options = [
            'http' => [  'method' => 'POST',
                        'header' => 'Content-type: application/json',
                        'content' => json_encode(
                            [
                                'username' => htmlentities($_POST['username']),
                                'password' => htmlentities($_POST['password'])
                            ])
                    ]
        ];

        // Envoi de la requête et lecture du JSON reçu
        $URL = LOGIN_SERVER_URL;
        print_r($URL);
        $contexte = stream_context_create($options);

        if (($response = @file_get_contents($URL, false, $contexte)) !== false) {
            // Analyse du JSON reçu
            $userInfo = json_decode($response, true);

            $_SESSION['user'] = new User($userInfo['username'],
                $userInfo['password'],
                $userInfo['userType'],
            );
            if ($userInfo['userType'] > -1) {
                if ($_SESSION['user']->getType() >= 2) {
                    $_SESSION['portUsine'] = USINE_BASE_PORT + $_SESSION['user']->getType() - 2;
                    header('Location: '.WEBSITE_URL_USINE);
                } else if ($_SESSION['user']->getType() == 1) {
                    header('Location: '.WEBSITE_URL.'Commandes/');
                    exit();
                } else if ($_SESSION['user']->getType() == 0) {
                    header('Location: '.WEBSITE_URL.'External/');
                    exit();
                } else{
                    header('Location: '.WEBSITE_URL);
                    exit();
                }
            }
        } else {
            echo "<p>Une erreur est survenue lors de la récupération des données.</p>";
        }
    }
