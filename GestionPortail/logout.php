<?php
require_once('Models/ServerConfig.php');
    session_unset();
    header('Location: '.WEBSITE_URL);
    exit();
?>