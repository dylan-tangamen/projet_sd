<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Apparemment il faudrait utiliser l'uri : ".../core_rt" mais il ne fonctionne pas ici -->

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Game Hub - Recherche</title>
<link rel="stylesheet" href="Ressource/bootstrap_hosted/css/bootstrap.min.css">
</head>
<body>
<jsp:include page="MainNavbar.jsp"/>

<div class="jumbotron text-center">
	<div class="container">
	<h1>Recherche</h1>
	<p class="lead">
		<c:out value="La bibliothèque JSTL est fonctionnelle."/>
	</p>
	</div>
	
</div>


<hr>

<div class="container">
	<div class="panel panel-default">
	<div class="panel-heading">
		<h2 class="panel-title">Liste de jeux</h2>
	</div>
	<table class="table table-bordered table-condensed table-hover">
		<thead class="thead-dark">
		<tr>
			<th>Titre</th>
			<th>Sortie</th>
			<th>Studio</th>
		</tr>
		</thead>
		<c:forEach items="${listeJeux}" var="jeu">
			<tr class="clickableRow" data-href="#">
				<td>
				<c:out value="${jeu.titre}"></c:out>
				</td>
				<td>
				<c:out value="${jeu.anneeSortie}"></c:out>
				</td>
				<td>
				<c:out value="${jeu.studio}"></c:out>
				</td>
			</tr>
		</c:forEach>
	</table>
	</div>
</div>


<hr>

<div class="container">
	<div class="panel panel-default">
	<div class="panel-heading"><h2 class=panel-title>Recherche</h2></div>
	<form class="form-inline" method="get" action="recherche">
		<div class="form-group">
			<label for="rech">Recherche</label>
			<input type="text" class="form-control" id="search" name="search"/>
		</div>
		<input type="submit" class="btn btn-primary" value="Rechercher"/>
	</form>
	
	<br>
	
	<table class="table table-bordered table-condensed table-hover">
	<c:choose>
	
	<c:when test="${searched[0]==null}">
	<!-- Si la recherche est vide (aucun résultat ou premier chargement de la page, on renvoie tous les titres de jeux -->
		<c:out value="${search}"></c:out>
		<thead class="thead-dark">
		<tr>
			<th>Titre</th>
		</tr>
		</thead>
		<c:forEach items="${listeJeux}" var="jeu">
		<tr>
			<td>
			<c:out value="${jeu.titre}"> </c:out>
			</td>
		</tr>
		</c:forEach>
	</c:when>
	
	<c:otherwise>
	<!-- Sinon on renvoie les jeux de la recherche, avec plus d'informations -->
		<thead class="thead-dark">
		<tr>
			<th>Titre</th>
			<th>Sortie</th>
			<th>Studio</th>
			<th>Description</th>
		</tr>
		</thead>
		<c:forEach items="${searched}" var="jeu">
		<tr>
			<td>
			<c:out value="${jeu.titre}"> </c:out>
			</td>
			<td>
			<c:out value="${jeu.anneeSortie}"></c:out>
			</td>
			<td>
			<c:out value="${jeu.studio}"> </c:out>
			</td>
			<td>
			<c:out value="${jeu.description}"> </c:out>
			</td>
		</tr>
		</c:forEach>
	</c:otherwise>
	
	</c:choose>
	</table>
	</div>
</div>


<hr>

<div class="container">
	<div class="panel panel-default">
		<div class="panel-heading">
			<h2 class="panel-title">Ajout de jeu</h2>
		</div>
		<p>Attention, l'apostrophe, les guillemets et les parenthèses ne sont pas supportées par notre système. Veuillez vous abstenir d'entrer un titre en contenant.<br>Ne vous inquiétez pas, ce problème sera bientôt résolu. Nous travaillons dur pour vous permettre d'ajouter vos jeux favoris à nos services.</p>
		<div class="container">
		<form  method="post" action="">
			<div class="form-group">
				<label for="titre"> Titre </label>
				<input type="text" class="form-control" id="addTitre" name="addTitre"/>
			</div>	
			<div class="form-group">
				<label for="anneeSortie"> Année de sortie </label>
				<input type="text" class="form-control"id="addAnneeSortie" name="addAnneeSortie"/>
			</div>
			<div class="form-group">	
				<label for="studio"> Studio </label>
				<input type="text" class="form-control" id="addStudio" name="addStudio"/>
			</div>
			<div class="form-group">	
				<label for="description"> Description </label>
				<input type="text" class="form-control" id="addDescription" name="addDescription"/>
			</div>	
			<input type="submit" class="btn btn-primary" name="add" value="Ajouter"/> 
		</form>
		</div>
	</div>
</div>





<hr>

<div class="container">
	<div class="panel panel-default w-100" >
	<div class="panel-heading">
		<h2 class="panel-title">Suppression de jeu</h2>
	</div>
	<form class="form-inline" method="post" action="recherche">
	<div class="form-group">
		<label for="titre">Jeu à retirer</label>
		<select class="form-control" id="rmTitre" name="rmTitre">
		<c:forEach items="${listeJeux}" var="jeu">					
			<option value="${jeu.titre}">${jeu.titre}</option>
		</c:forEach>
		</select>
	</div>
	<input type="submit" class="btn btn-danger float-right" name="remove" value="Retirer"/>
	</form>
	</div>
</div>

<hr>

<div class="container">
	<div class="panel panel-default">
	<div class="panel-heading">
		<h2 class="panel-title">Modification de jeu</h2>
	</div>
	<div class="container">
		<form method="post" action="recherche">
			<div class="form-group">
				<label for="titre">Jeu à modifier</label>
				<select class="form-control" id="updTitre" name="updTitre">
				<c:forEach items="${listeJeux}" var="jeu">
					<option value="${jeu.titre}">${jeu.titre}</option>
				</c:forEach>
				</select>
			</div>
			<div class="form-group">
				<label for="champs">Champ à modifier</label>
				<select class="form-control" id="updChamps" name="updChamps">
					<option value="Titre">Titre</option>
					<option value="AnneeSortie">AnneeSortie</option>
					<option value="Studio">Studio</option>
					<option value="Description">Description</option>
				</select>
			</div>
			<div class="form-group">
				<label for="nouvelle valeur">Nouvelle valeur</label>
				<input type="text" class="form-control" id="updValeur" name="updValeur"/>
			</div>
		
			<input type="submit" class="btn btn-primary" name="update" value="Modifier le champs"/>
			
		</form>
	</div>
	</div>
</div>	
	
	
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>	

</body>
</html>