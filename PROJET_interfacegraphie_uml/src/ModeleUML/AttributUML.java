package ModeleUML;


public class AttributUML {

	/******Attributs************/
	private String type;
	private String nom;
    private String valeur;
	
	
	/**************************Constructeurs********************************/

    public AttributUML(String type,String nom) {
		this.type = type;
    	this.nom = nom;
		this.valeur = "0";

	}
    
    
	public AttributUML(String type,String nom ,String valeur) {
		this.type = type;
		this.nom = nom;
		this.valeur = valeur;

	}
	
	/****************************Getteurs*********************************/
	public String getType() { return type; }
	public String getNom() { return nom; }
	public String getValeur() { return valeur; }
	
	
	
	
	/****************************Setteurs*******************************/
	public void setNom(String n) { nom = n; }
	public void setType(String t) { type = t; }
	public void setValeur(String v) { valeur = v; }
	
}