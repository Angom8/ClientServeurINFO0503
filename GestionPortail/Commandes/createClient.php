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

                    <form method="post" action="addClient.php">

                        <label for="nom" class="sr-only">Nom</label>
                        <input name="nom" type="text" id="nom" class="form-control" placeholder="Nom" required autofocus>
                        <label for="prenom" class="sr-only">Prénom</label>
                        <input name="prenom" type="text" id="prenom" class="form-control" placeholder="Prenom" required>
                        <br>
                        <button class="btn btn-lg btn-primary btn-block" type="submit">Ajouter un client</button>
                        <hr>
                        <?php
                        if(isset($_GET['invalid']) && $_GET['invalid'] === "true"){
                            echo '<p class="text-danger">Une erreur est survenue lors de la création du client.';
                        }
                        if(isset($_GET['valid']) && $_GET['valid'] === "true"){
                            echo '<p class="text-success">Le client a été ajouté avec succès.';
                        }
                        ?>

                    </form>
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