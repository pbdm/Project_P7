package ViewULM;

import javax.swing.undo.AbstractUndoableEdit;

public class UndoableModification extends AbstractUndoableEdit {
    private int addedValue;
    private ModelUndo model;
    public UndoableModification(ModelUndo m,int v) {
	addedValue = v;
	model = m;
    }
    public String getPresentationName() {
	return ""+addedValue;
    }
    public boolean canRedo() {
	return true;
    }
    public boolean canUndo() {
	return true;
    }
    public void undo() {
	model.add(-addedValue);
    }
    public void redo() {
	model.add(addedValue);
    }
}