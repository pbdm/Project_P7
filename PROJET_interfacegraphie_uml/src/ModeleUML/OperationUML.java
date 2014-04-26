package ModeleUML;

import java.util.ArrayList;
//
public class OperationUML{
	String type; 
	String nomOp;
	ArrayList<ParametreUML> listeParam;
	
	public OperationUML(String type,String nomOp){
		this.nomOp = nomOp;
		this.type = type;
		listeParam=new ArrayList<ParametreUML>();
		
	}
	//les methodes
	public ArrayList<ParametreUML> getListeParam(){
		return listeParam;
	}
	public String getNom(){
		return nomOp;
	}
	
	public String getType(){
		return type;
	}
	//*******************setteur
	public void setNom(String n){
		this.nomOp = n;
	}
	public void setType(String t){
		this.type = t;
	}
	public void addParam(ParametreUML para){
		listeParam.add(para);
	}
	
	public void delParam(Integer c){
		listeParam.remove(c);
	}

}


 