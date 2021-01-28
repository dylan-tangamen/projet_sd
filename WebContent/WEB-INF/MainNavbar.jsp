<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<nav class="navbar navbar-expand-sm navbar-dark bg-primary" >
  		<div class="container-fluid">
  		    <div class="navbar-header">
      			<span class="navbar-brand">Game Hub</span>
    		</div>
  		  	
  		  	<!-- Collapse dépend de jquery et j'arrive pas à l'importer -->
  		  	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarToggler" aria-controls="navbarToggler" aria-expanded="false" aria-label="Toggle navigation">
    			<span class="navbar-toggler-icon"></span>
  			</button>
  			<div class="collapse navbar-collapse" id="navbarToggler">
  				<ul class="navbar-nav">
      				<li class="nav-item">
      					<a class="nav-link active" href="./">Accueil</a>
      				</li>
      				<li class="nav-item">
      					<a class="nav-link" href="recherche">Recherche</a>
      				</li>
      				<c:choose>
				   	<c:when test="${empty sessionScope.sessionUtilisateur}">
      					<li class="nav-item">
      						<a class="nav-link" href="connexion">Connexion</a>
      					</li>
     					<li class="nav-item">
     						<a class="nav-link" href="inscription">Inscription</a>
     					</li>
				    </c:when>    
				    <c:otherwise>
      					<li class="nav-item">
      						<a class="nav-link" href="#">Profil</a>
      					</li>
     					<li class="nav-item">
     						<a class="nav-link" href="deconnexion">Déconnexion</a>
     					</li>
				    </c:otherwise>
					</c:choose>

    			</ul>
  			</div>
  		</div>
    		
	</nav>
<!-- 		<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script> -->
</body>
</html>