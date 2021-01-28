package jeux;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;



//Couche métier de l'application, reliée à la base de donnée DB_DAOperso
//et plus particulièrement au tableau éponyme.
public class Jeux {
	// Son seul rôle est donc de récupérer la liste des jeux.
	/*Dans la classe étudiants :
	 * - On charge le driver(on utilise Class du java util, pas lang)
	 * 		try {
	 * 			Class.forName("com.mysql.cj.jdbc.Driver");
	 * 		} catch (ClassNotFoundException e) {syso("Le driver n'est pas chargé.");}
	 *
	 * - On doit définir 3 objets :
 	 * 		- Connexion : représente la connexion à la base de donnée
	 * 		- Statement : permet d'écrire les requêtes sql
	 * 		- ResultSet : sauvegarde les résultats pour les transmettre à la jsp
	 */
	private static ResourceBundle propertiesSgbd=ResourceBundle.getBundle("fr.polytech.properties.config");
	private static String driverClass = propertiesSgbd.getString("sgbd.driver");
	private static String url = propertiesSgbd.getString("sgbd.url");
	private static String user = propertiesSgbd.getString("sgbd.user");
	private static String secret = propertiesSgbd.getString("sgbd.secret");
	
	private static Connection connection;

	public static void seConnecter() {
		//Factorisation de la connexion
		//Pas idéal, on est quand même obligé d'appeler un bout de code à tous les débuts de méthode.
		try {
			Class.forName(driverClass);	//Donne une erreur avant la def de forName, c'est normal
		} catch (ClassNotFoundException e) {
			System.out.println("Le driver MySQL n'est pas chargé.");
		}

		try {
		// Connexion
			connection = DriverManager.getConnection(url, user, secret);
		} catch	(SQLException e) {
			System.out.println("Problème de connexion à db_daoperso. "+e);
		}
	}
	
	//Modifier un jeu
	//Supprimer un jeu
	//Renvoyer une liste de jeux à partir d'une recherche textuelle (paginer les résultats avec bootstrap?)
	//Envoyer le tout sur git dans une branche "test1"

	// Liste tous les jeux
	public List<Jeu> listerTousLesJeux(){
		List<Jeu> resultat = new ArrayList<Jeu>();

		seConnecter();
		
		// Initialisation
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			statement = connection.createStatement();
		// Exécuter une requête
			resultSet = statement.executeQuery("SELECT * FROM `jeux`");
		// Récupération des données
			while(resultSet.next()) {
				int idJ = resultSet.getInt("idJ");
				String titre = resultSet.getString("titre");
				String image = resultSet.getString("image");
				int anneeSortie = resultSet.getInt("anneeSortie");
				String studio = resultSet.getString("studio");
				String description = resultSet.getString("description");
				resultat.add(new Jeu(idJ, titre, image, anneeSortie, studio, description));
			}
		} catch (SQLException e) {
			System.out.println("Problème de connexion à db_daoperso. "+e);
		} finally {
		//Clore la connexion
			try{
				if (connection!=null) {connection.close();}
				if (statement!=null) {statement.close();}
				if (resultSet!=null) {resultSet.close();}
			} catch (Exception e) {System.out.println("Problème de fin de connexion.");}
		}
		//Envoie des données extraites
		return resultat;
	}
	
	// Liste les jeux dont le titre ou le genre correspondent au moins partiellement à la recherche
	public List<Jeu> listerLike(HttpServletRequest request) {
		return listerLike(request.getParameter("search"));
	}
	public List<Jeu> listerLike(String condition){
		List<Jeu> resultat = new ArrayList<Jeu>();

		seConnecter();
		
		// Initialisation
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			statement = connection.createStatement();
		// Exécuter une requête
			String requeteSQL="SELECT * FROM `jeux` WHERE `titre` LIKE '%"+condition+"%' OR `image` LIKE '%"+condition+"%' OR `anneeSortie` LIKE '%"+condition+"%' OR `studio` LIKE '%"+condition+"%' OR `description` LIKE '%"+condition+"'";
			resultSet = statement.executeQuery(requeteSQL);
			System.out.println(requeteSQL);
		// Récupération des données
			while(resultSet.next()) {
				int idJ = resultSet.getInt("idJ");
				String titre = resultSet.getString("titre");
				String image = resultSet.getString("image");
				int anneeSortie = resultSet.getInt("anneeSortie");
				String studio = resultSet.getString("studio");
				String description = resultSet.getString("description");
				resultat.add(new Jeu(idJ, titre, image, anneeSortie, studio, description));
			}
		} catch (SQLException e) {
			System.out.println("Problème de connexion à db_daoperso. "+e);
		} finally {
		//Clore la connexion
			try{
				if (connection!=null) {connection.close();}
				if (statement!=null) {statement.close();}
				if (resultSet!=null) {resultSet.close();}
			} catch (Exception e) {System.out.println("Problème de fin de connexion.");}
		}
		//Envoie des données extraites
		return resultat;
	}

	
	// Ajoute un jeu à la base de donnée.
	public void ajouterJeu(Jeu jeu) {
		ajouterJeu(jeu.getTitre(), jeu.getAnneeSortie(), jeu.getStudio(), jeu.getDescription());
	}
	public void ajouterJeu(HttpServletRequest request) {
		ajouterJeu(request.getParameter("addTitre"), Integer.parseInt(request.getParameter("addAnneeSortie")), request.getParameter("addStudio"), request.getParameter("addDescription"));	
	}
	public void ajouterJeu(String titre, int anneeSortie, String studio, String description) {
		String requeteSQL="INSERT INTO `jeux`(`titre`,`anneeSortie`, `studio`, `description` ) VALUES ('"+titre+"','"+anneeSortie+"','"+studio+"','"+description+"')";

		seConnecter();
		
		// Initialisation
		PreparedStatement prepstatement = null;
		ResultSet resultSet = null;
		
		try {
		//Prévention des failles d'injection SQL via prepareStatement
			prepstatement = connection.prepareStatement("INSERT INTO `jeux`(`titre`,`anneeSortie`,`studio`,`description`) VALUES (?,?,?,?);");
		// Construire la requête
			prepstatement.setString(1, titre);
			prepstatement.setInt(2, anneeSortie);
			prepstatement.setString(3, studio);
			prepstatement.setString(4, description);
		// Exécution de la requête
			prepstatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Problème de connexion à db_daoperso. "+e+requeteSQL);
		} finally {
			try{
				if (connection!=null) {connection.close();}
				if (prepstatement!=null) {prepstatement.close();}
				if (resultSet!=null) {resultSet.close();}
			} catch (Exception e) {System.out.println("Problème de fin de connexion.");}
		}
	}

	// Efface un jeu de la base de donnée.
	public void retirerJeu(HttpServletRequest request) {
		String requeteSQL="DELETE FROM `jeux` WHERE titre='"+request.getParameter("rmTitre")+"';";
		
		seConnecter();
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			statement = connection.createStatement();
			statement.executeUpdate(requeteSQL);
		} catch (Exception e) {
			System.out.println("Problème de connexion à db_daoperso. "+e+requeteSQL);
		} finally {
			try{
				if (connection!=null) {connection.close();}
				if (statement!=null) {statement.close();}
				if (resultSet!=null) {resultSet.close();}
			} catch (Exception e) {System.out.println("Problème de fin de connexion.");}
		}
		
	}
	
	// Modifie un jeu de la base de donnée
	public void modifierJeu(HttpServletRequest request) {
		String requeteSQL="UPDATE `jeux` SET `"+request.getParameter("updChamps")
		+"`='"+request.getParameter("updValeur")+"'WHERE `titre`='"+request.getParameter("updTitre")+"';"; 
		
		// Ouverture connexion
		seConnecter();
		Statement statement =null;
		ResultSet resultSet=null;
		// Traitement requête
		try {
			statement = connection.createStatement();
			statement.executeUpdate(requeteSQL);
		} catch (Exception e) {
			System.out.println("Problème de connexion à db_daoperso. "+e+requeteSQL);
		} 
		// Fermeture de la connexion
		finally { 
			try{
				if (connection!=null) {connection.close();}
				if (statement!=null) {statement.close();}
				if (resultSet!=null) {resultSet.close();}
			} catch (Exception e) {System.out.println("Problème de fin de connexion.");}
		}
	}
		
		
	
	



}
