<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        
        <!-- Required meta tags -->
	    <meta charset="utf-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Game Hub - Connexion</title>
        
        
        
        
        <!-- Linking Bootstrap built in css -->
		<link rel="stylesheet" href="Ressource/bootstrap_hosted/css/bootstrap.min.css">
		
    </head>
    <body>
    
    <jsp:include page="/WEB-INF/MainNavbar.jsp"/>
        <form method="post" action="<c:url value="/connexion" />">
            <fieldset>
            
            
    
    <div class="container">
    	<div class="row content">>
    	  <div class="col-md-6 mb-3">
    		
    	  </div>
    	  
    	  <div class="col-md-6">
    	    <h3 class="signin-text mb-3">Connexion</h3>
    	    
	    	      <div class="form-group">
	    	         <label for="email">Adresse email <span class="requis">*</span></label>
	                 <input type="email" class="form-control" id="email" name="email" value="<c:out value="${utilisateur.email}"/>" />
	                 <span class="erreur">${form.erreurs['email']}</span>
	    	      </div>
	    	      <div class="form-group">
	    	        <label for="motdepasse">Mot de passe <span class="requis">*</span></label>
	                <input type="password" class="form-control" id="motdepasse" name="motdepasse" value=""  />
	                <span class="erreur">${form.erreurs['motdepasse']}</span>
	    	      </div>
	    	      <input type="submit" value="Connexion" class="btn btn-primary" />
	    	       </div>
	            </div>
	            </div>
	            
    	      
          </form>
                
                <p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>
                
                <c:if test="${!empty sessionScope.sessionUtilisateur}">
                 
                    <p class="succes">Vous êtes connecté(e) avec l'adresse : ${sessionScope.sessionUtilisateur.email}</p>
            
                </c:if>
                
            </fieldset>    
    </body>
</html>