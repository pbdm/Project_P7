package ModeleUML;

import java.util.ArrayList;

public class test {
	public static void main(String[] args) {  
		ArrayList<ClasseUML> classe = new ArrayList<ClasseUML>();
		ArrayList<AssociationUML> association = new ArrayList<AssociationUML>();
	//	ArrayList<OperationUML> listoperation = new ArrayList<OperationUML>(); 
	
		ClasseUML c1 = new ClasseUML("personne","package1");
		AttributUML a1 = new AttributUML("String","name","\"peng\"");
		AttributUML a2 = new AttributUML("Int","id","1");
		c1.addAttr(a1);
		c1.addAttr(a2);
		OperationUML o1 = new OperationUML("void","setId");
		ParametreUML p1 = new ParametreUML("Int","i");
		ParametreUML p2 = new ParametreUML("String","j");
		o1.addParam(p1);
		o1.addParam(p2);
		c1.addOp(o1);
		
		
		OperationUML o2 = new OperationUML("int","getId");
		ParametreUML p3 = new ParametreUML("Int","i");
		ParametreUML p4 = new ParametreUML("String","j");
		o2.addParam(p3);
		o2.addParam(p4);
		c1.addOp(o2);
		
		
		ClasseUML c2  = new ClasseUML("personne2","package1");
		c2.addAttr(a1);
		
		classe.add(c1);
		classe.add(c2);
		
		
		
		
		String path;
		path = "D:\\test.java";
		Output op = new Output(classe,association,"D:\\test.java");
		op.writeFile();
//		XML xml = new XML();
//		xml.BuildXMLDoc(classe,association);
		System.out.println("ooooooooooooooop");
	}
}
