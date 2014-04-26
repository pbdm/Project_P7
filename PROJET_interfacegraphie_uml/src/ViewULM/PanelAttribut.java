package ViewULM;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class PanelAttribut extends JPanel{
	JLabel lType;
	JLabel lNom;
	JLabel lValeur;
	JTextField tType;
	JTextField tNom;
	JTextField tValeur;
	//ArrayList<AttributUML> liste_attr;
	public PanelAttribut(){
		setLayout(new GridLayout(1,0));
		lType = new JLabel("Type:");
		tType = new JTextField();
		lNom = new JLabel("Nom:");
		tNom = new JTextField();
		lValeur = new JLabel("Valeur:");
		tValeur = new JTextField();
		
		add(lType);
		add(tType);
		add(lNom);
		add(tNom);
		add(lValeur);
		add(tValeur);
	    
	}
	public PanelAttribut(String type,String nom,String valeur){
		this();
		tType.setText(type);
		tNom.setText(nom);
		tValeur.setText(valeur);
	    
	}
}
