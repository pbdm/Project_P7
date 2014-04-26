package ModeleUML;

public class ParametreUML {
	String type; 
	String nom;
  
    
	public ParametreUML(String type, String nom){
		this.type = type;
		this.nom = nom;
	}
	public String getType(){
		return type;
	}
	public String getNom(){
		return nom;
	}
	
	public void setNom(String n) { nom = n; }
	public void setType(String t) { type = t; }
	
}

