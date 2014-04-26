package ViewULM;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.undo.UndoManager;
import javax.swing.undo.UndoableEdit;

public class ManagerUndo extends UndoManager{
	 private JButton annuler, retablir;
	    public ManagerUndo(JButton a,JButton r) {
		annuler = a;
		retablir = r;
		annuler.setEnabled(false);
		retablir.setEnabled(false);
		annuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    ManagerUndo.this.undo();
			}
		    });
		retablir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    ManagerUndo.this.redo();
			}
		    });
	    }
	    public boolean addEdit(UndoableEdit e) {
		boolean b = super.addEdit(e);
		updateItems();
		return b;
	    }
	    public void redo() {
		super.redo();
		updateItems();
	    }
	    public void undo() {
		super.undo();
		updateItems();
	    }
	    private void updateItems() {
		retablir.setEnabled(canRedo());
//		if (!canRedo()) {
//		    retablir.setText("Retablir");
//		} else {
//		    retablir.setText(editToBeRedone().getRedoPresentationName());
//		}
		annuler.setEnabled(canUndo());
//		if (!canUndo()) {
//		    annuler.setText("Annuler");
//		} else {
//		    annuler.setText(editToBeUndone().getUndoPresentationName());
//		}
	    }
}
