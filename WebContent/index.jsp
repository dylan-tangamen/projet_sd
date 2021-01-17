<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Apparemment il faudrait utiliser l'uri : ".../core_rt" mais il ne fonctionne pas ici -->

    
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Projet SD - Accueil</title>
	<!-- Linking Bootstrap built in css -->
	<link rel="stylesheet" href="Ressource/bootstrap_hosted/css/bootstrap.min.css">
</head>
<body>
	<nav class="navbar navbar-expand-sm navbar-dark bg-primary" >
  		<div class="container-fluid">
  		    <div class="navbar-header">
      			<span class="navbar-brand" href="#">Projet SD</span>
    		</div>
  		  	
  		  	<!-- Collapse dépend de jquery et j'arrive pas à l'importer -->
  		  	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarToggler" aria-controls="navbarToggler" aria-expanded="false" aria-label="Toggle navigation">
    			<span class="navbar-toggler-icon"></span>
  			</button>
  			<div class="collapse navbar-collapse" id="navbarToggler">
  				<ul class="navbar-nav">
      				<li class="nav-item">
      					<a class="nav-link active" href="#">Accueil</a>
      				</li>
      				<li class="nav-item">
      					<a class="nav-link" href="recherche">Recherche</a>
      				</li>
      				<li class="nav-item">
      					<a class="nav-link" href="#">Page 2</a>
      				</li>
     				<li class="nav-item">
     					<a class="nav-link" href="#">Page 3</a>
     				</li>
    			</ul>
  			</div>
  		</div>
    		
	</nav>



	<!-- Linking Bootstrap built in JavaScript -->	
	<script src="Ressource/bootstrap_hosted/js/jquery.js"></script><!-- ? Sert à quoi ? On a pas de jquery dans notre bootstrap local-->
	<script src="Ressource/bootstrap_hosted/js/boostrap.min.js"></script>

</body>
</html>