package jeux;



public class Jeu {
	private int idJ;
	private String titre;
	private String image;
	private int anneeSortie;  //Essayer d'ajuster le code avec type Date
	private String studio;
	private String description; //Type String peut-être léger	
	
	
	public Jeu(int idJ, String titre, String image, int anneeSortie, String studio, String description) {
		super();
		this.idJ = idJ;
		this.titre = titre;
		this.image = image;
		this.anneeSortie = anneeSortie;
		this.studio = studio;
		this.description = description;
	}
	
	
	public Jeu() {
		super();
	}


	public int getIdJ() {
		return idJ;
	}
	public void setIdJ(int idJ) {
		this.idJ = idJ;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	public int getAnneeSortie() {
		return anneeSortie;
	}


	public void setAnneeSortie(int anneeSortie) {
		this.anneeSortie = anneeSortie;
	}


	public String getStudio() {
		return studio;
	}
	public void setStudio(String studio) {
		this.studio = studio;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	} 
	
	
}