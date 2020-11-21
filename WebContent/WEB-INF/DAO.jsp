<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Apparemment il faudrait utiliser l'uri : ".../core_rt" mais il ne fonctionne pas ici -->

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>DAOperso - JSP</title>
</head>
<body>
<h1>DAOperso - JSP</h1>
<p>
	<c:out value="La bibliothèque JSTL est fonctionnelle."/>
</p>

<h2>Liste de jeux :</h2>
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

<h2>Ajout de jeu</h2>
<p>Attention, l'apostrophe, les guillemets et les parenthèses ne sont pas supportées par notre système. Veuillez vous abstenir d'entrer un titre ou un genre en contenant.<br>Ne vous inquiétez pas, ce problème sera bientôt résolu. Nous travaillons dur pour vous permettre d'ajouter vos jeux favoris à nos services.</p>
<form  method="post" action="dao">
	<label for="id"> Id : </label>
	<input type="number" id="addId" name="addId"/>
	<label for="titre"> Titre : </label>
	<input type="text" id="addTitre" name="addTitre"/>
	<label for="genre"> Genre : </label>
	<input type="text" id="addGenre" name="addGenre"/>
	<input type="submit" name="add" value="Ajouter"/>  
</form>

<h2>Suppression de jeu</h2>
<form method="post" action="dao">
	<label for="titre"> Titre de jeu à retirer :</label>
	<select id="rmTitre" name="rmTitre">
	<c:forEach items="${listeJeux}" var="jeu">					
		<option value="${jeu.titre}">${jeu.titre}</option>
	</c:forEach>
	</select>
	<input type="submit" name="remove" value="Retirer"/>
</form>

</body>
</html>