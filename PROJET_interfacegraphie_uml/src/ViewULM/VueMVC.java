package ViewULM;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.swing.*;



import ControlerUML.EcouteurEdition;
import ControlerUML.EcouteurFile;
import ControlerUML.EcouteurJTB;
import ControlerUML.EcouteurLocalisation;

//import java.awt.*;
import ModeleUML.AssociationUML;
import ModeleUML.ClasseUML;



/**
 * Classe representant le zone a dessin
 */

public class VueMVC extends JFrame{

//	public BufferedImage img;
//	public Graphics g;
	int w,h;
	public Panneau panel;
	public ManagerUndo man;
	public ModelUndo m;
	public ArrayList<ClasseUML> classe;
	public ArrayList<AssociationUML> association;
	public Boolean canNew;
	public String saveName;
	int wLocation=10;
	int hLocation=10;
	JMenu menu2;
	JMenu menu1;
	JMenu menu3;
	JMenu menu4;
	JMenu menu5;
	JMenu menu6;
	JMenuItem nouveau;
	JMenuItem ouvrir;
	JMenuItem enregistrer;
	JMenuItem enregistrerS;
	JMenuItem generation;
	JMenuItem quitter;
	JMenuItem annuler;
	JMenuItem refaire;
	JMenuItem couper;
	JMenuItem copier;
	JMenuItem coller;
	public ViewClasse viewClassecopied;
	public Boolean canColler;
//	ArrayList<Point> pLocation;
	public VueMVC() {
		canNew=true;
		canColler =false;
		saveName = "null";
		classe = new ArrayList<ClasseUML>();
	//	pLocation = new ArrayList<Point>();
		association = new ArrayList<AssociationUML>();
		setTitle("Outil de modelisation UML");
		w = 1024;
		h = 768;
	//	img = new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
//		g = img.getGraphics();
//		g.setColor(new Color(200,200,100));
//		g.fillRect(0, 0, w, h);
		setPreferredSize(new Dimension(w,h));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(700,700);
		setVisible(true);
		
	//	BufferedImage img = vue.getImg();
	//	Graphics g = vue.getG();
		
		/***********************CREATION MENU************************************/
		//Ajout barre de menu
		JMenuBar MBar = new JMenuBar();
				
		//Ajout Menus
		menu2 = new JMenu("Edition");
		menu1 = new JMenu("Fichier");
		menu3 = new JMenu("Diagramme");
		menu4 = new JMenu("Code");
		menu5 = new JMenu("Configuration");
		menu6 = new JMenu("Aide");
		
		JMenuItem fRanch = new JMenuItem("French");
		JMenuItem eNglish = new JMenuItem("English");
		JMenuItem cHinese = new JMenuItem("Chinese");
		EcouteurLocalisation el = new EcouteurLocalisation(this);
		menu5.add(fRanch);
		menu5.add(eNglish);
		menu5.add(cHinese);
		fRanch.setActionCommand("French");
		eNglish.setActionCommand("English");
		cHinese.setActionCommand("Chinese");
		fRanch.addActionListener(el);
		eNglish.addActionListener(el);
		cHinese.addActionListener(el);

		
		//Ajout items
		nouveau = new JMenuItem("Nouveau");
		ouvrir = new JMenuItem("Ouvrir");
		enregistrer = new JMenuItem("Enregistrer");
		enregistrerS = new JMenuItem("Enregistrer Sous...");
		generation = new JMenuItem("G¨¦n¨¦ration de code");
		quitter = new JMenuItem("Quitter");
		quitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(canNew==false){
					int i = JOptionPane.showConfirmDialog(null, "vous n'avez pas enregister!!,continue?");
					if(i == JOptionPane.NO_OPTION) {
						JOptionPane.showMessageDialog(null, "on continue pas");
					}
					else if( i == JOptionPane.CANCEL_OPTION) {
						System.out.println("on annule");
					}
					else {
						System.exit(0);
						}
					}
				
				else
					System.exit(0);
				
				
				
			}
		});
		//ecouteur save save sous open ,generation
		EcouteurFile ef = new EcouteurFile(this);
		nouveau.setActionCommand("nouveau");
		nouveau.addActionListener(ef);
		ouvrir.setActionCommand("ouvrir");
		ouvrir.addActionListener(ef);
		generation.setActionCommand("generation");
		generation.addActionListener(ef);
		enregistrer.setActionCommand("enregistrer");
		enregistrer.addActionListener(ef);
		enregistrerS.setActionCommand("enregistrerS");
		enregistrerS.addActionListener(ef);
		menu1.add(nouveau);
		menu1.add(ouvrir);
		menu1.add(new JSeparator());
		menu1.add(enregistrer);
		menu1.add(enregistrerS);
		menu1.add(new JSeparator());
		menu1.add(quitter);
		
		//Ajout items
		annuler = new JMenuItem("Annuler");
		refaire = new JMenuItem("Refaire");
		couper = new JMenuItem("Couper");
		copier = new JMenuItem("Copier");
		coller = new JMenuItem("Coller");
		
		
		menu2.add(annuler);
		menu2.add(refaire);
		menu2.add(new JSeparator());
		menu2.add(couper);
		menu2.add(copier);
		menu2.add(coller);
		coller.setActionCommand("coller");
		JMenuItem eImage = new JMenuItem("export image");
		eImage.setActionCommand("eImage");
		eImage.addActionListener(ef);
		menu3.add(eImage);
		menu4.add(generation);
		
		MBar.add(menu1);
		MBar.add(menu2);
		MBar.add(menu3);
		MBar.add(menu4);
		MBar.add(menu5);
		MBar.add(menu6);
		
		panel = new Panneau();
		panel.setLayout(null);
		add(panel,BorderLayout.CENTER);
		
		
		nouveau.setMnemonic(KeyEvent.VK_N);
		ouvrir.setMnemonic(KeyEvent.VK_O);
		enregistrer.setMnemonic(KeyEvent.VK_E);
		enregistrerS.setMnemonic(KeyEvent.VK_S);
		generation.setMnemonic(KeyEvent.VK_G);
		quitter.setMnemonic(KeyEvent.VK_Q);
		/*************************CREATION BARRE D'OUTILS****************************/
		JToolBar JTB = new JToolBar();
		JButton bNouveau = new JButton(); //nouveau
		JButton bOuvrir = new JButton(); //ouvrir
		JButton bSauver = new JButton(); //sauver
		JButton bAnnuler = new JButton(); //annuler
		JButton bRefaire = new JButton(); //refaire
		
		man = new ManagerUndo(bAnnuler,bRefaire);
		m = new ModelUndo();
//		
//		LabelUndo l = new LabelUndo();
//		m.addObserver(l);
//		JTB.add(l);
		
		JButton bCouper = new JButton(); //couper
		JButton bCopier = new JButton(); //copier 
		JButton bColler = new JButton(); //coller
		JButton bClass = new JButton(); //class
		EcouteurJTB eJTB = new EcouteurJTB(this);
		bClass.setActionCommand("class");  
		bClass.addActionListener(eJTB);
		bColler.setActionCommand("coller");  
		bColler.addActionListener(eJTB);
		coller.addActionListener(eJTB);          //****************
		bNouveau.setActionCommand("nouveau");
		bNouveau.addActionListener(ef);
		bOuvrir.setActionCommand("ouvrir");
		bOuvrir.addActionListener(ef);
		bSauver.setActionCommand("enregistrer");
		bSauver.addActionListener(ef);
		
		JTB.add(bNouveau);
		JTB.add(bOuvrir);
		JTB.add(bSauver);
		JTB.add(bAnnuler);
		JTB.add(bRefaire);
		JTB.add(bCouper);
		JTB.add(bCopier);
		JTB.add(bColler);
		JTB.add(bClass);
		
		
		
		//ajouts icon
		bNouveau.setIcon(new ImageIcon("image/new.gif")); 
		bOuvrir.setIcon(new ImageIcon("image/open.gif"));
		bSauver.setIcon(new ImageIcon("image/save.gif"));
		bAnnuler.setIcon(new ImageIcon("image/undo.gif"));
		bRefaire.setIcon(new ImageIcon("image/redo.gif"));
		bCouper.setIcon(new ImageIcon("image/cut.gif"));
		bCopier.setIcon(new ImageIcon("image/copy.png"));
		bColler.setIcon(new ImageIcon("image/paste.png"));
		bClass.setIcon(new ImageIcon("image/class.png"));
		JTB.setLayout(new GridLayout());
		setJMenuBar(MBar);
		add(JTB,BorderLayout.NORTH);
		//ajouts panel au bas
		pack();
		
		
		
		
		
		
		
		
		

		
		
		
	}
	
	public void createClass(){
		ClasseUML classeUML = new ClasseUML("new class");
		classe.add(classeUML);
		ViewClasse vc = new ViewClasse(m, man,this,classeUML);
		panel.add(vc);
		
		vc.setLocation(wLocation,hLocation);
		wLocation+=vc.getSize().width+20;
		if (wLocation>this.h){
			hLocation+=vc.getHeight()+20;
			wLocation=0;
		}
//		int i = 0;
//			if (w-pLocation.get(i).getX()<vc.getWidth())
//			if(w<this.h-vc.getSize().width)
//				if ((vc.getLocation().x-pLocation.get(i).x)<vc.getSize().width){
//					vc.setLocation(vc.getLocation().x+vc.getSize().width+20,vc.getLocation().y);
//					w+=vc.getSize().width+20;
//				}
//			else {
//				w=0;
//				if ((vc.getLocation().x-pLocation.get(i).x)<vc.getSize().width){
//					vc.setLocation(vc.getLocation().x+vc.getSize().width+20,vc.getLocation().y+vc.getSize().height);
//			}
//		}
	
		panel.repaint();
		canNew=false;
		this.pack();
	}
	
	public void delClass(ViewClasse viewClasse){
		panel.remove(viewClasse);
		panel.repaint();
		
		
	}
	
	public void cClass(){
		if(canColler){
			classe.add(this.viewClassecopied.classeUML);
			panel.add(this.viewClassecopied);
			panel.repaint();
			this.pack();
		}
	}
	
	public void reBuild(){
		panel.removeAll();
		ViewClasse vc2 = null;
		ViewClasse vc;
		Integer w = 0;
		Integer h = 0;
		for(int i=0;i<classe.size();i++){
			
			vc =new ViewClasse(null,null,this,this.classe.get(i));
			if(vc2!=null)
				if(w<this.h-vc.getSize().width){
					vc.setLocation(vc2.getLocation().x+vc.getSize().width+20,vc2.getLocation().y);
					w+=vc.getSize().width+20;
				}
				else{
					w=0;
					vc.setLocation(0,vc2.getLocation().y+vc.getSize().height+20);
				}
					
			panel.add(vc);
			vc.classeUML=this.classe.get(i);
			panel.repaint();
			this.pack();
			canNew=false;
			vc2=vc;
		}
			
			
		
		
	}
	public void setLoc(ResourceBundle resb){
		menu2.setText(resb.getString("edit"));
		menu1.setText(resb.getString("file"));
		menu3.setText(resb.getString("diag"));
		menu4.setText(resb.getString("code"));
		menu5.setText(resb.getString("conf"));
		menu6.setText(resb.getString("help"));
		nouveau.setText(resb.getString("new")); 
		ouvrir.setText(resb.getString("open")); 
		enregistrer.setText(resb.getString("save")); 
		enregistrerS.setText(resb.getString("save_as")); 
		generation.setText(resb.getString("gene")); 
		quitter.setText(resb.getString("exit")); 
		annuler.setText(resb.getString("undo"));
		refaire.setText(resb.getString("redo"));
		couper.setText(resb.getString("cut"));
		copier.setText(resb.getString("copy"));
		coller.setText(resb.getString("paste"));
		
		
	}
	
	
	public static void main(String[] args) {
		new VueMVC();
		
	}
}