package ModeleUML;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;



public class Output {
	ArrayList<ClasseUML> classe;
	ArrayList<AssociationUML> association;
	String path;
	
	public Output(ArrayList<ClasseUML> classe,ArrayList<AssociationUML> association,String path) {
		this.classe = classe;
		this.association = association;
		this.path = path;
	}
	public void writeFile(){
		File f = new File(path);
		try {
			FileOutputStream fos = new FileOutputStream(f);
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			BufferedWriter bw = new BufferedWriter(osw);
			//write 
			bw.write("//this is un fichier write by TPC");
			bw.newLine();
			for(int i=0;i<classe.size();i++){
				bw.write("class "+classe.get(i).getNom()+"{");//ici on a besoin de ajouter le association..
				bw.newLine();
				//les attributs
				for(int j = 0; j<classe.get(i).getListeAttr().size(); j++){
					bw.write("    ");
					bw.write(classe.get(i).getListeAttr().get(j).getType()+
							 " "+classe.get(i).getListeAttr().get(j).getNom()+" = "+
						     classe.get(i).getListeAttr().get(j).getValeur()+";");
					bw.newLine();
				}
				//les operations
		 		for(int j = 0; j<classe.get(i).getListeOp().size(); j++){
					bw.write("    ");
					bw.write(classe.get(i).getListeOp().get(j).getType()+
							 " "+classe.get(i).getListeOp().get(j).getNom()+"(");
					//les parametres
					for(int k = 0; k<classe.get(i).getListeOp().get(j).getListeParam().size(); k++){
						if (k==0){//the first pas ,
							bw.write(classe.get(i).getListeOp().get(j).getListeParam().get(k).getType()+" "+
									classe.get(i).getListeOp().get(j).getListeParam().get(k).getNom());
						}
						else{
						bw.write(","+classe.get(i).getListeOp().get(j).getListeParam().get(k).getType()+" "+
								classe.get(i).getListeOp().get(j).getListeParam().get(k).getNom());
						}
						
					}
					bw.write("){");
					bw.newLine();
					if (classe.get(i).getListeOp().get(j).getType().equals("void")==false){
						bw.write("        return i");
						bw.newLine();
					}
					bw.write("    }");
					bw.newLine();
						
				}
				bw.newLine();
				bw.write("}");	
			}
		
			
			
			
			
			
			
			
			bw.close();
			osw.close();
			fos.close(); 
			f.createNewFile();
			f.createNewFile();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
}
