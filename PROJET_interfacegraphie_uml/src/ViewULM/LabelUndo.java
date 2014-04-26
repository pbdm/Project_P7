package ViewULM;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

class LabelUndo extends JLabel implements Observer {
    public LabelUndo() {
	super("0",SwingConstants.RIGHT);
    }
    public void update(Observable o,Object v) {
	setText(v.toString());
    }
}