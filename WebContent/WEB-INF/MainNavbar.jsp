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
      			<span class="navbar-brand">Projet SD</span>
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
</body>
</html>