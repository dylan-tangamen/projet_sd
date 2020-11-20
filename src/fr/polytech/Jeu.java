package fr.polytech;

public class Jeu {
	private int id;
	private String titre;
	private String genre;
	
	// Constructeurs
	public Jeu() {
		super();
	}
	public Jeu(int id, String titre, String genre) {
		super();
		this.id = id;
		this.titre = titre;
		this.genre = genre;
	}
	
	// Getters/Setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	
	
}
