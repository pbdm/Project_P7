package ModeleUML;
import java.util.ArrayList;
public class ClasseUML
{
	private String nom;
	private String nomPaquet;

	private ArrayList<AttributUML> liste_attr;
	private ArrayList<OperationUML> liste_op;
	
	public ClasseUML(String nom) {
		this.nom = nom;
		this.nomPaquet = null;
		this.liste_attr = new ArrayList<AttributUML>();
		this.liste_op = new ArrayList<OperationUML>();
	}
	
	public ClasseUML(String nom,String nomP) {
		this.nom = nom;
		this.nomPaquet = nomP;
		this.liste_attr = new ArrayList<AttributUML>();
		this.liste_op = new ArrayList<OperationUML>();
	}
	
	
	
	
	/***************************Getteurs***********************************/
	
	public String getNom() { return nom; }
	public String getNomP() { return nomPaquet; }
	
	public ArrayList<AttributUML> getListeAttr() {
		return liste_attr;
		
	}
	
	public ArrayList<OperationUML> getListeOp() {
		return liste_op;
		
	}
	public void afficherListeAttr() {
		for(int i = 0; i<liste_attr.size(); i++) {
			System.out.println("op"+i+"->"+liste_attr.get(i));
		}
	}
	
	public void afficherListeOp() {
		for(int i = 0; i<liste_op.size(); i++) {
			System.out.println("op"+i+"->"+liste_op.get(i).getNom());
		}
	}
	
	
	/*************************Setteurs***************************/
	public void setNom(String n) { nom = n; }
	public void setNomP(String n) { nomPaquet = n; }

	
	/**************Methodes generales************/
	
	public void addAttr(AttributUML a) {
		liste_attr.add(a);
	}
	public void delAttr(Integer c) {
		liste_attr.remove(c);
	}
	
	public void addOp(OperationUML op) {
		liste_op.add(op);
	}
	public void delOp(Integer c) {
		liste_op.remove(c);
	}


}
