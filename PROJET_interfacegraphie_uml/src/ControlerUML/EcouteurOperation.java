package ControlerUML;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ViewULM.InfoClasse;
import ViewULM.PanelOperation;
import ViewULM.ViewClasse;

public class EcouteurOperation implements ActionListener{
	InfoClasse infoClasse;
	ViewClasse vc;
	
	public EcouteurOperation(InfoClasse ic,ViewClasse viewClasse) { 
		super(); 
		this.infoClasse = ic; 
		this.vc=viewClasse;
		
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand()=="ADD"){
			infoClasse.liste_p_oper.add(new PanelOperation(null,null,vc,infoClasse.cOperation));
//			System.out.println("cOperation"+infoClasse.cOperation);
//			if(vc.classeUML.getListeOp().size()!=0){
//				System.out.println(vc.classeUML.getListeOp().size());
//				for(int j=0;j<vc.classeUML.getListeOp().get(infoClasse.cOperation).getListeParam().size();j++){
//					po.liste_p_para.get(j).tNom.setText(vc.classeUML.getListeOp().get(infoClasse.cOperation).getListeParam().get(j).getNom());
//					po.liste_p_para.get(j).tType.setText(vc.classeUML.getListeOp().get(infoClasse.cOperation).getListeParam().get(j).getType());
//				}
//			}
			
			//vc.classeUML.addOp(new OperationUML(null,null));
			infoClasse.pOperation.add(infoClasse.liste_p_oper.get(infoClasse.cOperation));
			
			infoClasse.cOperation++;
			infoClasse.pack();
		}
		if(e.getActionCommand()=="DEL"){
			if (infoClasse.cOperation!=0){
				
				infoClasse.cOperation--;
				infoClasse.pOperation.remove(infoClasse.liste_p_oper.get(infoClasse.cOperation));
				infoClasse.liste_p_oper.remove(infoClasse.liste_p_oper.get(infoClasse.cOperation));
			}
			infoClasse.pack();
		}
		
	}

}
