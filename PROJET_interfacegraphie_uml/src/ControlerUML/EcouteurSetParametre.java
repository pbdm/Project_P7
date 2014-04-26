package ControlerUML;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ViewULM.FrameParametre;
import ViewULM.PanelOperation;

public class EcouteurSetParametre implements ActionListener{
	PanelOperation panelOperation;
	public EcouteurSetParametre(PanelOperation op){
		this.panelOperation=op;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		new FrameParametre(panelOperation);
////		System.out.println("cOperation"+infoClasse.cOperation);
////		if(vc.classeUML.getListeOp().size()!=0){
//			System.out.println(vc.classeUML.getListeOp().size());
//			for(int j=0;j<vc.classeUML.getListeOp().get(infoClasse.cOperation).getListeParam().size();j++){
//				po.liste_p_para.get(j).tNom.setText(vc.classeUML.getListeOp().get(infoClasse.cOperation).getListeParam().get(j).getNom());
//				po.liste_p_para.get(j).tType.setText(vc.classeUML.getListeOp().get(infoClasse.cOperation).getListeParam().get(j).getType());
//			}
//		}
		//System.out.println(cOperation);
		
	}

}
