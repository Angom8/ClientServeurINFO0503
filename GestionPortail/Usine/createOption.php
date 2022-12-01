<?php
require_once('../Models/User.php');
require_once('../Models/MiscFunctions.php');
session_start();

if(isset($_SESSION['user']) && $_SESSION['user']->getType() >= 2){
    getHeaderUsine();

    ?>
    <!-- Content Row -->
    <div class="row">

        <!-- Content Column -->
        <div class="col-lg-12 mb-4">

            <div class="card shadow mb-4">
                <div class="card-body">
                    <h2 class="m-0 font-weight-bold">Bienvenue sur le Gestionnaire d'Options</h2>

                    <hr>

                    <form method="post" action="addOption.php">

                        <label for="nom" class="sr-only">Nom</label>
                        <input name="nom" type="text" id="nom" class="form-control" placeholder="Nom de l'Option" required autofocus>
                        <br>
                        <button class="btn btn-lg btn-primary btn-block" type="submit">Ajouter une option</button>
                        <hr>
                        <?php
                        if(isset($_GET['invalid']) && $_GET['invalid'] === "true"){
                            echo '<p class="text-danger">Une erreur est survenue lors de la création de l\'option.';
                        }
                        if(isset($_GET['valid']) && $_GET['valid'] === "true"){
                            echo '<p class="text-success">L\'option a été ajouté avec succès.';
                        }
                        ?>

                    </form>
                </div>
            </div>




        </div>

    </div>

    <?php
    getFooterUsine();
}
else{
    header('Location: '.WEBSITE_URL);
    exit();
}
?>