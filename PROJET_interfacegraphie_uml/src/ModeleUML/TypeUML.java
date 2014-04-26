package ModeleUML;

import java.util.ArrayList;

class TypeUML{
	private final ArrayList<String> list_type;

	public TypeUML(){
		list_type = new ArrayList<String>();
		list_type.add("int");
		list_type.add("float");
		list_type.add("String");
	}
	
	public boolean isType(String type){
		for(int i = 0; i<list_type.size(); i++) {
			if (type.equals(list_type.get(i))){
				return true;
				}
		}
		return false;
	}
	public void addType(String type){
		list_type.add(type);
	}
	
	
}
