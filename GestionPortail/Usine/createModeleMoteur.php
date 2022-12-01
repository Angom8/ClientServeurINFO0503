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
                    <h2 class="m-0 font-weight-bold">Bienvenue sur le Gestionnaire de Modèles</h2>

                    <hr>

                    <form method="post" action="addModeleMoteur.php">

                        <label for="puissance" class="sr-only">Puissance</label>
                        <input name="puissance" type="number" min="0" id="puissance" class="form-control" placeholder="Puissance" required autofocus>

                        <label for="carburation" class="sr-only">Carburation</label>
                        <select name="carburation" id="carburationt">
                            <option value="SANS">Carburation</option>
                            <option value="DIESEL">Diesel</option>
                            <option value="ESSENCE">Essence</option>
                            <option value="GPL">GPL</option>
                            <option value="ELECTRIQUE">Electrique</option>
                            <option value="GAZNAT">Gaz naturel</option>
                            <option value="SANS">Sans</option>
                        </select>

                        <br>
                        <button class="btn btn-lg btn-primary btn-block" type="submit">Ajouter un modèle de moteur</button>
                        <hr>
                        <?php
                        if(isset($_GET['invalid']) && $_GET['invalid'] === "true"){
                            echo '<p class="text-danger">Une erreur est survenue lors de la création du modèle';
                        }
                        if(isset($_GET['valid']) && $_GET['valid'] === "true"){
                            echo '<p class="text-success">Le modèle a été ajouté avec succès.';
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