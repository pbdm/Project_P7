package ModeleUML;



public class AssociationUML {
	String nom;
	ClasseUML classA;
	ClasseUML classB;
	//String type_relation;
	
	public AssociationUML(String nom,ClasseUML classA,ClasseUML classB) {
		this.classA = classA;
		this.nom = nom;
	}
	
	public String getNom() {
		return nom;
	}
	
	public ClasseUML getClassA() {
		return classA;
	}
	
	public ClasseUML getClassB() {
		return classB;
	}

}
