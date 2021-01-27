package beans;

public class Utilisateur {

		private int idU;
		private String pseudo;
		private String mdp;
	 	private String email;
	    
	    
	    
	    public Utilisateur(int idU, String pseudo, String mdp, String email) {
	    	this.idU = idU ;
	    	this.pseudo = pseudo;
	    	this.mdp = mdp ;
	    	this.email = email ;
	    }
	    
	    public Utilisateur() {
	    	super();
	    }

	    public void setEmail(String email) {
		this.email = email;
	    }
	    public String getEmail() {
		return email;
	    }

	    public void setMdp(String mdp) {
		this.mdp = mdp;
	    }
	    public String getMdp() {
		return mdp;
	    }

	    public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	    }
	    public String getPseudo() {
		return pseudo;
	    }
	    
	    public void setIdU(int idU) {
			this.idU = idU;
		    }
		    public int getIdU() {
			return idU;
		    }
	}

