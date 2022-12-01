<?php
require_once('../Models/User.php');
require_once('../Models/MiscFunctions.php');
session_start();

if(isset($_SESSION['user']) && $_SESSION['user']->getType() >= 2){
    getHeaderUsine();

    $URL = USINE_SERVER_URL . $_SESSION["portUsine"] . "/";

    //Récupération des modèles
    $data = [
        "action" => 8
    ];
    $options = [
        'http' =>
            [
                'method' => 'POST',
                'header' => 'Content-type: application/json',
                'content' => json_encode($data)
            ]
    ];

    $contexte = stream_context_create($options);

    $tab_moteurs = null;

    if (($jsonTexte = @file_get_contents($URL, false, $contexte)) !== false) {
        $tab_moteurs = json_decode($jsonTexte, true);
    }

    //Récupération des options
    $data = [
        "action" => 9
    ];
    $options = [
        'http' =>
            [
                'method' => 'POST',
                'header' => 'Content-type: application/json',
                'content' => json_encode($data)
            ]
    ];

    $contexte = stream_context_create($options);

    $tab_options = null;

    if(($jsonTexte = @file_get_contents($URL, false, $contexte)) !== false) {
        $tab_options =  json_decode($jsonTexte, true);
    }

    ?>
    <!-- Content Row -->
    <div class="row">

        <!-- Content Column -->
        <div class="col-lg-12 mb-4">

            <div class="card shadow mb-4">
                <div class="card-body">
                    <h2 class="m-0 font-weight-bold">Bienvenue sur le Gestionnaire de Modèles</h2>

                    <hr>

                    <form method="post" action="addModeleVoiture.php">

                        <label for="nom" class="sr-only">Nom</label>
                        <input name="nom" type="text" id="nom" class="form-control" placeholder="nom" required autofocus>

                        <label for="couleur" class="sr-only">Couleur</label>
                        <select name="couleur" id="couleur">
                            <option value="ROUGE">Rouge</option>
                            <option value="VERT">Verte</option>
                            <option value="ORANGE">Orange</option>
                            <option value="FUSHIA">Fushia</option>
                            <option value="BLEU">Bleue</option>
                            <option value="TRANSPARENT">Transparente</option>
                        </select>

                        <label for="modele" class="sr-only">Couleur</label>
                        <select name="modele" id="modele">
                            <?php
                            foreach($tab_moteurs['datas'] as $moteur){
                                echo '<option value="'.$moteur['id'].'">'.$moteur['carburation'].' - '.$moteur['puissance'].'</option>';
                            }
                            ?>
                        </select>

                        <label for="options" class="sr-only">Couleur</label>
                        <select name="options" id="options">
                            <?php
                            foreach($tab_options['datas'] as $op){
                                echo '<option value="'.$op['id'].'">'.$op['nom'].'</option>';
                            }
                            ?>
                        </select>
                        <br>
                        <button class="btn btn-lg btn-primary btn-block" type="submit">Ajouter un modèle de voiture</button>
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