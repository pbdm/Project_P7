package ControlerUML;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JTextField;

import javax.swing.undo.UndoManager;

import ViewULM.*;


public class EcouteurTest implements ActionListener {

	 private ModelUndo m;
	 private UndoManager man;
	    public EcouteurTest(ModelUndo m,ManagerUndo man) {
		this.m = m;
		this.man = man;
	    }
	    public void actionPerformed(ActionEvent e) {
	    JTextField t = (JTextField)e.getSource();
		String s = t.getText();
		
		    int v = Integer.parseInt(s);
		    man.addEdit(new UndoableModification(m,1));
		    m.add(v);
		
		t.setText("0");
	    }

}
