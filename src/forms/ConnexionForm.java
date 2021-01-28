package forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jasypt.util.password.ConfigurablePasswordEncryptor;

import beans.Utilisateur;
import dao.DAOFactory;
import dao.UtilisateurDao;
import dao.UtilisateurDaoImpl;

public final class ConnexionForm {
    private static final String CHAMP_EMAIL  = "email";
    private static final String CHAMP_PASS   = "motdepasse";

    private String              resultat;
    private Map<String, String> erreurs      = new HashMap<String, String>();

    private UtilisateurDao      utilisateurDao;
    
    private static final String ALGO_CHIFFREMENT = "SHA-256";
    
    public ConnexionForm(UtilisateurDao utilisateurDao) {
        this.utilisateurDao = utilisateurDao;
	}

	public String getResultat() {
        return resultat;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public Utilisateur connecterUtilisateur( HttpServletRequest request ) {
        
        String email = getValeurChamp( request, CHAMP_EMAIL );
        String mdp = getValeurChamp( request, CHAMP_PASS );

        Utilisateur utilisateur = new Utilisateur();
        Utilisateur foundUser = null;
        
        try {
            //validationEmail( email );
            foundUser=utilisateurDao.trouver(email);
            System.out.println("email set "+foundUser);
        } catch ( Exception e ) {
            setErreur( CHAMP_EMAIL, e.getMessage() );
            System.out.println("email erreur ");
        }
        utilisateur.setEmail( email );

        try {

			validationMdp( foundUser, mdp );
            System.out.println("mdp correct");
        } catch ( Exception e ) {
            setErreur( CHAMP_PASS, e.getMessage() );
            System.out.println("mdp erreur "+e);
        }
        utilisateur.setMdp( mdp );

        if ( erreurs.isEmpty() ) {
            resultat = "Succ�s de la connexion.";
        } else {
            resultat = "�chec de la connexion.";
        }

        return utilisateur;
    }

    private void validationEmail( String email ) throws Exception {
        if ( email != null && !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
            throw new Exception( "Merci de saisir une adresse mail valide." );
        } else {
        	if (utilisateurDao.trouver(email)==null) {
        		throw new Exception( "Adresse mail non reconnue. " +utilisateurDao.trouver(email));
        	}
        }
    }

    private void validationMdp( Utilisateur user, String mdp ) throws Exception {
        ConfigurablePasswordEncryptor passwordEncryptor = new ConfigurablePasswordEncryptor();
        passwordEncryptor.setAlgorithm( ALGO_CHIFFREMENT );
        passwordEncryptor.setPlainDigest( false );
        String mdpChiffre = passwordEncryptor.encryptPassword( mdp );
    	if ( mdp != null ) {
            if (mdpChiffre.equals(user.getMdp())) {

            	throw new Exception( "Mot de passe incorrect.\n"+mdpChiffre+"\n"+user.getMdp() );
            } 
        } else {
            throw new Exception( "Merci de saisir un mot de passe." );
        }
    }

    private void setErreur( String champ, String message ) {
        erreurs.put( champ, message );
    }

    private static String getValeurChamp( HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null || valeur.trim().length() == 0 ) {
            return null;
        } else {
            return valeur;
        }
    }
}