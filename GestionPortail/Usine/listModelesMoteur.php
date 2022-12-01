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
                    <h2 class="m-0 font-weight-bold">Liste des Modèles de moteur</h2>
                </div>

                <?php
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

                $URL = USINE_SERVER_URL . $_SESSION["portUsine"]."/";

                $contexte = stream_context_create($options);

                $tab = null;

                if(($jsonTexte = @file_get_contents($URL, false, $contexte)) !== false) {
                    $tab =  json_decode($jsonTexte, true);
                }

                if($tab != null){
                    echo '
                        <div class="table-responsive">
	                        <table class="table card-table">
	                        		<thead class="card-table text-center">
                                        <th scope="col" class="lead">#</th>
                                        <th scope="col" class="lead">Puissance</th>
                                        <th scope="col" class="lead">Carburation</th>
                                        <th scope="col" class="lead">Action</th>
                                    </thead>
                                    <tbody>
                                    ';
                    foreach($tab["datas"] as $modele){
                        echo '<tr>
                                                <th scope="row" class="text-center lead-text">'.$modele['id'].'</th>
                                                <td scope="col" class="text-center lead-text">'.$modele['puissance'].'</td>
                                                 <td scope="col" class="text-center lead-text">'.$modele['carburation'].'</td>
                                                <td scope="col" class="text-center lead-text"> 
                                                    <div class="form-group row text-center">
                                                        <div class="col">
                                                            <form type="submit" method="post" action="delModeleMoteur.php">
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