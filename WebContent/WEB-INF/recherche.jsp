<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Apparemment il faudrait utiliser l'uri : ".../core_rt" mais il ne fonctionne pas ici -->

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Projet SD - Recherche</title>
<link rel="stylesheet" href="Ressource/bootstrap_hosted/css/bootstrap.min.css">
</head>
<body>
<h1>Recherche</h1>
<p>
	<c:out value="La bibliothèque JSTL est fonctionnelle."/>
</p>

<h2>Liste de jeux :</h2>
<!-- 
<table>
	<c:forEach items="${listeJeux}" var="jeu">
		<tr>
			<td>
			<c:out value="${jeu.titre}"></c:out>
			</td>
			<td>
			<c:out value="${jeu.genre}"></c:out>
			</td>
		</tr>
	</c:forEach>
</table>
 -->
<p>
	<form method="get" action="recherche">
		<label>Recherche : </label>
		<input type="text" id="search" name="search"/>
		<input type="submit" value="Rechercher"/>
	</form>
</p>
<c:choose>
<c:when test="${searched[0]==null}">
<table>
	<c:out value="${search}"></c:out>
	<c:forEach items="${listeJeux}" var="jeu">
	<tr>
		<td>
		<c:out value="${jeu.titre}"></c:out>
		</td>
		<td>
		<c:out value="${jeu.genre}"></c:out>
		</td>
	</tr>
	</c:forEach>
</table>
</c:when>
<c:otherwise>
<table>
	<c:forEach items="${searched}" var="jeu">
	<tr>
		<td>
		<c:out value="${jeu.titre}"></c:out>
		</td>
		<td>
		<c:out value="${jeu.genre}"></c:out>
		</td>
	</tr>
	</c:forEach>
</table>
</c:otherwise>
</c:choose>

<h2>Ajout de jeu</h2>
<p>Attention, l'apostrophe, les guillemets et les parenthèses ne sont pas supportées par notre système. Veuillez vous abstenir d'entrer un titre ou un genre en contenant.<br>Ne vous inquiétez pas, ce problème sera bientôt résolu. Nous travaillons dur pour vous permettre d'ajouter vos jeux favoris à nos services.</p>
<form  method="post" action="">
	<label for="id"> Id : </label>
	<input type="number" id="addId" name="addId"/>
	<label for="titre"> Titre : </label>
	<input type="text" id="addTitre" name="addTitre"/>
	<label for="genre"> Genre : </label>
	<input type="text" id="addGenre" name="addGenre"/>
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
	<label for="champs"> Champs à modifier :</label>
	<select id="updChamps" name="updChamps">
		<option value="Titre">Titre</option>
		<option value="Genre">Genre</option>
	</select>
	<label for="nouvelle valeur"> Nouvelle valeur :</label>
	<input type="text" id="updValeur" name="updValeur"/>
	<input type="submit" name="update" value="Modifier le champs"/>
</form>
	
	
	

</body>
</html>