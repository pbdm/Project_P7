package ControlerUML;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ViewULM.InfoClasse;
import ViewULM.ViewClasse;
import ViewULM.VueMVC;

public class EcouteurEdition implements ActionListener{
	ViewClasse vc;
	VueMVC vm;
	public EcouteurEdition(ViewClasse viewClasse,VueMVC vueMVC){
		this.vc=viewClasse;
		this.vm=vueMVC;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
//		m1.setActionCommand("inserer");
//        m2.setActionCommand("delete");
//        m3.setActionCommand("annuler");
//        m4.setActionCommand("copier");
//        m5.setActionCommand("couper");
//        m6.setActionCommand("coller");
		if(e.getActionCommand().equals("inserer")){
			InfoClasse ic = new InfoClasse(vc);
		 }
		if(e.getActionCommand().equals("delete")){
			vc.vMVC.delClass(vc);
			vc.vMVC.classe.remove(vc.classeUML);
			vc.vMVC.pack();
		 }
		if(e.getActionCommand().equals("annuler")){
			
		 }
		if(e.getActionCommand().equals("copier")){
			vc.vMVC.viewClassecopied=new ViewClasse(null,null,vc.vMVC,vc.classeUML);
				vc.vMVC.canColler=true;
		 }
		if(e.getActionCommand().equals("couper")){
			vc.vMVC.viewClassecopied=new ViewClasse(null,null,vc.vMVC,vc.classeUML);
				vc.vMVC.classe.remove(vc.classeUML);
				vc.vMVC.panel.remove(vc);
				vc.vMVC.canColler=true;
				vc.vMVC.panel.repaint();
				vc.vMVC.pack();
		 }
		if(e.getActionCommand().equals("coller")){
			if (!vc.equals(null))
				vc.vMVC.cClass();
			else
				vm.cClass();
		 }
			 
			        
	}

}
