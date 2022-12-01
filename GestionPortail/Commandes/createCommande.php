<?php
require_once('../Models/User.php');
require_once('../Models/MiscFunctions.php');
session_start();

if(isset($_SESSION['user']) && $_SESSION['user']->getType() == 1){
    getHeaderCommandes();

    /*

    Dans le cas où les commandes seraient traitées totalement, il faudrait :
        * Parcourir les modèles disponibles dans chaque usine
        * Récupérer les différentes options proposées
        * Ajouter les différentes informations proposées et les afficher dans le formulaire
    $i = 0;
    while($i<NB_USINES){
    }
     */


    ?>
    <!-- Content Row -->
    <div class="row">

        <!-- Content Column -->
        <div class="col-lg-12 mb-4">

            <div class="card shadow mb-4">
                <div class="card-body">
                    <h2 class="m-0 font-weight-bold">Bienvenue sur le Gestionnaire de Commandes</h2>

                    <hr>
                    <h4 class="m-0 font-weight-bold">Les commandes ne sont pas à traiter dans ce projet</h4>
                </div>
            </div>

        </div>

    </div>

    <?php
    getFooterCommandes();
}
else{
    header('Location: '.WEBSITE_URL);
    exit();
}
?>