package ModeleUML;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

public class XML {
	String nomFichier;
	public ArrayList<ClasseUML> classe;
	public ArrayList<AssociationUML> association;
	public XML(String nomF){
		this.nomFichier=nomF;
	}
	public void BuildXMLDoc(ArrayList<ClasseUML> classe,ArrayList<AssociationUML> association)
	{	Element root = new Element("root");
		Document Doc = new Document(root);   

		
		
		//add classe
		for(int i = 0; i<classe.size(); i++) {
			Element classUML = new Element("Class"); 
			classUML.setAttribute("Name",classe.get(i).getNom()); 
		//	classUML.setAttribute("Package",classe.get(i).getNomP()); 
			
			//add attribut
			for(int j = 0; j<classe.get(i).getListeAttr().size(); j++){
				Element attributUML = new Element("Attribute");
				attributUML.setAttribute("Name",classe.get(i).getListeAttr().get(j).getNom());
				attributUML.setAttribute("Type",classe.get(i).getListeAttr().get(j).getType());
				attributUML.setAttribute("Valeur",classe.get(i).getListeAttr().get(j).getValeur());
				classUML.addContent(attributUML);
			}
			

			//add operation
			for(int j = 0; j<classe.get(i).getListeOp().size(); j++){
				Element operationUML = new Element("Operation");
				operationUML.setAttribute("Name",classe.get(i).getListeOp().get(j).getNom());
				operationUML.setAttribute("Type",classe.get(i).getListeOp().get(j).getType());
				// add parametre
				for(int k = 0; k<classe.get(i).getListeOp().get(j).getListeParam().size(); k++){
					Element parametreUML = new Element("Parametre");
					parametreUML.setAttribute("Type",classe.get(i).getListeOp().get(j).getListeParam().get(k).getType());
					parametreUML.setAttribute("Nom",classe.get(i).getListeOp().get(j).getListeParam().get(k).getNom());
					
					operationUML.addContent(parametreUML);
				}
				
				
				classUML.addContent(operationUML);
			}
		
			root.addContent(classUML);
		}
		
		//add association
		for(int i = 0; i<association.size(); i++) {
			Element associationUML =  new Element("Association");
			associationUML.setAttribute("Name",association.get(i).getNom());
			associationUML.setAttribute("ClassA",association.get(i).getClassA().getNom());
			associationUML.setAttribute("ClassB",association.get(i).getClassB().getNom());
			root.addContent(association);
			
		}
		 XMLOutputter XMLOut = new XMLOutputter();  
         
	        try {
				XMLOut.output(Doc, new FileOutputStream(nomFichier));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	}
		
		public void parserXml(){
			classe = new ArrayList<ClasseUML>();
			association = new ArrayList<AssociationUML>();
			
			SAXBuilder builder=new SAXBuilder(false); 
			try {
				Document document=builder.build(nomFichier);
				Element root = document.getRootElement();
				//ANALYSE LES CLASS
				List listClass = root.getChildren("Class");
				for(int i=0;i<listClass.size();i++)
				{
					Element classeE = (Element) listClass.get(i);
					ClasseUML classUML = new ClasseUML(classeE.getAttributeValue("Name"),classeE.getAttributeValue("Package"));
					//ANALYSE LES ATTRIBUT
					List listAttribute = classeE.getChildren("Attribute");
					for(int j=0;j<listAttribute.size();j++){
						Element attributeE = (Element) listAttribute.get(j);
						AttributUML attributUML = new AttributUML(
								attributeE.getAttributeValue("Name"),
								attributeE.getAttributeValue("Type"),
								attributeE.getAttributeValue("Valeur"));
						classUML.addAttr(attributUML);
					}
					//ANALYSE LES OPERATION
					List listOperation = classeE.getChildren("Operation");
					for(int j=0;j<listOperation.size();j++){
						Element operationE = (Element) listOperation.get(j);
						OperationUML operationUML = new OperationUML(
								operationE.getAttributeValue("Name"),
								operationE.getAttributeValue("Type"));
						//ANALYSE LES PARAMETRE
						List listParametre = classeE.getChildren("Parametre");
						for(int k=0;k<listParametre.size();k++){
							Element parametreE = (Element) listParametre.get(k);
							ParametreUML parametreUML = new ParametreUML(
									parametreE.getAttributeValue("Type"),
									parametreE.getAttributeValue("Nom"));
							operationUML.addParam(parametreUML);
						}
						classUML.addOp(operationUML);
					}
					classe.add(classUML);
					
				}
				
				
				//ANALYSE LES ASSOCIATION
				List listAssociation = root.getChildren("Association");
				for(int i=0;i<listAssociation.size();i++)
				{
					
					Element  associationE= (Element) listAssociation.get(i);
					ClasseUML classA = new ClasseUML (associationE.getAttributeValue("ClassA"));
					ClasseUML classB = new ClasseUML (associationE.getAttributeValue("ClassB"));
					AssociationUML assiociationUML = new AssociationUML(associationE.getAttributeValue("Name"),classA,classB);
					
					association.add(assiociationUML);
				}

			} catch (JDOMException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
//	public static void main(String[] args) {  
//			ArrayList<ClasseUML> classeE = new ArrayList<ClasseUML>();
//			ArrayList<AssociationUML> association = new ArrayList<AssociationUML>();
//			
//			ClasseUML classUml = new ClasseUML("BOOK3");
//			classUml.setNomP("pack");
//			ClasseUML classUml2 = new ClasseUML("BOOK2");
//			classUml2.setNomP("pack2");
//			classeE.add(classUml2);
//			classeE.add(classUml);
//			
//			
//			XML j2x = new XML();  
//	        j2x.BuildXMLDoc(classeE,association);  
//		//	j2x.parserXml("uerrr.xml");
//			System.out.println("yyyyyyyyyyyyyyy");
//	    }  
}
