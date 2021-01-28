package forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jasypt.util.password.ConfigurablePasswordEncryptor;

import beans.Utilisateur;
import dao.DAOException;
import dao.UtilisateurDao;

public class InscriptionForm {

	private static final String CHAMP_EMAIL  = "email";
    private static final String CHAMP_PASS   = "mdp";
    private static final String CHAMP_CONF   = "confirmation";
    private static final String CHAMP_PSEUDO    = "pseudo";
    
    private String resultat;
    private Map<String, String> erreurs      = new HashMap<String, String>();
    private UtilisateurDao      utilisateurDao;
    
    private static final String ALGO_CHIFFREMENT = "SHA-256";


    public InscriptionForm( UtilisateurDao utilisateurDao ) {
        this.utilisateurDao = utilisateurDao;
    }

    public String getResultat() {
        return resultat;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }
    
    public Utilisateur inscrireUtilisateur( HttpServletRequest request ) {
        String email = getValeurChamp( request, CHAMP_EMAIL );
        String mdp = getValeurChamp( request, CHAMP_PASS );
        String confirmation = getValeurChamp( request, CHAMP_CONF );
        String pseudo = getValeurChamp( request, CHAMP_PSEUDO );

        Utilisateur utilisateur = new Utilisateur();
        try {
            traiterEmail( email, utilisateur );
            traiterMdp( mdp, confirmation, utilisateur );
            traiterPseudo( pseudo, utilisateur );

            if ( erreurs.isEmpty() ) {
            	System.out.println(erreurs);
                utilisateurDao.creer( utilisateur );
                resultat = "Succ�s de l'inscription.";
            } else {
                resultat = "�chec de l'inscription. "+erreurs;
            }
        } catch ( DAOException e ) {
            resultat = "�chec de l'inscription : une erreur impr�vue est survenue, merci de r�essayer dans quelques instants.";
            e.printStackTrace();
        }

        return utilisateur;
    }
    
    private void traiterEmail( String email, Utilisateur utilisateur ) {
        try {
            validationEmail( email );
        } catch ( FormValidationException e ) {
        	System.out.println("duplicata détecté");
            setErreur( CHAMP_EMAIL, e.getMessage() );
        }
        utilisateur.setEmail( email );
    }


    private void traiterMdp( String mdp, String confirmation, Utilisateur utilisateur ) {
        try {
            validationMdp( mdp, confirmation );
        } catch ( FormValidationException e ) {
            setErreur( CHAMP_PASS, e.getMessage() );
            setErreur( CHAMP_CONF, null );
        }

       
        ConfigurablePasswordEncryptor passwordEncryptor = new ConfigurablePasswordEncryptor();
        passwordEncryptor.setAlgorithm( ALGO_CHIFFREMENT );
        passwordEncryptor.setPlainDigest( false );
        String mdpChiffre = passwordEncryptor.encryptPassword( mdp );

        utilisateur.setMdp( mdpChiffre );
    }

   
    private void traiterPseudo( String pseudo, Utilisateur utilisateur ) {
        try {
            validationPseudo( pseudo );
        } catch ( FormValidationException e ) {
            setErreur( CHAMP_PSEUDO, e.getMessage() );
        }
        utilisateur.setPseudo( pseudo );
    }

    private void validationEmail( String email ) throws FormValidationException {
        if ( email != null ) {
            if ( !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
                throw new FormValidationException( "Merci de saisir une adresse mail valide." );
            } else if ( utilisateurDao.trouver( email ) != null ) {
                throw new FormValidationException( "Cette adresse email est déj�  utilisée, merci d'en choisir une autre." );
            }
        } else {
            throw new FormValidationException( "Merci de saisir une adresse mail." );
        }
    }

    private void validationMdp( String mdp, String confirmation ) throws FormValidationException {
        if ( mdp != null && confirmation != null ) {
            if ( !mdp.equals( confirmation ) ) {
                throw new FormValidationException( "Les mots de passe entrés sont différents, merci de les saisir �  nouveau." );
            } else if ( mdp.length() < 3 ) {
                throw new FormValidationException( "Les mots de passe doivent contenir au moins 3 caractères." );
            }
        } else {
            throw new FormValidationException( "Merci de saisir et confirmer votre mot de passe." );
        }
    }

    private void validationPseudo( String pseudo ) throws FormValidationException {
        if ( pseudo != null && pseudo.length() < 3 ) {
            throw new FormValidationException( "Le pseudo doit contenir au moins 3 caractères." );
        }
    }

    private void setErreur( String champ, String message ) {
        erreurs.put( champ, message );
    }

    
    private static String getValeurChamp( HttpServletRequest request, String pseudoChamp ) {
        String valeur = request.getParameter( pseudoChamp );
        if ( valeur == null || valeur.trim().length() == 0 ) {
            return null;
        } else {
            return valeur;
        }
    }
}