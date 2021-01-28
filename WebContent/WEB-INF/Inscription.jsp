<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
    	<meta charset="utf-8" />
        <link rel = "stylesheet" type="text/css" href="style.css">
        <title>Game Hub - Inscription</title>
                <!-- Linking Bootstrap built in css -->
		<link rel="stylesheet" href="Ressource/bootstrap_hosted/css/bootstrap.min.css">
        
    </head>
    <body>
    	<jsp:include page="/WEB-INF/MainNavbar.jsp"/>
        <form method="post" action="inscription">
            <fieldset>
            
            	<div class="container">
            		<div class="form-group">
            			<div class="col-md-7">
            				<h1 class="text-left">Inscription</h1>
            				<p class="text-left">Vous pouvez vous inscrire via ce formulaire.</p>
            			
            			<div class="form-group">
             					<label for="email">Email</label>
             					<div class="col-md-10">
             						<input type="email" class="form-control" id="email" name="email" value="<c:out value="${param.email}"/>"  />
             						<span class="erreur">${erreurs['email']}</span>
             					</div>
             				</div>
             				<div class="form-group">
             					<label for="mdp">Mot de passe</label>
             					<div class="col-md-10">
             						<input type="password" class="form-control" id="mdp" name="mdp" value="<c:out value="${param.mdp}"/>"  />
             						<span class="erreur">${erreurs['mdp']}</span>
             					</div>
             				</div>
             				<div class="form-group">
             					<label for="confirmation">Confirmation mot de passe</label>
             					<div class="col-md-10">
             						<input type="password" class="form-control" id="confirmation" name="confirmation" value="<c:out value="${param.confirmation}"/>"  />
             						<span class="erreur">${erreurs['confirmation']}</span>
             					</div>
             				</div>
             				<div class="form-group">
             					<label for="pseudo">Pseudo</label>
             					<div class="col-md-10">
             						<input type="text" class="form-control" id="pseudo" name="pseudo" value="<c:out value="${param.pseudo}"/>"  />
             						 <span class="erreur">${erreurs['pseudo']}</span>
             					</div>
             				</div>
             				<input type="submit" value="Inscription" class="btn btn-primary" />
             				<br />
            			
             				
             				
             			 </div>
                  	</div>
                  	<div class="col-md-7">
            			<h1 class="text-left">Déjà inscrit ?</h1>
						<a href="/projet_sd/connexion">Connectez-vous !</a>
            		</div>	
               </div>

                
                <p class="${empty erreurs ? 'succes' : 'erreur'}">${resultat}</p>
            </fieldset>
        </form>
    
    </body>
</html>