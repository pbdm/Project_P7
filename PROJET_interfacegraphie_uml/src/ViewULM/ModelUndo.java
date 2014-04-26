package ViewULM;

import java.util.Observable;

public class ModelUndo extends Observable{
	private int value;
    public ModelUndo() {
	value = 0;
    }
    public void add(int v) {
	value += v;
	setChanged();
	notifyObservers(value);
    }
    public int getValue() {
	return value;
    }
}
