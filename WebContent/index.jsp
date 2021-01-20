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
	<jsp:include page="WEB-INF/MainNavbar.jsp"/>
	<p>
		<c:out value="La bibliothèque JSTL est fonctionnelle."/>
	</p>



	<!-- Linking Bootstrap built in JavaScript -->	
	<script src="Ressource/bootstrap_hosted/js/jquery.js"></script><!-- ? Sert à quoi ? On a pas de jquery dans notre bootstrap local-->
	<script src="Ressource/bootstrap_hosted/js/boostrap.min.js"></script>

</body>
</html>