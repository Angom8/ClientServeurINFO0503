<?php
require_once('../Models/User.php');
require_once('../Models/MiscFunctions.php');
session_start();

if(isset($_SESSION['user']) && $_SESSION['user']->getType() == 0){
    //getModeles
    getHeaderExternal();

    $all_models = [];

    //On parcourt la liste des modèles (ou voitures) des usines
    for($i = 0;$i<NB_USINES;$i++){

        $URL = USINE_SERVER_URL.(USINE_BASE_PORT+$i)."/";

        $data = [
            "action" => 10,
            "datas" => ['id' => intval(htmlentities($_POST['id']))]
        ];

        // Préparation de la requête
        $options = [
            'http' =>
                [
                    'method' => 'POST',
                    'header' => 'Content-type: application/json',
                    'content' => json_encode($data)
                ]
        ];

        $contexte = stream_context_create($options);

        $tab = null;

        if(($jsonTexte = @file_get_contents($URL, false, $contexte)) !== false) {
            $tab =  json_decode($jsonTexte, true);
            array_push($all_models, $tab);
        }
    }

    ?>
    <!-- Content Row -->
    <div class="row">

        <!-- Content Column -->
        <div class="col-lg-12 mb-4">

            <!-- Project Card Example -->
            <div class="card shadow mb-4">
                <div class="card-body">
                    <h2 class="m-0 font-weight-bold">Modèles de Voiture disponibles</h2>
                    <?php
                    if($all_models){
                    echo '
                    <div class="table-responsive">
                        <table class="table card-table">
                            <thead class="card-table text-center">
                            <th scope="col" class="lead">#</th>
                            <th scope="col" class="lead">Nom</th>
                            <th scope="col" class="lead">Moteur</th>
                             <th scope="col" class="lead">Options</th>
                            </thead>
                            <tbody>
                            ';
                    foreach($all_models as $tab) {
                        foreach ($tab['datas'] as $modele) {
                            echo '<tr>
                                                <th scope="row" class="text-center lead-text">' . $modele['id'] . '</th>
                                                <td scope="col" class="text-center lead-text">' . $modele['nom'] . '</td>
                                                 <td scope="col" class="text-center lead-text">' . $modele['modele'] . '</td>
                                                 <td scope="col" class="text-center lead-text"><ul>';

                            foreach ($modele['options'] as $option) {
                                echo "<li>$option</li>";
                            }
                            echo '</ul></td></tr>';
                        }
                    }

                        echo'    </tbody>
                        </table>
                    </div>

                    ';

                    }

                    else{
                        echo'
                        <p class="mb-4 font-weight-bold text-center">Il n\'y a pas de modèles à afficher !</p>
                        ';
                    }
                    ?>
                </div>
            </div>

        </div>

    </div>

    <?php
    getFooterExternal();
}
else{
    header('Location:'.WEBSITE_URL);
    exit();
}
?>