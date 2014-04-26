package ViewULM;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import ControlerUML.EcouteurEdition;

public class MenuPopUpClasse extends JPopupMenu{ 
	JMenuItem m1;
	JMenuItem m2;
	JMenuItem m3;
	JMenuItem m4;
	JMenuItem m5;
	JMenuItem m6;
	ViewClasse vc;
    public MenuPopUpClasse(ViewClasse jp){
    	this.vc = jp;
    	m1=new JMenuItem("inserer/modifier infoClasse!");
    	m2=new JMenuItem("Delete Classe!");
        m3=new JMenuItem("annuler");
        m4=new JMenuItem("copier");
        m5=new JMenuItem("couper");
        m6=new JMenuItem("coller");
        this.add(m1);
        this.add(m2);
        this.add(m3);
        this.add(m4);
        this.add(m5);
        this.add(m6);
        m1.setActionCommand("inserer");
        m2.setActionCommand("delete");
        m3.setActionCommand("annuler");
        m4.setActionCommand("copier");
        m5.setActionCommand("couper");
        m6.setActionCommand("coller");
        EcouteurEdition ec=new EcouteurEdition(this.vc,null);
        m1.addActionListener(ec);
        m2.addActionListener(ec);
        m3.addActionListener(ec);
        m4.addActionListener(ec);
        m5.addActionListener(ec);
        m6.addActionListener(ec);
    }
   		
}
