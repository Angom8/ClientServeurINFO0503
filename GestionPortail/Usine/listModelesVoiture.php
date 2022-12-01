<?php
require_once('../Models/User.php');
require_once('../Models/MiscFunctions.php');
session_start();

if(isset($_SESSION['user']) && $_SESSION['user']->getType() >= 2){
    getHeaderUsine();

    $URL = USINE_SERVER_URL . $_SESSION["portUsine"]."/";

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

    $data = [
        "action" => 10
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

    $tab = null;

    if(($jsonTexte = @file_get_contents($URL, false, $contexte)) !== false) {
        $tab =  json_decode($jsonTexte, true);
    }

    ?>
    <!-- Content Row -->
    <div class="row">

        <!-- Content Column -->
        <div class="col-lg-12 mb-4">

            <div class="card shadow mb-4">
                <div class="card-body">
                    <h2 class="m-0 font-weight-bold">Liste des Modèles de voiture</h2>
                </div>

                <?php


                if($tab != null){
                    echo '
                        <div class="table-responsive">
	                        <table class="table card-table">
	                        		<thead class="card-table text-center">
                                        <th scope="col" class="lead">#</th>
                                        <th scope="col" class="lead">Nom</th>
                                        <th scope="col" class="lead">Moteur</th>
                                        <th scope="col" class="lead">Options</th>
                                        <th scope="col" class="lead">Action</th>
                                    </thead>
                                    <tbody>
                                    ';
                    foreach($tab["datas"] as $modele){
                        echo '<tr>
                                                <th scope="row" class="text-center lead-text">'.$modele['id'].'</th>
                                                <td scope="col" class="text-center lead-text">'.$modele['nom'].'</td>
                                                 <td scope="col" class="text-center lead-text">'.$modele['modele'].'</td>
                                                 <td scope="col" class="text-center lead-text"><ul>';

                        foreach($modele['options'] as $option){
                            echo "<li>$option</li>";
                        }
                        echo '</ul></td>
                                                <td scope="col" class="text-center lead-text"> 
                                                    <div class="form-group row text-center">
                                                        <div class="col-lg-9">
                                                            <form method="post" action="addOptionModeleVoiture.php">
                                                                <select name="options" id="options">';
                        foreach($tab_options['datas'] as $op){
                            echo '<option value="'.$op['id'].'">'.$op['nom'].'</option>';
                        }
                        echo'
                                    </select>
                                                            
                                                            
                                                                <input id="id" name="id" type="hidden" value="'.$modele['id'].'">
                                                                <button type="submit" class="btn btn-primary">Ajouter une option au modèle</button>
                                                            </form>
                                                        </div>
                                                        <div class="col-lg-3">
                                                            <form type="submit" method="post" action="delModeleVoiture.php">
                                                                <input id="id" name="id" type="hidden" value="'.$modele['id'].'">
                                                                <button  class="btn btn-warning"><i class="fa fa-times"></i></button>
                                                            </form>
                                                        </div>

                                                    </div>
                                                </td>               
                                        ';
                    }

                    echo '
                                    </tbody>
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

    <?php
    getFooterUsine();
}
else{
    header('Location: '.WEBSITE_URL);
    exit();
}
?>