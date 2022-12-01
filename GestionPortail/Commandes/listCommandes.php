<?php
require_once('../Models/User.php');
require_once('../Models/MiscFunctions.php');
session_start();

if(isset($_SESSION['user']) && $_SESSION['user']->getType() == 1){
    getHeaderCommandes();

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