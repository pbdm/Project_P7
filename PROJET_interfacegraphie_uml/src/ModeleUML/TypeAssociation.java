package ModeleUML;

import java.util.ArrayList;

public class TypeAssociation {
	private final ArrayList<String> listeTypeAssocication;
	
	public  TypeAssociation(){
		listeTypeAssocication = new ArrayList<String>();
		listeTypeAssocication.add("Assocication");
		listeTypeAssocication.add("Composition");
		listeTypeAssocication.add("Agregation");
		
	}
	public boolean isType(String type){
		for(int i = 0; i<listeTypeAssocication.size(); i++) {
			if (type.equals(listeTypeAssocication.get(i))){
				return true;
				}
		}
		return false;
	}
}
