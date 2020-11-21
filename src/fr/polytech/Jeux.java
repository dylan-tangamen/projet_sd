package fr.polytech;

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
				int id = resultSet.getInt("idJ");
				String titre = resultSet.getString("titre");
				String genre = resultSet.getString("genre");
				resultat.add(new Jeu(id, titre, genre));
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

	public void ajouterJeu(Jeu jeu) {
		ajouterJeu(jeu.getId(),jeu.getTitre(),jeu.getGenre());
	}
	public void ajouterJeu(HttpServletRequest request) {
		ajouterJeu(Integer.parseInt(request.getParameter("addId")), request.getParameter("addTitre"),request.getParameter("addGenre"));	
	}
	public void ajouterJeu(int id, String titre, String genre) {
		String requeteSQL="INSERT INTO `jeux`(`idJ`, `titre`, `genre`) VALUES ("+id+",'"+titre+"','"+genre+"')";

		seConnecter();
		
		// Initialisation
		PreparedStatement prepstatement = null;
		ResultSet resultSet = null;
		
		try {
		//Prévention des failles d'injection SQL via prepareStatement
			prepstatement = connection.prepareStatement("INSERT INTO `jeux`(`idJ`, `titre`, `genre`) VALUES (?,?,?);");
		// Construire la requête
			prepstatement.setInt(1, id);
			prepstatement.setString(2, titre);
			prepstatement.setString(3, genre);
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
	
	public void modifierJeu(HttpServletRequest request) {
		String requeteSQL="UPDATE `jeux` SET `"+request.getParameter("updChamps")
		+"`='"+request.getParameter("updValeur")+"'WHERE `titre`='"+request.getParameter("updTitre")+"';"; 
		
		seConnecter();
		Statement statement =null;
		ResultSet resultSet=null;
		
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
		
		
	
	

}
