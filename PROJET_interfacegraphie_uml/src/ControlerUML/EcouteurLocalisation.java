package ControlerUML;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

import ViewULM.VueMVC;

public class EcouteurLocalisation implements ActionListener{
	VueMVC vueMVC;
	public EcouteurLocalisation(VueMVC vm){
		this.vueMVC=vm;
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand()=="French"){
			vueMVC.setLoc(ResourceBundle.getBundle("myres",new Locale("fr")));
		}
		if (e.getActionCommand()=="Chinese"){
			vueMVC.setLoc(ResourceBundle.getBundle("myres",new Locale("zh_CN")));
		}
		if (e.getActionCommand()=="English"){
			vueMVC.setLoc(ResourceBundle.getBundle("myres",new Locale("en")));
		}
	}

}
