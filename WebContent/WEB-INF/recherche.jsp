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

<h1>Recherche</h1>
<p>
	<c:out value="La bibliothèque JSTL est fonctionnelle."/>
</p>

<h2>Liste de jeux :</h2>

<table>
	<tr>
		<th>Titre</th>
		<th>Sortie</th>
		<th>Studio</th>
	</tr>
	<c:forEach items="${listeJeux}" var="jeu">
		<tr>
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
 
<h2>Recherche</h2>
<p>
	<form method="get" action="recherche">
		<label>Recherche : </label>
		<input type="text" id="search" name="search"/>
		<input type="submit" value="Rechercher"/>
	</form>
</p>
<table>
<c:choose>

<c:when test="${searched[0]==null}">
<!-- Si la recherche est vide (aucun résultat ou premier chargement de la page, on renvoie tous les titres de jeux -->
	<c:out value="${search}"></c:out>
	<tr>
		<th>Titre</th>
	</tr>
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
	<tr>
		<th>Titre</th>
		<th>Sortie</th>
		<th>Studio</th>
		<th>Description</th>
	</tr>
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

<h2>Ajout de jeu</h2>
<p>Attention, l'apostrophe, les guillemets et les parenthèses ne sont pas supportées par notre système. Veuillez vous abstenir d'entrer un titre en contenant.<br>Ne vous inquiétez pas, ce problème sera bientôt résolu. Nous travaillons dur pour vous permettre d'ajouter vos jeux favoris à nos services.</p>
<form  method="post" action="">
	<label for="titre"> Titre : </label>
	<input type="text" id="addTitre" name="addTitre"/><br>
	<label for="anneeSortie"> Année de sortie : </label>
	<input type="text" id="addAnneeSortie" name="addAnneeSortie"/><br>
	<label for="studio"> Studio : </label>
	<input type="text" id="addStudio" name="addStudio"/><br>
	<label for="description"> Description : </label>
	<input type="text" id="addDescription" name="addDescription"/><br>
	<input type="submit" name="add" value="Ajouter"/> 
</form>

<h2>Suppression de jeu</h2>
<form method="post" action="recherche">
	<label for="titre"> Titre de jeu à retirer :</label>
	<select id="rmTitre" name="rmTitre">
	<c:forEach items="${listeJeux}" var="jeu">					
		<option value="${jeu.titre}">${jeu.titre}</option>
	</c:forEach>
	</select>
	<input type="submit" name="remove" value="Retirer"/>
</form>

<h2>Modification de jeu</h2>
<form method="post" action="recherche">
	<label for="titre">Titre du jeu à modifier :</label>
	<select id="updTitre" name="updTitre">
	<c:forEach items="${listeJeux}" var="jeu">
		<option value="${jeu.titre}">${jeu.titre}</option>
	</c:forEach>
	</select>
	<br>
	<label for="champs"> Champs à modifier :</label>
	<select id="updChamps" name="updChamps">
		<option value="Titre">Titre</option>
		<option value="AnneeSortie">AnneeSortie</option>
		<option value="Studio">Studio</option>
		<option value="Description">Description</option>
	</select>
	<br>
	<label for="nouvelle valeur"> Nouvelle valeur :</label>
	<input type="text" id="updValeur" name="updValeur"/>
	<input type="submit" name="update" value="Modifier le champs"/>
</form>
	
	
	

</body>
</html>