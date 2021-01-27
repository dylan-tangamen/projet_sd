package forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import beans.Utilisateur;

public final class ConnexionForm {
    private static final String CHAMP_EMAIL  = "email";
    private static final String CHAMP_PASS   = "motdepasse";

    private String              resultat;
    private Map<String, String> erreurs      = new HashMap<String, String>();

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

        try {
            validationEmail( email );
            System.out.println("email set");
        } catch ( Exception e ) {
            setErreur( CHAMP_EMAIL, e.getMessage() );
            System.out.println("email erreur ");
        }
        utilisateur.setEmail( email );

        try {
            validationMdp( mdp );
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
        }
    }

    private void validationMdp( String mdp ) throws Exception {
        if ( mdp != null ) {
            if ( mdp.length() < 3 ) {
                throw new Exception( "Le mot de passe doit contenir au moins 3 caract�res." );
            }
        } else {
            throw new Exception( "Merci de saisir votre mot de passe." );
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