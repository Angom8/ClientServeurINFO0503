<?php
	require_once('Models/User.php');
	session_start();
?>
<!DOCTYPE html>
<html lang="fr">
    <head>
        <title>Portail</title>
        <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
	<link href="css/main.css" rel="stylesheet">
    </head>
    <!--Template exemple issu de Bootstrap-->
	<body class="text-center">
		  <form method="post" class="form-signin" action="login.php">
		    
		  <h1 class="h3 mb-3 font-weight-normal">Connexion</h1>
		  <label for="username" class="sr-only">Username</label>
		  <input name="username" type="text" id="username" class="form-control" placeholder="Username" required autofocus>
		  <label for="password" class="sr-only">Password</label>
		  <input name="password" type="password" id="password" class="form-control" placeholder="Password" required>

		  <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
		  <p class="mt-5 mb-3 text-muted">&copy; 2020</p>
		</form>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
    </body>
</html>
