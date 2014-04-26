package ControlerUML;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import ViewULM.ManagerUndo;
import ViewULM.ModelUndo;

import ViewULM.VueMVC;


public class EcouteurJTB implements ActionListener {
	VueMVC vueMVC;
	ManagerUndo man;
	ModelUndo m;
	public EcouteurJTB(VueMVC vueMVC) { 
		super(); 
		this.vueMVC = vueMVC; 
		this.man = vueMVC.man;
		this.m = vueMVC.m;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand()=="class"){
			vueMVC.createClass();
		}
		if(e.getActionCommand()=="coller"){
			vueMVC.cClass();
								
		}
	}

}
