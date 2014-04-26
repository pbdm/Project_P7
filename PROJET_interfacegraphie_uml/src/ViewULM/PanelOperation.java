package ViewULM;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ControlerUML.EcouteurSetParametre;

public class PanelOperation extends JPanel{
	//AttributUML attributUML;
	JLabel lType;
	JLabel lNom;
	
	JTextField tType;
	JTextField tNom;
	PanelParametre pParametre;
	public ArrayList<PanelParametre> liste_p_para;
	Integer cParametre;
	ViewClasse vc;
	Integer cOperation;
	
	
	public PanelOperation(String type,String nom,ViewClasse viewClasse, Integer cp){
		this.vc=viewClasse;
		this.cOperation=cp;
		liste_p_para = new ArrayList<PanelParametre>();
		
		if (vc.classeUML.getListeOp().size()!=0){
			//System.out.println("size:"+vc.classeUML.getListeOp().get(cOperation).getListeParam().size());
		//	if(vc.classeUML.getListeOp().get(cOperation).getListeParam().size()!=0)
			for(int j=0;j<vc.classeUML.getListeOp().get(cOperation).getListeParam().size();j++){
				PanelParametre pa = new PanelParametre();
				pa.tNom.setText(vc.classeUML.getListeOp().get(cOperation).getListeParam().get(j).getNom());
				pa.tType.setText(vc.classeUML.getListeOp().get(cOperation).getListeParam().get(j).getType());
				liste_p_para.add(pa);
			}
		}
		
	
	//	liste_p_para = new ArrayList<PanelParametre>();
		setLayout(new GridLayout(1,0));
		lType = new JLabel("Type:");
		tType = new JTextField();
		lNom = new JLabel("Nom:");
		tNom = new JTextField();
		tType.setText(type);
		tNom.setText(nom);
		pParametre = new PanelParametre();
		add(lType);
		add(tType);
		add(lNom);
		add(tNom);
		JButton bSetParametre = new JButton("Set Parametre");		
		bSetParametre.addActionListener(new EcouteurSetParametre(this));
		add(bSetParametre);	    
	}

	
}