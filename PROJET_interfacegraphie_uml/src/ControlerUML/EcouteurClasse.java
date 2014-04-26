package ControlerUML;

import java.awt.Point;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;



import ViewULM.*;

public class EcouteurClasse implements MouseMotionListener,MouseListener{
	ViewClasse jp;
	int xe,ye,xi,yi,xc,yc;
	public EcouteurClasse(ViewClasse jp) { 
		super(); 
		this.jp = jp; 
		
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		xe = e.getX(); // get a pos 
        ye = e.getY(); 
        xc = xc + xe - xi; 
        yc = yc + ye - yi;  
		jp.setLocation(xc,yc);
		//jp.repaint(); 
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getButton()==3){
			MenuPopUpClasse mp = new MenuPopUpClasse(jp);
			mp.show(e.getComponent(),e.getX(), e.getY());
			
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		Point p = jp.getLocation();
        xc = p.x;
        yc = p.y;
        xi = e.getX();
        yi = e.getY();
        jp.addMouseMotionListener(this);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		xe = e.getX(); // get a pos
        ye = e.getY();
        xc = xc + xe - xi;
        yc = yc + ye - yi;
       // jp.setLocation(xc,yc);
        jp.repaint(); 
        jp.removeMouseMotionListener(this);
	}


}
