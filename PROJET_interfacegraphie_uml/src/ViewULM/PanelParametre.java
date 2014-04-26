package ViewULM;


import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class PanelParametre extends JPanel{
	JLabel lType;
	JLabel lNom;
	
	public JTextField tType;
	public JTextField tNom;
	
	public PanelParametre(){
		setLayout(new GridLayout(1,0));
		lType = new JLabel("Type:");
		tType = new JTextField();
		lNom = new JLabel("Nom:");
		tNom = new JTextField();
		add(lType);
		add(tType);
		add(lNom);
		add(tNom);
		
	    
	}
	public PanelParametre(String type,String nom){
		this();
		tType.setText(type);
		tNom.setText(nom);
		
	    
	}
	
}