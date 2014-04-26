package ViewULM;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import java.util.ArrayList;


import javax.swing.BorderFactory;

import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.JTextField;
import javax.swing.border.Border;


import ControlerUML.EcouteurClasse;
import ControlerUML.EcouteurTest;
import ModeleUML.AttributUML;
import ModeleUML.ClasseUML;
import ModeleUML.OperationUML;

public class ViewClasse extends JPanel {
	public ClasseUML classeUML;
	private JTextField tNom;
	ManagerUndo man;
	ModelUndo m;
	JPanel pAttribut;
	JPanel pOperation;
	public VueMVC vMVC;
	public ViewClasse(ModelUndo m,ManagerUndo man ,VueMVC vueMVC,ClasseUML cU){
		this.vMVC=vueMVC;
		this.man=man;
		this.m =m;
		this.classeUML=cU;
		addMouseListener(new EcouteurClasse(this));
	//	classeUML = new ClasseUML(nomClass);
	//	vMVC.classe.add(classeUML);
		setBounds(50, 10,200, 300);
		setPreferredSize(new Dimension(200,200));
		setBackground(Color.gray);
		Border border= BorderFactory.createLineBorder(Color.PINK);
		JPanel pNom=new JPanel(); pNom.setBorder(border);     //panel du nom
        JPanel pAO =new JPanel(); pAO.setLayout(new GridLayout(2,1));
		pAttribut=new JPanel(new GridLayout(0,1)); pAttribut.setBorder(border);  //panel du attribut
	//	pAttribut.add(new JLabel("Attribut"));
        pOperation=new JPanel(new GridLayout(0,1));  pOperation.setBorder(border);  //panel du operation
      //  pOperation.add(new JLabel("Operation"));
        setLayout(new BorderLayout()); 
        add(pNom,BorderLayout.NORTH);
        add(pAO,BorderLayout.CENTER);
        
        pAO.add(pAttribut);
        
        pAO.add(pOperation);
       
        tNom = new JTextField();
        pNom.add(tNom);
        tNom.setText(classeUML.getNom());
        tNom.setEnabled(false);
        tNom.addActionListener(new EcouteurTest(m,man));
        repaint();
	}
	
	

//	public String getNom(){
//		return tNom.getText();
//	}
	public void setNom(String nom){
		tNom.setText(nom);
		classeUML.setNom(nom);
	}
//	public ArrayList<AttributUML> getAttribut(){
//		return classeUML.getListeAttr()
//	}
	public void setAttribut(ArrayList<AttributUML> liste_attr){
		pAttribut.removeAll();
		for(int i = 0; i<liste_attr.size(); i++) {
			pAttribut.add(new JLabel("Attribut:Nom:"+liste_attr.get(i).getNom()+
									"  Type:"+liste_attr.get(i).getType()+
									"  Valeur:"+liste_attr.get(i).getValeur()));
		}
	}
	public void setOperation(ArrayList<OperationUML> liste_oper){
		pOperation.removeAll();
		for(int i = 0; i<liste_oper.size(); i++) {
			pOperation.add(new JLabel("Operation:Nom:"+liste_oper.get(i).getNom()+
									"  Type:"+liste_oper.get(i).getType()
									));
		}
	}
}
