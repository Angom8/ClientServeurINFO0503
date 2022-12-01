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
                    <h2 class="m-0 font-weight-bold">Liste des Clients</h2>
                </div>

                <?php
                    $URL = COMMANDES_SERVER_URL."viewClients.php";
                    $tab = null;
                    if(($jsonTexte = @file_get_contents($URL)) !== false) {
                        $tab =  json_decode($jsonTexte, true);
                    }

                    if($tab != null){
                        echo '
                        <div class="table-responsive">
	                        <table class="table card-table">
	                        		<thead class="card-table text-center">
                                        <th scope="col" class="lead">#</th>
                                        <th scope="col" class="lead">Nom</th>
                                        <th scope="col" class="lead">Prenom</th>
                                        <th scope="col" class="lead">Action</th>
                                    </thead>
                                    <tbody>
                                    ';
                        foreach($tab as $client){
                            echo '<tr>
                                                <th scope="row" class="text-center lead-text">'.$client['id'].'</th>
                                                <td scope="col" class="text-center lead-text">'.$client['nom'].'</td>
                                                <td scope="col" class="text-center lead-text">'.$client['prenom'].'</td>
                                                <td scope="col" class="text-center lead-text"> 
                                                    <div class="form-group row text-center">
                                                        <div class="col">
                                                            <form type="submit" method="post" action="delClient.php">
                                                                <input id="id" name="id" type="hidden" value="'.$client['id'].'">
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
                        <p class="mb-4 font-weight-bold text-center">Il n\'y a pas de clients à afficher !</p>
                        ';
                    }

                ?>


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