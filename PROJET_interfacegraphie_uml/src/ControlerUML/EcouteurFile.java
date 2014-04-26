package ControlerUML;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import ModeleUML.Output;
import ModeleUML.XML;

import ViewULM.VueMVC;

public class EcouteurFile implements ActionListener {
	class FiltreJava extends FileFilter //generation
	{
	    public boolean accept(File f) {
	    	return true;
	    }
	    public String getDescription() {
	    	return ".java";
	    }	
	}
	class FiltreXMLO extends FileFilter //ouvirer file xml
	{
	    public boolean accept(File f) {
			String c = f.getName();
			return f.isDirectory() || c.endsWith(".xml") ;
	    	
	    }
	    public String getDescription() {
	    	return ".xml";
	    }	
	}
	
	class FiltreImage extends FileFilter //IMAGE
	{
	    public boolean accept(File f) {
			return true;
	    	
	    }
	    public String getDescription() {
	    	return ".jpg";
	    }	
	}
	
	class FiltreXMLEn extends FileFilter //save as file xml
	{
	    public boolean accept(File f) {
			return true;
	    	
	    }
	    public String getDescription() {
	    	return ".xml";
	    }	
	}
	VueMVC vc;
    public EcouteurFile(VueMVC vueMVC) {
    	vc=vueMVC;
    	System.out.println(vc.saveName);
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("nouveau")){    
			if(vc.canNew==false){
				int i = JOptionPane.showConfirmDialog(null, "vous n'avez pas enregister!!,continue?");
				if(i == JOptionPane.NO_OPTION) {
					JOptionPane.showMessageDialog(null, "on continue pas");
				}
				else if( i == JOptionPane.CANCEL_OPTION) {
					System.out.println("on annule");
				}
				else {
					new VueMVC();
					vc.setVisible(false);
					}
				}
			else{
				new VueMVC();
				vc.setVisible(false);
			}	
		}	
			
		
		String endFile = null;
		int resultat = 0;
		JFileChooser fc = new JFileChooser();
		fc.setMultiSelectionEnabled(false);
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		if (e.getActionCommand().equals("generation")){
			
			fc.setFileFilter(new FiltreJava());
			resultat = fc.showSaveDialog(vc);
			
			endFile=".java";
			if (resultat == JFileChooser.APPROVE_OPTION) {
				Output op = new Output(vc.classe,vc.association,fc.getSelectedFile().toString()+endFile);
				op.writeFile();
			}
		}
		
		
		if (e.getActionCommand().equals("ouvrir")){   
			if(vc.canNew==false){
				int i = JOptionPane.showConfirmDialog(null, "vous n'avez pas enregister!!,continue?");
				if(i == JOptionPane.NO_OPTION) {
					JOptionPane.showMessageDialog(null, "on continue pas");
				}
				else if( i == JOptionPane.CANCEL_OPTION) {
					System.out.println("on annule");
				}
				else {
					fc.setFileFilter(new FiltreXMLO());
					resultat = fc.showOpenDialog(vc);
					
					
					if (resultat == JFileChooser.APPROVE_OPTION) {
						XML xml = new XML(fc.getSelectedFile().toString());
						xml.parserXml();
						vc.classe=xml.classe;
						vc.association=xml.association;
						vc.reBuild();
					}
				}
			}
			else{
				fc.setFileFilter(new FiltreXMLO());
				resultat = fc.showOpenDialog(vc);
				
				
				if (resultat == JFileChooser.APPROVE_OPTION) {
					XML xml = new XML(fc.getSelectedFile().toString());
					xml.parserXml();
					vc.classe=xml.classe;
					vc.association=xml.association;
					vc.reBuild();
			}
				
			}	
		}
		if (e.getActionCommand().equals("enregistrer")){   
			if((vc.saveName).equals("null")){
				fc.setFileFilter(new FiltreXMLEn());
				resultat = fc.showSaveDialog(vc);
				endFile=".xml";
				if (resultat == JFileChooser.APPROVE_OPTION) {
					XML xml = new XML(fc.getSelectedFile().toString()+endFile);
					xml.BuildXMLDoc(vc.classe,vc.association);
					vc.saveName=fc.getSelectedFile().toString()+endFile;
				}
				
			}
			else{
				XML xml = new XML(vc.saveName);
				xml.BuildXMLDoc(vc.classe,vc.association);
			}
			vc.canNew=true;
		}
		if (e.getActionCommand().equals("enregistrerS")){ 
			fc.setFileFilter(new FiltreXMLEn());
			resultat = fc.showSaveDialog(vc);
			endFile=".xml";
			if (resultat == JFileChooser.APPROVE_OPTION) {
				XML xml = new XML(fc.getSelectedFile().toString()+endFile);
				xml.BuildXMLDoc(vc.classe,vc.association);
			}
			
		}
		if (e.getActionCommand().equals("eImage")){ 
			try {
				Graphics gi;
				BufferedImage bi = new BufferedImage(vc.panel.getWidth(),vc.panel.getHeight(), BufferedImage.TYPE_INT_RGB);
				gi=bi.getGraphics();
				vc.panel.print(gi);
				fc.setFileFilter(new FiltreImage());
				resultat = fc.showSaveDialog(vc);
				endFile=".jpeg";
				if (resultat == JFileChooser.APPROVE_OPTION) {
					ImageIO.write(bi, "jpeg", new File(fc.getSelectedFile().toString()+endFile));
				}
				
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} 
		}
		
	}

}
