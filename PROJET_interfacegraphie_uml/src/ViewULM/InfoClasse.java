package ViewULM;


import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JPanel;

import javax.swing.JTextField;


import ControlerUML.EcouteurOperation;
import ModeleUML.AttributUML;
import ModeleUML.OperationUML;
import ModeleUML.ParametreUML;

public class InfoClasse extends JFrame{
	ViewClasse vc;
	JPanel jp;
	JPanel pAttribut;
	public JPanel pOperation;
	JTextField tNom;
	ArrayList<PanelAttribut> liste_p_attr;
	Integer cAttribut;
	public ArrayList<PanelOperation> liste_p_oper;
	public Integer cOperation;
	public InfoClasse(ViewClasse viewClasse){
		cAttribut =0;
		cOperation =0;
		liste_p_attr = new ArrayList<PanelAttribut>();
		liste_p_oper = new ArrayList<PanelOperation>();
		this.vc= viewClasse;
		jp = new JPanel();
		//jp.setLayout(new GridLayout(1,0));
		jp.add(new JLabel("Nom"));
		tNom = new JTextField(vc.classeUML.getNom());
		jp.add(tNom);
		jp.add(new JLabel("Attribut"));
		JButton bAddAttribut = new JButton("Add Attribut");
		JButton bDelAttribut = new JButton("Del Attribut");
		JButton bAddOperation = new JButton("Add Operation");
		JButton bDelOperation = new JButton("Del Operation");
		pAttribut = new JPanel(new GridLayout(0,1));
		pOperation = new JPanel(new GridLayout(0,1));
		jp.add(pAttribut);
		jp.add(new JLabel("Operation"));
		
		jp.add(pOperation);
		pAttribut.add(bAddAttribut);
		pAttribut.add(bDelAttribut);
		pOperation.add(bAddOperation);
		pOperation.add(bDelOperation);
		//initalisation du panel du attribut
		for(int i=0;i<vc.classeUML.getListeAttr().size();i++){
			liste_p_attr.add(new PanelAttribut(vc.classeUML.getListeAttr().get(i).getType(),
					vc.classeUML.getListeAttr().get(i).getNom(),
					vc.classeUML.getListeAttr().get(i).getValeur()
					));
			pAttribut.add(liste_p_attr.get(cAttribut));
			cAttribut++;
		}
		//initalisation du panel du operation
		for(int i=0;i<vc.classeUML.getListeOp().size();i++){
			liste_p_oper.add(new PanelOperation(vc.classeUML.getListeOp().get(i).getType(),
					vc.classeUML.getListeOp().get(i).getNom(),vc,cOperation
					));
			pOperation.add(liste_p_oper.get(cOperation));
			
				
			cOperation++;
		}
		
		bAddAttribut.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				liste_p_attr.add(new PanelAttribut());
				pAttribut.add(liste_p_attr.get(cAttribut));//vue
				vc.classeUML.addAttr(new AttributUML(liste_p_attr.get(cAttribut).tType.getText(),//model
							liste_p_attr.get(cAttribut).tNom.getText(),
							liste_p_attr.get(cAttribut).tValeur.getText()
							));
				cAttribut++;
				pack();
			}
			
		});
		
		bDelAttribut.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (cAttribut!=0){
					cAttribut--;
					pAttribut.remove(liste_p_attr.get(cAttribut));
					liste_p_attr.remove(liste_p_attr.get(cAttribut));
					
				}
				pack();
			}
			
		});
		bAddOperation.setActionCommand("ADD");
		bAddOperation.addActionListener(new EcouteurOperation(this,vc));
		
		bDelOperation.setActionCommand("DEL");
		bDelOperation.addActionListener(new EcouteurOperation(this,vc));
		
		
		
		
		
		JButton bValider = new JButton("Valider");
		bValider.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				//mis - a jour le base
				vc.classeUML.setNomP(tNom.getText());
				vc.classeUML.getListeAttr().clear();
				vc.classeUML.getListeOp().clear();
				for(int i = 0; i<cAttribut; i++) {
					AttributUML attributUML =new AttributUML(liste_p_attr.get(i).tType.getText(),
							liste_p_attr.get(i).tNom.getText(),
							liste_p_attr.get(i).tValeur.getText()
							);
					
					vc.classeUML.getListeAttr().add(attributUML);
					
				}
				for(int i = 0; i<cOperation; i++) {
					
					OperationUML operationUML =new OperationUML(liste_p_oper.get(i).tType.getText(),
							liste_p_oper.get(i).tNom.getText()
							);
					for(int j=0;j<liste_p_oper.get(i).liste_p_para.size();j++){
						ParametreUML parametreUML = new ParametreUML(liste_p_oper.get(i).liste_p_para.get(j).tType.getText(),
						liste_p_oper.get(i).liste_p_para.get(j).tNom.getText());
						operationUML.addParam(parametreUML);
						}
					vc.classeUML.getListeOp().add(operationUML);
					
				}
				//mis - a - jour le vue
				vc.setNom(tNom.getText());
			
				vc.setAttribut(vc.classeUML.getListeAttr());
				vc.setOperation(vc.classeUML.getListeOp());
				setVisible(false);
				vc.vMVC.pack();
				vc.vMVC.repaint();

			}
			
		});
		JButton bAnnuler = new JButton("Annuler");
		jp.add(bValider);
		jp.add(bAnnuler);
		
		
		this.add(jp);
		
		
		
		this.pack();
		this.setVisible(true);
	}
	
}
