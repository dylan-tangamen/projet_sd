package servlets;



import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UtilisateurDao;
import beans.Utilisateur;
import dao.DAOFactory;
import forms.InscriptionForm;




public class Inscription extends HttpServlet {
   
	 public static final String CONF_DAO_FACTORY = "daofactory";
	    public static final String ATT_USER         = "utilisateur";
	    public static final String ATT_FORM         = "form";
	    public static final String VUE              = "/WEB-INF/Inscription.jsp";
	    
	    public static final String CHAMP_EMAIL  = "email";
	    public static final String CHAMP_PASS   = "mdp";
	    public static final String CHAMP_CONF   = "confirmation";
	    public static final String CHAMP_NOM    = "pseudo";
	    public static final String ATT_ERREURS  = "erreurs";
	    public static final String ATT_RESULTAT = "resultat";

	    private UtilisateurDao     utilisateurDao;

	    public void init() throws ServletException {
	    	
	        /* R�cup�ration d'une instance de notre DAO Utilisateur */
	        this.utilisateurDao = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getUtilisateurDao();
	    }

	    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
	        /* Affichage de la page d'inscription */
	        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
	    }

	    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
	       
	    	String resultat;
	    	Map<String, String> erreurs = new HashMap<String, String>();

	        /* R�cup�ration des champs du formulaire. */
	        String email = request.getParameter( CHAMP_EMAIL );
	        String mdp = request.getParameter( CHAMP_PASS );
	        String confirmation = request.getParameter( CHAMP_CONF );
	        String pseudo = request.getParameter( CHAMP_NOM );

	        /* Validation du champ email. */
	        try {
	            validationEmail( email );
	        } catch ( Exception e ) {
	            erreurs.put( CHAMP_EMAIL, e.getMessage() );
	        }

	        /* Validation des champs mot de passe et confirmation. */
	        try {
	            validationMdp( mdp, confirmation );
	        } catch ( Exception e ) {
	            erreurs.put( CHAMP_PASS, e.getMessage() );
	        }

	        /* Validation du champ nom. */
	        try {
	            validationPseudo( pseudo );
	        } catch ( Exception e ) {
	            erreurs.put( CHAMP_NOM, e.getMessage() );
	        }

	        /* Initialisation du r�sultat global de la validation. */
	        if ( erreurs.isEmpty() ) {
	            resultat = "Succ�s de l'inscription.";
	        } else {
	            resultat = "�chec de l'inscription.";
	        }

	        /* Stockage du r�sultat et des messages d'erreur dans l'objet request */
	        request.setAttribute( ATT_ERREURS, erreurs );
	        request.setAttribute( ATT_RESULTAT, resultat );

	        /* Transmission de la paire d'objets request/response � notre JSP */
	        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
	    
	    	
	    	
	    	/* Pr�paration de l'objet formulaire */
	        InscriptionForm form = new InscriptionForm( utilisateurDao );

	        /* Traitement de la requ�te et r�cup�ration du bean en r�sultant */
	        Utilisateur utilisateur = form.inscrireUtilisateur( request );

	        /* Stockage du formulaire et du bean dans l'objet request */
	        request.setAttribute( ATT_FORM, form );
	        request.setAttribute( ATT_USER, utilisateur );

	        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
	    }
	    
	    private void validationEmail( String email ) throws Exception {
	        if ( email != null && email.trim().length() != 0 ) {
	            if ( !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
	                throw new Exception( "Merci de saisir une adresse mail valide." );
	            }
	        } else {
	            throw new Exception( "Merci de saisir une adresse mail." );
	        }
	    }

	    /**
	     * Valide les mots de passe saisis.
	     */
	    private void validationMdp( String mdp, String confirmation ) throws Exception{
	        if (mdp != null && mdp.trim().length() != 0 && confirmation != null && confirmation.trim().length() != 0) {
	            if (!mdp.equals(confirmation)) {
	                throw new Exception("Les mots de passe entr�s sont diff�rents, merci de les saisir � nouveau.");
	            } else if (mdp.trim().length() < 3) {
	                throw new Exception("Les mots de passe doivent contenir au moins 3 caract�res.");
	            }
	        } else {
	            throw new Exception("Merci de saisir et confirmer votre mot de passe.");
	        }
	    }

	    /**
	     * Valide le nom d'utilisateur saisi.
	     */
	    private void validationPseudo( String pseudo ) throws Exception {
	        if ( pseudo != null && pseudo.trim().length() < 3 ) {
	            throw new Exception( "Le pseudo doit contenir au moins 3 caract�res." );
	        }
	    }
	
}